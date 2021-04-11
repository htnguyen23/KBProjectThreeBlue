// --== CS400 File Header Information ==--
// Name: Xiaohan Zhu
// Email: xzhu274@wisc.edu
// Team: KB blue
// Role: data wrangler
// TA: Keren
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

import java.util.ArrayList;
import java.util.List;

public class Park implements ParkInterface{
	
	private String name;
	private String description;
	private String states;

	/**
	 * constructor for Park object
	 * 
	 * @param data String array of information about Park
	 */
    public Park(String[] data) {
    	this.name = data[0];
    	this.description = data[1];
    	this.states = data[2];
    }
    
    /**
     * name getter
     */
	@Override
	public String getName() {
		return this.name;
	}

	/**
	 * states getter
	 */
	@Override
	public String getStates() {
		return this.states;
	}

	/**
	 * states setter
	 * @param states
	 */
	@Override
	public void setStates(String states) {
		this.states = states;
		
	}

	/**
	 * description getter
	 */
	@Override
	public String getDescription() {
		return this.description;
	}

	/**
	 * description setter
	 * @param description
	 */
	@Override
	public void setDescription(String description) {
		this.description = description;
		
	}

	

}
