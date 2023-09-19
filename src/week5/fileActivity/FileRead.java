package week5.fileActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.*;

public class FileRead {

    private static final String filePath = "courses.txt";
    private static final Pattern pattern = Pattern.compile(".*\t*");

    public static void main(String[] args) {
        try{
            File coursesFile = new File(filePath);
            Scanner coursesScanner = new Scanner(coursesFile);

            while (coursesScanner.hasNext(pattern)){

                System.out.println(coursesScanner.next(pattern));
            }
        }
        catch (FileNotFoundException exception){
            exception.printStackTrace();
        }
    }
}
