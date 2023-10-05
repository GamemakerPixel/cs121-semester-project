package characterbattle;

import java.util.HashMap;
import java.util.Set;
import java.util.Collection;

public class StatObject{
  private int spendableStatPoints;
  private String[] statNames;

  private HashMap<String, Integer> minimumTrueValues;
  private HashMap<String, Integer> trueValuePerPoint;

  private HashMap<String, Integer> pointValues;
  
  protected StatObject(
      int spendableStatPoints, 
      String[] statNames,
      int[] minimumTrueValuesArray,
      int[] trueValuePerPointArray
  ){

    this.spendableStatPoints = spendableStatPoints;
    this.statNames = statNames;
    minimumTrueValues = new HashMap<String, Integer>();
    trueValuePerPoint = new HashMap<String, Integer>();

    for (int statNameIndex = 0; statNameIndex < statNames.length; statNameIndex++){
      minimumTrueValues.put(statNames[statNameIndex],
          minimumTrueValuesArray[statNameIndex]);
      trueValuePerPoint.put(statNames[statNameIndex],
          trueValuePerPointArray[statNameIndex]);
    }

    pointValues = new HashMap<String, Integer>();

    for (String statName: statNames){
      pointValues.put(statName, 0);
    }
  }

  public void setPointValues(HashMap<String, Integer> newPointValues) throws IllegalArgumentException{
    if (!validatePointValues(newPointValues)){
      throw new IllegalArgumentException("Invalid point values.");
    }

    pointValues.putAll(newPointValues);
  }

  protected boolean validatePointValues(HashMap<String, Integer> newPointValues){
    //Validate stat names
    Set<String> newStatNames = newPointValues.keySet();

    if (newStatNames.size() != statNames.length){
      return false;
    }

newStatNameLoop:
    for (String newStatName: newStatNames){
      for (String validStatName: statNames){
        if (newStatName.equals(validStatName)){
          continue newStatNameLoop;
        }
      }

      return false;
    }

    //Validate stat values
    Collection<Integer> newStatValues = newPointValues.values();

    int statSum = 0;

    for (int newStatValue: newStatValues){
      if (newStatValue < 0){
        return false;
      }

      statSum += newStatValue;
    }

    if (statSum > spendableStatPoints){
      return false;
    }

    return true;
  }

  public int getStatPointValue(String statName) throws IllegalArgumentException{
    Integer statPointValue = pointValues.get(statName);

    if (statPointValue == null){
      throw new IllegalArgumentException(String.format(
            "Stat \"%s\" does not exist.",
            statName
            ));
    }

    return statPointValue;
  }

  public int getStatTrueValue(String statName) throws IllegalArgumentException{
    int statPointValue = getStatPointValue(statName);

    return getStatTheoreticalValue(statName, statPointValue);
  }

  public int getStatTheoreticalValue(String statName, int points) throws IllegalArgumentException{
    if (minimumTrueValues.get(statName) == null){
      throw new IllegalArgumentException(String.format(
            "Stat \"%s\" does not exist.",
            statName
            ));
    }

    int statMinimumValue = minimumTrueValues.get(statName);
    int statPerPointValue = trueValuePerPoint.get(statName);

    return statMinimumValue + statPerPointValue * points;

  }

  public int getSpendableStatPoints(){
    return spendableStatPoints;
  }

  public String[] getStatNames(){
    return statNames;
  }

  protected HashMap<String, Integer> getPointValues(){
    return pointValues;
  }

}
