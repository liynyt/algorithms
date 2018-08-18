package ProducerConsumer;

public interface Model{
	Runnable newRunableConsumer();
	Runnable newRunableProducer();
}