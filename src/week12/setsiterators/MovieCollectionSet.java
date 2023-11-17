package week12.setsiterators;

import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;

public class MovieCollectionSet{
  private static MovieCollectionSet instance; // Decided to make it a singleton for fun

  private static final Scanner scanner = new Scanner(System.in);

  private HashSet<String> moviesSet;

  private MovieCollectionSet(){
    moviesSet = new HashSet<String>();
  }

  public static void initialize(){
    if (instance != null){ return; }
    instance = new MovieCollectionSet();
  }

  public static MovieCollectionSet getInstance(){
    return instance;
  }

  
  public void addMovies(){
    while (true){
      System.out.println("Enter a movie title to add (or \"0\" to quit): ");
      String title = scanner.nextLine();

      if (title.equals("0")){ return; }

      moviesSet.add(title);
    }
  }

  public void displayMovies(){
    Iterator<String> iterator = moviesSet.iterator();

    while (iterator.hasNext()){
      System.out.println(iterator.next());
    }

    /* Alternatively:
     * for (String title: moviesSet){
     *    System.out.println(title);
     * }
     */
  }

  
}
