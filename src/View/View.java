package View;

import java.util.ArrayList;

public class View {
	private String status;
	private ArrayList<String> flags;
	private String header;
	private int totalDist;
	
	public View(String header, int totalDist, ArrayList<String> flags) {
		this.status = "OK";
		this.header = header;
		this.totalDist = totalDist;
		this.flags = flags;
	}

	public boolean addLeg(){
		// add a leg to the SVG and XML file
		// check for labels and distances
		return false;
	}

	private boolean addLine(){
		// add line to SVG and labels/distances if needed
		return false;
	}
	
	private boolean addDist(){
		// Create the SVG distance label
		return false;
	}	

	public boolean addLabel(){
		// Add label to city locations in SVG
		return false;
	}
	
	public String getStatus(){
		return this.status;
	}
	public void setStatus(String status){
		this.status = status;
	}
}