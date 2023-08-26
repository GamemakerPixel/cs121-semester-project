package weekTwo;

import java.util.Scanner;

public class StringMethods {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("What's your favorite animal?");
        String favoriteAnimal = scanner.nextLine();

        if (favoriteAnimal.equals("Firefly")){
            System.out.println("That's mine too!");
        }
        else if (favoriteAnimal.equalsIgnoreCase("Turtle")){
            System.out.println("That's my best friend's favorite!");
        }
        else if (favoriteAnimal.isEmpty()){
            System.out.println("That's boring.");
        }
        else if (favoriteAnimal.charAt(0) == 'F') {
            System.out.println("The first letter matches my favorite animal.");
        }
        else{
            System.out.println("That's cool I suppose.");
            System.out.printf("Here is some info about your input: \n" +
                    "Uppercase: %s \n" +
                    "Lowercase: %s \n" +
                    "Number of characters: %d \n" +
                    "Concatenated with itself: %s \n",
                    favoriteAnimal.toUpperCase(),
                    favoriteAnimal.toLowerCase(),
                    favoriteAnimal.length(),
                    favoriteAnimal + favoriteAnimal);
        }
    }
}
