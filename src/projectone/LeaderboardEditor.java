package projectone;

import java.util.HashMap;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class LeaderboardEditor{
  public static String LEADERBOARD_PATH = "data/projectone/leaderboard.dat";

  public static HashMap<String, Integer> readLeaderboard(){
    Scanner scanner;

    try{
      File file = new File(LEADERBOARD_PATH);
      file.getParentFile().mkdirs(); // Create missing parent directories.
      file.createNewFile(); // Create the file if it doesn't exist yet.
      scanner = new Scanner(file);
    }
    catch (IOException exception){
      System.out.println("Unable to open leaderboard file.");
      return null;
    }

    HashMap<String, Integer> pastScores = new HashMap<>();

    while (scanner.hasNextLine()){
      String line = scanner.nextLine();

      int colonIndex = line.indexOf(':');

      if (colonIndex == -1){
        System.out.println("Corrupt leaderboard file.");
        return null;
      }
      
      String playerName = line.substring(0, colonIndex);
      int playerScore;

      try{
        playerScore = Integer.parseInt(line.substring(colonIndex + 1));
      }
      catch (NumberFormatException exception){
        System.out.println("Corrupt leaderboard file.");
        return null;
      }

      pastScores.put(playerName, playerScore);
    }

    return pastScores;

  }


}
