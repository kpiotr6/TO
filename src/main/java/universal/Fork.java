package universal;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Fork {
    public Lock lock = new ReentrantLock(true);
}
