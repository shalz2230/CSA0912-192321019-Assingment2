
import java.util.*;

class Team {
    String name;
    int points;
    int goalDiff;

    Team(String name, int points, int goalDiff) {
        this.name = name;
        this.points = points;
        this.goalDiff = goalDiff;
    }

    @Override
    public String toString() {
        return name + "(" + points + "," + goalDiff + ")";
    }
}

public class Task07 {

    static Comparator<Team> byRank =
        Comparator.comparingInt((Team t) -> t.points)
        .reversed()
        .thenComparing(
            Comparator.comparingInt((Team t) -> t.goalDiff)
            .reversed()
        )
        .thenComparing(t -> t.name);

    // Original design: sort every time ranking is requested
    static List<Team> originalRanking(List<Team> teams) {
        List<Team> result = new ArrayList<>(teams);
        result.sort(byRank);
        return result;
    }

    // Redesigned solution: sort once and reuse the result
    static List<Team> redesignedRanking(List<Team> teams) {
        List<Team> result = new ArrayList<>(teams);
        result.sort(byRank);
        return Collections.unmodifiableList(result);
    }

    static List<Team> createTeams(int size) {
        List<Team> teams = new ArrayList<>();

        for (int i = 1; i <= size; i++) {
            teams.add(new Team(
                "Team" + i,
                i % 101,
                i % 51
            ));
        }

        return teams;
    }

    static long calculateChecksum(List<Team> ranking) {
        long sum = 0;

        for (Team team : ranking) {
            sum += team.points + team.goalDiff;
        }

        return sum;
    }

    static void test(int size) {

        int requests = 50;
        List<Team> teams = createTeams(size);

        System.out.println("\nTEAM COUNT: " + size);
        System.out.println("Ranking Requests: " + requests);

        // Original approach
        long originalChecksum = 0;
        long startOriginal = System.nanoTime();

        for (int i = 0; i < requests; i++) {
            originalChecksum += calculateChecksum(
                originalRanking(teams)
            );
        }

        long endOriginal = System.nanoTime();

        // Redesigned approach: prepare ranking once
        long startBuild = System.nanoTime();
        List<Team> cachedRanking = redesignedRanking(teams);
        long endBuild = System.nanoTime();

        long redesignedChecksum = 0;
        long startRedesigned = System.nanoTime();

        for (int i = 0; i < requests; i++) {
            redesignedChecksum += calculateChecksum(cachedRanking);
        }

        long endRedesigned = System.nanoTime();

        System.out.printf(
            "Original repeated sorting: %.3f ms%n",
            (endOriginal - startOriginal) / 1e6
        );

        System.out.printf(
            "Redesign initial sorting: %.3f ms%n",
            (endBuild - startBuild) / 1e6
        );

        System.out.printf(
            "Redesign repeated reads: %.3f ms%n",
            (endRedesigned - startRedesigned) / 1e6
        );

        System.out.println(
            "Results Match: " +
            (originalChecksum == redesignedChecksum)
        );
    }

    public static void main(String[] args) {

        System.out.println("TASK 07 - DESIGN CHALLENGE");
        System.out.println("==========================");

        System.out.println("\nBOTTLENECK DIAGNOSIS");
        System.out.println("Original design sorts the list for every ranking request.");
        System.out.println("Repeated sorting wastes processing time.");

        System.out.println("\nREDESIGN OBJECTIVE");
        System.out.println("Sort once and reuse the sorted ranking.");
        System.out.println("Avoid unnecessary repeated sorting.");

        System.out.println("\nPERFORMANCE VALIDATION");

        test(1000);
        test(10000);
        test(50000);

        System.out.println("\nFINAL DESIGN DECISION");
        System.out.println("Redesign is suitable when ranking is requested frequently.");
        System.out.println("It avoids repeated sorting when data remains unchanged.");
        System.out.println("If teams change, the cached ranking must be refreshed.");
        System.out.println("The cached result requires additional memory.");

        System.out.println("\nTESTING COMPLETED");
    }
}
