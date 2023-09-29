package week6.overloaded;



public class PieTest{
  public static void main(String[] args) {
    Pie[] pies = {
      new Pie("Cherry", 4, 72.0),
      new Pie("Peach"),
      new Pie(),
    };

    // NOTE: Getters called inside class
    // They are publicly accessable so they could be demonstrated here,
    // but for convience I left them out. (They are still tested by toString().)
    System.out.println("Initialized States:");
    System.out.println(pies[0]);
    System.out.println(pies[1]);
    System.out.println(pies[2]);

    System.out.println("Modifying Pie 1");
    pies[0].setFlavor("Plum");
    pies[0].setSlices(10);
    pies[0].setTemperature(400.0); //NOTE: also sets isBaked accordingly
    System.out.println(pies[0]);

    System.out.println("Unbaking Pie 1");
    pies[0].setBaked(false);
    System.out.println(pies[0]);

    System.out.println("Eating pie slices");
    pies[0].eatSlice(9);
    System.out.println(pies[0]);
    pies[0].eatSlice();
    System.out.println(pies[0]);
    System.out.printf("Can we eat another slice? %b \n", pies[0].eatSlice());
  }
}
