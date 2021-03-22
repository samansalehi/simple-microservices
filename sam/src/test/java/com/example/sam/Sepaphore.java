package com.example.sam;

import org.junit.jupiter.api.Test;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class Sepaphore {
    public static void main(String[] args) throws InterruptedException {
//        semaphore();
//        atomicTest();
        concurrentMap();
    }

    private static void semaphore() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Semaphore semaphore = new Semaphore(5);
        Runnable runnable= ()->{
            boolean permit =false;
            try{
                permit = semaphore.tryAcquire(1, TimeUnit.SECONDS);
                if (permit)
                {
                    System.out.println("acquire semaphore");
                    TimeUnit.SECONDS.sleep(5);
                }else
                {
                    System.out.println("Could not acquire semaphore");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                if (permit)
                {
                    semaphore.release();;
                }
            }
        };
        IntStream.range(0,10).forEach(i->executorService.submit(runnable));
        executorService.shutdown();
    }


    public static void atomicTest() throws InterruptedException {
        AtomicInteger atomicInteger =new AtomicInteger(0);
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        IntStream.range(0,1000).forEach(i->executorService.submit(atomicInteger::incrementAndGet));
        executorService.shutdown();
        executorService.awaitTermination(5,TimeUnit.SECONDS );
        System.out.println(atomicInteger.get());
    }

    public static void concurrentMap()
    {
        ConcurrentMap<String,String> concurrentMap = new ConcurrentHashMap<>();
        concurrentMap.put("Saman","salehi");
        concurrentMap.put("somayeh","behbahani");
        concurrentMap.put("salar","salehi");

        concurrentMap.forEach((key,value)-> System.out.printf("%s = %s\n",key,value));
        concurrentMap.merge("Saman","Saber",(oldval,newval)->oldval + " was "+newval);
        System.out.println(concurrentMap.get("Saman"));
        System.out.println(ForkJoinPool.getCommonPoolParallelism());
     }
    }
