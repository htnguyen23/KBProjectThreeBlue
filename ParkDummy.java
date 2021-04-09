import java.util.LinkedList;

public class ParkDummy
{
  private LinkedList<ParkDummy> neighbors;
  private LinkedList<Integer> neighborWeights;
  private String name;
  
  public ParkDummy()
  {
    
  }
  
  public String getName()
  {
    return name;
  }
  
  public LinkedList<ParkDummy> getNeighbors()
  {
    return neighbors;
  }
  
  public LinkedList<Integer> getNeighborWeights()
  {
    return neighborWeights;
  }
}
