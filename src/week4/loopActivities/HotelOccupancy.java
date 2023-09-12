package week4.loopActivities;

import java.util.Scanner;

public class HotelOccupancy {

    private static final Scanner scanner = new Scanner(System.in);

    private static int totalRooms = 0;
    private static int totalOccupiedRooms = 0;

    public static void main(String[] args) {
        System.out.println("How many floors are there?");

        int floors = Integer.parseInt(scanner.nextLine());

        for (int floorIndex = 0; floorIndex < floors; floorIndex++){
            promptFloorOccupancy(floorIndex);
        }

        printSummary(floors);
    }

    private static void promptFloorOccupancy(int floorIndex){
        System.out.println("How many rooms are on floor " + (floorIndex + 1) + "?");
        int rooms = Integer.parseInt(scanner.nextLine());
        System.out.println("How many rooms are occupied on floor " + (floorIndex + 1) + "?");
        int occupiedRooms = Integer.parseInt(scanner.nextLine());

        if (occupiedRooms > rooms){
            System.out.println("Warning: Impossible number of occupied rooms on floor " + (floorIndex + 1));
        }

        totalRooms += rooms;
        totalOccupiedRooms += occupiedRooms;
    }

    private static void printSummary(int floors){
        System.out.println("Summary:");

        System.out.println("Floors: " + floors);
        System.out.println("Total Rooms: " + totalRooms);
        System.out.println("Total Occupied Rooms: " + totalOccupiedRooms);
        System.out.println("Total Vacant Rooms: " + (totalRooms-totalOccupiedRooms));
        double occupancyRate = ((double) totalOccupiedRooms / totalRooms);
        System.out.printf("Occupancy Rate: %.2f (%.0f%%)", occupancyRate, occupancyRate * 100);
    }
}
