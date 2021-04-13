// --== CS400 File Header Information ==--
// Name: Xiaohan Zhu
// Email: xzhu274@wisc.edu
// Team: KB blue
// Role: data wrangler
// TA: Keren
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;
import java.util.NoSuchElementException;
import java.util.zip.DataFormatException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DataWranglerTests {
	private ParkDataReader readerToTest;
	private Hashtable<String, Park> testPark;
	private CS400Graph<Park> testGraph;
	
	/**
     * Instantiate a new ParkDataReader object, and read in two csv files, one
     * containing park information, and one containing graph information (adjacency
     * matrix).
     * 
     * @throws FileNotFoundException when the input data file is not found
	 * @throws IOException when there is I/O problems with input data file 
	 * @throws DataFormatException when the number of columns do not match.
     */
	@BeforeEach
	public void createGraph() throws FileNotFoundException, IOException, DataFormatException {
		readerToTest = new ParkDataReader();
		testPark = readerToTest.readParkData(new FileReader("National Park Descriptions and States.csv"));
		testGraph = readerToTest.readGraphData(new FileReader("national_parks.csv"));
	}

	/**
	 * This test tests the functionality of readParkData method. It checks 
	 * whether park information is correctly stored and returned for park objects.
	 */
	@Test
	public void testReadParkData(){
		assertEquals(testPark.get("Badlands").getStates(),"South Dakota");
		assertEquals(testPark.get("Yellowstone").getStates(),"\"Idaho, Montana, Wyoming\"");
	
	}
	
	/**
	 * This test tests the functionality of readGraphData method. It checks 
	 * whether the graph is correctly created by checking the shortest path
	 * data sequence (should be 3 parks) and distance/total weight cost 
	 * (should be 757) between Madison and Hot Springs.
	 */
	@Test
	public void testReadGraphData(){
		assertEquals(testGraph.shortestPath(testPark.get("Madison (City)"),testPark.get("Hot Springs")).size(),3);
		assertEquals(testGraph.getPathCost(testPark.get("Madison (City)"),testPark.get("Hot Springs")), 757);
		}
	
	/**
	 * This test tests that we cannot return to Madison, i.e, no path can be
	 * found from other parks to Madison.
	 */
	@Test
	public void testNoPathReturnToMadison(){
		boolean isThrown = false;
		try {
			testGraph.dijkstrasShortestPath(testPark.get("Hot Springs"),testPark.get("Madison (City)"));
		}catch (NoSuchElementException e) {
			isThrown = true;
//			assertEquals(e.getMessage(),"no path from start to end can be found");
		}
		assertEquals(isThrown, true);
		
	}
	
}
