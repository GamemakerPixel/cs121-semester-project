package week11.sortingactivity;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class Sorting{
  public static final int ARRAY_LENGTH = 5;

  private static final Scanner SCANNER = new Scanner(System.in);

  public static int[] getArray(){
    int[] array = new int[ARRAY_LENGTH];

    for (int i = 0; i < array.length; i++){
      System.out.println("Enter a number:");
      array[i] = Integer.parseInt(SCANNER.nextLine());
    }

    return array;
  }

  public static int[] sortArray(int[] array){
    ArrayList<Integer> list = new ArrayList<>(array.length);

    for (int element: array){ list.add(element); }

    list = sortList(list);
    return list.toArray(new int[0]);
  }

  public static ArrayList<Integer> sortList(ArrayList<Integer> list){
    // Base case, already sorted.
    if (list.size() <= 1){
      return list;
    }

    int splitIndex = list.size() / 2;

    ArrayList<Integer> leftList = list.subList(0, splitIndex);
    ArrayList<Integer> rightList = list.subList(splitIndex, list.size());

    ArrayList<Integer> sortedList = mergeLists(sortList(leftList), sortList(rightList));

    return sortedList;
  }

  private static ArrayList<Integer> mergeLists(ArrayList<Integer> listA, ArrayList<Integer> listB){
    ArrayList<Integer> mergedList = new ArrayList<>(listA.size() + listB.size());

    while(listA.size() > 0 && listB.size() > 0){
      shrinkingList = (listA.get(0) <= listB.get(0)) ? listA : listB;

      mergedList.add(shrinkingList.remove(0));
    }

    // One of these will be empty
    mergedList.addAll(listA);
    mergedList.addAll(listB);

    return mergedList;
  }
}
