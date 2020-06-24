///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  (MoiveDbMain.java)
// File:             (IndexTreeTester.java)
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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Tester implementation using IndexTree as the data structure.
 *
 * DO NOT MODIFY THIS CLASS.
 *
 * @author apul
 */

public class IndexTreeTester implements TesterADT<String, MovieRecord> {

	// MovieDb object which creates IndexTrees and provides an interface to
	// query the IndexTree for given index (e.g., director).
	private MovieDb movieDb;

	public IndexTreeTester(List<MovieRecord> movieRecords) {
		movieDb = new MovieDb(movieRecords);	
	}

	/**
	 * Returns a list of data values having index key equal to input key.
	 * For example, search for MovieRecords with director (index) =
	 * James Cameron (key). Hint: Use movieDb methods. 
	 * 
	 * @param index
	 *            index (attribute) of the movie record by which search is to be
	 *            done.
	 * @param key
	 *            key to be searched.
	 * @return list of movie records that match the key.
	 */
	@Override
	public List<MovieRecord> searchByKey(String index, String key) {
		// TODO

		//create a list to store movie records based on search criteria
		List<MovieRecord> list = new ArrayList<MovieRecord>();

		//create a movieDB iterator to iterate through the list of movie records
		Iterator<IndexTreeNode<String, List<MovieRecord>>> itr = 
				movieDb.getIndexTree(index).iterator();

		//while not empty...
		while (itr.hasNext())
		{
			//iterate through the list of movie records and add the searched movie
			//record to the list
			IndexTreeNode<String, List <MovieRecord>> node = itr.next();

			if (node.getKey().equals(key))
			{
				list.addAll(node.getData());
			}
		}
		
		//return a list of movie records based on search criteria
		return list;
	}

	/**
	 * Returns a list of data values having index key in the specified range.
	 * For example, search for MovieRecords with 2015 &gt;= releaseYear (index) &lt;= 2016.
	 * Hint: Use movieDb methods. 
	 * 
	 * @param index
	 *            attribute of the movie record by which search is to be done.
	 * @param minVal
	 *            lower bound of the range search.
	 * @param maxVal
	 *            upper bound of the range search.
	 * @return list of movie records that fall in the given range.
	 */
	@Override
	public List<MovieRecord> rangeSearch(String index, String minVal, String maxVal) {
		// TODO
		
		//create a list to store movie records
		List<MovieRecord> list = new ArrayList<MovieRecord>();

		//for each record in the database, add matching records based on search criteria to the list
		for (int i = 0; i < movieDb.getMovies().size(); i++)
		{
			if (movieDb.getMovies().get(i).getValByAttribute(index).compareTo(minVal) >= 0
					&& movieDb.getMovies().get(i).getValByAttribute(index).compareTo(minVal) <= 0)
			{
				list.add(movieDb.getMovies().get(i));
			}
		}
		
		//return the list of movie records matching the specified search criteria
		return list;
	}

	/**
	 * Returns a sorted list of keys - index defines which key we want to sort
	 * on. Hint: Use IndexTreeIterator to do in-order traversal.
	 * 
	 * @param index
	 *            the index to which sort on.
	 * @return list of index values sorted in lexicographically increasing order.
	 * E.g., [..., "Christopher Nolan", ..., "James Cameron", ...] for director
	 * as index.
	 */
	@Override
	public List<String> allSortedKeys(String index) {
		// TODO
		
		//create a list to store sorted keys
		List<String> list = new ArrayList<String>();
		
		//create an iterator to iterate through the movieDb index tree
		Iterator<IndexTreeNode<String, List<MovieRecord>>> itr = movieDb.getIndexTree(index).iterator();
		
		//while not empty...
		while (itr.hasNext()){
			list.add(itr.next().getKey()); //add each node matching the key
										   //description to the list
		}

		//return a sorted list of items matching the key description
		return list;

	}
}
