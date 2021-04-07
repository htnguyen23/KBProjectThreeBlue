import java.io.IOException;

public class Main {
	public static void main(String[] args) {
		try {
			Backend graph = new Backend("national_parks.csv", "National Park Descriptions and States.csv");
			Frontend run = new Frontend(graph);
			run.baseMode();
		}
		catch (IOException e) {
			System.out.println("Could not import file or run program.");
		}
	}
}
