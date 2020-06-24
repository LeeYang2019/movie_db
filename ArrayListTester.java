///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  (MoiveDbMain.java)
// File:             (ArrayListTester.java)
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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Tester implementation using ArrayList as the data structure.
 *
 * @author apul
 */
public class ArrayListTester implements TesterADT<String, MovieRecord>{
	// ArrayList of movie records.
	private List<MovieRecord> movieRecordList;

	/**
	 * Constructor that initializes the movieRecordList.
	 * 
	 * @param movieRecordList
	 *            list of movie records.
	 */
	public ArrayListTester(List<MovieRecord> movieRecordList) {
		this.movieRecordList = movieRecordList;
	}

	/**
	 * Returns size of the data list.
	 * 
	 * @return number of movie records.
	 */
	public int size() {
		return movieRecordList.size();
	}

	/**
	 * Search for records having index value equal to key.
	 * 
	 * @param index
	 *            the index (attribute) we want to search for.
	 * @param key
	 *            the key value we are looking for.
	 * @return the list of movie records.
	 */
    @Override
	public List<MovieRecord> searchByKey(String index, String key) {
    	
    	//create a list to store movie records 
    	List<MovieRecord> list = new ArrayList<>();
    	
    	//loop through the movie record list
    	for (int i = 0; i < size(); i++)
    	{	
    		//identify a movie record based on the key and add it to the list
    		if (movieRecordList.get(i).getValByAttribute(index).equalsIgnoreCase(key))
    		{
    			list.add(movieRecordList.get(i));
    		}
    	}
    	
    	//return the new list of movie records
    	return list;
    }

    /**
     * Search for records having index value within the range minVal and maxVal.
     * 
     * @param index
     *            the index tree to search.
     * @param minVal
     *            minimum value of the range (inclusive).
     * @param maxVal
     *            maximum value of the range (inclusive).
     * @return list of MovieRecords with index key in the specified range (both inclusive).
     */
	@Override
	public List<MovieRecord> rangeSearch(String index, String minVal, String maxVal) {
		
		//create a new list to store movie records returned through range search
		List<MovieRecord> list = new ArrayList<>();
		
		//loop through the movie record list
		for (int i = 0; i < movieRecordList.size(); i++)
		{
			//identify movie records within the specified range, based on the search
			//criteria and add them to the list one at a time
			if (movieRecordList.get(i).getValByAttribute(index).compareTo(minVal) >= 0 
					&& movieRecordList.get(i).getValByAttribute(index).compareTo(maxVal) <= 0)
			{
				list.add(movieRecordList.get(i));
			}
		}
		
		//return the new list of movie records
		return list;
    }

	/**
	 * Returns a sorted list of keys - index defines which key we want to sort on.
	 * Hint: You can define a Comparator to compare two MovieRecords based on
	 * specified index (e.g., director). You can then use this comparator class
	 * to directly sort using List.sort method.
	 * 
	 * @param index the index to which sort on.
	 * @return Sorted list of key values. 
	 * E.g., [..., "Christopher Nolan", ..., "James Cameron", ...] for director as index.
	 */
	@Override
	public List<String> allSortedKeys(String index) {
		
		//create a new list to store movie records
		//create a comparator
		List<String> list = new ArrayList<>();
		CompareMovie comp = new CompareMovie();
		
		//loop though the movie record list and add it to the list
		for (int i = 0; i < size(); i++) 
		{
			list.add(movieRecordList.get(i).getValByAttribute(index));
		}
		
		//compare movie record strings with comparator
		list.sort(comp);
		
		//return the new list
		return list;
	
	}
	
	class CompareMovie implements Comparator<String> 
	{

		@Override
		public int compare(String o1, String o2) {
			
			//return the numerical value when string 01 
			//is compared to string 02
			return o1.compareTo(o2);
		}
		
	}
	
}