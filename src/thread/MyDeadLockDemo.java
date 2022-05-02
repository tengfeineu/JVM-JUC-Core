package thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wangtf@citic.com
 * @version 1.0.0
 * @ClassName MyDeadLockDemo.java
 * @Description TODO
 * @createTime 2022年04月27日 09:02:00
 */
public class MyDeadLockDemo {

    public static void main(String[] args) {

        String lockA = "lockA";
        String lockB = "lockB";
        new Thread(new DeadLockResource(lockA, lockB), "ThreadA").start();
        new Thread(new DeadLockResource(lockB, lockA), "ThreadB").start();
    }

    static class DeadLockResource implements Runnable {

        private String lockA;
        private String lockB;

        public DeadLockResource(String lockA, String lockB) {
            this.lockA = lockA;
            this.lockB = lockB;
        }

        @Override
        public void run() {

            synchronized (lockA) {
                System.out.println("线程" + Thread.currentThread().getName() + "占用lockA资源");

                synchronized (lockB) {
                    System.out.println("线程" + Thread.currentThread().getName() + "占用lockB资源");
                }
            }
        }
    }
}
