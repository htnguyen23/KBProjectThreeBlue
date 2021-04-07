import java.util.ArrayList;
import java.util.Scanner;

public class Frontend {
	
	// instance variables
	private Backend backend;
	private boolean firstTimeBaseMode = true;
	private boolean firstTimeParkMode = true;
	private boolean firstTimeRouteKeeperMode = true;
	private boolean firstTimeRandomMode = true;
	private Scanner scanner = new Scanner(System.in);
	private Park[] savedRoutes = new Park[3];
	
	
	public Frontend(Backend back) {
		this.backend = back;
	}
	
	public void baseMode() {
		String in = "";
		if (firstTimeBaseMode) {
			
			System.out.println("        ___        ___                       \\    |  |     /                            /\\                    _________________________ ___        ________");
			System.out.println("       /  _\\_^^^^_/_  \\                       \\   |  |    /                            /##\\                  /\\                        \\   \\      /        \\");
			System.out.println("      /  /  _\\__/_  \\  \\                   \\   \\ _|__|__ /   /                   /\\   /####\\                / /\\/ / / / / / / / / / /   \\   \\    /       *  \\__");
			System.out.println("      |//  / /  \\ \\  \\\\|                    \\   / ***** \\   /                   /**\\ /######\\    /\\         \\/ _\\/\\/ / / / / / / / / /   \\ __\\__/           |__|>");
			System.out.println("       / _/ / || \\ \\_ \\                   \\  \\ / ******* \\ /  /                /****/       /\\  /##\\         \\/ _\\/\\/\\/ / / / / / / /            \\_       _/");
			System.out.println("      / /  |  ||  |  \\ \\              _____\\_ / ********* \\ _/_____           /****/        ((\\/####\\         \\/  \\/\\/\\/\\/\\/ / / / /               \\_   _/");
			System.out.println("      \\/    \\/  \\/    \\/              _______ | ********* | _______          /    /          ))      \\             \\/\\/\\/\\/\\/\\/ / /                  \\_/");
			System.out.println("            |____|                         /  \\ ********* /  \\              /    /          //        \\             \\/\\/\\/\\/\\/\\/\\/                 __/");
			System.out.println("            |    |                        /  / \\ ******* / \\  \\            /    /          ((          \\             \\/\\/\\/\\/\\/\\/              ___/");
			System.out.println("            |____|                          /   \\_______/   \\                  /            \\\\          \\              /\\/\\/\\/________________/");
			System.out.println("            |    |                         /   /   |  |  \\   \\                /              ))          \\            /  / /  /    |      |");
			System.out.println("    /^^^^^^^|____|^^^^^^^\\                    /    |  |   \\                  /              //            \\          /  / /  /     |      |");  
			System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^               /     |  |    \\                /              //                       /__/_/__/     /|\\    /|\\ ");
			
			
			System.out.println("Welcome to The Golden Eagle Path! Here you can find a trip to any of the National Parks in our database.");
			System.out.println("If you add a park to your Route Keeper, it will save your destination park as well as the route to take to get there.");
			System.out.println("The route will take you to your chosen park by way of other National Parks -- so you can stop along the way!");
			System.out.println("As a note, you can only store up to three trips in your Route Keeper. \n");
			firstTimeBaseMode = false;
		}
		displayOptions("base");
		in = scanner.nextLine();
		if (in.equalsIgnoreCase("p")) {
			parkMode();
		}
		else if (in.equalsIgnoreCase("k")) {
			routeKeeperMode();
		}
		else if (in.equalsIgnoreCase("r")) {
			randomMode();
		}
		else if (in.equalsIgnoreCase("x")) {
			System.out.println("Thank you for using The Golden Eagle Path! Goodbye and have fun on your adventure!!!");
			System.out.println("                     _________________                                                                                      __________");
			System.out.println("                    /__  ____________/\\                                                                                  __\\          /__");
			System.out.println("          _________/  / / ||         \\ \\______________                                                                   \\     |\\ |     /");
			System.out.println("         /________/__/ /__||__________\\/____________/ \\                                                                  |     | \\|     |");
			System.out.println("        /_            /  __            \\           _\\/_|                                                                 |      __      |");
			System.out.println("        |_|        ___\\                /___       |__|_|                                                                 \\     |__|     /");     
			System.out.println(" _______|_|       /    \\              /    \\      |__|_|_______________________________________________                   \\    |       /");      
			System.out.println("   ___  |________|  ()  |____________|  ()  |________|_|      ____         ____        ____         ___                    \\    __    /");             
			System.out.println("                  \\ __ /              \\ __ /                                                                                \\  |__`  /");                       
			System.out.println(" ______________________________________________________________________________________________________                      \\ ,__| /");                  
			System.out.println("                                                                                                                              \\____/");                 
			
			System.exit(0);
		}
		else {
			System.out.println("Please enter valid input.");
			baseMode();
		}
		
	}
	
	
	public void parkMode() {
		String in = "";
		// CHANGE TO METHOD FROM BACKEND
		Park[] list = backend.getListOfParks();
		// ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
		if (firstTimeParkMode) {
			System.out.println("Welcome to Park Search Mode!");
			for (int i = 0; i < list.length; i++) {
				System.out.print("" + (i+1) + " -- " + list[i].getName() + "\t");
			}
			// ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
			System.out.println("\nAbove are all the National Parks in our database.");
			firstTimeParkMode = false;
		}
		displayOptions("park");
		in = scanner.nextLine();
		if (in.equalsIgnoreCase("x")) {
			baseMode();
		}
		else if (in.equalsIgnoreCase("a")) {
			addPark(list);
			// ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
		}
		else if (in.equalsIgnoreCase("n")) {
			for (int i = 0; i < list.length; i++) {
				System.out.print("" + (i+1) + " -- " + list[i].getName() + "\t");
			}
			// ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
			System.out.println("\nAbove are all the National Parks in our database.");
			parkMode();
		}
		else if (in.equalsIgnoreCase("i")) {
			System.out.println("Enter the index that appears before the park name.");
			in = scanner.nextLine();
			for (int i = 1; i <= list.length; i++) {
				if (in.equals("" + i)) {
					displayRoute(list[i-1]);
					parkMode();
				}
			} // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
			System.out.println("Please enter valid input.");
			parkMode();
		}
		else {
			for (int i = 1; i <= list.length; i++) {
				if (in.equals("" + i)) {
					System.out.println("Park Name: " + list[i-1].getName());
					System.out.println("State(s) park is in: " + list[i-1].getStates());
					System.out.println("Brief Description: " + list[i-1].getDescription());
					parkMode();
				} // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
			}
			System.out.println("Please enter valid input.");
			parkMode();
		}
		
	}

