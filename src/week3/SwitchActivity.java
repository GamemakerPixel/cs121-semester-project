package week3;

import java.util.Scanner;

public class SwitchActivity {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int area; //km^2
        int volume; //km^3
        int averageDepth; //m
        double averageTemperature;

        System.out.print("Enter the name of an ocean: ");

        String oceanName = scanner.nextLine().toLowerCase();

        switch (scanner.nextLine().toLowerCase()){
            case "arctic":
                area = 11588000;
                volume = 18750000;
                averageDepth = 1205;
                break;
            case "pacific":
                area = 168723000;
                volume = 669880000;
                averageDepth = 4080;
                break;
            case "north atlantic":
                break;
            case "south atlantic":
                break;
            case "indian":
                break;
            case "southern":
                break;
            default:
                System.out.println("There is no information avaliable for the " + oceanName + " ocean.");
                return;
        }
    }
}
