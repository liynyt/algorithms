package ProducerConsumer;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

public class WaitNotifyModel implements Model{
	private final Object BUFFER_LOCK = new Object();
	private final Queue<Task> buffer = new LinkedList<>();
	private final AtomicInteger increaseTaskNo = new AtomicInteger(0);
	private final int cap;
	public WaitNotifyModel(int cap) {
		this.cap = cap;
	}
	@Override
	public Runnable newRunableConsumer() {
		return new ConsumerImpl();
	}
	@Override
	public Runnable newRunableProducer() {
		return new ProducerImpl();
	}
	private class ConsumerImpl extends AbstractConsumer implements Consumer, Runnable{
		@Override
		public void consume() throws InterruptedException {
			synchronized (BUFFER_LOCK) {
				while(buffer.size() == 0) {
					BUFFER_LOCK.wait();
				}
				Task task = buffer.poll();
				assert(task != null);
				Thread.sleep(500 + (long)(Math.random() * 500));
				System.out.println("consume " + task.num);
				BUFFER_LOCK.notifyAll();
			}
		}
	}
	private class ProducerImpl extends AbstractProducer implements Producer, Runnable{
		@Override
		public void produce() throws InterruptedException {
			synchronized (BUFFER_LOCK) {
				Thread.sleep((long)(Math.random() * 500));
				while(buffer.size() == cap) {
					BUFFER_LOCK.wait();
				}
				Task task = new Task(increaseTaskNo.getAndIncrement());
				buffer.add(task);
				System.out.println("produce " + task.num);
				BUFFER_LOCK.notifyAll();
			}
		}
		
	}
	public static void main(String[] args) {
		Model model = new WaitNotifyModel(3);
		for(int i=0; i<2; i++) {
			new Thread(model.newRunableConsumer()).start();;
		}
		for(int i=0; i< 5;i++) {
			new Thread(model.newRunableProducer()).start();
		}

	}
}
