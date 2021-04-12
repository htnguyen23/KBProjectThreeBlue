import java.io.IOException;
import java.util.zip.DataFormatException;

public class Main {
	public static void main(String[] args) {
		try {
			//IM: changed order of filenames to match Backend's constructor
			Backend graph = new Backend("national_park_descriptions_and_states.csv", "national_parks.csv");
			Frontend run = new Frontend(graph);
			run.baseMode();
		}
		catch (IOException e) {
			System.out.println("Could not import file or run program.");
		} catch (DataFormatException e) {
			System.out.println("DataFormatException: Could not import file or run program.");
		}
	}
}
