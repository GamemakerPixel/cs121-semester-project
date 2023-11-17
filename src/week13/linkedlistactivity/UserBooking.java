package week13.linkedlistactivity;

import java.util.LinkedList;

public class UserBooking{
  private String userName;
  private String movieTitle;
  private int runtime;
  private String genre;
  private String description;
  private LinkedList<String> theaterList;

  public UserBooking(String userName, String movieTitle,
      int runtime, String genre, String description, String theater)
  {
    this.userName = userName;
    this.movieTitle = movieTitle;
    this.runtime = runtime;
    this.genre = genre;
    this.description = description;
    theaterList = new LinkedList<String>();
    theaterList.add(theater);
  }

  public void addTheater(String theater){
    theaterList.add(theater);
  }

  public void removeTheater(String theater){
    theaterList.remove(theater);
  }

  public String getUserInfo(){
    return userName;
  }

  public void displayUserBooking{
    System.out.println("Booking:");
    for (String theater: theaterList){
      System.out.println(theater);
    }
  }
}
