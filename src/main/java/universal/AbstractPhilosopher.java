package universal;

public abstract class AbstractPhilosopher extends Thread {
    final protected Fork[] forks;
    final protected int number;
    protected long iters;
    protected long sum;
    protected  int maxIters;
    private long start;
    public AbstractPhilosopher(Fork[] forks, int number, int maxIters){
        this.forks = forks;
        this.number = number;
        this.iters = 0;
        this.sum = 0;
        this.maxIters = maxIters;
    }
    public long getAverageTimeNs(){
        return sum/iters;
    }
    public int getNumber() {
        return number;
    }
    public void startMeasure() {
        this.start = System.nanoTime();
    }
    public void endMeasure() {
        this.sum += (System.nanoTime() - this.start);
        this.iters++;
    }
    public abstract void run();
}
