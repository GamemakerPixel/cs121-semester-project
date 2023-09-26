package characterbattle;

import java.util.Scanner;

public class Game{
  private enum MenuOption {PLAY, LEADERBOARD, EXIT}

  private static final boolean DEBUG = true;
  
  private static Scanner scanner = new Scanner(System.in);
  
  //private static Player[] players = new Player[2];

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

  private static MenuOption showMainMenu(){
    String[] options = {
      "Play",
      "Leaderboard",
      "Quit",
    };

    System.out.println("Welcome to Bit Bash!");

    return MenuOption.values()[showMenu(options)];
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


  private static void play(){
    
  }

  /*private static Player selectPlayer(int number){
    System.out.printf("\nWho is player %d?\n", number);
    
  }*/
}
