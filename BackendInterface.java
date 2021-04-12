// --== CS400 File Header Information ==--
// Name: Casey Waddell
// Email: ctwaddell@wisc.edu
// Team: KB
// TA: Keren
// Lecturer: Gary
// Notes to Grader: 

import java.util.LinkedList;

public interface BackendInterface {
	public CS400Graph<Park>.Path returnPathTo(Park destination);

	public CS400Graph<Park>.Path fromTo(Park departure, Park destination);

	public boolean doesParkExist(String searchQuery);

	public Park[] findParks();

	public Park randomPark();
	// I will only implement the bottom ones if I have time or if we want them bad
	// enough
	// essentially they let you add a stop in between the departure and destination
	// public Path fromToTo(Vertex departure, Vertex stop, Vertex destination);
	// public Path fromToToTo(Vertex departure, Vertex stop1, Vertex stop2, Vertex
	// destination);
}
