package week13.genericsactivity; 

import java.util.ArrayList;
import java.util.Arrays;

public class Main{
  public static void main(String[] args) {
    ArrayList<Integer> integerList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
    ArrayList<Double> doubleList = new ArrayList<>(Arrays.asList(1.1, 2.2, 3.3, 4.4, 5.5));
    ArrayList<Character> characterList = new ArrayList<>(Arrays.asList('a', 'b', 'c', 'd', 'e'));
    ArrayList<String> stringList = new ArrayList<>(Arrays.asList("alpha", "beta", "gamma", "epsilon"));
    
    // Again I don't know why we'd use an object for this method, sooo
    
    System.out.println("Integer: ");
    GenericMethods.printArrayList(integerList);

    System.out.println("Double: ");
    GenericMethods.printArrayList(doubleList);

    System.out.println("Character: ");
    GenericMethods.printArrayList(characterList);

    System.out.println("String: ");
    GenericMethods.printArrayList(stringList);



  }
}
