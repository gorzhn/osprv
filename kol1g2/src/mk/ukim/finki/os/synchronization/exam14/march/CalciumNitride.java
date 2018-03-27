package mk.ukim.finki.os.synchronization.exam14.march;

import java.util.Date;
import java.util.HashSet;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

import mk.ukim.finki.os.synchronization.ProblemExecution;
import mk.ukim.finki.os.synchronization.TemplateThread;

public class CalciumNitride {

	static Semaphore kalcium;
	static Semaphore azot;
	static int brojKalcium;
	static Semaphore nazad;
	static int brojAzot;
	static Semaphore lock;
	static Semaphore lock2;
	static Semaphore lock3;
	public static void init() {
    	kalcium = new Semaphore(3);
    	azot = new Semaphore(0);
    	brojKalcium = 0;
    	nazad = new Semaphore(0);
    	brojAzot = 0;
    	lock = new Semaphore(1);
    	lock2 = new Semaphore(1);
    	lock3 = new Semaphore(0);
    }

    public static class Calcium extends TemplateThread {

        public Calcium(int numRuns) {
            super(numRuns);
        }

        @Override
        public void execute() throws InterruptedException {
        	kalcium.acquire();
        	lock.acquire();
        	brojKalcium++;
        	
        	
        	if(brojKalcium == 3) {
        		azot.release(2);
        		brojKalcium = 0;
        	}
        	lock.release();
        	nazad.acquire();
            state.bond();
            
            lock3.acquire();
            lock.acquire();
            brojKalcium++;
            if(brojKalcium == 3) {
            state.validate();
            brojKalcium = 0;
            kalcium.release(3);
            }
            lock.release();
        }
    }

    public static class Nitrogen extends TemplateThread {

        public Nitrogen(int numRuns) {
            super(numRuns);
        }

        @Override
        public void execute() throws InterruptedException {
            azot.acquire();
            lock2.acquire();
            brojAzot++;
            
            if(brojAzot == 2) {
            	nazad.release(5);
            	brojAzot = 0;
            }
            lock2.release();
            nazad.acquire();
        	state.bond();
        	lock2.acquire();
        	brojAzot++;
        	if(brojAzot == 2) {
        		lock3.release(3);
        		brojAzot = 0;
        		//azot.release(2);
        	}
        	lock2.release();
        	
        	
            
          //  state.validate();
        }
    }
    static CalciumNitrideState state = new CalciumNitrideState();

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            run();
        }
    }

    public static void run() {
        try {
            Scanner s = new Scanner(System.in);
            int numRuns = 1;
            int numIterations = 100;
            s.close();

            HashSet<Thread> threads = new HashSet<Thread>();

            for (int i = 0; i < numIterations; i++) {
                Nitrogen n = new Nitrogen(numRuns);
                threads.add(n);
                Calcium ca = new Calcium(numRuns);
                threads.add(ca);
                ca = new Calcium(numRuns);
                threads.add(ca);
                n = new Nitrogen(numRuns);
                threads.add(n);
                ca = new Calcium(numRuns);
                threads.add(ca);
            }

            init();

            ProblemExecution.start(threads, state);
            System.out.println(new Date().getTime());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
