package week14.binarysearchtreeactivity;



public class BinarySearchTreeDemo
{
	private Node root;
	
	// add a node to the tree
	public void addNode(char key)
	{
		Node newNode = new Node(key); // create and initialize a new Node
		
		if (root == null) // if there is no root, the new Node becomes the root
		{
			root = newNode;
		}
		else
		{
			Node currentNode = root; // set root as the Node we start with in traversing the tree
			Node parent; // declare a parent for the new Node
			
			while (true) 
			{
				// root is the top parent, so we start there (we set the currentNode to root, remember)
				parent = currentNode; 
				// check if the new Node should go on the left side of the parent Node
				if (key < currentNode.getKey())
				{					
					currentNode = currentNode.getLeftChild(); // Make currentNode the left child
					if (currentNode == null) // if the left child has no children
					{
						parent.setLeftChild(newNode); // then add the new node to the left of it
						return; // done
					}
				}
				else // the new node will go on the right side
				{
					currentNode = currentNode.getRightChild();					
					if (currentNode == null) // if the right child has no children
					{						
						parent.setRightChild(newNode); // add the new node to the right of it
						return; //done
					}
				}
			}
		}
	}
	
	// inorder traversal: all nodes are visited in ascending order
	// recursion is used to visit a node and then its child nodes
	public void inOrderTraverse(Node currentNode)
	{
		if (currentNode != null)
		{
			inOrderTraverse(currentNode.getLeftChild()); // traverse the left side
			System.out.print(currentNode.getKey() + " "); // print the current node visited
			inOrderTraverse(currentNode.getRightChild()); // traverse the right side
		}
	}
	
	public void preOrderTraverse(Node currentNode)
	{
		if (currentNode != null)
		{
			System.out.print(currentNode.getKey() + " "); // print the current node visited
			preOrderTraverse(currentNode.getLeftChild()); // traverse the left side
			preOrderTraverse(currentNode.getRightChild()); // traverse the right side			
		}
	}
	
	public void postOrderTraverse(Node currentNode)
	{
		if (currentNode != null)
		{
			postOrderTraverse(currentNode.getLeftChild()); // traverse the left side
			postOrderTraverse(currentNode.getRightChild()); // traverse the right side
			System.out.print(currentNode.getKey() + " "); // print the current node visited
		}
	}
	
	public String findNode(int key)
	{
		Node currentNode = root; // start at the root node
		while (currentNode.getKey() != key) // while current node is not the key
		{
			if (key < currentNode.getKey()) // if key is less than current node, go left
			{
				currentNode = currentNode.getLeftChild(); // current node becomes left child
			}
			else
			{
				currentNode = currentNode.getRightChild(); // or current node becomes right child
			}
			if (currentNode == null) // if not found
			{
				return String.format("%d not found", key);
			}
		}
		return String.format("%d was found", currentNode.getKey()); // current node is key
	}
	
	public Node getRoot()
	{
		return root;
	}
	
	public String removeNode(int key)
	{
		Node currentNode = root; // start at top of tree
		Node parent = root;
		boolean isLeftChild = true; // we use this to tell us whether to search right or left
		
		while (currentNode.getKey() != key) // while the current node isn't the key
		{
			parent = currentNode;  // set parent to the current node
			if (key < currentNode.getKey()) // if key less than current node, then go to left side
			{
				isLeftChild = true; // it is a leftChild
				currentNode = currentNode.getLeftChild(); // set current node to left child
			}
			else // key is greater than current node
			{
				isLeftChild = false; // it is a right child
				currentNode = currentNode.getRightChild(); // set current node to right child
			}
			if (currentNode == null) // if node wasn't found
			{
				return String.format("%d not found", key);
			}
		}
		// if node has no children, remove it
		if (currentNode .getLeftChild() == null && currentNode.getRightChild() == null)
		{
			if (currentNode == root) // if node is root, remove it
				root = null;
			else if (isLeftChild) // if it is left child of the parent
			{
				parent.setLeftChild(null); // remove it from parent
			}
			else // if it is right child of parent
			{
				parent.setRightChild(null); // remove it from parent
			}
		}
		else if (currentNode.getRightChild() == null) // if node has no right child
		{
			if (currentNode == root) // if node is root
			{
				root = currentNode.getLeftChild(); // root is set to left child (remove root and put left child in its place)
			}
			else if (isLeftChild) // if node is left child, move node's left child to parent node
			{
				parent.setLeftChild(currentNode.getLeftChild());
			}
			else // if node is right child, move node's left child to parent child
			{
				parent.setRightChild(currentNode.getLeftChild());
			}
		}
		else if (currentNode.getLeftChild() == null) // if node has no left child
		{
			if (currentNode == root) // if node is root
			{
				root = currentNode.getRightChild(); // root is set to right child (remove root and put right child in its place)
			}
			else if (isLeftChild) // if node is left child, move node's right child to parent node
			{
				parent.setLeftChild(currentNode.getRightChild());
			}
			else // if node is right child, move node's right child to parent child
			{
				parent.setRightChild(currentNode.getRightChild());
			}
		}
		else // there are two children, so need to find the removed node's replacement
		{
			Node replacement = getReplacementNode(currentNode); // calls the getReplacementNode() method below
			if (currentNode == root) // if node is root, replace it with the replacement node
			{
				root = replacement;
			}
			else if (isLeftChild) // if removed node was left child, make replacement a left child
			{
				parent.setLeftChild(replacement);
			}
			else // if removed node was right child, make replacement a right child
			{
				parent.setRightChild(replacement);
			}
			replacement.setLeftChild(currentNode.getLeftChild()); // set replacement's left child to removed node's left child
		}
		return String.format("%d was removed", key);
	}
	
	public Node getReplacementNode(Node replacedNode)
	{
		Node replacementParent = replacedNode;
		Node replacement = replacedNode;		
		Node currentNode = replacedNode.getRightChild();		
		while (currentNode != null) // while there are no more left children
		{
			replacementParent = replacement;
			replacement = currentNode;
			currentNode = currentNode.getLeftChild();
		}
		// if the replacement isn't the right child, move the replacement into the parent's left child slot
		// and move the replaced node's right child into the replacement's right child slot
		if (replacement != replacedNode.getRightChild())
		{
			replacementParent.setLeftChild(replacement.getRightChild());
			replacement.setRightChild(replacedNode.getRightChild());
		}
		return replacement;
	}
}
































































