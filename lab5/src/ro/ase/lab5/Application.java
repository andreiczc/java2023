package ro.ase.lab5;

import java.util.ArrayList;

public class Application {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Running on thread " + Thread.currentThread().threadId());
            }
        });

        thread.start();

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 10000; ++i) {
                System.out.println(i);
            }
        });

        thread2.start();

        thread.join();
        thread2.join();

        // 1 ... 10000000
        var array = new ArrayList<Integer>();
        for (var i = 0; i < 1000; ++i) {
            array.add(i);
        }

        // thread 1 -> 0....size/2
        // thread 2 -> size/2 ... size

        MyThread threadSum1 = new MyThread(array, 0, array.size() / 2);
        MyThread threadSum2 = new MyThread(array, array.size() / 2, array.size());

        threadSum1.start();
        threadSum2.start();

        threadSum1.join();
        threadSum2.join();

        long finalSum = 0;
        finalSum += threadSum1.getSum();
        finalSum += threadSum2.getSum();

        System.out.println("Final sum is " + finalSum);
        System.out.println(array
                .stream()
                .mapToInt(value -> value)
                .sum());
    }
}
