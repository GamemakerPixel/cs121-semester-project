package week13.genericsactivity;

import java.util.ArrayList;

public class GenericMethods{
  public static <E> void printArrayList(ArrayList<E> list){
    for (E element: list){
      System.out.printf("%s, ", element);
    }
    System.out.print("\b\b\n");
  }
}
