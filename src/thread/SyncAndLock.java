package thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wangtf@citic.com
 * @version 1.0.0
 * @ClassName SyncAndLock.java
 * @Description TODO
 * @createTime 2022年04月26日 08:56:00
 */
public class SyncAndLock {

    public static void main(String[] args) {
        synchronized (new Object()){

        }

        new ReentrantLock();
    }
}


