import java.util.List;

public interface ParkInterface extends Comparable<ParkInterface>{
	public String getName();
	public String getStates();
	public void setStates(String states);
	public String getDescription();
	public void setDescription(String description);
	
//	public List<ParkInterface> getNeighbors();
//	public List<Integer> getWeights();
	public CS400Graph.Vertex getPredecessor();
	
	// from super class Comparable
	public int compareTo(ParkInterface otherPark);

}
