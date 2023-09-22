package fileActivity.week5;

import java.util.Scanner;
import java.util.ArrayList;

public class FileWrite{

  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {
    ArrayList<String> headers = promptForHeaders();

    ArrayList<ArrayList<Object>> data = promptForData(headers);

    System.out.println(data);
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
    ArrayList<ArrayList<Object>> data = new ArrayList<>();

    for (String header : headers){
      data.add(promptDatatype(header));
    }

    return data;
  }

  private static ArrayList<Object> promptDatatype(String header){
    
    while (true){
      System.out.printf("Enter the datatype for the %s column. (i:int, d:double, s:String)", header);

      String input = scanner.nextLine();

      switch(input){
        case "i":
          return new ArrayList<Integer>();
          break;
        case "d":
          return new ArrayList<Double>();
          break;
        case "s":
          return new ArrayList<String>();
        default:
          continue;
      }
    }

    
  }
}
