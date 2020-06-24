///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  (MoiveDbMain.java)
// File:             (IndexTreeIterator.java)
// Semester:         (Introduction to Data Structures) Fall 2016
//
// Author:           (Nhialee Yang nyang5@wisc.edu)
// CS Login:         (nhialee)
// Lecturer's Name:  (Alexander Brooks)
// Lab Section:      (N/A)
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ////////////////////
//
// Pair Partner:     (Yia Xiong)
// Email:            (yxiong58@wisc.edu)
// CS Login:         (yia)
// Lecturer's Name:  (Alexander Brooks)
// Lab Section:      (N/A)
//
//////////////////////////// 80 columns wide //////////////////////////////////

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * The Iterator for IndexTree that is built using Java's Stack class. This
 * iterator steps through the IndexTree using an INORDER traversal.
 *
 * @author apul
 */
public class IndexTreeIterator<K extends Comparable<K>, V> implements Iterator<IndexTreeNode<K, V>> {

	// Stack to track where the iterator is in the tree.
	Stack<IndexTreeNode<K, V>> stack;

	/**
	 * Constructs the iterator so that it is initially at the smallest value in
	 * the set. Hint: Go to farthest left node and push each node onto the stack
	 * while stepping down the IndexTree to get there.
	 *
	 * @param node
	 *            the root node of the IndexTree
	 */
	public IndexTreeIterator(IndexTreeNode<K, V> node) {
	
		//create a stack to push nodes
		stack = new Stack<IndexTreeNode<K, V>>();
		
		if (node != null) {
			RVLTraversal(node);
		}
	}

	private void RVLTraversal(IndexTreeNode<K, V> node) {
		
		//implement reverse in-order traversal so that the 
		//furthest left node is at the top of the stack
		if (node.getLeftChild() != null) {
			RVLTraversal(node.getLeftChild());
		}
		
		//from left to right, push each node into the stack
		stack.push(node);

		if (node.getRightChild() != null) {
			RVLTraversal(node.getRightChild());
		}
	}

	/**
	 * Returns true iff the iterator has more items.
	 *
	 * @return true iff the iterator has more items
	 */
	public boolean hasNext() {
		
		//if the stack is empty
		return !stack.isEmpty();
	}

	/**
	 * Returns the next node in the iteration.
	 *
	 * @return the next item in the iteration
	 * @throws NoSuchElementException
	 *             if the iterator has no more items.
	 */
	public IndexTreeNode<K, V> next() {
	
		//if empty throw exception
		if (!hasNext()) {
			throw new NoSuchElementException();
		}
		
		//return the next item in the stack
		return stack.pop();
	}

	/**
	 * Not Supported
	 */
	public void remove() 
	{
		throw new UnsupportedOperationException();
	}
}
