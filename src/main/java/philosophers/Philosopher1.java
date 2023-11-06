package philosophers;

import universal.AbstractPhilosopher;
import universal.Fork;

public class Philosopher1 extends AbstractPhilosopher {

    public Philosopher1(Fork[] forks, int number, int maxIters){
        super(forks,number, maxIters);
    }


    public void run(){
        for(int i=0;i<maxIters;i++){
            synchronized (forks[number]){
                System.out.println("Filozof "+number+" bierze lewy widelec "+number);
                synchronized (forks[(number+1)%forks.length]){
                    System.out.println("Filozof "+number+" bierze prawy widelec "+(number+1)%forks.length);
                }
            }
            System.out.println("Filozof "+number+" myśli");
        }
    }
}
