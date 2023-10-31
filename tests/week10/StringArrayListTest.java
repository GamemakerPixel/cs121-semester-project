package week10;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class StringArrayListTest{
  private static final String[] initialElements = {
    "A",
    "B",
    "C",
    "E",
    "F",
  };

  private static StringArrayList stringArrayList;

  @BeforeEach
  public void initialize(){
    stringArrayList = new StringArrayList(initialElements);
  }

  @Test
  public void getC(){
    assertEquals("C", stringArrayList.getElement(2));
  }

  @Test
  public void getSize(){
    assertEquals(5, stringArrayList.getSize());
  }

  @Test
  public void appendG(){
    stringArrayList.addString("G");
    assertEquals("G", stringArrayList.getElement(
          stringArrayList.getSize() - 1));
  }

  @Test
  public void insertD(){
    stringArrayList.addString("D", 3);
    assertEquals("D", stringArrayList.getElement(3));
  }

  @Test
  public void removeIndexTwo(){
    String elementTwo = stringArrayList.getElement(2);

    stringArrayList.removeString(2);
    assertFalse(elementTwo.equals(stringArrayList.getElement(2)));
  }

  @Test
  public void removeE(){
    int index = stringArrayList.removeString("E");
    assertNotEquals("E", stringArrayList.getElement(index));
  }

  @Test
  public void printContents(){
    StringArrayList.PrintMode[] printModes = StringArrayList.PrintMode.values();

    for (StringArrayList.PrintMode mode: printModes){
      stringArrayList.printList(mode);
    }
  }
}
