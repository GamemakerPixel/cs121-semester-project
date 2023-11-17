package week13.hashmaps;

import java.util.HashMap;

public class FancyMap{
  private HashMap<String, Integer> fancyPoints;

  public FancyMap(){
    fancyPoints = new HashMap<String, Integer>();
  }

  public void addFancyEntree(String name, int points){
    fancyPoints.put(name, points);
  }

  public void removeFancyEntree(String name){
    fancyPoints.remove(name);
  }

  public int getFancyPoints(String name){
    return fancyPoints.get(name);
  }

  public void displayEntree(String name){
    if (!fancyPoints.containsKey(name)){ return; }
    System.out.printf("%s: %d\n", name, fancyPoints.get(name));
  }

  public void displayMap(){
    fancyPoints.forEach((key, value) -> {
      System.out.printf("%s: %d\n", key, value);
    });
  }
}
