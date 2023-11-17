package week12.binarysearch;

import java.util.ArrayList;

public class BinarySearchDemo{
  public static int binarySearch(ArrayList<Integer> sortedList, int key){
    return binarySearch(sortedList, 0, sortedList.size() - 1, key);
  }

  // Goes from [start, end]
  private static int binarySearch(ArrayList<Integer> sortedList,
      int start, int end, int key
      ){
    if (end < start){
      return -1;
    }

    int halfwayIndex = (start + end) / 2;
    int halfwayElement = sortedList.get(halfwayIndex);
    
    if (halfwayElement == key){
      return halfwayIndex;
    }
    else if (halfwayElement > key){
      return binarySearch(sortedList, start, halfwayElement - 1, key);
    }
    else{
      return binarySearch(sortedList, halfwayIndex + 1, end, key);
    }
  }

  public static ArrayList<Integer> quicksort(ArrayList<Integer> list){
    if (list.size() <= 1){
      return list;
    }

    int pivot = list.get(0);

    ArrayList<Integer> lesserList = new ArrayList<>();
    ArrayList<Integer> greaterList = new ArrayList<>();

    for (int elementIndex = 1; elementIndex < list.size(); elementIndex++){
      int element = list.get(elementIndex);
      if (element <= pivot){
        lesserList.add(element);
      }
      else{
        greaterList.add(element);
      }
    }

    ArrayList<Integer> sortedLesserList = quicksort(lesserList);
    ArrayList<Integer> sortedGreaterList = quicksort(greaterList);

    sortedLesserList.add(pivot);
    sortedLesserList.addAll(sortedGreaterList);

    return sortedLesserList;
  }
}

