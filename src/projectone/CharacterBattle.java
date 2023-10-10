package projectone;

import java.util.Scanner;
import java.util.HashMap;
import java.util.Set;
import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.ArrayList;

public class CharacterBattle{
  private enum MainMenuOption {PLAY, LEADERBOARD, EXIT}
  private enum PlayerSelectOption {NEW, LOAD}
  private enum SlotSelectOption {NEW, NONE}
  private enum StatSelectOption {HIT_POINTS, BASE_DAMAGE, FINISH_EDITING}

  private static final boolean DEBUG = false;
  
  private static Scanner scanner = new Scanner(System.in);
  
  public static void main(String[] args) {
    while (true){
      switch (showMainMenu()){
        case PLAY:
          debugMessage("Playing game...");
          play();
          break;
        case LEADERBOARD:
          displayLeaderboard();
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

    System.out.println("\nWelcome to Bit Bash!");

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
        selectedIndex = Integer.parseInt(input);

        if (selectedIndex >= options.length){ continue; }
        return selectedIndex;
      }
      catch (NumberFormatException exception){
        continue;
      }
    }
  }

  private static String showUnindexedMenu(String[] options){
    return options[showMenu(options)];
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

  private static void displayLeaderboard(){
    HashMap<String, Integer> leaderboard = LeaderboardEditor.readLeaderboard();

    ArrayList<String> playerNames = new ArrayList<>(leaderboard.keySet());
    ArrayList<Integer> scores = new ArrayList<>(leaderboard.values());

    if (playerNames.size() == 0){
      System.out.println("\nNo one has played the game on this device yet!");
      return;
    }

    // Sort
    for (int startIndex = 0; startIndex < scores.size() - 1; startIndex++){
      String maximumPlayerName = "";
      int maximumScore = -1;
      int maximumIndex = -1;
      for (int scoreIndex = startIndex; scoreIndex < scores.size(); scoreIndex++){
        if (scores.get(scoreIndex) > maximumScore){
          maximumScore = scores.get(scoreIndex);
          maximumPlayerName = playerNames.get(scoreIndex);
          maximumIndex = scoreIndex;
        }
      }

      playerNames.remove(maximumIndex);
      playerNames.add(startIndex, maximumPlayerName);

      scores.remove(maximumIndex);
      scores.add(startIndex, maximumScore);
    }

    System.out.println("\n- - - - - Leaderboard - - - - -");
    System.out.println("Player:              \tScore:");

    int placementNumber = 1;
    int placementDifference = 1;

    for (int playerIndex = 0; playerIndex < playerNames.size(); playerIndex++){
      System.out.printf(
          "\t%d. %-18s\t%d\n",
          placementNumber,
          playerNames.get(playerIndex),
          scores.get(playerIndex)
          );

      // Does not increment placement if next player is tied (or if there is no next player)
      if (playerIndex + 1 < playerNames.size()
          && scores.get(playerIndex) != scores.get(playerIndex + 1)){
        placementNumber += placementDifference;
        placementDifference = 1;
          }
      else{
        // If a tie occurs, the next place won't exist
        // Ex: 1st, 2nd, 2nd, 4th, 5th
        placementDifference++;
      }
    }


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
        Player loadedPlayer = loadPlayer();
        if (loadedPlayer == null){
          System.out.println("\nNo players have been created on this device yet!");
          return selectPlayer(number);
        }
        return loadedPlayer;
    }
  
    //Unreachable
    return null;
  }

  private static Player createNewPlayer(){
    System.out.println("\nName your player:");
    String name = scanner.nextLine();

    return new Player(name);
  }

