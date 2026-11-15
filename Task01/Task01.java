
import java.util.function.Function;

public class Task01 {

    interface TicketBuilder {
        String build(String[] fields);
    }

    static String usingString(String[] fields) {
        String ticket = "";

        for (String field : fields) {
            ticket += field;
        }

        return ticket;
    }

    static String usingStringBuilder(String[] fields) {
        StringBuilder ticket = new StringBuilder();

        for (String field : fields) {
            ticket.append(field);
        }

        return ticket.toString();
    }

    static String usingStringBuffer(String[] fields) {
        StringBuffer ticket = new StringBuffer();

        for (String field : fields) {
            ticket.append(field);
        }

        return ticket.toString();
    }

    static long benchmark(TicketBuilder builder,
                          String[] fields,
                          int tickets) {

        long start = System.nanoTime();
        long checksum = 0;

        for (int i = 0; i < tickets; i++) {
            String result = builder.build(fields);
            checksum += result.length();
        }

        long end = System.nanoTime();

        if (checksum == 0) {
            System.out.println("No ticket generated");
        }

        return end - start;
    }

    static void runTest(String workload,
                        int tickets,
                        int fieldCount) {

        String[] fields = new String[fieldCount];

        for (int i = 0; i < fieldCount; i++) {
            fields[i] = "FIELD" + i + " ";
        }

        TicketBuilder[] builders = {
            Task01::usingString,
            Task01::usingStringBuilder,
            Task01::usingStringBuffer
        };

        String[] names = {
            "String",
            "StringBuilder",
            "StringBuffer"
        };

        System.out.println("\n" + workload);
        System.out.println("------------------------------");
        System.out.println("Tickets: " + tickets);
        System.out.println("Fields per ticket: " + fieldCount);

        for (int j = 0; j < builders.length; j++) {

            // Warm-up
            benchmark(builders[j], fields, 100);

            long totalTime = 0;

            for (int r = 0; r < 5; r++) {
                totalTime += benchmark(builders[j], fields, tickets);
            }

            double averageMs = totalTime / 5.0 / 1_000_000.0;

            System.out.printf("%s Average Time: %.3f ms%n",
                    names[j], averageMs);
        }

        System.out.println("Estimated intermediate String objects:");
        System.out.println("String: Many intermediate objects");
        System.out.println("StringBuilder: One final String per ticket");
        System.out.println("StringBuffer: One final String per ticket");
    }

    public static void main(String[] args) {

        System.out.println("RAILWAY E-TICKET GENERATION");
        System.out.println("===========================");

        System.out.println("\nPREDICTION:");
        System.out.println("String creates many intermediate objects.");
        System.out.println("StringBuilder is expected to be faster.");
        System.out.println("StringBuffer provides synchronized methods.");

        runTest("WORKLOAD 1 - SHORT TICKET", 1, 6);

        runTest("WORKLOAD 2 - LARGE TICKET GENERATION",
                10000, 50);

        runTest("WORKLOAD 3 - CUSTOM WORKLOAD",
                2000, 100);

        System.out.println("\nDESIGN DECISION:");
        System.out.println("Use StringBuilder for individual tickets.");
        System.out.println("Use StringBuffer when synchronized");
        System.out.println("access to a shared mutable buffer is required.");

        System.out.println("\nTESTING COMPLETED");
    }
}
