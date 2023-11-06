import philosophers.*;
import universal.AbstractPhilosopher;
import universal.Fork;

class Philosopher1Test {
    public static void main(String[] args) throws InterruptedException {
        int iterations = 1000;
        int testNumber = 20;
        int[] ns = new int[]{5,10,20,50,100};
        for(int j=0;j<ns.length;j++){
            for(int t=0;t<testNumber;t++){
                AbstractPhilosopher[] philosophers = new AbstractPhilosopher[ns[j]];
                Fork[] forks = new Fork[ns[j]];
                for(int k=0;k<ns[j];k++){
                    forks[k] = new Fork();
                    philosophers[k] = new Philosopher1(forks,k,iterations);
                }
                for(int k=0;k<ns[j];k++){
                    philosophers[k].start();
                }
                for(int k=0;k<ns[j];k++){
                    philosophers[k].join();
                }
            }
        }
    }
}
