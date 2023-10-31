package week9;



public class MovieBookingSimulation{
  public static void main(String[] args) {
    int[][] theater = new int[7][10];

    // {
    //    {title, type, seats left, ticket price}
    // }
    String[][] movieInformation = new String[][] {
      {"The Legend of Bert", "3D", "13", "$5.00"},
      {"Turtle Pond", "2D", "53", "$4.00"},
      {"Flying Turtles", "2D", "34", "$4.50"},
    };

    printTheater(theater);
    printMovieInformation(movieInformation);
    randomlyBookSeats(theater, 0.333);
    printTheater(theater);
  }

  private static void printTheater(int[][] theater){
    for (int[] row: theater){
      for (int seat: row){
        System.out.printf("%d ", seat);
      }
      System.out.printf("\n");
    }
  }

  private static void printMovieInformation(String[][] movieInformation){
    System.out.printf("%-20s%-6s%-6s%-6s\n",
        "Title", "Type", "Seats", "Price");

    for (String[] movie: movieInformation){
      System.out.printf("%-20s%-6s%-6s%-6s\n",
          movie[0], movie[1], movie[2], movie[3]);
    }
  }

  private static void randomlyBookSeats(int[][] theater, double probability){
    for (int rowIndex = 0; rowIndex < theater.length; rowIndex++){
      for (int columnIndex = 0; columnIndex < theater[rowIndex].length; columnIndex++){
        theater[rowIndex][columnIndex] = (Math.random() < probability) ? 1 : 0;
      }
    }
  }
}
