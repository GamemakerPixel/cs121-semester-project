package week13.linkedlistactivity;



public class Main{
  public static void main(String[] args) {
    UserBooking bookingA = new UserBooking("Turtling", "Flying Turtles Everywhere",
        60*60*1, "Adventure", "A movie about three turtles exploring everywhere.",
        "Scute Showings");
    UserBooking bookingB = new UserBooking("Bert", "Turtles in the Sticks",
        60*60*1, "Reality", "What happens when three turtles are placed on an island, " +
        "without anything to aid their survival?",
        "Freshwater Theater");

    bookingA.addTheater("Shimmering Shells");
    bookingA.addTheater("Sandy Showings");
    bookingA.addTheater("Coral Theater");

    bookingB.addTheater("Saltwater Showings");
    bookingB.addTheater("Crystal Cove Theater");
    bookingB.addTheater(":")
  }
}
