package week3;

import java.util.Scanner;

public class SwitchActivity {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int area; //km^2
        int volume; //km^3
        int averageDepth; //m

        System.out.print("Enter the name of an ocean: ");

        String oceanName = scanner.nextLine().toLowerCase();

        switch (oceanName){
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
            case "atlantic":
                area = 85133000;
                volume = 310410900;
                averageDepth = 3646;
                break;
            case "indian":
                area = 70560000;
                volume = 264000000;
                averageDepth = 3741;
                break;
            case "southern":
                area = 21960000;
                volume = 71800000;
                averageDepth = 3270;
                break;
            default:
                System.out.println("There is no information avaliable for the " + oceanName + " ocean.");
                return;
        }

        System.out.printf("The %s ocean has an area of %d square meters, a volume of %d cubic meters, and " +
                "an average depth of %d meters.", oceanName, area, volume, averageDepth);
    }
}
