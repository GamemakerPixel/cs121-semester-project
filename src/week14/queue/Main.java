package week14.queue;



public class Main{
  private static final int[] ELEMENTS = {1, 2, 3, 5, 7, 11, 13};

  public static void main(String[] args) {
    MyQueue<Integer> queue = new MyQueue<>();

    System.out.println("Enqueueing");
    for (int element: ELEMENTS){
      System.out.printf("%d ", element);
      queue.enqueue(element);
    }
    System.out.print("\n");

    System.out.println("Dequeueing");
    while (!queue.isEmpty()){
      System.out.printf("%d ", queue.dequeue());
    }
    
    System.out.print("\n");
  }
}
