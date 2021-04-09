import java.io.FileReader;
import java.util.Hashtable;
import java.util.LinkedList;
import CS400Graph.Path;
import CS400Graph.Vertex;

public class ParkDataReaderDummy
{
  public ParkDataReaderDummy(FileReader f)
  {
    
  }
    
  public LinkedList<ParkDummy> readDataSetList(FileReader filereader)
  {
    LinkedList<ParkDummy> r = new LinkedList<>();
    return r;
  }
  
  public Hashtable<ParkDummy, Vertex> readDataSet(FileReader filereader) 
  {
      Hashtable<ParkDummy, Vertex> r = new HashMap<>();
      return r;
  }
}