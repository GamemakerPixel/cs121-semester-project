package week9;

public class Movie{
  private static int instanceCount = 0;

  private String title;
  private String description;
  private String genre;
  private int[] showingTimes;

  public Movie(String title, String description, String genre, int[] showingTimes){
    this.title = title;
    this.description = description;
    this.genre = genre;
    this.showingTimes = showingTimes;

    instanceCount++;
  }

  public static int getInstanceCount(){
    return instanceCount;
  }


}
