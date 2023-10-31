package week10.inheritance;



public class SeatCategory extends Movie{
  private double seatPrice;

  public SeatCategory(String title, String genre, int runtimeSeconds, double seatPrice){
    super(title, genre, runtimeSeconds);
    this.seatPrice = seatPrice;
  }

  public void setSeatPrice(double seatPrice){
    this.seatPrice = seatPrice;
  }

  public double getSeatPrice(){
    return seatPrice;
  }

  public String toString(){
    String movieString = super.toString();

    movieString = movieString.substring(0, movieString.length() - 1); // remove }

    movieString += String.format(
        "\tseatPrice: %.2f\n" +
        "}",
        seatPrice
        );

    return movieString;
  }
}
