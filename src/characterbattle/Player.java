package characterbattle;



public class Player{
  //protected static int lastId = 0;
  //protected int id;
  protected String name;
  /*
  protected int totalRounds;
  protected int totalWonRounds;
  protected int totalTournaments;
  protected int totalWonTournaments;
  */

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

  public String getName(){
    return name;
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
