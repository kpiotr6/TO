package philosophers;

import universal.AbstractPhilosopherSemaphore;
import universal.Fork;

public class Philosopher5 extends AbstractPhilosopherSemaphore {
    public Philosopher5(Fork[] forks, int number, int maxIters){
        super(forks,number, maxIters);
    }


    public void run(){
        for(int i=0;i<maxIters;i++) {
            Fork first = forks[number];
            Fork second = forks[(number+1)%forks.length];
            this.startMeasure();
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (first){
                System.out.println("Filozof "+number+" bierze lewy widelec "+number);
                synchronized (second){
                    this.endMeasure();
                    System.out.println("Filozof "+number+" bierze prawy widelec "+(number+1)% forks.length);
                }
            }
            semaphore.release();
            System.out.println("Filozof "+number+" myśli");
        }
    }
}