  private static Player loadPlayer(){
    String[] loadablePlayers = LeaderboardEditor.readLeaderboard().keySet().toArray(new String[0]);

    if (loadablePlayers.length == 0){
      return null;
    }

    return new Player(
        loadablePlayers[showMenu(loadablePlayers)]
        );
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

      System.out.printf("\nCreate a moveset for %s.\n", character.getName());
      
      character.setMoveset((Move[]) editNameableSlots(
            new Move[Character.MOVESET_SIZE],
            "move",
            () -> { return createMove(); }
            ));
    }
  }

  //TODO: Limit player name length to 16 characters
  private static void printPlayerBanner(Player player){
    System.out.printf("\n- - - - - %s - - - - -\n", player.getName() + "\'s Turn");
  }

  private static Nameable[] editNameableSlots(Nameable[] nameables,
      String nameableType,
      Callable<Nameable> newMethod){
    while (true){
      System.out.printf("Select a %s slot to edit, or \"Finish Editing\" when you're done.\n"
          + "(Must have one filled slot.)\n", nameableType);

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
      "None",
    };

    System.out.printf("Select a %s for this slot.\n", nameableType);

    return SlotSelectOption.values()[showMenu(options)];

  }

  private static Character createCharacter(){
    String name;

    while (true){
      System.out.printf("\nEnter a name for your character. (%d characters max.)\n",
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
      System.out.printf("\nEnter a name for this move. (%d characters max.)\n",
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
      System.out.println("\nSelect a stat to edit or \"Finish Editing\" when you're done.");
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
      System.out.printf("\nHow many points would you like spent on %s?\n", statName);

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
      System.out.printf("v v - - - Round %d! - - - v v", roundIndex + 1);
      int winner = battleRound(players);
      System.out.printf("\n!!! - X - %s has won the round! - X - !!!\n", players[winner].getName());
      scores[winner]++;
    }

    Player winningPlayer = determineTournamentWinner(players, scores);

    printTournamentResults(players, scores, winningPlayer);
  }

  private static void displayTournamentBanner(Player[] players, int rounds){
    String header = String.format("\n" +
        "# - # - # - # - # - # - # - # - # - # - # - # -\n" + 
        "# - " +       "%16s vs %-16s " +        " - # -\n" +
        "# - # - # - # - # - # - # - # - # - # - # - # -\n" + 
        "# - # - # - IN A %2s ROUND TOURNAMENT! - # - # -\n" +
        "# - # - # - # - # - # - # - # - # - # - # - # -\n",

        players[0].getName(), players[1].getName(),
        String.format("%d", rounds)
        );

    System.out.println(header);
  }

  //Returns the index of the player that wins the round.
  private static int battleRound(Player[] players){
    Character[] selectedCharacters = new Character[players.length];

    for (int playerIndex = 0; playerIndex < players.length; playerIndex++){
      Player player = players[playerIndex];
      printPlayerBanner(player);
      System.out.println("Select a character to use for this round.");

      String[] characterNames = player.getCharacterNames();

      Character selectedCharacter = player.getCharacter(showUnindexedMenu(characterNames));
      selectedCharacters[playerIndex] = selectedCharacter;

      debugMessage("Player choose " + selectedCharacter);
    }

    Character.RoundState[] characterStates = new Character.RoundState[selectedCharacters.length];

    for (int characterIndex = 0; characterIndex < selectedCharacters.length; characterIndex++){
      characterStates[characterIndex] = selectedCharacters[characterIndex].generateRoundState();
    }

    while (true){
      int winner = battleSubround(players, selectedCharacters, characterStates);

      if (winner != -1){
        return winner;
      }
    }
  }

  private static int battleSubround(Player[] players, Character[] characters, Character.RoundState[] states){
    printCurrentStates(states);

    Move[] moves = new Move[players.length];

    for (int playerIndex = 0; playerIndex < players.length; playerIndex++){
      printPlayerBanner(players[playerIndex]);

      System.out.println("Select a move to use.");

      String[] moveNames = characters[playerIndex].getMovesetNames();

      moves[playerIndex] = characters[playerIndex].getMove(showUnindexedMenu(moveNames));
    }

    System.out.println("\nX - X - Attacking! - X - X\n");

    int[] moveSpeedRanking = rankMoveSpeed(moves);

    for (int rankedIndex: moveSpeedRanking){
      int rawDamage = characters[rankedIndex].getStatTrueValue("Base Power")
        + moves[rankedIndex].getStatTrueValue("Move Power");
      int trueDamage = characters[1 - rankedIndex].calculateTrueDamage(rawDamage);
      
      System.out.printf("%s uses %s, it does %d damage!\n",
          characters[rankedIndex].getName(),
          moves[rankedIndex].getName(),
          trueDamage
          );

      states[1 - rankedIndex].takeDamage(trueDamage);

      int winner = determineWinner(states);

      if (winner != -1){
        return winner;
      }
    }

    // No winner yet, keep fighting.
    return -1;
  }

  private static void printCurrentStates(Character.RoundState[] states){
    System.out.println("\n<3 - - - - - - - - <3");
    for (Character.RoundState state: states){
      System.out.printf("%s: %d HP\n", state.getName(), state.getHitPoints());
      System.out.println("-- - - - - - - - - --");
    }
  }

  private static int[] rankMoveSpeed(Move[] moves){
    //Lazy solution, could update later for multiple players
    if (moves[1].getStatTrueValue("Move Speed") > moves[0].getStatTrueValue("Move Speed") ||
        moves[1].getStatTrueValue("Move Speed") == moves[0].getStatTrueValue("Move Speed") &&
        (int)(2 * Math.random()) == 1){
      return new int[] {1, 0};
        }
    else{
      return new int[] {0, 1};
    }
  }

  private static int determineWinner(Character.RoundState[] states){
    for (int stateIndex = 0; stateIndex < states.length; stateIndex++){
      if (states[stateIndex].isDefeated()){
        return 1 - stateIndex;
      }
    }
    
    return -1;
  }

  private static Player determineTournamentWinner(Player[] players, int[] scores){
    int maxScore = -1;
    int maxScoreIndex = 0;

    for (int scoreIndex = 0; scoreIndex < scores.length; scoreIndex++){
      if (scores[scoreIndex] > maxScore){
        maxScore = scores[scoreIndex];
        maxScoreIndex = scoreIndex;
      }
    }

    return players[maxScoreIndex];
  }

  private static void printTournamentResults(Player[] players, int[] scores, Player winningPlayer){
    System.out.printf("\n"
        + "# - # - # - # - # - # - # - # - # - # - # - # - \n"
        + "# - # - ...AND THE RESULTS ARE IN...  - # - # - \n"
        + "# - # - # - # - # - # - # - # - # - # - # - # - \n"
        + "# - # - # - # FINAL SCORES: # - # - # - # - # - \n");
    for (int playerIndex = 0; playerIndex < players.length; playerIndex++){
      System.out.printf(
          "With a final score of %d... %s!\n",
          scores[playerIndex], players[playerIndex].getName()
          );
    }
    System.out.printf(
          "# - # - # - # - # - # - # - # - # - # - # - # - \n"
        + "AND THE WINNER IS... %s!!\n"
        + "# - # - # - # - # - # - # - # - # - # - # - # - \n",
        winningPlayer.getName()
        );

    Player losingPlayer = (winningPlayer == players[0]) ? players[1] : players[0];

    int[] previousScores = new int[] {
      LeaderboardEditor.getPlayerScore(winningPlayer.getName()),
      LeaderboardEditor.getPlayerScore(losingPlayer.getName()),
    };
    int[] trophyChange = LeaderboardEditor.recordTournamentResults(
        winningPlayer.getName(), losingPlayer.getName());

    System.out.printf(
        "\n%s was awarded %d trophies, and %s was deducted %d."
        + "\n%s %d -> %d"
        + "\n%s %d -> %d\n",
        winningPlayer.getName(), trophyChange[0],
        losingPlayer.getName(), trophyChange[1],

        winningPlayer.getName(), previousScores[0], previousScores[0] + trophyChange[0],
        losingPlayer.getName(), previousScores[1], previousScores[1] - trophyChange[1]
        );
  }
}
