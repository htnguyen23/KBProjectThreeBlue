// --== CS400 File Header Information ==--
// Name: Casey Waddell
// Email: ctwaddell@wisc.edu
// Team: KB
// TA: Keren
// Lecturer: Gary
// Notes to Grader: 

import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.util.zip.DataFormatException;
import org.junit.jupiter.api.Test;

public class BackendTests 
{
  /**
   * testConstructor makes sure the Backend constructor works and creates an
   * object as it is supposed to. This also looks for fringe cases like if a filename
   * is wrong/doesn't exist, if the file is null, if the filetype is wrong, etc.
   */
  @Test
  void testConstructor() 
  {
    try //test basic constructor
    {
      Backend test = new Backend("national_park_info.csv", "national_parks.csv");
      if(test.equals(null))
      {
        fail("The created Backend does not exist. Error with constructor.");
      }
    }
    catch(IOException e){}
    catch(DataFormatException e){}
    catch(NullPointerException e)
    {
      fail("The created Backend does not exist. Error with constructor.");
    }
    
    try //test constructor with fake filenames
    {
      Backend test2 = new Backend("national_park_info.csv", "national_parks.csv");
    }
    catch(IOException e)
    {
      //expected case
    }
    catch(DataFormatException e){}
    catch(NullPointerException e){}
    
    try //test constructor with wrong file types
    {
      Backend test3 = new Backend("national_park_info.csv", "national_parks.csv");
    }
    catch(IOException e){}
    catch(DataFormatException e)
    {
      //expected case
    }
    catch(NullPointerException e){}
  }
  
  /**
   * testSearchingFinding tests the final methods which include doesParkExist(), findParks(), and randomPark()
   * doesParkExist() returns a boolean if the park represented by a string search query exists
   * findParks() returns all the parks in the Backend's hashtable
   * randomPark() returns a random park object
   */
  @Test
  void testSearchingFinding()
  {
    try
    {
      Backend test = new Backend("national_park_info.csv", "national_parks.csv");
      Park[] a = new Park[0];
      String[] c = {"a","a","a"};
      Park b = new Park(c);
      Park[] testArray = test.findParks();
      if(!testArray.getClass().equals(a.getClass()))
      {
        fail("The findParks() method did not return the right object type");
      }
      if(testArray.length != 51)
      {
        fail("The findParks() method did not instantiate to the right size");
      }
      if(!testArray[0].getClass().equals(b.getClass()))
      {
        fail("The findParks() method did not contain the right object type");
      }
      
      if(test.doesParkExist("Grand Teton") == false)
      {
        fail("The doesParkExist() method didn't return a Park that should exist");
      }
      if(test.doesParkExist("a") == true)
      {
        fail("The doesParkExist() method returned true for a Park that shouldn't exist");
      }
      
      if(!test.randomPark().getClass().equals(b.getClass()))
      {
        fail("The randomPark() method doesn't return an object of class Park when it should");
      }
      Park a1 = test.randomPark();
      Park a2 = test.randomPark();
      Park a3 = test.randomPark();
      Park a4 = test.randomPark();
      Park a5 = test.randomPark();
      if(a1.equals(a2) && a2.equals(a3) && a3.equals(a4) && a4.equals(a5))
      {
        fail("The randomPark() method likely doesn't use proper randomness. The chance of this is being a false negative is .00000003 percent, though.");
      }
    }
    catch(IOException e){}
    catch(DataFormatException e){}
  }
  
  /**
   * testPathing tests the 2 pathing methods included in the Backend. These two tests are a path
   * between any 2 given parks or a path between madison and a given park-- fromTo() and returnPathTo() respectively.
   */
  @Test
  void testPathing()
  {
    try
    {
      Backend test = new Backend("national_park_info.csv", "national_parks.csv");
      Park[] destinations = test.findParks();
      Park destination = destinations[0]; //this park should be "Voyaguers"
      CS400Graph<Park>.Path testPath = test.returnPathTo(destination);
      if(testPath.distance != 778) //value should be 778 as calculated by hand
      {
        fail("The calculated distance did not match the actual shortest distance.");
      }
      Park destination2 = destinations[5];
      CS400Graph<Park>.Path testPath2 = test.returnPathTo(destination2);
      if(testPath2.distance != 2068)
      {
         fail("The calculated distance did not match the actual shortest distance.");
      }
      for(int i = 0; i < destinations.length; i++)
      {
        System.out.println(destinations[i].getName() + " at index " + i);
        
      }
      
      //Madison is at index 40, Island Royale is at 42
      if(test.fromTo(destinations[40], destinations[42]).distance != 374)
      {
        fail("The calculated distance between Madison and Island Royale did not match the actual distance.");
      }
      //Saguaro is at index 20, Big Bend is at index 8
      if(test.fromTo(destinations[20], destinations[8]).distance != 652)
      {
        fail("The calculated distance between Saguaro and Big Bend did not match the actual distance.");
      }
      if(test.fromTo(destinations[0], destinations[0]).distance != 0)
      {
        fail("The calculated distance from a location to itself is not 0 as it should be.");
      }
    }
    catch(IOException e){}
    catch(DataFormatException e){}
  }

}
