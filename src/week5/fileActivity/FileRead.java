package week5.fileActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileRead {
    public static void main(String[] args) {
        try{
            File coursesFile = new File("courses.txt");
            Scanner coursesScanner = new Scanner(coursesFile);
        }
        catch (FileNotFoundException exception){
            exception.printStackTrace();
        }
    }
}
