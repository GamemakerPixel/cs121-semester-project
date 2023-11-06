package week12;

import java.util.ArrayList;

public class BinarySearchDemo{
  public static int binarySearch(ArrayList<Integer> sortedList, int key){
    return binarySearch(sortedList, 0, sortedList.size() - 1, key);
  }

  // Goes from [start, end]
  private static int binarySearch(ArrayList<Integer> sortedList, int start, int end, int key){
    if (end < start){

    }
  }

  // {1, 3, 4, 5, 7, 8} s:7 m:3
  // {7, 8} s:7 m:0
  // 
  // {1, 3, 4, 5, 7, 8} s:6 m:3
  // {7, 8} s:6 m:0
  // {} end is one below start
}

