package projectone;



public class Move extends StatObject implements Nameable{
  public static final int SPENDABLE_STAT_POINTS = 10;

  public static final int MAXIMUM_NAME_LENGTH = 16;

  public static final String[] STAT_NAMES = {
    "Move Power",
    "Move Speed",
  };

  private static final int[] STAT_MINIMUMS = {
    5,
    0,
  };

  private static final int[] STAT_PER_POINT_VALUES = {
    5,
    1,
  };

  private String name;


  public Move(String name){
    super(SPENDABLE_STAT_POINTS, STAT_NAMES, STAT_MINIMUMS, STAT_PER_POINT_VALUES);

    if (name.length() > MAXIMUM_NAME_LENGTH){
      System.err.println("Warning: Move name too long.");
    }

    this.name = name;
  }

  public String getName(){
    return name;
  }

} 
