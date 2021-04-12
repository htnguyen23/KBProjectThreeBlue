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

	public Backend(String parkDataFile, String graphDataFile) throws IOException, DataFormatException {
		try {
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
		} catch (IOException e) {
			throw new IOException("There is an input/output problem with the input data file.");
		} catch (DataFormatException e) {
			throw new DataFormatException("The data format of the input file is incompatible.");
		}
		tableKeys = new ArrayList<String>(table.keySet());
		madison = table.get("Madison (City)");

	}

	public CS400Graph<Park>.Path returnPathTo(Park destination) {

		CS400Graph<Park>.Path returnPath = graph.dijkstrasShortestPath(madison, destination);
		return returnPath;
	}

	public CS400Graph<Park>.Path fromTo(Park departure, Park destination) {
		CS400Graph<Park>.Path returnPath = graph.dijkstrasShortestPath(departure, destination);
		return returnPath;
	}

	public boolean doesParkExist(String searchQuery) {
		for (int i = 0; i < parkList.size(); i++) {
			if (parkList.get(i).getName().equals(searchQuery)) {
				return true;
			}
		}
		return false;
	}

	// IM: chnaged findParks() method
	public Park[] findParks() {
		Park[] returnList = new Park[NUMBER_OF_PARKS];
		for (int i = 0; i < tableKeys.size(); i++) {
			returnList[i] = (table.get(tableKeys.get(i)));
		}
		return returnList;
	}

	public Park randomPark() {
		int index = (int) Math.floor(Math.random() * tableKeys.size());
		String key = tableKeys.get(index);
		return table.get(key);
	}
}
