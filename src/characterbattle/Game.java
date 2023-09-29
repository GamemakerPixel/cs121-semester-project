package characterbattle;

import java.util.Scanner;

public class Game{
  private enum MainMenuOption {PLAY, LEADERBOARD, EXIT}
  private enum PlayerSelectOption {NEW, LOAD}
  private enum CharacterSelectOption {NEW, LOAD, NONE}
  private enum StatSelectOption {HIT_POINTS, BASE_DAMAGE, FINISH_EDITING}

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

  //TODO: Unify stats into a datatype

  private static Character createCharacter(){
    String name = promptCharacterName();

    int statPointsRemaining = Character.STAT_POINTS;

    int statHitPoints = 0;
    int statBaseDamage = 0;

    while (true){
      System.out.printf("%d stat points remaining.\n", statPointsRemaining);

      switch (showStatSelectMenu(statHitPoints, statBaseDamage)){
        case HIT_POINTS:
          statHitPoints = promptNewStatValue(statPointsRemaining);
          statPointsRemaining -= statHitPoints;
          break;
        case BASE_DAMAGE:
          statBaseDamage = promptNewStatValue(statPointsRemaining);
          statPointsRemaining -= statBaseDamage;
          break;
        case FINISH_EDITING:
          return new Character(name, statHitPoints, statBaseDamage);
      }
    }
  }

  private static StatSelectOption showStatSelectMenu(int statHitPoints, int statBaseDamage){
    String[] options = new String[Character.STAT_NAMES.length + 1];

    System.arraycopy(Character.STAT_NAMES, 0, options, 0, Character.STAT_NAMES.length);

    options[options.length - 1] = "Finish Editing";

    options[0] += String.format(" Stat:%d, Actual:%d",
        statHitPoints, 
        Character.computeHitPoints(statHitPoints));
    options[1] += String.format(" Stat:%d, Actual:%d",
        statBaseDamage, 
        Character.computeBaseDamage(statBaseDamage));


    return StatSelectOption.values()[showMenu(options)];
  }

  //TODO: Allow users to lower stat values.
  private static int promptNewStatValue(int statPointsRemaining){
    while (true){
      System.out.println("Enter a new stat value: ");

      try{
        int newStatValue = Integer.parseInt(scanner.nextLine());

        if (newStatValue < 0){
          System.out.println("Value cannot be negative.");
          continue;
        }

        if (newStatValue > statPointsRemaining){
          System.out.println("Not enough stat points! Lower other stat values first.");
          continue;
        }

        return newStatValue;
      }
      catch (NumberFormatException exception){
        continue;
      }
    }
  }

  private static String promptCharacterName(){
    while (true){
      System.out.printf("Enter a name for your character (%d characters or less): ", Character.MAXIMUM_NAME_LENGTH);
      String name = scanner.nextLine();

      if (name.length() == 0){
        System.out.println("You must name your character.");
        continue;
      }
      if (name.equals("Empty Slot")){
        System.out.println("\"Empty Slot\" is an illegal name.");
        continue;
      }
      if (name.length() > Character.MAXIMUM_NAME_LENGTH){
        System.out.printf("Name cannot be over %d characters.", Character.MAXIMUM_NAME_LENGTH);
        continue;
      }

      return name;
    }
  }


  
}
