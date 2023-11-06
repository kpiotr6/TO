import philosophers.*;
import universal.AbstractPhilosopher;
import universal.AbstractPhilosopherSemaphore;
import universal.Fork;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, InterruptedException {

        int iterations = 1000;
        int testNumber = 30;
        int[] ns = new int[]{5,10,20,50,100};
        Class<AbstractPhilosopher>[] philosopherClasses = new Class[]
                {Philosopher2.class, Philosopher3.class, Philosopher4.class, Philosopher5.class, Philosopher6.class};
        for(int i = 0; i<philosopherClasses.length; i++) {
            Constructor<AbstractPhilosopher> c = philosopherClasses[i].getConstructor(Fork[].class,int.class,int.class);
            for(int j=0;j<ns.length;j++){
                long[][] philosophersAvg = new long[ns[j]][testNumber];
                for(int t=0;t<testNumber;t++){
                    AbstractPhilosopher[] philosophers = new AbstractPhilosopher[ns[j]];
                    Fork[] forks = new Fork[ns[j]];
                    Semaphore s = null;
                    if(i>2){
                        s = new Semaphore(ns[j]-1,true);
                    }
                    for(int k=0;k<ns[j];k++){
                        forks[k] = new Fork();
                        philosophers[k] = c.newInstance(forks,k,iterations);
                        if(i>2){
                            ((AbstractPhilosopherSemaphore) philosophers[k]).setSemaphore(s);
                        }
                    }
                    System.out.println("Test "+t+", Rozwiązanie "+(i+2));
                    for(int k=0;k<ns[j];k++){
                        philosophers[k].start();
                    }
                    for(int k=0;k<ns[j];k++){
                        philosophers[k].join();
                        philosophersAvg[k][t] = philosophers[k].getAverageTimeNs();
                    }
                }
                new Saver(philosophersAvg).writeTo("res", i+2);
            }
        }
    }
}
