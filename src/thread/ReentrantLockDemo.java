package thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author wangtengfei
 * @Description TODO
 * @Date 2022/2/28 10:22
 * @Param 可重入锁（也叫做递归锁）
 * <p>
 * 指的是同一线程外层函数获得锁之后，内层递归函数仍然能获取该锁的代码，在同一个线程在外层方法获取锁的时候，在进入内层方法会自动获取锁。
 * <p>
 * 也即是说，线程可以进入任何一个它已经拥有的锁所同步着的代码块。
 * <p>
 * ReentrantLock/synchronized就是一个典型的可重入锁。
 * <p>
 * 可重入锁最大的作用是避免死锁。
 * @return
 **/
public class ReentrantLockDemo {
    public static void main(String[] args) {
        Phone phone = new Phone();
        syncTest(phone);

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println();
        Thread t3 = new Thread(phone);
        Thread t4 = new Thread(phone);
        t3.start();
        t4.start();

    }


    private static void syncTest(Phone phone) {

        new Thread(() -> {
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "t1").start();


        new Thread(() -> {
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "t2").start();
    }

}

class Phone implements Runnable {
    //Synchronized TEST 一个典型的可重入锁

    public synchronized void sendSMS() {
        System.out.println(Thread.currentThread().getId() + "\t" + "sendSMS()");
        sendEmail();
    }

    public synchronized void sendEmail() {
        System.out.println(Thread.currentThread().getId() + "\t" + "sendEmail()");
    }

    //Reentrant TEST 一个典型的可重入锁

    Lock lock = new ReentrantLock();

    @Override
    public void run() {
        get();
    }

    // 加锁几次释放几次要配对
    public void get() {
        //lock.lock();
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getId() + "\t" + "get()");
            set();
        } finally {
            //lock.unlock();
            lock.unlock();
        }
    }

    public void set() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getId() + "\t" + "set()");
        } finally {
            lock.unlock();
        }
    }

}
