package week10.interfaces;



public class NameableTester{
  public static void main(String[] args) {
    Nameable user = new User("Bert", "Turt1es");
    Nameable movie = new Movie("When Turtles Fly");

    System.out.println(user.getName());
    System.out.println(movie.getName());
  }
}
