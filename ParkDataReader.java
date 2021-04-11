// --== CS400 File Header Information ==--
// Name: Xiaohan Zhu
// Email: xzhu274@wisc.edu
// Team: KB blue
// Role: data wrangler
// TA: Keren
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.zip.DataFormatException;


public class ParkDataReader {
	
	
	Hashtable<String, Park> parkTable;
	
	/**
	 * This method reads in the file containing park information including name,
	 * description, and state. It creates Park objects and stores parks in
	 * a hash table.
	 * 
	 * @param inputFileReader input data file containing park information
	 * @return a hash table containing all Park objects with String name as key
	 * @throws IOException when there is I/O problems with input data file 
	 * @throws DataFormatException when the number of columns do not match.
	 */
	public Hashtable<String, Park> readParkData(Reader inputFileReader) throws IOException, DataFormatException {
		if (inputFileReader == null) {
			throw new NullPointerException("File is null");
		}

		parkTable = new Hashtable<>();
		
		//insert Madison 
		String[] madisonData = new String[3];
		madisonData[0] = "Madison (City)";
		madisonData[1] = null;
		madisonData[2] = null;
		Park madison = new Park(madisonData);
		parkTable.put("Madison (City)", madison);
		
		BufferedReader buffRead = new BufferedReader(inputFileReader);
	
		buffRead.readLine(); //skip the header and start reading from the second line
		String line = buffRead.readLine();
		while (line!= null) {
//			String[] data = line.split(",");
			String[] data = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
			parkTable.put(data[0], new Park(data));  //add park to the hash table
			line = buffRead.readLine(); 
		}
		buffRead.close();
		return parkTable;
		
	}
	
	
	

	CS400Graph<Park> parkGraph;
	
	/**
	 * This method reads in the data file containing adjacency matrix and creates
	 * the graph by inserting vertices(storing Park object) and edges between 
	 * them accordingly.
	 * 
	 * @param inputFileReader input data file containing adjacency matrix
	 * @return graph class according to the adjacency matrix
	 * @throws IOException when there is I/O problems with input data file
	 * @throws DataFormatException when the number of columns do not match.
	 */
	public CS400Graph<Park> readGraphData(Reader inputFileReader) throws IOException, DataFormatException{
		if (inputFileReader == null) {
			throw new NullPointerException("File is null");
		}
		parkGraph = new CS400Graph<>();
		
//		String[] madisonData = new String[3];
//		madisonData[0] = "Madison (City)";
//		madisonData[1] = null;
//		madisonData[2] = null;
//		Park madison = new Park(madisonData);
//		parkGraph.insertVertex(madison);
		
		//insert vertices
		for (String name: parkTable.keySet()) {
			parkGraph.insertVertex(parkTable.get(name));
		}
		
		
		//read in the adjacency matrix to insert edges in the graph
		BufferedReader buffRead = new BufferedReader(inputFileReader);
		
		String headerLine = buffRead.readLine(); 
		String[] header = headerLine.split(",");//header
		
		String line = buffRead.readLine();
		while (line!= null) {
			String[] data = line.split(",");
			for (int i = 1; i< data.length; i++) {
				if (Integer.parseInt(data[i]) != -1) {
					parkGraph.insertEdge(parkTable.get(data[0]), parkTable.get(header[i]), Integer.parseInt(data[i]));
					
				}
			}  
			line = buffRead.readLine(); 
		}
		buffRead.close();
		
		
		
		
		return parkGraph;
	}
	
}
