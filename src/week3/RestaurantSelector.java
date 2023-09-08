package week3;

import java.util.Scanner;

public class RestaurantSelector {

    private static final Scanner scanner = new Scanner(System.in);
    private static final String[] restrictions = {"a vegetarian", "a vegan", "gluten-free"};
    private static final String[] restaurants = {
            "Joe's Gormet Burgers",
            "Main Street Pizza Company",
            "Corner Caf\u00e9",
            "Mama's Fine Italian",
            "The Chef's Kitchen",
    };

    private static final boolean[][] restaurantRestrictions = {
            {false, false, false},
            {true, false, true},
            {true, true, true},
            {true, false, false},
            {true, true, true},
    };

    public static void main(String[] args) {
        boolean[] responses = {
                promptForDietaryRestriction(restrictions[0]),
                promptForDietaryRestriction(restrictions[1]),
                promptForDietaryRestriction(restrictions[2]),
        };

        System.out.println("Your restaurant options are:");

        restaurantLoop:
        for (int restaurantIndex = 0; restaurantIndex < restaurants.length; restaurantIndex++){
            boolean[] restaurantSpecificRestrictions = restaurantRestrictions[restaurantIndex];

            for (int restrictionIndex = 0; restrictionIndex < restaurantSpecificRestrictions.length; restrictionIndex++){
                if (responses[restrictionIndex] && !restaurantSpecificRestrictions[restrictionIndex]){
                    continue restaurantLoop;
                }
            }

            System.out.println(restaurants[restaurantIndex]);
        }

    }

    private static boolean promptForDietaryRestriction(String restriction){
        System.out.print("Is anyone in your party " + restriction + "? ");

        while (true){
            System.out.println("(Yes/No)");
            String response = scanner.nextLine();

            if (response.equals("Yes")){
                return true;
            }
            else if (response.equals("No")){
                return false;
            }
            //else case is to prompt again
        }
    }
}
