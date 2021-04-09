import java.io.FileReader;
import java.util.Hashtable;
import java.util.LinkedList;
import java.lang.Math;
import CS400Graph.Path;
import CS400Graph.Vertex;
import CS400Graph.Edge;
import CS400Graph.*;

public class Backend implements BackendInterface
{ 
  private ParkDataReaderDummy reader; 
  private FileReader filereader;
  private LinkedList<ParkDummy> parkList;
  private LinkedList<Vertex> vertexList;
  private CS400Graph graph;
  private ParkDummy madison;
  
  public Backend(String filename)
  {
    this.filereader = new FileReader(filename);
    this.reader = new ParkDataReaderDummy(filereader);
    parkList = reader.readDataSetList(filereader);
    graph.vertices = reader.readDataSet(filereader);
      
    madison = parkList.get(0);
    
    for(int i = 0; i < parkList.size(); i++)
    {
      graph.insertVertex(parkList.get(i));
      for(int j = 0; j < parkList.get(i).getNeighbors().size(); j++)
      {
        graph.insertEdge(parkList.get(i), parkList.get(i).getNeighbors().get(j), parkList.get(i).getNeighborWeights().get(j));
      }
    } //populates graph with both vertices and edges
  }
  
  public Path returnPathTo(ParkDummy destination) 
  {
    
    Path returnPath = CS400Graph.dijkstrasShortestPath(madison, destination);
    return returnPath;
  }

  public Path fromTo(ParkDummy departure, ParkDummy destination)
  {
    Path returnPath = CS400Graph.dijkstrasShortestPath(departure, destination);
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

  public LinkedList<ParkDummy> findParks(String searchQuery)
  {
    LinkedList<ParkDummy> returnList = new LinkedList<ParkDummy>();
    for(int i = 0; i < parkList.size(); i++)
    {
      if(parkList.get(i).getName().contains(searchQuery))
      {
        returnList.add(parkList.get(i));
      }
    }
    return returnList;
  }

  public ParkDummy randomPark() 
  {
    int index = (int)Math.floor(Math.random() * parkList.size());
    return parkList.get(index);
  }
}
