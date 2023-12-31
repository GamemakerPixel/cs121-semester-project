package week5.fileactivity;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.IOException;
import java.io.FileWriter;

public class FileWrite{

  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {
    ArrayList<String> headers = promptForHeaders();

    ArrayList<ArrayList<String>> data = promptForData(headers);

    writeToFile(headers, data);

    System.out.println("Running writer");
  }

  protected static ArrayList<String> promptForHeaders(){
    System.out.println("Enter a list of headers, then \"exit\" to move on.");

    return readInputList();
  }

  protected static ArrayList<ArrayList<String>> promptForData(ArrayList<String> headers){
    ArrayList<ArrayList<String>> data = new ArrayList<>();

    for (String header : headers){
      data.add(promptHeaderData(header));
    }

    return data;
  }

  protected static ArrayList<String> promptHeaderData(String header){
    System.out.printf("Enter data for the %s row, and \"exit\" to move on.\n", header);

    return readInputList();


  }

  protected static ArrayList<String> readInputList(){
    ArrayList<String> list = new ArrayList<>();

    while(true){
      String element = scanner.nextLine();

      if (element.equals("exit")){
        break;
      }

      list.add(element); 
    }
  
    return list;
  }  

  protected static void writeToFile(ArrayList<String> headers, ArrayList<ArrayList<String>> data){
    try{
      FileWriter writer = new FileWriter("written-file.txt");

      writer.write(formatData(headers, data));
      writer.close();
    }
    catch (IOException exception){
      exception.printStackTrace();
    }
  }

  protected static String formatData(ArrayList<String> headers, ArrayList<ArrayList<String>> data){
    String formattedData = "";

    for (int headerIndex = 0; headerIndex < headers.size(); headerIndex++){
      formattedData += headers.get(headerIndex) + ":";

      for (String dataElement: data.get(headerIndex)){
        formattedData += dataElement + ",";
      }

      formattedData += "\n";
    }

    return formattedData;
  }

}
