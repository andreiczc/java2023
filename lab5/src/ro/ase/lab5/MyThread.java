package ro.ase.lab5;

import java.util.List;

/*
sums up elements from `elements`;
has a start and stop Idx
is able to retrieve a sum
 */
public class MyThread extends Thread {

    private final List<Integer> elements;
    private int startIdx;
    private int stopIdx;
    private long sum;

    public MyThread(List<Integer> elements, int startIdx, int stopIdx) {
        this.elements = elements;
        this.startIdx = startIdx;
        this.stopIdx = stopIdx;
    }

    public long getSum() {
        return sum;
    }

    @Override
    public void run() {
        System.out.println("Current thread " + threadId());

        for(var i = startIdx; i < stopIdx; ++i) {
            sum += elements.get(i);
        }
    }
}
