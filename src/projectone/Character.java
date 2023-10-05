package characterbattle;

import java.util.HashMap;

public class Character extends StatObject implements Nameable{
  public static final int SPENDABLE_STAT_POINTS = 15;

  public static final int MAXIMUM_NAME_LENGTH = 16;
  public static final int MOVESET_SIZE = 2;

  public static final String[] STAT_NAMES = {
    "Hit Points",
    "Base Power",
  };

  private static final int[] STAT_MINIMUMS = {
    25,
    10,
  };

  private static final int[] STAT_PER_POINT_VALUES = {
    5,
    3,
  };

  private String name;
  private Move[] moveset;

  public class RoundState{
    private int hitPoints;
    private String name;

    public RoundState(Character character){
      hitPoints = character.getStatTrueValue("Hit Points");
      name = character.getName();
    }

    public int takeDamage(int damage){
      hitPoints -= damage;
      if (hitPoints < 0){
        hitPoints = 0;
      }
      return hitPoints;
    }

    public int getHitPoints(){
      return hitPoints;
    }

    public boolean isDefeated(){
      return hitPoints == 0;
    }

    public String getName(){
      return name;
    }
  }


  public Character(String name){
    super(SPENDABLE_STAT_POINTS, STAT_NAMES, STAT_MINIMUMS, STAT_PER_POINT_VALUES);

    if (name.length() > MAXIMUM_NAME_LENGTH){
      System.err.println("Warning: Character name too long.");
    }

    this.name = name;
  }

  public String getName(){
    return name;
  }

  public Move getMove(String name){
    for (Move move: moveset){
      if (move == null){
        continue;
      }
      if (move.getName().equals(name)){
        return move;
      }
    }
    return null;
  }

  public RoundState generateRoundState(){
    return new RoundState(this);
  }

  public String[] getMovesetNames(){
    int trueMovesetSize = 0;

    for (Move move: moveset){
      if (move != null){
        trueMovesetSize++;
      }
    }

    String[] trueMovesetNames = new String[trueMovesetSize];
    
    int trueMoveIndex = 0;
    for (Move move: moveset){
      if (move != null){
        trueMovesetNames[trueMoveIndex] = move.getName();
        trueMoveIndex++;
      }
    }

    return trueMovesetNames;
  }

  public void setMoveset(Move[] moveset) throws IllegalArgumentException{
    if (moveset.length != MOVESET_SIZE){
      throw new IllegalArgumentException(String.format(
            "Moveset size %d incorrect for character %s.",
            moveset.length, name
            ));
    }

    this.moveset = moveset;
  }

  public String toString(){
    return getName();
  }

} 
