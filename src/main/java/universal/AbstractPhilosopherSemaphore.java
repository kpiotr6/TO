package universal;

import java.util.concurrent.Semaphore;

public abstract class AbstractPhilosopherSemaphore extends  AbstractPhilosopher{
    protected Semaphore semaphore;
    public AbstractPhilosopherSemaphore(Fork[] forks, int number, int maxIters) {
        super(forks, number, maxIters);
    }
    public void setSemaphore(Semaphore semaphore){
        this.semaphore = semaphore;
    }
}
