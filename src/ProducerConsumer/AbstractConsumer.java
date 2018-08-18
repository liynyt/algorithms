package ProducerConsumer;

abstract class AbstractConsumer implements Consumer, Runnable {
	@Override
	public void run() {
		while(true) {
			try {
				consume();
			}
			catch (Exception e) {
				e.printStackTrace();
				break;
			}
		}
		
	}
}
