package ProducerConsumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class BlockingQueueModel implements Model{

	private final BlockingQueue<Task> queue;
	private final AtomicInteger increaseTaskNo = new AtomicInteger(0);
	public BlockingQueueModel(int cap) {
		this.queue = new LinkedBlockingQueue<>(cap);
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
		public void consume() throws InterruptedException{
			Task task = queue.take();
			Thread.sleep(500 + (long)(Math.random() * 500));
			System.out.println("consume " + task.num);
		}
	}
	private class ProducerImpl extends AbstractProducer implements Producer, Runnable{
		public void produce() throws InterruptedException{
			Thread.sleep((long)(Math.random() * 500));
			Task task = new Task(increaseTaskNo.getAndIncrement());
//			Task task = new Task(queue.size());
			queue.put(task);
			System.out.println("produce " + task.num);
		}
	}
	
	public static void main(String[] arg0) {
		Model model = new BlockingQueueModel(3);
//		for(int i=0; i<2; i++) {
			new Thread(model.newRunableConsumer()).start();;
//		}
//		for(int i=0; i< 5;i++) {
			new Thread(model.newRunableProducer()).start();
//		}
	}
	
}
