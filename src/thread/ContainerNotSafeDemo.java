package thread;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class ContainerNotSafeDemo {
    public static void main(String[] args) {
        //listNotSafe();
        //setNoSafe();
        mapNotSafe();
    }

    private static void mapNotSafe() {
        //Map<String,String> map=new HashMap<>();
        Map<String, String> map = new ConcurrentHashMap<>();
        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 8));
                System.out.println(Thread.currentThread().getName() + "\t" + map);
            }, String.valueOf(i)).start();
        }
    }

    private static void setNoSafe() {
        //Set<String> set=new HashSet<>();
        Set<String> set = new CopyOnWriteArraySet<>();
        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(Thread.currentThread().getName() + "\t" + set);
            }, String.valueOf(i)).start();
        }
        new HashSet<>().add("a");
        /**
         *  private static final Object PRESENT = new Object();
         *  关注key value是个PRESENT常量对象
         *  public boolean add(E e) {
         *     return map.put(e, PRESENT)==null;
         *  }
         **/
    }

    private static void listNotSafe() {

        new ArrayList<>().add(1);

        //List<String> list=new ArrayList<>();
        //List<String> list=new Vector<>();
        //Collection;
        //List<String> list = Collections.synchronizedList(new ArrayList<>());
        /**
         *  不要只会用
         *  1.故障现象
         *  java.util.ConcurrentModificationException
         *
         *  2.导致原因
         *  并发争抢导致，参考花名册问题
         *  一个人正在写，另外一个人过来抢夺，导致不一致异常。并发修改异常
         *
         *
         *  3.解决方案
         *      3.1 List<String> list=new Vector<>();
         *      3.2 List<String> list = Collections.synchronizedList(new ArrayList<>());
         *      3.3 List<String> list = new CopyOnWriteArrayList<>()
         *
         *      CopyOnWrite容器即写时复制的容器。待一个容器添加元素的时候，不直接往当前容器Object[]添加，而是先将
         *      当前容器Object[]进行copy，复制出一个新的容器Object[] newELements，然后新的容器Object[ ] newELements里添加元素，
         *      添加完元素之后，再将原容器的引用指向新的容器setArray (newELements)。
         *      这样做的好处是可以对CopyOnWrite容器进行并发的读，而不需要加锁（区别于Vector和Collections.synchronizedList()），
         *      因为当前容器不会添加任何元素。所以CopyOnWrite容器也是一种读写分离的思想，读和写不同的容器。
         **/
        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(Thread.currentThread().getName() + "\t" + list);
            }, String.valueOf(i)).start();
        }
    }
}
