package philosophers;

import universal.AbstractPhilosopher;
import universal.Fork;

public class Philosopher3 extends AbstractPhilosopher {

    public Philosopher3(Fork[] forks, int number, int maxIters){
        super(forks,number, maxIters);
    }


    public void run(){
        for(int i=0;i<maxIters;i++) {
            Fork first = forks[number];
            Fork second = forks[(number+1)% forks.length];
            if(number%2==1){
                Fork tmp = first;
                first = second;
                second = tmp;
            }
            this.startMeasure();
            synchronized (first){
                System.out.println("Filozof "+number+" bierze lewy widelec "+number);
                synchronized (second){
                    this.endMeasure();
                    System.out.println("Filozof "+number+" bierze prawy widelec "+(number+1)%forks.length);
                }
            }
            System.out.println("Filozof "+number+" myśli");

        }
    }
}
