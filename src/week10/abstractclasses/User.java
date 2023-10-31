package week10.abstractclasses;



public abstract class User{
  private String email;
  private String password; //Don't do this
  private String firstName;
  private String lastName;

  public User(String firstName, String lastName, String email, String password){
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
  }

  public abstract boolean authenticate(String email, String password);

  protected void setEmail(String email){
    this.email = email;
  }

  protected void setPassword(String password){
    this.password = password;
  }

  public String getEmail(){
    return email;
  }

  protected String getPassword(){
    return password;
  }


}
