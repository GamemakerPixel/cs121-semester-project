package week10;

import java.util.ArrayList;
import java.util.List;

public class StringArrayList{
  public enum PrintMode {
    FOR,
    FOR_EACH,
    TO_STRING,
  };

  private ArrayList<String> stringList;

  public StringArrayList(String[] initialElements){
    this.stringList = new ArrayList<String>(List.of(initialElements));
  }

  public void addString(String string){
    stringList.add(string);
  }

  public void addString(String string, int index){
    if (index >= stringList.size()){ return; }

    stringList.add(index, string);
  }

  public void removeString(int index){
    stringList.remove(index);
  }

  public int removeString(String string){
    int index = stringList.indexOf(string);
    stringList.remove(string);
    return index;
  }

  public int getSize(){
    return stringList.size();
  }

  public String getElement(int index){
    return stringList.get(index);
  }

  public void printList(PrintMode printMode){
    switch (printMode){
      case FOR:
        System.out.println("[");
        for (int index = 0; index < stringList.size(); index++){
          System.out.printf("%s,\n", stringList.get(index));
        }
        System.out.println("]");
        break;
      case FOR_EACH:
        System.out.println("[");
        for (String string: stringList){
          System.out.printf("%s,\n", string);
        }
        System.out.println("]");
        break;
      case TO_STRING:
        System.out.println(stringList);
    }
  }
}
