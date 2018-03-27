public class TwoThreads{
	public 	static void main (String[] args) {
	Runnable obj = new ThreadAB("A","B");
	Runnable obj2 = new ThreadAB("1","2");
	Thread t1 = new Thread(obj);
	Thread t2 = new Thread(obj2);
	t1.start();
	t2.start();
	}
	
}
class Base{};
class ThreadAB  implements Runnable {
	public String t1;
	public String t2;
	public ThreadAB (String t1, String t2) {
		this.t1 = t1;
		this.t2 = t2;
	}
	public void  run() {
		System.out.printf("%s%s",t1,t2);
	}
	
}