
import java.util.*;

public class Task04 {

    static class Medicine {
        String code;
        String name;
        int stock;

        Medicine(String code, String name, int stock) {
            this.code = code;
            this.name = name;
            this.stock = stock;
        }

        public String toString() {
            return code + " - " + name + " - Stock: " + stock;
        }
    }

    static void testMaps(int size) {

        Map<String, Medicine> hashMap = new HashMap<>();
        Map<String, Medicine> treeMap = new TreeMap<>();
        Map<String, Medicine> linkedMap = new LinkedHashMap<>();

        for (int i = size; i >= 1; i--) {
            String code = String.format("MED%06d", i);
            Medicine medicine =
                new Medicine(code, "Medicine" + i, 100 + i);

            hashMap.put(code, medicine);
            treeMap.put(code, medicine);
            linkedMap.put(code, medicine);
        }

        System.out.println("\nMEDICINE COUNT: " + size);

        String searchCode = "MED000001";

        long start = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            hashMap.get(searchCode);
        }
        long hashTime = System.nanoTime() - start;

        start = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            treeMap.get(searchCode);
        }
        long treeTime = System.nanoTime() - start;

        start = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            linkedMap.get(searchCode);
        }
        long linkedTime = System.nanoTime() - start;

        System.out.printf("HashMap 100000 Lookups: %.3f ms%n",
                          hashTime / 1e6);
        System.out.printf("TreeMap 100000 Lookups: %.3f ms%n",
                          treeTime / 1e6);
        System.out.printf("LinkedHashMap 100000 Lookups: %.3f ms%n",
                          linkedTime / 1e6);

        start = System.nanoTime();
        for (Medicine m : treeMap.values()) {
            // Code-order report
        }
        long codeReport = System.nanoTime() - start;

        start = System.nanoTime();
        for (Medicine m : linkedMap.values()) {
            // Insertion-order report
        }
        long insertionReport = System.nanoTime() - start;

        System.out.printf("TreeMap Code Order Report: %.3f ms%n",
                          codeReport / 1e6);
        System.out.printf("LinkedHashMap Insertion Order Report: %.3f ms%n",
                          insertionReport / 1e6);

        System.out.println("First Medicine in TreeMap: "
                           + treeMap.values().iterator().next());

        System.out.println("First Medicine in LinkedHashMap: "
                           + linkedMap.values().iterator().next());

        System.out.println("HashMap Size: " + hashMap.size());
        System.out.println("TreeMap Size: " + treeMap.size());
        System.out.println("LinkedHashMap Size: " + linkedMap.size());
    }

    public static void main(String[] args) {

        System.out.println("PHARMACY INVENTORY - MAP ANALYSIS");
        System.out.println("=================================");

        testMaps(1000);
        testMaps(10000);
        testMaps(50000);

        System.out.println("\nDESIGN DECISION");
        System.out.println("HashMap is suitable for fast stock lookup.");
        System.out.println("TreeMap is suitable for medicine code order.");
        System.out.println("LinkedHashMap maintains insertion order.");

        System.out.println("\nHashtable is not preferred in new code.");
        System.out.println("It is synchronized and usually slower when");
        System.out.println("thread safety is not required.");

        System.out.println("\nTESTING COMPLETED");
    }
}
