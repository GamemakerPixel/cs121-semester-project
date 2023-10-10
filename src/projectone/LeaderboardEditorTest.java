package projectone;

import java.util.HashMap;

public class LeaderboardEditorTest{
  public static void main(String[] args) {
    System.out.println(LeaderboardEditor.readLeaderboard());

    int[] differences = LeaderboardEditor.recordTournamentResults(args[0], args[1]);

    System.out.println(args[0] + " gained " + differences[0]);
    System.out.println(args[1] + " lost " + differences[1]);
    System.out.println(LeaderboardEditor.readLeaderboard());
  }
}
