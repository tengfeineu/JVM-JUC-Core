package thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 独占锁：指该锁一次只能被一个线程所持有。对ReentrantLock和Synchronized而言都是独占锁
 * <p>
 * 共享锁：指该锁可被多个线程所持有。
 * <p>
 * 多个线程同时读一个资源类没有任何问题，所以为了满足并发量，读取共享资源应该可以同时进行。但是，如果有一个线程想去写共享资源来，就不应该再有其它线程可以对该资源进行读或写。
 * <p>
 * 对ReentrantReadWriteLock其读锁是共享锁，其写锁是独占锁。
 * <p>
 * 读锁的共享锁可保证并发读是非常高效的，读写，写读，写写的过程是互斥的。
 * 写操作：原子+独占，整个过程必须是一个完整的统一体，中间不许分割和打断
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        //MyDefaultCache cache = new MyDefaultCache();
        MyCache cache = new MyCache();
        //写
        for (int i = 1; i <= 5; i++) {
            final int tempInt = i;
            new Thread(() -> {
                cache.put(tempInt + "", tempInt + "");
            }, String.valueOf(i)).start();
        }
        //读
        for (int i = 1; i <= 5; i++) {
            final int tempInt = i;
            new Thread(() -> {
                cache.get(tempInt + "");
            }, String.valueOf(i)).start();
        }
    }
}

class MyCache {
    //缓存更新快，需要用volatile修饰
    private volatile Map<String, Object> map = new HashMap<>();
    private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

    public void put(String key, Object value) {

        rwLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t" + "正在写入： " + key);
            //模拟网络传输
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
            }
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "\t" + "写入完成");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    public void get(String key) {
        rwLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t" + "正在读取： " + key);
            //模拟网络传输
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Object result = map.get(key);
            System.out.println(Thread.currentThread().getName() + "\t" + "读取完成： " + result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rwLock.readLock().unlock();
        }
    }
}

class MyDefaultCache {
    //缓存更新快，需要用volatile修饰
    private Map<String, Object> map = new HashMap<>();

    public void put(String key, Object value) {
        System.out.println(Thread.currentThread().getName() + "\t" + "正在写入： " + key);
        //模拟网络传输
        try {
            TimeUnit.MILLISECONDS.sleep(300);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        map.put(key, value);
        System.out.println(Thread.currentThread().getName() + "\t" + "写入完成");
    }

    public void get(String key) {
        System.out.println(Thread.currentThread().getName() + "\t" + "正在读取： " + key);
        //模拟网络传输
        try {
            TimeUnit.MILLISECONDS.sleep(300);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Object result = map.get(key);
        System.out.println(Thread.currentThread().getName() + "\t" + "读取完成： " + result);
    }
}