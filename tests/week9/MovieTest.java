package week9;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class MovieTest{
  public static Movie instanceOne;
  public static Movie instanceTwo;

  @BeforeEach
  public void setUp(){
    instanceOne = new Movie("Turtle Land", "A movie about turtles", "Fantasy", new int[]{
      13 * 60,
      16 * 60,
    });
    instanceTwo = new Movie("Bert the Turt", "Follow adventurer Bert as he explores Turtle Pond",
        "Adventure",
        new int[] {
          9 * 60,
          11 * 60,
        });
  }

  @Test
  public void instanceCountIsTwo(){
    assertEquals(Movie.getInstanceCount(), 2);
  }
}
