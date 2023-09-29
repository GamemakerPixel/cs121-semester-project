package week6.testactivity;



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
    this.isBaked = false;
  }

  public Pie(String flavor){
    this.flavor = flavor;
    this.slices = 8;
    this.temperature = 100.0;
    this.isBaked = (temperature >= BAKE_TEMPERATURE);
  }

  public Pie(String flavor, int slices, double temperature){
    this.flavor = flavor;
    this.slices = slices;
    this.temperature = temperature;
    this.isBaked = (temperature >= BAKE_TEMPERATURE);
  }

  public void setFlavor(String flavor){
    this.flavor = flavor;
  }

  public void setSlices(int slices){
    this.slices = Math.max(0, slices);
  }

  public void setTemperature(double temperature){
    this.temperature = temperature;
    setBaked();
  }

  public void setBaked(boolean isBaked){
    this.isBaked = isBaked;
  }

  public void setBaked(){
    //Pies don't unbake
    this.isBaked = (temperature >= BAKE_TEMPERATURE) || isBaked;
  }

  public String getFlavor(){
    return flavor;
  }

  public int getSlices(){
    return slices;
  }

  public double getTemperature(){
    return temperature;
  }

  public boolean getBaked(){
    return isBaked;
  }


  public boolean eatSlice(int slicesEaten){
    if (slices - slicesEaten < 0){ return false; }

    slices -= slicesEaten;
    return true;
  }

  public boolean eatSlice(){
    return eatSlice(1);
  }

  public String toString(){
    return String.format(
        "%s pie, with %d slices, at a temperature of %.2f and isBaked is %b.",
        getFlavor(), getSlices(), getTemperature(), getBaked()
        );
    //Normally, I would not call getters from inside the class
  }
}
