import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Team {
    final String name;
    final int points;
    final int goalDiff;

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

class Leaderboard<T> {
    private final List<T> items = new ArrayList<>();
    private final Comparator<? super T> order;

    Leaderboard(Comparator<? super T> order) {
        this.order = order;
    }

    void add(T item) {
        items.add(item);
    }

    // TODO 1 completed: sort a copy, preserving insertion order in the source list.
    List<T> ranking() {
        List<T> copy = new ArrayList<>(items);
        copy.sort(order);
        return copy;
    }

    // Demonstrates a producer wildcard: this list supplies T values.
    void addAll(List<? extends T> values) {
        items.addAll(values);
    }
}

public class LeaderboardDemo {
    public static void main(String[] args) {
        // TODO 2 completed: points descending, goal difference descending, name ascending.
        Comparator<Team> byRank =
                Comparator.comparingInt((Team t) -> t.points).reversed()
                        .thenComparing(Comparator.comparingInt(
                                (Team t) -> t.goalDiff).reversed())
                        .thenComparing(t -> t.name);

        Leaderboard<Team> board = new Leaderboard<>(byRank);
        board.add(new Team("Panthers", 9, 4));
        board.add(new Team("Eagles", 9, 6));
        board.add(new Team("Cobras", 6, 1));
        board.add(new Team("Bisons", 9, 4));

        System.out.println(board.ranking());
        System.out.println("Original insertion order remains unchanged: "
                + "Panthers, Eagles, Cobras, Bisons");
    }
}
