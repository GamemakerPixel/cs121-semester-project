package week6.overloaded;



public class Pie{
  public static final double BAKE_TEMPERATURE = 350.0;

  private String flavor;
  private int slices;
  private double temperature;
  private boolean isBaked;

  public Pie(){
    this.flavor = "Apple";
    this.slices = 8;
    this.temperature = 100.0;
    this.isBaked = true;
  }

  public Pie(String flavor){
    this.flavor = flavor;
    this.slices = 8;
    this.temperature = 100.0;
    this.isBaked = true;
  }

  public Pie(String flavor, int slices, double temperature){
    this.flavor = flavor;
    this.slices = slices;
    this.temperature = temperature;
    this.isBaked = (temperature >= BAKE_TEMPERATURE);
  }
}
