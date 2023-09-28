package characterbattle;

import java.util.Scanner;

public class Game{
  private enum MainMenuOption {PLAY, LEADERBOARD, EXIT}
  private enum PlayerSelectOption {NEW, LOAD}
  private enum CharacterSelectOption {NEW, LOAD, NONE}

  private static final boolean DEBUG = true;
  
  private static Scanner scanner = new Scanner(System.in);
  
  public static void main(String[] args) {
    while (true){
      switch (showMainMenu()){
        case PLAY:
          debugMessage("Playing game...");
          play();
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

    int rounds = promptForRounds();

    initializeTeams(players);

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
        return createNewPlayer();
      case LOAD:
        //TODO: Loading players from file
        return new Player("LOADED_PLAYER");
    }
  
    //Unreachable
    return null;
  }

  private static Player createNewPlayer(){
    System.out.println("\nName your player:");
    String name = scanner.nextLine();

    return new Player(name);
  }

  private static int promptForRounds(){
    int rounds;

    while(true){
      try{
        System.out.println("\nEnter the number of rounds in the tournament (must be odd):");
        rounds = Integer.parseInt(scanner.nextLine());

        if (rounds % 2 == 1){
          break;
        }
      }
      catch (NumberFormatException exception){
        continue;
      }
    }
    
    return rounds;
  }

  private static void initializeTeams(Player[] players){
    for (Player player : players){
      printPlayerBanner(player);

      player.setTeam(editTeam(new Character[Player.TEAM_SIZE]));
    }
  }

  //TODO: Limit player name length to 16 characters
  private static void printPlayerBanner(Player player){
    System.out.printf("\n- - - - - %-16s - - - - -\n", player.getName());
  }

  private static Character[] editTeam(Character[] team){
    while (true){
      System.out.println("Select a character slot to edit, or \"Finish Editing\" when you're done.");

      String[] menuOptions = generateEditTeamMenuOptions(team);

      int selectedCharacterIndex = showMenu(menuOptions);

      // Checks if player selects "Finish Editing"
      if (selectedCharacterIndex == menuOptions.length - 1){
        if (isTeamEmpty(team)){
          System.out.println("At least one character is required to continue.");

          continue;
        }

        break;
      }
      else{
        CharacterSelectOption option = showCharacterSelectMenu();

        switch (option){
          case NEW:
            team[selectedCharacterIndex] = createCharacter();
          case LOAD:
            break;
          case NONE:
            team[selectedCharacterIndex] = null;
        }
      }
    } 

    return team;
  }

  private static String[] generateEditTeamMenuOptions(Character[] team){
    String[] options = new String[team.length + 1];

    for (int characterIndex = 0; characterIndex < team.length; characterIndex++){
      if (team[characterIndex] == null){
        options[characterIndex] = "Empty Slot";
      }
      else{
        options[characterIndex] = team[characterIndex].getName();
      }
    }

    options[options.length - 1] = "Finish Editing";

    return options;
  }

  private static boolean isTeamEmpty(Character[] team){
    for (Character character : team){
      if (character != null){
        return false;
      }
    }

    return true;
  }

  private static CharacterSelectOption showCharacterSelectMenu(){
    String[] options = {
      "New",
      "Load",
      "None",
    };

    System.out.println("Select a character for this slot.");

    return CharacterSelectOption.values()[showMenu(options)];

  }

  private static Character createCharacter(){
    //int stat_points_remaining = Character.STAT_POINTS;
    
    return new Character("New Character", 7, 8);
  }


  
}
