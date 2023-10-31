package week10.abstractclasses;



public class UserTester{
  public static void main(String[] args) {
    Customer customer = new Customer("Bert", "the Turt", "berttheturt@turtles.com", "Turt1es", 5);
    AdminUser adminUser = new AdminUser("Legendary", "Turt", "legendaryturt@movieapp.com", "Adm1nTurt", 99);

    System.out.println(customer.authenticate("berttheturt@turtles.com", "Turt1es"));
    System.out.println(adminUser.authenticate("legendaryturt@movieapp.com", "Adm1nTurt"));
    System.out.println(adminUser.authenticate("legendaryturt@movieapp.com", "Adm1nTurt123")); //Don't do this lol
  }
}
