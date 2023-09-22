package fileActivity.week5;

import java.util.Scanner;
import java.util.ArrayList;

public class FileWrite{

  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {
    ArrayList<String> headers = promptForHeaders();

    promptForData(headers);
  }

  private static ArrayList<String> promptForHeaders(){
    ArrayList<String> headers = new ArrayList<>();

    System.out.println("Enter a list of headers, then \"exit\" to move on.");

    while(true){
      String header = scanner.nextLine();

      if (header.equals("exit")){
        break;
      }

      headers.add(header); 
    }
  
    return headers;
  }

  private static ArrayList<ArrayList<Object>> promptForData(ArrayList<String> headers){
    ArrayList<ArrayList<Object>> data = ArrayList<>();

    for (String header : headers){
      data.append(promptDatatype(header));
    }
  }

  private static ArrayList<Object> promptDatatype(String header){
    
    while (true){
      System.out.printf("Enter the datatype for the %s column. (i:int, d:double, s:String)");

      String input = TYPE HERE :)
    }

    
  }
}
