package philosophers;

import universal.AbstractPhilosopher;
import universal.Fork;

public class Philosopher2 extends AbstractPhilosopher {
    public Philosopher2(Fork[] forks, int number,  int maxIters){
        super(forks,number, maxIters);
    }




    public void run(){
        for(int i=0;i<maxIters;i++) {
            Fork first = forks[number];
            Fork second = forks[(number+1)%forks.length];
            if(number%2==1){
                Fork tmp = first;
                first = second;
                second = tmp;
            }
            boolean firstLock = false;
            boolean secondLock = false;
            this.startMeasure();
            while (!secondLock || !firstLock){
                firstLock = first.lock.tryLock();
                if(firstLock){
                    secondLock = second.lock.tryLock();
                    if(!secondLock){
                        first.lock.unlock();
                    }
                }
            }
            this.endMeasure();
            System.out.println("Filozof "+number+" bierze oba widelce "+number);
            second.lock.unlock();
            first.lock.unlock();
            System.out.println("Filozof "+number+" myśli");
        }

    }
}
