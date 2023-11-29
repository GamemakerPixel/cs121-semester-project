package week14.binarysearchtreeactivity;



public class Node
{
	private char key;
	private Node leftChild;
	private Node rightChild;
		
	public Node(char key)
	{
		this.key = key;
	}
	
	public char getKey()
	{
		return key;
	}
	
	public Node getLeftChild()
	{
		return leftChild;
	}
	
	public void setLeftChild(Node leftChild)
	{
		this.leftChild = leftChild;
	}
	
	public Node getRightChild()
	{
		return rightChild;
	}
	
	public void setRightChild(Node rightChild)
	{
		this.rightChild = rightChild;
	}
}


