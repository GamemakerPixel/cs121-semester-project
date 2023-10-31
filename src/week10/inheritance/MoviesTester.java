package week10.inheritance;



public class MoviesTester{
  public static void main(String[] args) {
    Movie movie = new Movie("When Turtles Fly", "Fantasy", 60*60*2);

    System.out.println(movie);

    TheaterMovie theaterMovie = new TheaterMovie("Magic Turtles", "Adventure",
        60*45*2, "A movie following the adventures of three turtles who discovered" +
        " magical crystals at the bottom of a lake.");

    System.out.println(theaterMovie);

    theaterMovie.setRuntimeSeconds(60*60);

    System.out.println(theaterMovie);

    SeatCategory seatCategory = new SeatCategory("Infinity", "Horror",
        60*60*3, 6.0);
    
    System.out.println(seatCategory);

    seatCategory.setSeatPrice(seatCategory.getSeatPrice() - 1.0);

    System.out.println(seatCategory);
  }
}
