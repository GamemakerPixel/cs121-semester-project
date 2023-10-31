package week10.inheritance;



public class Movie{
  private String title;
  private String genre;
  private int runtimeSeconds;

  public Movie(String title, String genre, int runtimeSeconds){
    this.title = title;
    this.genre = genre;
    this.runtimeSeconds = runtimeSeconds;
  }

  public void setTitle(String title){
    this.title = title;
  }

  public void setGenre(String genre){
    this.genre = genre;
  }

  public void setRuntimeSeconds(int runtimeSeconds){
    this.runtimeSeconds = runtimeSeconds;
  }

  public String getTitle(){
    return title;
  }

  public String getGenre(){
    return genre;
  }

  public int getRuntimeSeconds(){
    return runtimeSeconds;
  }

  @Override
  public String toString(){
    return String.format(
        "Movie {\n" +
        "\ttitle: \"%s\"\n" +
        "\tgenre: \"%s\"\n" +
        "\truntimeSeconds: %d\n" +
        "}",
        title,
        genre,
        runtimeSeconds
        );
  }
}
