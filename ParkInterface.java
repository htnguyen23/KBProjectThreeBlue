// --== CS400 File Header Information ==--
// Name: Xiaohan Zhu
// Email: xzhu274@wisc.edu
// Team: KB blue
// Role: data wrangler
// TA: Keren
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

import java.util.List;

public interface ParkInterface{
	public String getName(); //name getter
	public String getStates(); //states getter
	public void setStates(String states); //states setter
	public String getDescription(); //description getter
	public void setDescription(String description); //description setter
	
//	public List<ParkInterface> getNeighbors();
//	public List<Integer> getWeights();
//	public CS400Graph.Vertex getPredecessor();
	
	// from super class Comparable
//	public int compareTo(ParkInterface otherPark);

}
