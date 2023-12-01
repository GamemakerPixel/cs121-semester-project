package week14.queue;

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class MyQueue<E>{
  LinkedList<E> queueList;

  public MyQueue(){
    queueList = new LinkedList<E>();
  }

  public void enqueue(E element){
    queueList.addLast(element);
  }

  public E dequeue() throws NoSuchElementException{
    return queueList.removeFirst();
  }

  public E peek() throws NoSuchElementException{
    return queueList.getFirst();
  }

  public boolean isEmpty(){
    return (queueList.size() == 0);
  }
}
