// --== CS400 File Header Information ==--
// Name: Casey Waddell
// Email: ctwaddell@wisc.edu
// Team: KB
// TA: Keren
// Lecturer: Gary
// Notes to Grader: 

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.zip.DataFormatException;
import java.lang.Math;

public class Backend implements BackendInterface {
	private final int NUMBER_OF_PARKS = 51;
	private ParkDataReader reader;
	private FileReader filereader;
	private FileReader filereader2;
	private LinkedList<Park> parkList;
	private ArrayList<String> tableKeys;
	private CS400Graph<Park> graph;
	private Hashtable<String, Park> table;
	private Park madison;

	
	/**
	 * The constructor for the Backend. It takes in two arguments representing the names of the csv
	 * files. After it has these, it creates readParkData objects to extract the data of the csv
	 * files using the datawrangler's methods. 
	 * @param parkDataFile, String representing the name of the file, but furthermore this file is
	 * used to create all the park objects and populate the hashtable using datawrangler's methods
	 * @param graphDataFile, String representing the name of the file, but furthermore this file is
	 * used to create the actual graph and populate it with the Park objects already created using
	 * the datawrangler's methods
	 * @throws IOException, this is thrown when the datawrangler's methods can't read the formatting
	 * in the file
	 * @throws DataFormatException, this is thrown when the datawrangler's methods can't read the
	 * given file type
	 */
	public Backend(String parkDataFile, String graphDataFile) throws IOException, DataFormatException 
	{
		try
		{
			this.filereader = new FileReader(parkDataFile);
			this.reader = new ParkDataReader();
			reader.readParkData(filereader);

			this.filereader2 = new FileReader(graphDataFile);
			reader.readGraphData(filereader2);

			// IM: changed from using 2 different readers to using the same reader -
			// NullPointerException results from using 2 readers as the parkTable depends on
			// the parkGraph and vice versa
			table = reader.parkTable;
			graph = reader.parkGraph;
		} 
		catch (IOException e) 
		{
			throw new IOException("There is an input/output problem with the input data file.");
		} 
		catch (DataFormatException e) 
		{
			throw new DataFormatException("The data format of the input file is incompatible.");
		}
		tableKeys = new ArrayList<String>(table.keySet());
		madison = table.get("Madison (City)");

	}

	/**
	 * returnPathTo finds the most efficient route to a given park using dijkstras algorithm.
	 * The assumed departure for this method is ALWAYS madison, the origin point of our
	 * travel agency
	 * @param destination, a Park object to find the route to
	 * @return CS400Graph<Park>.Path, a Path object representing the most efficient route to the
	 * given destination
	 */
	public CS400Graph<Park>.Path returnPathTo(Park destination) 
	{
		CS400Graph<Park>.Path returnPath = graph.dijkstrasShortestPath(madison, destination);
		return returnPath;
	}

	/**
	 * fromTo is similar to returnPathTo, except it can take any two parks as input to calculate the
	 * most efficient route between them.
	 * @param departure, Park representing the starting point
	 * @param destination, Park representing the ending point
	 * @return CS400Graph<Park>.Path, a Path object represenging the most efficient route between
	 * the given parks
	 */
	public CS400Graph<Park>.Path fromTo(Park departure, Park destination) 
	{
		CS400Graph<Park>.Path returnPath = graph.dijkstrasShortestPath(departure, destination);
		return returnPath;
	}

	/**
	 * doesParkExist searches through the Hashtable to see if a park with the given key exists
	 * @param searchQuery, a String the user inputs to search the hashtable for
	 * @return boolean, true if the park exists and false if it does not
	 */
	public boolean doesParkExist(String searchQuery) 
	{
		for (int i = 0; i < parkList.size(); i++) 
		{
			if (parkList.get(i).getName().equals(searchQuery)) 
			{
				return true;
			}
		}
		return false;
	}

	// IM: changed findParks() method
	/**
	 * findParks returns all the Parks in the hashtable
	 * @return Park[] representing all the parks in the hashtable
	 */
	public Park[] findParks() 
	{
		Park[] returnList = new Park[NUMBER_OF_PARKS];
		for (int i = 0; i < tableKeys.size(); i++) 
		{
			returnList[i] = (table.get(tableKeys.get(i)));
		}
		return returnList;
	}

	/**
	 * randomPark finds a random park within the hashtable and returns it
	 * @return Park representing the random park that was found
	 */
	public Park randomPark() 
	{
		int index = (int) Math.floor(Math.random() * tableKeys.size());
		String key = tableKeys.get(index);
		return table.get(key);
	}
}
