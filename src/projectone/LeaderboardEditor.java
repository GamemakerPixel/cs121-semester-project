package projectone;

import java.util.HashMap;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


public class LeaderboardEditor{
  public static final String LEADERBOARD_PATH = "data/projectone/leaderboard.dat";

  private static final int BASE_TROPHY_GAIN = 20;
  private static final int ADDITIONAL_TROPHY_GAIN = 15;
  private static final int ADDITIONAL_GAIN_WIDTH = 100;
  private static final double INITIAL_GAIN_SLOPE = 0.07;

  private static final double TROPHY_LOSS_FACTOR = 0.667;

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

  public static int[] recordTournamentResults(String winnerName, String loserName){
    int trophyDifference = getPlayerScore(loserName) - getPlayerScore(winnerName);
    int loserInitialTrophies = getPlayerScore(loserName);

    int trophyGain = calculateTrophyGain(trophyDifference);
    int trophyLoss = calculateTrophyLoss(trophyGain);
    
    addToPlayerScore(winnerName, trophyGain);
    addToPlayerScore(loserName, -trophyLoss);

    int actualTrophyLoss = loserInitialTrophies - getPlayerScore(loserName);

    return new int[] {
      trophyGain,
      actualTrophyLoss
    };
  }

  public static int calculateTrophyGain(int trophyDifference){
    // If the winning player has ADDITIONAL_GAIN_WIDTH trophies less than
    // the losing player, they are awarded BASE_TROPHY_GAIN + ADDITIONAL_TROPHY_GAIN throphies.
    //
    // If the winning player has ADDITIONAL_GAIN_WIDTH trophies more than
    // the losing player, they are awarded BASE_TROPHY_GAIN - ADDITIONAL_TROPHY_GAIN throphies.
    //
    // Trophies are awarded using a cubic function, increasing gain as trophyDifference grows.
    //
    // INITIAL_GAIN_SLOPE is the derivative of this function when trophyDifference is 0.
    
    if (trophyDifference >= ADDITIONAL_GAIN_WIDTH){
      // Award maximum trophies
      return BASE_TROPHY_GAIN + ADDITIONAL_TROPHY_GAIN;
    }
    else if (trophyDifference <= -ADDITIONAL_GAIN_WIDTH){
      // Award minimum trophies
      return BASE_TROPHY_GAIN - ADDITIONAL_TROPHY_GAIN;
    }
    

    double cubicCoefficient = (ADDITIONAL_TROPHY_GAIN
      - INITIAL_GAIN_SLOPE * ADDITIONAL_GAIN_WIDTH)
      / Math.pow(ADDITIONAL_GAIN_WIDTH, 3.0);

    return (int) Math.round(
        cubicCoefficient * Math.pow(trophyDifference, 3.0)
        + INITIAL_GAIN_SLOPE * trophyDifference
        + BASE_TROPHY_GAIN
        );
  }

  public static int calculateTrophyLoss(int trophyGain){
    return (int) Math.round(trophyGain * TROPHY_LOSS_FACTOR);
  }

  private static void addToPlayerScore(String playerName, int additionalScore){
    HashMap<String, Integer> currentLeaderboard = readLeaderboard();

    currentLeaderboard.put(playerName, 
        Math.max(0, getPlayerScore(playerName) + additionalScore)
        );

    overwriteLeaderboard(currentLeaderboard);
  }

  public static int getPlayerScore(String playerName){
    HashMap<String, Integer> currentLeaderboard = readLeaderboard();

    if (!currentLeaderboard.containsKey(playerName)){
      return 0;
    }

    return currentLeaderboard.get(playerName);
  }

  private static void overwriteLeaderboard(HashMap<String, Integer> scoreData){
    FileWriter writer;

    try{
      File file = new File(LEADERBOARD_PATH);
      file.getParentFile().mkdirs(); // Create missing parent directories.
      file.createNewFile(); // Create the file if it doesn't exist yet.
      writer = new FileWriter(file);

      String newData = "";

      for (String playerName: scoreData.keySet()){
        newData += playerName + ":" + scoreData.get(playerName) + "\n";
      }

      writer.write(newData);
      writer.close();
    }
    catch (IOException exception){
      System.out.println("Unable to open leaderboard file.");
      return;
    }

  }

}
