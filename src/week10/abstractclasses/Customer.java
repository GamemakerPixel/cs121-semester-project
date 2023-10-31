package week10.abstractclasses;



public class Customer extends User{
  private int loyaltyPoints;

  public Customer(String firstName, String lastName, String email, String password, int loyaltyPoints){
    super(firstName, lastName, email, password);
    this.loyaltyPoints = loyaltyPoints;
  }

  public boolean authenticate(String email, String password){
    return (email.equals(getEmail()) && password.equals(getPassword()));
  }

  public int addLoyaltyPoints(int points){
    loyaltyPoints += points;
    return loyaltyPoints;
  }
}
