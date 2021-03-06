import java.util.*;
import java.io.*;

public class DupsBST{

	private Node root;

	public DupsBST(){ //default constructor 
		root = null;}

	// INSERT //
	public void insert(int key){
		root = insert(root,key);}

	private Node insert(Node p, int key){
		if(p == null)
			return new Node(key);
		if(key == p.data){
			p.count++;
			return p;
		}
		if(key < p.data)
			p.left = insert(p.left,key);
		if(key > p.data)
			p.right = insert(p.right,key);
		else
			return p;
	
		return p;
	}

	// SEARCH //
	public boolean search(int key){
		return search(root,key);}

	private boolean search(Node p, int key){
		if(p == null)
			return false;
		if(p.data == key)
			return true;
		else if(key < p.data)
			return search(p.left,key);
		else return search(p.right,key);
	}

	// DELETE //
	public void delete(int key){
		root = delete(root,key);}

	private Node delete(Node p, int key){
		if(p == null)
			return p;
		//recur down the tree to find which node to be deleted
		if(key < p.data)
			p.left = delete(p.left,key);
		else if(key > p.data)
			p.right = delete(p.right, key);

		//found it. this node needs to be deleted
		else{

			if(p.count>1){
				p.count--;
				return p;
			}
			// when node to be deleted has one or no child
			if(p.left == null)
				return p.right;
			else if(p.right == null)
				return p.left;

			//when node to be deleted has two children
			p.data = minValue(p.right);
			p.right = delete(p.right,p.data);
		}
		return p;
	}

	private int minValue(Node p){
		int minV = p.data;
		while(p.left != null){
			minV = p.left.data;
			p = p.left;
		}
		return minV;
	}

	// TRAVERSALS //
	public void inorder(){
		inorder(root);}
	private void inorder(Node p){
		if(p != null){
			inorder(p.left);
			System.out.print(" "+p.toString());
			inorder(p.right);
		}
	}

	// Inorder successor
	public Node inorderSuccessor(Node n){
		return inorderSuccessor(root,n);}

	private Node inorderSuccessor(Node root, Node n){
		if(n.right != null)
			return minNode(n.right);
		Node succ = null;
		while(root != null){
			if(n.data < root.data){
				succ = root;
				root = root.left;
			}
			else if(n.data > root.data)
				root = root.right;
			else
				break;
		}
		return succ;
	}

	private Node minNode(Node p){
		Node min = p;
		while(p.left != null){
			min = p.left;
			p = p.left;
		}
		return min;
	}

	//The Node class
	private class Node{
		private int data;
		private int count;
		private Node left;
		private Node right;
	
		public Node(int i){
			this.data = i;
			this.count = 1;
			this.left = null;
			this.right = null;
		}

		public String toString(){
			return " "+data+"("+count+")";
		}
	}//end of class Node

	public static void main(String[] args){
		DupsBST bst = new DupsBST();
		bst.insert(50);
		bst.insert(30);
		bst.insert(50);
		bst.insert(40);
		bst.insert(70);
		bst.insert(60);
		bst.insert(50);
		bst.insert(70);
		System.out.println("Inorder traversal of the constructed tree");
		bst.inorder();

		//System.out.println("Tree is BST? " + bst.isBST());
		System.out.println("\nDelete key 50");
		bst.delete(50);
		System.out.println("Inorder traversal after deleting 20");
		bst.inorder();

		System.out.println("\nDelete key 30");
		bst.delete(30);
		System.out.println("Inorder traversal after deleting 30");
		bst.inorder();
	}
}
