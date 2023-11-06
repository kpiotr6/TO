package philosophers;

import universal.AbstractPhilosopherSemaphore;
import universal.Fork;

public class Philosopher6 extends AbstractPhilosopherSemaphore {

    public Philosopher6(Fork[] forks, int number, int maxIters){
        super(forks,number, maxIters);

    }


    public void run(){
        for(int i=0;i<maxIters;i++) {
            Fork first = forks[number];
            Fork second = forks[(number+1)%forks.length];
            String firstString = "lewy";
            String secondString = "prawy";
            this.startMeasure();
            boolean acquired = semaphore.tryAcquire();
            if (!acquired){
                Fork tmp = first;
                first = second;
                second = tmp;
                firstString = "prawy";
                secondString = "lewy";
            }
            synchronized (first){
                System.out.println("Filozof "+number+" bierze "+firstString+" widelec "+number);
                synchronized (second){
                    this.endMeasure();
                    System.out.println("Filozof "+number+" bierze "+secondString+" widelec "+(number+1)% forks.length);
                }
            }
            if(acquired){
                semaphore.release();
            }
            System.out.println("Filozof "+number+" myśli");
        }
    }
}
