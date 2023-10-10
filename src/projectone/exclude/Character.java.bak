package characterbattle;



public class Character{
  public static final int STAT_POINTS = 15;
  public static final String[] STAT_NAMES = {
    "Hit Points",
    "Base Damage",
  };
  public static final int MAXIMUM_NAME_LENGTH = 16;
  public static final int MOVESET_SIZE = 2;

  public static final int MINIMUM_HIT_POINTS = 25;
  public static final int MINIMUM_BASE_DAMAGE = 10;

  public static final int HIT_POINTS_PER_STAT = 5;
  public static final int BASE_DAMAGE_PER_STAT = 3;

  private String name;
  private int hitPoints;
  private int baseDamage;

  private Move[] moveset;
  
  //private int defense;
  //private int criticalStrikeChance; //out of 100
  
  public Character(String name, int statHitPoints, int statBaseDamage) throws IllegalArgumentException{
    if (!validateStats(statHitPoints, statBaseDamage)){
      throw new IllegalArgumentException(String.format(
            "Stats for %s outside allowed range. (hp:%d, bd:%d)",
            name, statHitPoints, statBaseDamage
            ));
    }

    this.name = name;
    this.hitPoints = computeHitPoints(statHitPoints);
    this.baseDamage = computeBaseDamage(statBaseDamage);
  }

  public String getName(){
    return name;
  }

  public int getHitPoints(){
    return hitPoints;
  }

  public int getBaseDamage(){
    return baseDamage;
  }

  public void setMoveset(Move[] moveset) throws IllegalArgumentException{
    if (moveset.length != MOVESET_SIZE){
      throw new IllegalArgumentException(String.format(
            "Moveset size %d does not match required size (%d).",
            moveset.length, MOVESET_SIZE
            ));
    }

    this.moveset = moveset;
  }

  private static boolean validateStats(int statHitPoints, int statBaseDamage){
    if (statHitPoints < 0 || statBaseDamage < 0){
      return false;
    }
    if ((statHitPoints + statBaseDamage) > STAT_POINTS){
      return false;
    }
    return true;
  }

  //These methods allow the use of more complicated functions like logarithms to be used neatly.
  public static int computeHitPoints(int statHitPoints){
    return statHitPoints * HIT_POINTS_PER_STAT + MINIMUM_HIT_POINTS;
  }

  public static int computeBaseDamage(int statBaseDamage){
    return statBaseDamage * BASE_DAMAGE_PER_STAT + MINIMUM_BASE_DAMAGE;
  }
}
