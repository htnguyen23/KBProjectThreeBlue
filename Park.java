import java.util.ArrayList;
import java.util.List;

public class Park implements ParkInterface{
	
	private String name;
	private String description;
	private String states;

	
    public Park(String[] data) {
    	this.name = data[0];
    	this.description = data[1];
    	this.states = data[2];
    }
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getStates() {
		return this.states;
	}

	@Override
	public void setStates(String states) {
		this.states = states;
		
	}

	@Override
	public String getDescription() {
		return this.description;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
		
	}

	

}
