
import java.util.*;

public class Task02 {

    static final int[] SIZES = {1000, 10000, 50000};

    static long timeAddEnd(List<Integer> list, int n) {
        long start = System.nanoTime();

        for (int i = 0; i < n; i++) {
            list.add(i);
        }

        return System.nanoTime() - start;
    }

    static long timeAddFront(List<Integer> list, int n) {
        long start = System.nanoTime();

        for (int i = 0; i < n; i++) {
            list.add(0, i);
        }

        return System.nanoTime() - start;
    }

    static long timeAddMiddle(List<Integer> list, int n) {
        long start = System.nanoTime();

        for (int i = 0; i < n; i++) {
            list.add(list.size() / 2, i);
        }

        return System.nanoTime() - start;
    }

    static long timeGet(List<Integer> list, int n) {
        long start = System.nanoTime();
        long total = 0;

        for (int i = 0; i < n; i++) {
            total += list.get(i);
        }

        long elapsed = System.nanoTime() - start;

        if (total == -1) System.out.println(total);

        return elapsed;
    }

    static long timeIteration(List<Integer> list) {
        long start = System.nanoTime();
        long total = 0;

        for (int value : list) {
            total += value;
        }

        long elapsed = System.nanoTime() - start;

        if (total == -1) System.out.println(total);

        return elapsed;
    }

    static long timeRemoveFront(List<Integer> list, int n) {
        long start = System.nanoTime();

        for (int i = 0; i < n; i++) {
            list.remove(0);
        }

        return System.nanoTime() - start;
    }

    static long timeRemoveMiddle(List<Integer> list, int n) {
        long start = System.nanoTime();

        for (int i = 0; i < n; i++) {
            list.remove(list.size() / 2);
        }

        return System.nanoTime() - start;
    }

    static void benchmark(int size) {

        System.out.println("\nPLAYLIST SIZE: " + size);
        System.out.println("--------------------------------");

        List<Integer> array = new ArrayList<>();
        List<Integer> linked = new LinkedList<>();

        System.out.printf("ArrayList Add End: %.3f ms%n",
                timeAddEnd(array, size) / 1e6);

        System.out.printf("LinkedList Add End: %.3f ms%n",
                timeAddEnd(linked, size) / 1e6);

        array.clear();
        linked.clear();

        System.out.printf("ArrayList Add Front: %.3f ms%n",
                timeAddFront(array, size) / 1e6);

        System.out.printf("LinkedList Add Front: %.3f ms%n",
                timeAddFront(linked, size) / 1e6);

        array.clear();
        linked.clear();

        timeAddEnd(array, size);
        timeAddEnd(linked, size);

        System.out.printf("ArrayList Add Middle: %.3f ms%n",
                timeAddMiddle(new ArrayList<>(array), size / 10) / 1e6);

       System.out.printf("LinkedList Add Middle: %.3f ms%n",
        timeAddMiddle(new LinkedList<>(linked), size / 10) / 1e6);

        System.out.printf("ArrayList Get: %.3f ms%n",
                timeGet(array, size) / 1e6);

        System.out.printf("LinkedList Get: %.3f ms%n",
                timeGet(linked, size) / 1e6);

        System.out.printf("ArrayList Iteration: %.3f ms%n",
                timeIteration(array) / 1e6);

        System.out.printf("LinkedList Iteration: %.3f ms%n",
                timeIteration(linked) / 1e6);

        System.out.printf("ArrayList Remove Front: %.3f ms%n",
                timeRemoveFront(new ArrayList<>(array), size / 10) / 1e6);

        System.out.printf("LinkedList Remove Front: %.3f ms%n",
                timeRemoveFront(new LinkedList<>(linked), size / 10) / 1e6);

        System.out.printf("ArrayList Remove Middle: %.3f ms%n",
                timeRemoveMiddle(new ArrayList<>(array), size / 10) / 1e6);

        System.out.printf("LinkedList Remove Middle: %.3f ms%n",
                timeRemoveMiddle(new LinkedList<>(linked), size / 10) / 1e6);

        ArrayDeque<Integer> deque = new ArrayDeque<>();

        long start = System.nanoTime();

        for (int i = 0; i < size; i++) {
            deque.addLast(i);
        }

        for (int i = 0; i < size / 10; i++) {
            deque.removeFirst();
        }

        long dequeTime = System.nanoTime() - start;

        System.out.printf("ArrayDeque Up Next: %.3f ms%n",
                dequeTime / 1e6);
    }

    public static void main(String[] args) {

        System.out.println("MUSIC PLAYLIST COLLECTION COMPARISON");
        System.out.println("====================================");

        for (int size : SIZES) {
            benchmark(size);
        }

        System.out.println("\nDESIGN DECISION");
        System.out.println("ArrayList is suitable for frequent random access.");
        System.out.println("LinkedList supports efficient end operations.");
        System.out.println("ArrayDeque is suitable for Up Next queue.");

        System.out.println("\nTESTING COMPLETED");
    }
}