	public void addPark(Park[] parks) {
		// ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
		if (savedRoutes[2] != null) {
			System.out.println("Your Route Keeper is full. Please go to Route Keeper Mode and delete a route before trying to add another route.");
			parkMode();
		}
		System.out.println("Enter the index that appears before the park you would like to add.");
		String in = scanner.nextLine();
		for (int i = 0; i < parks.length; i++) {
			if (in.equals("" + (i+1))) {
				for (int j = 0; j < savedRoutes.length; j++) {
					if (savedRoutes[j] == null) {
						savedRoutes[j] = parks[i];
						System.out.println("The route to " + savedRoutes[j].getName() + " has been added. \n");
						baseMode();
					}
				}
			}
		} // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
		System.out.println("Please enter a valid index.");
		addPark(parks);
	}
	
	public void routeKeeperMode() {
		String in = "";
		if (firstTimeRouteKeeperMode) {
			System.out.println("Welcome to Route Keeper Mode! Here you can view and edit the routes you have previously saved.");
			firstTimeRouteKeeperMode = false;
		}
		if (savedRoutes[0] == null) {
			displayOptions("emptyKeeper");
			in = scanner.nextLine();
			if (in.equalsIgnoreCase("p")) {
				parkMode();
			}
			else if (in.equalsIgnoreCase("x")) {
				baseMode();
			}
			else {
				System.out.println("Please enter valid input.");
				routeKeeperMode();
			}
		}
		else {
			System.out.println("Here are your saved Routes: \n");
			for (int i = 0; i < savedRoutes.length; i++) {
				if (savedRoutes[i] != null) {
					System.out.println((i+1) + " -- Park Name: " + savedRoutes[i].getName() + " National Park");
					System.out.println("Located in: " + savedRoutes[i].getStates());
					System.out.println("Brief Description: \n" + savedRoutes[i].getDescription() + "\n");
				}
			}
			displayOptions("keeper");
			in = scanner.nextLine();
			if (in.equalsIgnoreCase("r")) {
				int temp = 0;
				System.out.println("Enter the index that appears before the park name that would like to remove.");
				in = scanner.nextLine();
				if (in.equals("1")) {
					temp = 0;
				}
				else if (in.equals("2")) {
					temp = 1;
				}
				else if (in.equals("3")) {
					temp = 2;
				}
				else {
					System.out.println("Please enter valid input.");
					routeKeeperMode();
				}
				if (temp == 2) {
					savedRoutes[2] = null;
					System.out.println("That route has been removed. \n");
					routeKeeperMode();
				}
				else {
					for (int i = temp; i < (savedRoutes.length-1); i++) {
						savedRoutes[i] = savedRoutes[i+1];
					}
					savedRoutes[2] = null;
					System.out.println("That route has been removed. \n");
					routeKeeperMode();
				}
			}
			else if (in.equalsIgnoreCase("t")) {
				int temp = 0;
				System.out.println("Enter the index that appears before the park name.");
				in = scanner.nextLine();
				if (in.equals("1")) {
					temp = 0;
				}
				else if (in.equals("2")) {
					temp = 1;
				}
				else if (in.equals("3")) {
					temp = 2;
				}
				else {
					System.out.println("Please enter valid input.");
					routeKeeperMode();
				}
				System.out.println("Congratulations!!! You have chosen the trip to " + savedRoutes[temp].getName() + "! Have fun visiting all the National Parks along the way!");
				System.out.println("Thank you for using our app! Goodbye.");
				
				System.out.println("                     _________________                                                                                      __________");
				System.out.println("                    /__  ____________/\\                                                                                  __\\          /__");
				System.out.println("          _________/  / / ||         \\ \\______________                                                                   \\     |\\ |     /");
				System.out.println("         /________/__/ /__||__________\\/____________/ \\                                                                  |     | \\|     |");
				System.out.println("        /_            /  __            \\           _\\/_|                                                                 |      __      |");
				System.out.println("        |_|        ___\\                /___       |__|_|                                                                 \\     |__|     /");     
				System.out.println(" _______|_|       /    \\              /    \\      |__|_|_______________________________________________                   \\    |       /");      
				System.out.println("   ___  |________|  ()  |____________|  ()  |________|_|      ____         ____        ____         ___                    \\    __    /");             
				System.out.println("                  \\ __ /              \\ __ /                                                                                \\  |__`  /");                       
				System.out.println(" ______________________________________________________________________________________________________                      \\ ,__| /");                  
				System.out.println("                                                                                                                              \\____/");                 
				
				System.exit(0);
			}
			else if (in.equalsIgnoreCase("x")) {
				baseMode();
			}
			else if (in.equals("1")) {
				displayRoute(savedRoutes[0]);
				routeKeeperMode();
			}
			else if (in.equals("2")) {
				if (savedRoutes[1] != null) {
					displayRoute(savedRoutes[1]);
					routeKeeperMode();
				}
				else {
					System.out.println("Please enter valid input.");
					routeKeeperMode();
				}
			}
			else if (in.equals("3")) {
				if (savedRoutes[2] != null) {
					displayRoute(savedRoutes[2]);
					routeKeeperMode();
				}
				else {
					System.out.println("Please enter valid input.");
					routeKeeperMode();
				}
			}
			else {
				System.out.println("Please enter valid input.");
				routeKeeperMode();
			}
		}
	}
	
