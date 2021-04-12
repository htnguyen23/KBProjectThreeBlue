import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.zip.DataFormatException;
import java.lang.Math;

public class Backend implements BackendInterface
{ 
  private final int NUMBER_OF_PARKS = 52;
  private ParkDataReader reader; 
  private ParkDataReader reader2;
  private FileReader filereader;
  private FileReader filereader2;
  private LinkedList<ParkDummy> parkList;
  private ArrayList<String> tableKeys;
  private CS400Graph<Park> graph;
  private Hashtable<String, Park> table;
  private Park madison;
  
  public Backend(String parkDataFile, String graphDataFile) throws IOException, DataFormatException
  {
    try
    {
      this.filereader = new FileReader(parkDataFile);
      this.reader = new ParkDataReader();
      reader.readParkData(filereader);
      
      this.filereader2 = new FileReader(graphDataFile);
      this.reader2 = new ParkDataReader();
      reader2.readGraphData(filereader2);
      
      table = reader.parkTable;
      graph = reader2.parkGraph;
    }
    catch(IOException e)
    {
      throw new IOException("There is an input/output problem with the input data file.");
    }
    catch(DataFormatException e)
    {
      throw new DataFormatException("The data format of the input file is incompatible.");
    }
    tableKeys = new ArrayList<String>(table.keySet());
    madison = table.get("Madison (City)");
    
    //for(int i = 0; i < parkList.size(); i++)
    //{
    //  graph.insertVertex(parkList.get(i));
    //  for(int j = 0; j < parkList.get(i).getNeighbors().size(); j++)
    //  {
    //    graph.insertEdge(parkList.get(i), parkList.get(i).getNeighbors().get(j), parkList.get(i).getNeighborWeights().get(j));
    //  }
    //} //populates graph with both vertices and edges
  }
  
  public CS400Graph<Park>.Path returnPathTo(Park destination) 
  {
    
    CS400Graph<Park>.Path returnPath = graph.dijkstrasShortestPath(madison, destination);
    return returnPath;
  }

  public CS400Graph<Park>.Path fromTo(Park departure, Park destination)
  {
    CS400Graph<Park>.Path returnPath = graph.dijkstrasShortestPath(departure, destination);
    return returnPath;
  }

  public boolean doesParkExist(String searchQuery) 
  {
    for(int i = 0; i < parkList.size(); i++) 
    {
      if(parkList.get(i).getName().equals(searchQuery))
      {
        return true;
      }
    }
    return false;
  }

  public LinkedList<Park> findParks(String searchQuery)
  {
    LinkedList<Park> returnList = new LinkedList<Park>();
    for(int i = 0; i < tableKeys.size(); i++)
    {
      if(tableKeys.get(i).contains(searchQuery))
      {
        returnList.add(table.get(tableKeys.get(i)));
      }
    }
    return returnList;
  }

  public Park randomPark() 
  {
    int index = (int)Math.floor(Math.random() * tableKeys.size());
    String key = tableKeys.get(index);
    return table.get(key);
  }
}
