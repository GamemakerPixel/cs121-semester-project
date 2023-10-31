package week10.inheritance;



public class TheaterMovie extends Movie{
  private String description;

  public TheaterMovie(String title, String genre, int runtimeSeconds, String description){
    super(title, genre, runtimeSeconds);
    this.description = description;
  }

  public void setDescription(String description){
    this.description = description;
  }

  public String getDescription(){
    return description;
  }

  public String toString(){
    String movieString = super.toString();

    movieString = movieString.substring(0, movieString.length() - 1); // remove }

    movieString += String.format(
        "\tdescription: %s\n" +
        "}",
        description
        );

    return movieString;
  }
}
