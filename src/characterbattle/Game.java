package characterbattle;

import java.util.Scanner;
import java.util.HashMap;
import java.util.Set;
import java.util.Collection;
import java.util.concurrent.Callable;

public class Game{
  private enum MainMenuOption {PLAY, LEADERBOARD, EXIT}
  private enum PlayerSelectOption {NEW, LOAD}
  private enum SlotSelectOption {NEW, LOAD, NONE}
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

    System.out.println("* * * DEBUG: " + message);
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

    //Here would be a good spot to save new game data.
    
    battleTournament(players, rounds);

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

      Character[] team = (Character[]) editNameableSlots(
          new Character[Player.TEAM_SIZE],
          "character",
          () -> { return createCharacter(); }
          );

      player.setTeam(team);

      initializeMovesets(team);
    }
  }

  private static void initializeMovesets(Character[] characters){
    for (Character character: characters){
      if (character == null){ continue; }

      System.out.printf("Create a moveset for %s.\n", character.getName());
      
      character.setMoveset((Move[]) editNameableSlots(
            new Move[Character.MOVESET_SIZE],
            "move",
            () -> { return createMove(); }
            ));
    }
  }

  //TODO: Limit player name length to 16 characters
  private static void printPlayerBanner(Player player){
    System.out.printf("\n- - - - - %-16s - - - - -\n", player.getName());
  }

  private static Nameable[] editNameableSlots(Nameable[] nameables,
      String nameableType,
      Callable<Nameable> newMethod){
    while (true){
      System.out.printf("Select a %s slot to edit, or \"Finish Editing\" when you're done.\n", nameableType);

      String[] menuOptions = generateNameableSlotOptions(nameables);

      int selectedSlotIndex = showMenu(menuOptions);

      // Checks if player selects "Finish Editing"
      if (selectedSlotIndex == menuOptions.length - 1){
        if (allSlotsEmpty(nameables)){
          System.out.printf("At least one %s is required to continue.\n", nameableType);

          continue;
        }

        break;
      }
      else{
        SlotSelectOption option = showSlotSelectMenu(nameableType);

        switch (option){
          case NEW:
            try{
              nameables[selectedSlotIndex] = newMethod.call();
            }
            catch (Exception exception){
              System.out.printf("Error: Could not call create method for type %s.", nameableType);
              nameables[selectedSlotIndex] = null;
            }
          case LOAD:
            break;
          case NONE:
            nameables[selectedSlotIndex] = null;
        }
      }
    } 

    return nameables;

  }

  private static String[] generateNameableSlotOptions(Nameable[] nameables){
    String[] options = new String[nameables.length + 1];

    for (int nameableIndex = 0; nameableIndex < nameables.length; nameableIndex++){
      if (nameables[nameableIndex] == null){
        options[nameableIndex] = "Empty Slot";
      }
      else{
        options[nameableIndex] = nameables[nameableIndex].getName();
      }
    }

    options[options.length - 1] = "Finish Editing";

    return options;
  }

  private static boolean allSlotsEmpty(Nameable[] nameables){
    for (Nameable nameable: nameables){
      if (nameable != null){
        return false;
      }
    }

    return true;
  }
  
  private static SlotSelectOption showSlotSelectMenu(String nameableType){
    String[] options = {
      "New",
      "Load",
      "None",
    };

    System.out.printf("Select a %s for this slot.\n", nameableType);

    return SlotSelectOption.values()[showMenu(options)];

  }

  private static Character createCharacter(){
    String name;

    while (true){
      System.out.printf("Enter a name for your character. (%d characters max.)\n",
          Character.MAXIMUM_NAME_LENGTH);
      name = scanner.nextLine();

      if (name.length() > Character.MAXIMUM_NAME_LENGTH){
        System.out.println("Name is too long.");
        continue;
      }

      break;
    }

    Character character = new Character(name);

    editStatObject(character);

    return character;
  }

  //Realistically I could have done some abstraction and combined createCharacter() and createMove(), but I don't really have time.
  private static Move createMove(){
    String name;

    while (true){
      System.out.printf("Enter a name for this move. (%d characters max.)\n",
          Move.MAXIMUM_NAME_LENGTH);
      name = scanner.nextLine();

      if (name.length() > Move.MAXIMUM_NAME_LENGTH){
        System.out.println("Name is too long.");
        continue;
      }

      break;
    }

    Move move = new Move(name);

    editStatObject(move);

    return move;

  }

  private static void editStatObject(StatObject statObject){
    int statPointsRemaining = statObject.getSpendableStatPoints();

    String[] statNames = statObject.getStatNames();

    HashMap<String, Integer> newStatPointValues = new HashMap<>();

    for (String statName: statNames){
      newStatPointValues.put(statName, statObject.getStatPointValue(statName));
    }

    while (true){
      System.out.println("Select a stat to edit or \"Finish Editing\" when you're done.");
      System.out.printf("Stat Points Remaining: %d\n", statPointsRemaining);

      String[] menuOptions = generateStatEditMenuOptions(newStatPointValues, statObject);

      int menuIndexSelected = showMenu(menuOptions);

      // Finish Editing
      if (menuIndexSelected == menuOptions.length - 1){
        statObject.setPointValues(newStatPointValues);
        return;
      }

      String statToEdit = statNames[menuIndexSelected];

      statPointsRemaining -= editStat(statToEdit,
          statPointsRemaining,
          newStatPointValues,
          statObject
          );
    }
  }

  private static String[] generateStatEditMenuOptions(
      HashMap<String, Integer> statPointValues, 
      StatObject statObject
  ){
    String[] options = new String[statPointValues.keySet().size() + 1];

    String[] statNamesArray = statObject.getStatNames();

    for (int statNameIndex = 0; statNameIndex < statNamesArray.length; statNameIndex++){
      String statName = statNamesArray[statNameIndex];
      int statValue = statPointValues.get(statName);

      options[statNameIndex] = String.format(
          "%s: %d; Points Spent: %d",
          statName, 
          statObject.getStatTheoreticalValue(statName, statValue),
          statValue
          );
    }

    options[options.length - 1] = "Finish Editing";

    return options;
  }

  private static int editStat(String statName, 
      int statPointsRemaining,
      HashMap<String, Integer> statPointValues, 
      StatObject statObject
  ){
    int currentPointsSpent = statPointValues.get(statName);
    int pointsToSpend;

    while (true){
      System.out.printf("How many points would you like spent on %s?\n", statName);

      try{
        pointsToSpend = Integer.parseInt(scanner.nextLine());
      }
      catch (NumberFormatException exception){
        System.out.println("Please enter a number.");
        continue;
      }

      if (pointsToSpend < 0){
        System.out.println("You cannot have negative stats.");
        continue;
      }

      if (pointsToSpend - currentPointsSpent <= statPointsRemaining){
        break;
      }

      System.out.println("Not enough stat points.");
    }

    statPointValues.put(statName, pointsToSpend);
    // Difference in points spent originally versus now.
    return pointsToSpend - currentPointsSpent;

  }

  private static void battleTournament(Player[] players, int rounds){
    displayTournamentBanner(players, rounds);

    int[] scores = new int[2];

    for (int roundIndex = 0; roundIndex < rounds; roundIndex++){
      scores[battleRound(players)]++;
    }
  }

  private static void displayTournamentBanner(Player[] players, int rounds){
    String header = String.format(
        "# - # - # - # - # - # - # - # - # - # - # - # -\n" + 
        "# - " +       "%16s vs %-16s " +        " - # -\n" +
        "# - # - # - # - # - # - # - # - # - # - # - # -\n" + 
        "# - # - # - IN A %d ROUND TOURNAMENT! - # - # -\n" +
        "# - # - # - # - # - # - # - # - # - # - # - # -\n",

        players[0].getName(), players[1].getName(),
        rounds
        );

    System.out.println(header);
  }

  //Returns the index of the player that wins the round.
  private static int battleRound(Player[] players){
    Character[] selectedCharacters = new Character[players.length];
  }
}
