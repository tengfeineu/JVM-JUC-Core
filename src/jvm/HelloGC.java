package jvm;

/**
 * @author wangtf@citic.com
 * @version 1.0.0
 * @ClassName HelloGC.java
 * @Description TODO
 * @createTime 2022年04月27日 19:50:00
 */
public class HelloGC {
    public static void main(String[] args) throws InterruptedException {
        /*System.out.println("**HelloGC**");

        Thread.sleep(Integer.MAX_VALUE);*/

        // 返回Java虚拟机中内存的总量
       /* long totalMemory = Runtime.getRuntime().totalMemory();
        int processors = Runtime.getRuntime().availableProcessors();
        System.out.println(processors);

        // 返回Java虚拟机中试图使用的最大内存量
        long maxMemory = Runtime.getRuntime().maxMemory();

        System.out.println("TOTAL_MEMORY(-Xms) = " + totalMemory + "(字节)、" + (totalMemory / (double) 1024 / 1024) + "MB");
        System.out.println("MAX_MEMORY(-Xmx) = " + maxMemory + "(字节)、" + (maxMemory / (double) 1024 / 1024) + "MB");*/

        System.out.println("**HelloGC**");
        //byte[] bytes = new byte[50 * 1024 * 1024];
    }
}