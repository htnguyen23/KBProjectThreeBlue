import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.zip.DataFormatException;





public class ParkDataReader {
	
	
	Hashtable<String, Park> parkTable;
	
	
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
			parkTable.put(data[0], new Park(data));  
			line = buffRead.readLine(); 
		}
		buffRead.close();
		return parkTable;
		
	}
	
	
	

	CS400Graph<Park> parkGraph;
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
