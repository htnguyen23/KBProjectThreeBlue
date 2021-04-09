import java.util.LinkedList;
import CS400Graph.Path;

public interface BackendInterface 
{
  public Path returnPathTo(ParkDummy destination);
  public Path fromTo(ParkDummy departure, ParkDummy destination);
  public boolean doesParkExist(String searchQuery);
  public LinkedList<ParkDummy> findParks(String searchQuery);
  public ParkDummy randomPark();
  //I will only implement the bottom ones if I have time or if we want them bad enough
  //essentially they let you add a stop in between the departure and destination
  //public Path fromToTo(Vertex departure, Vertex stop, Vertex destination);
  //public Path fromToToTo(Vertex departure, Vertex stop1, Vertex stop2, Vertex destination);
}
