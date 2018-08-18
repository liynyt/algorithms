package ProducerConsumer;

abstract class AbstractProducer implements Producer, Runnable{
	public void run() {
		while(true) {
			try {
				produce();
			} catch (Exception e) {
				e.printStackTrace();
				break;
			}
		}
	}
}
