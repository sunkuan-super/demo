package com.sk.util.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author: create by sunkuan
 * @Description: 重现SimpleDateFormat类的线程安全问题
 * @date: 2022/7/10 - 9:54
 */

/**
 *
 * 1、SimpleDateFormat 类不是线程安全的根本原因是：DateFormat 类中的 Calendar 对象被多线程共享，而 Calendar 对象本身不支持线程安全
 *
 *
 * 2、SimpleDateFormat 类为何不是线程安全的
 * 那么，接下来，我们就一起来看看真正引起 SimpleDateFormat 类线程不安全的根本原因。
 * 通过查看 SimpleDateFormat 类的源码，我们得知：SimpleDateFormat 是继承自 DateFormat类，DateFormat 类中维护了一个全局的 Calendar 变量，如下所示：
 *
 * protected Calendar calendar;
 * 从注释可以看出，这个 Calendar 对象既用于格式化也用于解析日期时间。接下来，我们再查看parse() 方法接近最后的部分。
 *
 * 可见，最后的返回值是通过调用 CalendarBuilder.establish() 方法获得的，而这个方法的参数正好就是前面的 Calendar 对象。
 * 接下来，我们再来看看 CalendarBuilder.establish() 方法，如下所示
 *
 * 在 CalendarBuilder.establish() 方法中先后调用了 cal.clear() 与 cal.set()，也就是先清除 cal 对象中设置的值，再重新设置新的值。
 * 由于Calendar内部并没有线程安全机制，并且这两个操作也都不是原子性的，所以当多个线程同时操作一个 SimpleDateFormat 时就会引起 cal 的值混乱。类似地， format() 方法也存在同样的问题。
 *
 * 3、解决办法
 * 3.1 SimpleDateFormat对象不共享，谁用到自己创建。这种方式在高并发下会创建大量的SimpleDateFormat类对象影响程序的性能，所以，这种方式在实际生产环境不太被推荐。
 * 3.2 synchronized锁方式  虽然这种方式能够解决 SimpleDateFormat 类的线程安全问题，但是由于在程序的执行过程中，为 SimpleDateFormat 类对象加上了 synchronized 锁，导致同一时刻只能有一个线程执行 parse(String) 方法。此时，会影响程序的执行性能，在要求高并发的生产环境下，此种方式也是不太推荐使用的
 * 3.3 Lock锁方式  Lock锁方式与synchronized锁方式实现原理相同，都是在高并发下通过 JVM 的锁机制来保证程序的线程安全。此种方式同样会影响高并发场景下的性能，不太建议在高并发的生产环境使用。代码实现方式与 synchronized 类似，此处不再列出
 * 3.4 ThreadLocal方式   使用ThreadLocal存储每个线程拥有的 SimpleDateFormat 对象的副本，能够有效的避免多线程造成的线程安全问题
 * 3.5 DateTimeFormatter方式
 */

public class SimpleDateFormatDemo {

    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 执行总次数
     */
    private static final int EXECUTE_COUNT = 1000;

    /**
     * 同时运行的线程数量
     */
    private static final int THREAD_COUNT = 20;

    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(THREAD_COUNT);
        CountDownLatch latch = new CountDownLatch(EXECUTE_COUNT);

        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < EXECUTE_COUNT; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    SDF.parse("2022-07-10");
                } catch (InterruptedException e) {
                    System.out.println("获取信号量出错");
                    e.printStackTrace();
                    System.exit(1);
                } catch (ParseException e) {
                    System.out.println("ParseException异常--线程：" + Thread.currentThread().getName() + " 格式化日期失败");
                    e.printStackTrace();
                    System.exit(1);
                } catch (NumberFormatException e) {
                    System.out.println("NumberFormatException异常---线程：" + Thread.currentThread().getName() + " 格式化日期失败");
                    e.printStackTrace();
                    System.exit(1);
                }

                semaphore.release();
                latch.countDown();
            });


        }

        latch.await();
        executorService.shutdown();
        System.out.println("所有线程格式化日期成功");
    }
}