	public void randomMode() {
		if (firstTimeRandomMode) {
			System.out.println("Welcome to Random Mode! Each time you come to this mode, you will receive a new suggestion.");
			firstTimeRandomMode = false;
		}
		String in = null;
		System.out.println("Here is a random suggestion for a park:");
		Park park = backend.getRandom();
		// ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
		System.out.println("1 -- Park Name: " + park.getName() + " National Park");
		System.out.println("Located in: " + park.getStates());
		System.out.println("Brief Description: \n" + park.getDescription());
		displayRoute(park);
		displayOptions("random");
		in = scanner.nextLine();
		if (in.equals("r")) {
			randomMode();
		}
		else if (in.equals("a")) {
			Park[] rand = {park};
			addPark(rand);
		}
		else if (in.equals("x")) {
			baseMode();
		}
		else {
			System.out.println("Please enter valid input.");
			randomMode();
		}
	}
	
	public void displayRoute(Park park) {
		Park[] route = backend.returnPathTo(park);
		// ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
		System.out.println("\nHere is the route from Madison to your chosen park: \n");
		for (int j = 0; j < route.length; j++) {
			System.out.println("Park Name: " + route[j].getName());
			System.out.println("Brief Description: \n" + route[j].getDescription());
			System.out.println("State(s) park is in: " + route[j].getStates() + "\n");
		} // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
	}
	
	private void displayOptions(String mode) {
		if (mode.equals("base")) {
			System.out.println("If you would like to search for a National Park, press 'p'.");
			System.out.println("If you would like a suggestion for a park, press 'r'.");
			System.out.println("If you would like to view the saved trips in your Route Keeper, press 'k'.");
			System.out.println("If you would like to exit the program, press 'x'. \n");
		}
		if (mode.equals("park")) {
			System.out.println("If you would like to see a list of National Parks in our database, press 'n'.");
			System.out.println("If you would like to see more information about a park, enter the index that appears before the park name.");
			System.out.println("If you would like to view more information about a route, press 'i'.");
			System.out.println("If you would like to add a park to your Route Keeper, press 'a'.");
			System.out.println("If you would like to go back, press 'x'. \n");
		}
		if (mode.equals("random")) {
			System.out.println("If would like to get a new suggestion, press 'r'.");
			System.out.println("If you would like to add this park to your Route Keeper, press 'a'.");
			System.out.println("If you would like to go back, press 'x'. \n");
		}
		if (mode.equals("keeper")) {
			System.out.println("If you would like to view more information about a route, enter the index that appears before the park name.");
			System.out.println("If you would like to remove a trip, press 'r'.");
			System.out.println("If you have decided on a trip, press 't'.");
			System.out.println("If you would like to go back, press 'x'. \n");
		}
		if (mode.equals("emptyKeeper")) {
			System.out.println("You do not have any saved trips.");
			System.out.println("If you would like to search for a National Park, press 'p'.");
			System.out.println("If you would like to go back, press 'x'. \n");
		}
	}

}
