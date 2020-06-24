///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  (MoiveDbMain.java)
// File:             (IndexTree.java)
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

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Generic IndexTree implementation where each node is identified by a key and
 * can have a list of values i.e duplicate keys are allowed.
 * 
 * For example: You can insert &lt;K1, V1&gt; and &lt;K1, V2&gt; in the IndexTree.
 * After you insert, the node corresponding to key K1 will have a list of data
 * values [V1, V2].
 *
 * @author CS367
 */
public class IndexTree<K extends Comparable<K>, V> implements IndexTreeADT<K, V>, 
Iterable<IndexTreeNode<K, List<V>>> {

	// Root node.
	private IndexTreeNode<K, List<V>> root;

	/**
	 * Constructs a IndexTree and initializes the root node.
	 */
	public IndexTree() {
		root = null;
	}

	/**
	 * Returns iterator with respect to the root node.
	 * 
	 * @return the iterator for the IndexTree.
	 */
	public Iterator<IndexTreeNode<K, List<V>>> iterator() {
		return new IndexTreeIterator<K, List<V>>(root);
	}

	/**
	 * Search for the node with key equals to input key. Hint: Call
	 * a search helper method to recursively traverse the tree.
	 *
	 * @param key
	 *            the key to search.
	 * @return data value in the tree for the corresponding key.
	 * @throws IllegalArgumentException
	 *             if key is null.
	 */	
	public List<V> search(K key) {
		// Check for null key and throw IllegalArgumentException() exception.
		if (key == null)
		{
			throw new IllegalArgumentException();
		}
	
		// Call Search helper method with root and key to be searched.   
		return search(root, key);
	}

	/**
	 * Recursive helper method to find the key equals to input key.
	 * 
	 * @param node
	 * @param key
	 * @return
	 */
	private List<V> search(IndexTreeNode<K, List<V>> node, K key)
	{
		//Check for a valid node; throw KeyNotFoundException node is invalid
		if (node == null)
		{
			throw new KeyNotFoundException();
		}
		
		//return the node's data if the data of the node is the same as the key
		if (node.getKey().compareTo(key) == 0)
		{
			return node.getData();
		}
		//search the leftChild if the the node's key is greater than key
		else if (node.getKey().compareTo(key) > 0) 
		{
			return search(node.getLeftChild(), key);
		}
		//search the rightChild if the node's key is greater than key
		else 
		{
			return search(node.getRightChild(), key);
		}
	}


	/**
	 * Inserts a (key, value) pair into the IndexTree. This will call a 
	 * recursive method with root node and (key, value) to be inserted in the 
	 * IndexTree.
	 * 
	 * @param key
	 *            key of the new data to be inserted.
	 * @param value
	 *            data to be inserted.
	 * @throws IllegalArgumentException
	 *             if key is null.
	 */
	public void insert(K key, V value) {
		
		//Check for invalid Key and throw IllegalArgumentException().
		if (key == null)
		{
			throw new IllegalArgumentException();
		}
		
		//if key is valid, then call helper method with root, key, and value 
		//and return node.  Assigned the node to root.
		root = insert(root, key, value);
	}

	/** 
	 * Recursive helper method to find the position and insert a key and value
	 * into the IndexTree. 
	 * 
	 * NOTE: STUDENTS MUST IMPLEMENT insert recursively, 
	 * but you may define your own recursive helper method instead of
	 * defining and using this method. 
	 * 
	 * @param node
	 *            node is the recursive parameter with initial value being root
	 *            of the IndexTree.
	 * @param key
	 *            key of the new data to be inserted.
	 * @param value
	 *            data to be inserted.
	 */	
	private IndexTreeNode<K, List<V>> insert(IndexTreeNode<K, List<V>> node, 
			K key, V value) {
		
		//Check if node is null, if so; create a list, add value to the list, 
		//and create a new node with the key and the list and assign it to node.
		if (node == null)
		{
			ArrayList<V> list = new ArrayList<V>();
			list.add(value);
			node = new IndexTreeNode<K, List<V>>(key, list);
			return node;
		}

		//otherwise, if the key matches the node's key
		else if (node.getKey().compareTo(key) ==  0)
		{
			node.getData().add(value);  //add the value to the node's data
			return node;
		}	
		//otherwise, if the key is greater than the node's key, add to the left 
		//subtree
		else if (node.getKey().compareTo(key) > 0)
		{
			node.setLeftChild(insert(node.getLeftChild(), key, value));
		}
		//otherwise, the key is less than the node's key and add to the right 
		//subtree
		else
		{
			node.setRightChild(insert(node.getRightChild(), key, value));
		}
		
		return node;
	}

	/**
	 * Returns a list of data values which have keys in the specified range
	 * (inclusive of minValue and maxValue). Hint: This must be done recursively
	 * using a range search helper method and call it with root.
	 * Note: Range values are always compared lexicographically For example,
	 * "15" &lt; "7" lexicographically.
	 *
	 * @param minValue
	 *            the minimum value of the desired range (inclusive).
	 * @param maxValue
	 *            the maximum value of the desired range (inclusive).
	 * @return the sorted list of keys in the specified range.
	 * @throws IllegalArgumentException
	 *             if either minValue or maxValue is null.
	 */	
	public List<V> rangeSearch(K minValue, K maxValue) {
		
		//check for invalid minValue or maxValue and throw IllegalArgumentException
		if (minValue == null || maxValue == null)
		{
			throw new IllegalArgumentException();
		}

		// Ensure min is less than max
		if ( minValue.compareTo(maxValue) > 0 ) {
			K t = minValue;
			minValue = maxValue;
			maxValue = t;
		}

		List<V> list = new ArrayList<>();
		
		// Call the rangeSearch helper method
		 rangeSearch(list, root, minValue, maxValue);
		 
		 return list;
	}
	
	/**
	 * Recursive helper method to find a list of data values which have keys in 
	 * the specified range.
	 * 
	 * @param list
	 * @param node
	 * @param minValue
	 * @param maxValue
	 */
	private void rangeSearch(List<V> list, IndexTreeNode<K, List<V>> node, 
			K minValue, K maxValue)
	{
		// Base case:  Check for invalid node; return if node is invalid
		if (node == null) 
		{
			return;
		}
		
		// Traverse left subtree if the node's key is greater than the maxValue
		if (node.getKey().compareTo(maxValue) > 0) 
		{
			rangeSearch(list, node.getLeftChild(), minValue, maxValue);
		}
		
		// Traverse right subtree if the node's key is less than the minValue
		if (node.getKey().compareTo(minValue) < 0) 
		{
			rangeSearch(list, node.getRightChild(), minValue, maxValue);
		}
		
		// Traverse both right and left subtree if the node's key is in-between 
		// the max and min value.
		if (node.getKey().compareTo(maxValue) <= 0 && 
				node.getKey().compareTo(minValue) >= 0) 
		{
			list.addAll(node.getData());  // add all node's data to list
			rangeSearch(list, node.getLeftChild(), minValue, maxValue);
			rangeSearch(list, node.getRightChild(), minValue, maxValue);
		}
	}
	

	/**
	 * Returns the number of nodes in the tree. This must be done recursively
	 * using the helper method to get the number of nodes.
	 * 
	 *  @return number of nodes in the tree.
	 */
	public int size() {
		// Call size's helper method with root pass in
		return size(root);
	}
	
	/**
	 * Recursive helper method to find the number of nodes in the tree.
	 * 
	 * @param node
	 * @return number of nodes in the tree.
	 */
	private int size(IndexTreeNode<K, List<V>> node)
	{
		//Check for invalid node; if node is invalid, return 0.
		if (node == null) 
		{
			return 0;
		}
		else //add together the size of the left and right subtree to 1
		{
			return size(node.getLeftChild()) +  size(node.getRightChild()) + 1;
		}
	}

	/**
	 * Returns height of the tree. Hint: Use a recursive helper method
	 * and call it with root node to calculate the height.
	 *
	 * @return the height of the tree.
	 */
	public int getHeight() {
		// Call getHeight helper method and pass in root
		return getHeight(root);
	}
	
	/**
	 * Recursive helper method to find the height of a tree.
	 * 
	 * @param node
	 * @return the height of the tree.
	 */
	private int getHeight(IndexTreeNode<K, List<V>> node)
	{
		// Check for invalid node and return 0 if node is invalid
		if (node == null) 
		{
			return 0;
		}
		else  //otherwise check the height of the left and right subtree
		{
			//add the highest subtree to 1 and return the value
			return Math.max(getHeight(node.getLeftChild()), 
					getHeight(node.getRightChild())) + 1;
		}
	}

	/**
	 * Returns total number of data values in the tree.
	 * Hint: Call a recursive helper method to recursively count the nodes.
	 * 
	 * @return the total data count (values of all data values in the tree).
	 */
	public int getTotalDataCount() {
		// Call the getTotalDataCount helper method passing in root
		return getTotalDataCount(root);
	}
	
	/**
	 * Recursive helper method to find the total number of data values in the 
	 * tree.
	 * 
	 * @param node
	 * @return the total data count (values of all data values in the tree).
	 */
	private int getTotalDataCount(IndexTreeNode<K, List<V>> node)
	{
		// Check for invalid node; return 0 if node is invalid
		if (node == null) 
		{
			return 0;
		}
		else  // Otherwise return the total number of data in node and in node's 
			  //right and left subtree.
		{
			int numData = node.getData().size();
			return getTotalDataCount(node.getLeftChild()) + 
					getTotalDataCount(node.getRightChild()) + 
					numData;
		}
	}

	/**
	 * Returns average number of data values per node (E.g., Node with key "key"
	 * and list of values List&lt;V&gt; = {v1, v2, v3} has number of data values as 3)
	 * rounded to 3 decimal places.
	 * Hint: Use getTotalDataCount() and size().
	 * 
	 * @return the average data count.
	 */
	public double getAvgDataCount() {
		// TODO
		
		//method for rounding to 3 decimal places
		DecimalFormat df = new DecimalFormat("#.###");
		df.setRoundingMode(RoundingMode.CEILING);
		
		//evaluate average
		double avgDataCount = (double) getTotalDataCount() / (double) size();
		
		//parse the string into a double
		double avg = Double.parseDouble(df.format(avgDataCount));
		
		//return average
		return avg;
	}

	/**
	 * Displays the top maxNumLevels of the tree. DO NOT CHANGE IT!
	 * You can use this method to debug your code.
	 *
	 * @param maxNumLevels
	 *            from the top of the IndexTree that will be displayed.
	 */
	public void displayTree(int maxNumLevels) {
		System.out.println("---------------------------" +
				"IndexTree Display--------------------------------");
		displayTree(root, 0, maxNumLevels);
	}

	/**
	 * Recursive helper function to display the top levels of the IndexTree.
	 * 
	 * @param node
	 *            initial value being root of IndexTree.
	 * @param curLevel
	 *            initial value 0.
	 * @param maxNumLevels
	 *            initial value being the number of levels of the tree to be
	 *            displayed.
	 */
	private void displayTree(IndexTreeNode<K, List<V>> node, int curLevel,
			int maxNumLevels) {
		
		if (maxNumLevels <= curLevel)
			return;
		if (node == null)
			return;
		for (int i = 0; i < curLevel; i++) {
			System.out.print("|--");
		}
		System.out.println(node.getKey());
		displayTree(node.getLeftChild(), curLevel + 1, maxNumLevels);
		displayTree(node.getRightChild(), curLevel + 1, maxNumLevels);
	}

}
