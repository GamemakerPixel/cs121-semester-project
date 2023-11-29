package week14.binarysearchtreeactivity;



public class BinarySearchTreeTester{
  public static void main(String[] args) {
    BinarySearchTreeDemo tree = new BinarySearchTreeDemo();

    tree.addNode('D');
    tree.addNode('B');
    tree.addNode('F');
    tree.addNode('A');
    tree.addNode('C');
    tree.addNode('E');
    tree.addNode('G');
    
    System.out.println("In order traverse:");
    tree.inOrderTraverse(tree.getRoot());
    System.out.print("\n");

    System.out.println("Pre order traverse:");
    tree.preOrderTraverse(tree.getRoot());
    System.out.print("\n");

    System.out.println("Post order traverse:");
    tree.postOrderTraverse(tree.getRoot());
    System.out.print("\n");



  }
}
