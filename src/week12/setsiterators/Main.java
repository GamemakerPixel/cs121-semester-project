package week12.setsiterators;



public class Main{
  public static void main(String[] args) {
    MovieCollectionSet.initialize();
    
    MovieCollectionSet set = MovieCollectionSet.getInstance();

    set.addMovies();
    set.displayMovies();
  }
}
