package characterbattle;

import java.util.Scanner;

public class Game{
  private enum MainMenuOption {PLAY, LEADERBOARD, EXIT}
  private enum PlayerSelectOption {NEW, LOAD}

  private static final boolean DEBUG = true;
  
  private static Scanner scanner = new Scanner(System.in);
  
  public static void main(String[] args) {
    while (true){
      switch (showMainMenu()){
        case PLAY:
          debugMessage("Playing game...");
          break;
        case LEADERBOARD:
          debugMessage("Showing leaderboard...");
          break;
        case EXIT:
          debugMessage("Quitting...");
          return;
      }
    }
  }

  private static void debugMessage(String message){
    if (!DEBUG){ return; }

    System.out.println("DEBUG: " + message);
  }

  private static MainMenuOption showMainMenu(){
    String[] options = {
      "Play",
      "Leaderboard",
      "Quit",
    };

    System.out.println("Welcome to Bit Bash!");

    return MainMenuOption.values()[showMenu(options)];
  }

  private static int showMenu(String[] options){
    while (true){
      System.out.print("\n");
      for (int optionIndex = 0; optionIndex < options.length; optionIndex++){
        System.out.printf("%d: %s\n", optionIndex, options[optionIndex]);
      }
      System.out.print("\n");

      String input = scanner.nextLine();

      int selectedIndex;

      try{
        return Integer.parseInt(input);
      }
      catch (NumberFormatException exception){
        continue;
      }
    }
  }

  //Maintains control over game while playing
  private static void play(){
    Player[] players = new Player[2];

    players[0] = selectPlayer(1);
    players[1] = selectPlayer(2);

    System.out.println(players);
  }

  private static Player selectPlayer(int number){
    String[] options = {
      "New",
      "Load",
    };

    System.out.printf("\nWho is player %d?\n", number);
    
    PlayerSelectOption option = PlayerSelectOption.values()[showMenu(options)];

    switch (option){
      case NEW:
        break;
      case LOAD:
        break;
    }
  }

  private static Player createNewPlayer(){

  }
  
}
