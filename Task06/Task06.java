
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.List;

class Patient {
    String name;
    int severity;
    int arrivalOrder;

    Patient(String name, int severity, int arrivalOrder) {
        this.name = name;
        this.severity = severity;
        this.arrivalOrder = arrivalOrder;
    }

    @Override
    public String toString() {
        return name + "(Severity " + severity + ")";
    }
}

public class Task06 {

    static Comparator<Patient> patientOrder =
        Comparator.comparingInt((Patient p) -> p.severity)
        .thenComparingInt(p -> p.arrivalOrder);

    static void testQueue(int size) {

        PriorityQueue<Patient> queue =
            new PriorityQueue<>(patientOrder);

        long startAdd = System.nanoTime();

        for (int i = 1; i <= size; i++) {
            int severity = (i % 5) + 1;
            queue.add(new Patient("Patient" + i, severity, i));
        }

        long endAdd = System.nanoTime();

        long startRemove = System.nanoTime();

        while (!queue.isEmpty()) {
            queue.poll();
        }

        long endRemove = System.nanoTime();

        System.out.printf(
            "Patients: %d | Add Time: %.3f ms | Remove Time: %.3f ms%n",
            size,
            (endAdd - startAdd) / 1e6,
            (endRemove - startRemove) / 1e6
        );
    }

    public static void main(String[] args) {

        System.out.println("HOSPITAL EMERGENCY TRIAGE SYSTEM");
        System.out.println("================================");

        PriorityQueue<Patient> queue =
            new PriorityQueue<>(patientOrder);

        queue.add(new Patient("Arun", 3, 1));
        queue.add(new Patient("Bala", 1, 2));
        queue.add(new Patient("Charan", 2, 3));
        queue.add(new Patient("Divya", 1, 4));
        queue.add(new Patient("Ezhil", 5, 5));
        queue.add(new Patient("Farhan", 2, 6));
        queue.add(new Patient("Gokul", 4, 7));
        queue.add(new Patient("Hari", 3, 8));

        System.out.println("\nTREATMENT ORDER:");

        while (!queue.isEmpty()) {
            System.out.println(queue.poll());
        }

        System.out.println("\nQUEUE PERFORMANCE TEST");
        System.out.println("=======================");

        testQueue(1000);
        testQueue(10000);
        testQueue(50000);

        System.out.println("\nDESIGN DECISION");
        System.out.println("PriorityQueue is suitable for emergency triage.");
        System.out.println("Lower severity number receives higher priority.");
        System.out.println("Arrival order is maintained for equal severity.");
        System.out.println("Adding a patient: O(log n)");
        System.out.println("Removing next patient: O(log n)");
        System.out.println("A plain FIFO queue cannot handle severity priority.");

        System.out.println("\nFAIRNESS NOTE");
        System.out.println("Minor patients may wait longer during continuous emergencies.");
        System.out.println("Priority can be increased based on waiting time.");

        System.out.println("\nTESTING COMPLETED");
    }
}
