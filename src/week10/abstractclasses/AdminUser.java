package week10.abstractclasses;



public class AdminUser extends User{
  private int accessLevel;

  public AdminUser(String firstName, String lastName, String email, String password, int accessLevel){
    super(firstName, lastName, email, password);
    this.accessLevel = accessLevel;
  }

  public boolean authenticate(String email, String password){
    //Abyssmal idea don't do this
    return (email.equals(getEmail()) && password.equals(getPassword() + "123"));
  }

  public int getAccessLevel(){
    return accessLevel;
  }
}
