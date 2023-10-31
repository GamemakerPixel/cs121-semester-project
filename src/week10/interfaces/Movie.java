package week10.interfaces;



public class Movie implements Nameable{
  public String title;

  public Movie(String title){
    this.title = title;
  }

  public void setName(String name){
    this.title = name;
  }

  public String getName(){
    return title;
  }
}
