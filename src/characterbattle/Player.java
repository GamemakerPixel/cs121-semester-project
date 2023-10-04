package characterbattle;



public class Player{
  public static final int MAXIMUM_NAME_LENGTH = 16;
  public static final int TEAM_SIZE = 2;

  //protected static int lastId = 0;
  //protected int id;
  protected String name;
  /*
  protected int totalRounds;
  protected int totalWonRounds;
  protected int totalTournaments;
  protected int totalWonTournaments;
  */

  protected Character[] team;

  //Name must be 16 characters or less
  public Player(String name){
    this.name = name;
    /*
    totalRounds = 0;
    totalWonRounds = 0;
    totalTournaments = 0;
    totalWonTournaments = 0;
    */
  }

  /*public Player(File file){

  }*/

  public void setTeam(Character[] team) throws IllegalArgumentException{
    if (team.length != TEAM_SIZE){
      throw new IllegalArgumentException(String.format(
            "Team size %d does not match required size (%d).",
            team.length, TEAM_SIZE
            ));
    }

    this.team = team;
  }

  public String getName(){
    return name;
  }

  public String[] getCharacterNames(){
    int trueTeamLength = 0;

    for (Character character: team){
      if (character != null){
        trueTeamLength++;
      }
    }

    String[] characterNames = new String[trueTeamLength];

    int trueCharacterIndex = 0;
    for (Character character: team){
      if (character != null){
        characterNames[trueCharacterIndex] = character.getName();
        trueCharacterIndex++;
      }
    }

    return characterNames;
  }

  public Character getCharacter(String characterName){
    for (Character character: team){
      if (character == null){
        continue;
      }
      if (character.getName().equals(characterName)){
        return character;
      }
    }

    return null;
  }

  public String toString(){
    return getName();
  }

  /*
  //Used to determine leaderboard ranking (Ex. 4/5 ~= 6/10 ~= 9/20)
  public double getRankableScore(){
    return Math.pow(totalRounds, (double) totalWonRounds / totalRounds);
  }

  public void incrementRounds(boolean isWon){
    totalRounds++;
    totalWonRounds += (isWon) ? 1 : 0;
  }

  public void incrementTournaments(boolean isWon){
    totalTournaments++;
    totalWonTournaments += (isWon) ? 1 : 0;
  }

  //TODO: Prevent Division by Zero
  public String getStatLine(){
    return String.format(
        "%19s %16s %16s\n",
        name,
        String.format("%d%% (%d / %d)", 
          getPercentage(totalWonRounds, totalRounds), totalWonRounds, totalRounds
          ),
        String.format("%d%% (%d / %d)", 
          getPercentage(totalWonTournaments, totalTournaments), totalWonTournaments, totalTournaments
          ),
        );
  }

  private int getPercentage(int dividend, int divisor){
    return (int) (100.0 * ((double) dividend / divisor));
  }
  */
}
