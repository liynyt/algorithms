package ProducerConsumer;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockConditionModel implements Model{
	private final Lock BUFFER_LOCK = new ReentrantLock();
	private final Condition BUFFER_CON = BUFFER_LOCK.newCondition();
	private final Queue<Task> buffer = new LinkedList<>();
	private final AtomicInteger increaseTaskNo = new AtomicInteger(0);
	private final int cap;
	public LockConditionModel(int cap) {
		this.cap = cap;
	}
	@Override
	public Runnable newRunableConsumer() {
		// TODO Auto-generated method stub
		return new ConsumerImpl();
	}
	@Override
	public Runnable newRunableProducer() {
		// TODO Auto-generated method stub
		return new ProducerImpl();
	}

	private class ConsumerImpl extends AbstractConsumer implements Consumer, Runnable{
		@Override
		public void consume() throws InterruptedException {
			BUFFER_LOCK.lockInterruptibly();
			try {
				while(buffer.size() == 0) {
					BUFFER_CON.await();
				}
				Task task = buffer.poll();
				assert(task != null);
				Thread.sleep(500 + (long)(Math.random() * 500));
				System.out.println("consume " + task.num);
				BUFFER_CON.signalAll();;
			}finally {
				BUFFER_LOCK.unlock();
			}
			
		}
		
	}
	private class ProducerImpl extends AbstractProducer implements Producer, Runnable{
		@Override
		public void produce() throws InterruptedException {
			Thread.sleep((long)(Math.random() * 500));
			BUFFER_LOCK.lockInterruptibly();
			try {
				while(buffer.size() == cap) {
					BUFFER_CON.await();
				}
				Task task = new Task(increaseTaskNo.getAndIncrement());
				buffer.add(task);
				System.out.println("produce " + task.num);
				BUFFER_CON.signalAll();
			} finally {
				BUFFER_LOCK.unlock();
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
