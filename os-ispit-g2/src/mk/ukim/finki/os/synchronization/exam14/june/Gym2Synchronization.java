package mk.ukim.finki.os.synchronization.exam14.june;

import java.util.Date;
import java.util.HashSet;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

import mk.ukim.finki.os.synchronization.ProblemExecution;
import mk.ukim.finki.os.synchronization.TemplateThread;

public class Gym2Synchronization {
	static Semaphore sala;
	static Semaphore soblekuvalna;
	static Semaphore halt;
	static Semaphore lock;
	static int broj;
    public static void init() {
    	sala = new Semaphore(12);
    	soblekuvalna = new Semaphore(4);
    	halt = new Semaphore(0);
    	broj = 0;
    	lock = new Semaphore(1);    
    }
   

    public static class Player extends TemplateThread {

        public Player(int numRuns) {
            super(numRuns);
        }

        @Override
        public void execute() throws InterruptedException {
        	sala.acquire();
        	state.vlezi();
        	lock.acquire();
        	broj++;
        	if(broj == 12) {
        	halt.release(12);
        	broj = 0;
        	}
        	lock.release();
        	
        	
        	halt.acquire();
            state.sportuvaj();
            soblekuvalna.acquire();
            state.presobleci();
            soblekuvalna.release();
            lock.acquire();
            broj++;
            if(broj==12) {
                state.slobodnaSala(); // samo posledniot go povikuva
                broj=0;
                sala.release(12);
            }
            lock.release();
        }
    }
    static Gym2State state = new Gym2State();

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            run();
        }
    }

    public static void run() {
        try {
            Scanner s = new Scanner(System.in);
            int numRuns = 1;
            int numIterations = 1200;
            s.close();

            HashSet<Thread> threads = new HashSet<Thread>();

            for (int i = 0; i < numIterations; i++) {
                Player h = new Player(numRuns);
                threads.add(h);
            }

            init();

            ProblemExecution.start(threads, state);
            System.out.println(new Date().getTime());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
