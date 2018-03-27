import java.util.concurrent.Semaphore;
class Q{
	int item;
	static Semaphore semCon = new Semaphore(0);
	static Semaphore semProd = new Semaphore(1);
	
	public void get() {
		try {
			semCon.acquire();
		}
		catch
			(InterruptedException e) {}
		
	System.out.println("Consumer consumed an item" + item);
	semProd.release();
	}
	public void put(int item) {
		try {
			semProd.acquire();
			
		}
		catch(InterruptedException e) {}
	this.item = item;
	System.out.println("Producer produced an item" + item);
	semCon.release();
	}
}
class Producer implements Runnable {
	Q q;
	Producer (Q q){
		this.q = q;
		new Thread(this,"Producer").start();
	}
	public void run() {
		for(int i = 0 ; i < 5; i++) {
			q.put(i);
		}
	}
}

class Consumer implements Runnable{
	Q q;
	Consumer(Q q){
		this.q = q;
		new Thread(this,"Consumer").start();
	}
	public void run() {
		for(int i = 0 ; i < 5; i++) {
			q.get();
		}
	}
}




public class ProducerConsumer {

	public static void main(String[] args) {
	Q q = new Q();
	new Consumer(q);
	new Producer(q);
	
	}
}
