package characterbattle;



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

  public void setMoveset(Move[] moveset) throws IllegalArgumentException{
    if (moveset.length != MOVESET_SIZE){
      throw new IllegalArgumentException(String.format(
            "Moveset size %d incorrect for character %s.",
            moveset.length, name
            ));
    }

    this.moveset = moveset;
  }

} 
