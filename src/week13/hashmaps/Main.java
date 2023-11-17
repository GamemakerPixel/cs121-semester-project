package week13.hashmaps;



public class Main{
  public static void main(String[] args) {
    FancyMap fancyMap = new FancyMap();

    fancyMap.addFancyEntree("Turtle", 15);
    fancyMap.addFancyEntree("Bert", 20);
    fancyMap.addFancyEntree("Snowman", 10);

    fancyMap.displayMap();

    fancyMap.removeFancyEntree("Snowman");

    fancyMap.displayMap();
  }
}
