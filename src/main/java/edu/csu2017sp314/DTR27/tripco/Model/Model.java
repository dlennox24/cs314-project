package main.java.edu.csu2017sp314.DTR27.tripco.Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Model {

	
    private String status;
    private ArrayList<Location> Locations;
    private ArrayList<Leg> Legs;


    // Constructor
    // param: filename of the CSV file. 
    public Model(String filename) {
    	
    	//readCSV takes the filepath, and returns an arrayList of all locations
    	this.Locations = readCSV(filename);
    	
    	//generateLegs takes the Locations arrayList and generates arrayList of all Legs
    	//    all trips and calculations happen withtin generate legs
    	//    may need to be moved outside the constructor, and called to support options.
        this.Legs = generateLegs(this.Locations);
        
        this.status = "OK";
    }


    // generateLegs: 
    // param: arrayList of Locations
    //       Lost of heavy lifting within generateLegs. Trip order and coordinate calculation happen within
   
	private ArrayList<Leg> generateLegs(ArrayList<Location> locations) {
		
		// return arrayList of Legs
		ArrayList<Leg> ret = new ArrayList();
		
		// distances is a 2d array table of all possible distances between legs.
		// should be 0's down the asymtope.
		
		System.out.println("\n\ndistances array\n\n--------------------------------------------------------------------------------");
		int [][] distances = new int [ this.Locations.size()][ this.Locations.size()];
		for(int i = 0; i < this.Locations.size(); i++){
			for(int j = 0; j < this.Locations.size(); j++){

			Location a = this.Locations.get(i);
			Location b = this.Locations.get(j);
			Leg l = new Leg(a,b);
			distances[i][j] = l.getDistance();
					
			}


		}	

		
		
		for(int i = 0; i < this.Locations.size(); i++){
		System.out.print(" \t"+ i + "\t ");
		}
		System.out.println();
		for(int i = 0; i < this.Locations.size(); i++){
			System.out.print(i+" ");
			for(int j = 0; j < this.Locations.size(); j++){
				
				System.out.print("\t" + distances[i][j] + "\t");
				
				
			}
			System.out.println();
		}
		System.out.println("\n\n new distances array--------------------------------------------------------------------------------");
		
		//NEAREST NEIGHBOR
		for(int i = 0; i < this.Locations.size(); i++){
			//System.out.print("|");

			for(int j = 0; j < this.Locations.size(); j++){
				//System.out.print(distances[i][j] + "  ");
				

			}
			//System.out.println("|");

		}
		
		
		
		ArrayList<Integer> NN = new ArrayList();
		
		//set a high value to test against, every next low will replace this.
		int LOW = 99999;
		
		for(int i = 0; i < this.Locations.size(); i++){
			
			//arrayList of the visited locations indexes.
			//size check against the size of the locations array to tell when all nodes have been visited.
			ArrayList<Integer> visited = new ArrayList();
			
			int total = 0;
			int startingIndex = i;

			//start the the nearest neigbor at each element in the list.
			int currentIndex = i;
			
			//add the starting location to the visited list
			visited.add(currentIndex);
			
			while(visited.size() < this.Locations.size()){
				
			int least = 99999;
			int leastIndex = -1;
			
				//find the nearest location to the location that you are at.
				//looks though all distances in the distances 2d array in each locations row 
				for(int j = 0; j < this.Locations.size(); j++){
					
					//System.out.println("least = " + least +"\tdistances["+currentIndex+"]["+j+"] = " + distances[currentIndex][j]);
					
					
					//check to make sure the distance is not 0(would mean that it is the same location)
					//check if the distance to the location is less than the previous least
					//check if the location has been visited, by checking for the index in the visited list
					if ((distances[currentIndex][j] != 0) && (distances[currentIndex][j] < least) && !visited.contains(j)){
						
						//save the new smallest distance
						least = distances[currentIndex][j];
						//save the index of the location. the last leastIndex will become the next location.
						leastIndex = j;
						
						
						//System.out.print(least + " ");
						//System.out.println(leastIndex);
						
					}

					
				}
			
			//add the location with the shortest distance to the visited list, mark it visited
			visited.add(leastIndex);
			
			//System.out.println("added " + leastIndex);

			//least distance becomes the new distance
			currentIndex = leastIndex;
			total = total + least;
			}
			
			//add final leg from the ending locaiton ot the starting location.
			//not a value in the distances table, so have to make two locations and get the distance and add
			Location a = this.Locations.get(currentIndex);
			Location b = this.Locations.get(startingIndex);
			Leg l = new Leg(a,b);
			total = total + l.getDistance();
			
			System.out.print(visited + " ");
			System.out.print(l.getDistance() + "\t");
			System.out.println(total);
			
			//if the total distance is less then the other nearest tours. save the arraylist of the order of the trip
			//save the lowest distance
			if(total < LOW ){
				LOW = total;
				NN = visited;
			}

		}
		
		System.out.println();
		System.out.println(NN + "\t");
		
		long t = 0;
		
		//add the legs in order of the Nearest Neighbor arrayList
		//must -1 because the increment happens within.		
		for(int i = 0; i < NN.size()-1;){
			//make two locations, starting at index 0 of the nearest arrayList
			Location a = this.Locations.get(NN.get(i));
			i++;
			Location b = this.Locations.get(NN.get(i));
			
			//make a leg and add it to the return array lsit of legs.
			Leg l = new Leg(a,b);
			System.out.print(l.getDistance() + " | ");
			t = t + l.getDistance();
			ret.add(l);
		
		}
		
		//add the final leg, from the last location to the starting location.
		//have to look up the indexes from the nearest array list
		Location a = this.Locations.get(NN.get(NN.size()-1));
		Location b = this.Locations.get(NN.get(0));
		Leg l = new Leg(a,b);
		ret.add(l);
		t = t + l.getDistance();

		
		System.out.print(l.getDistance() + "\t total:");
		System.out.println(t);
		System.out.println();

		return ret;
	}

	//get the header line of the CSV file and return as string (id,name,longitude,latitude)
	private String getHeaderLine(Scanner scanner) {
		String ret = null;
		if(scanner.hasNextLine()){ 
			String line = scanner.nextLine();
			ret = line;
		}
		return ret;
	}
	
	//get the content lines of the CSV file and return as stringArray
	private ArrayList<String> getCSVlines(Scanner scanner2) {
		ArrayList<String> ret = new ArrayList();
		
		if(scanner2.hasNextLine()){ 
			scanner2.nextLine();
		}	
	
		while(scanner2.hasNextLine()){	
			String line = scanner2.nextLine();
			System.out.println(line);
			if (!line.equals("")){
				ret.add(line); 
			}
		}
		
		return ret;
	}
    
	
	//read the CSV file, generate a locationArray.
    private ArrayList<Location> readCSV(String filename) {
    	
    	String headerLine = null;
		ArrayList<String> csvLines = new ArrayList();
		
		
		try {
			
			Scanner scanner = new Scanner(new File(filename));
			Scanner scanner2 = new Scanner(new File(filename));
			
			headerLine = getHeaderLine(scanner);
			scanner = null;
			csvLines = getCSVlines(scanner2);
			scanner2 = null;
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
    	return generateLocationArray(headerLine, csvLines);
    	
	}
    
    //process the longitude coordinate of a DegreeMinSecond return a double
    private double processDMSlong(String longCordString){
    	double longitude;
    	
    	//set a value to keep track of if we have a positive or negative vcoordinate
    	int EWscalar = 0;
    	
    	//strip all white space from the string
    	longCordString= longCordString.replaceAll("\\s+","");    			
		System.out.println(longCordString);
		
		//check if the coordinate is East or West hemisphere
		if (longCordString.indexOf("E") >= 0 ){
			
			System.out.println("E");
			
			//strip the letter from the cordininate
			//set the scalar
			longCordString = longCordString.replaceAll("E", "");
			EWscalar = 1;
			
			
		}else {
			
			System.out.println("W");
			longCordString = longCordString.replaceAll("W", " ");
			System.out.println(longCordString);
			EWscalar = -1;
		
		}
		
		//strip the string of characters not a number or a decimal and replace wth a space
		longCordString = longCordString.replaceAll("[^0-9.]", " ");
		System.out.println(longCordString);
		
    	//strip all white space from the string
		longCordString = longCordString.replaceAll("\\s+"," ");
		System.out.println(longCordString);
		
		//split the string on spaces and extract the values
		String[] longCordArray = longCordString.split(" ");
		double longDeg = Double.parseDouble(longCordArray[0]);
		double longMin = Double.parseDouble(longCordArray[1]);
		double longSec = Double.parseDouble(longCordArray[2]);
		
		//turn the seperate fields into one coordinate
		longitude = (EWscalar) * (longDeg) + (longMin/60) + (longSec/3600);

    	return longitude;
    	
    }
    private double processDMSlat(String latCordString){
    	double latitude;
    	int NSscalar = 0;
    	
    	latCordString= latCordString.replaceAll("\\s+","");    			
		System.out.println(latCordString);
		
		if (latCordString.indexOf("N") >= 0 ){
			
			System.out.println("N");
			latCordString = latCordString.replaceAll("N", "");
			NSscalar = 1;
			
			
		}else {
			
			System.out.println("S");
			latCordString = latCordString.replaceAll("S", " ");
			System.out.println(latCordString);
			NSscalar = -1;
		
		}
		
		latCordString = latCordString.replaceAll("[^0-9.]", " ");
		System.out.println(latCordString);
		latCordString = latCordString.replaceAll("\\s+"," ");
		System.out.println(latCordString);
		
		String[] latCordArray = latCordString.split(" ");
		double latDeg = Double.parseDouble(latCordArray[0]);
		double latMin = Double.parseDouble(latCordArray[1]);
		double latSec = Double.parseDouble(latCordArray[2]);
		System.out.println("deg= " + latDeg);
		System.out.println("min= " + latMin);
		System.out.println("sec= " + latSec);

		
		latitude = (NSscalar) * ((latDeg) + (latMin/60) + (latSec/3600));
		System.out.println("lat = " + latitude);

    	return latitude;
    	
    }
    
	private double processDMlong(String longCordString) {
		double longitude;
    	int EWscalar = 0;
    	
    	longCordString= longCordString.replaceAll("\\s+","");    			
		System.out.println(longCordString);
		
		if (longCordString.indexOf("E") >= 0 ){
			
			System.out.println("E");
			longCordString = longCordString.replaceAll("E", "");
			EWscalar = 1;
			
			
		}else {
			
			System.out.println("W");
			longCordString = longCordString.replaceAll("W", " ");
			System.out.println(longCordString);
			EWscalar = -1;
		
		}
		
		longCordString = longCordString.replaceAll("[^0-9.]", " ");
		System.out.println(longCordString);
		longCordString = longCordString.replaceAll("\\s+"," ");
		System.out.println(longCordString);
		
		String[] longCordArray = longCordString.split(" ");
		double longDeg = Double.parseDouble(longCordArray[0]);
		double longMin = Double.parseDouble(longCordArray[1]);

		longitude = (EWscalar) * (longDeg) + (longMin/60);

    	return longitude;
	}
	private double processDMlat(String latCordString) {
	
		double latitude;
    	int NSscalar = 0;
    	
    	latCordString= latCordString.replaceAll("\\s+","");    			
		System.out.println(latCordString);
		
		if (latCordString.indexOf("N") >= 0 ){
			
			System.out.println("N");
			latCordString = latCordString.replaceAll("N", "");
			NSscalar = 1;
			
			
		}else {
			
			System.out.println("S");
			latCordString = latCordString.replaceAll("S", " ");
			System.out.println(latCordString);
			NSscalar = -1;
		
		}
		
		latCordString = latCordString.replaceAll("[^0-9.]", " ");
		System.out.println(latCordString);
		latCordString = latCordString.replaceAll("\\s+"," ");
		System.out.println(latCordString);
		
		String[] latCordArray = latCordString.split(" ");
		double latDeg = Double.parseDouble(latCordArray[0]);
		double latMin = Double.parseDouble(latCordArray[1]);
		System.out.println("deg= " + latDeg);
		System.out.println("min= " + latMin);

		
		latitude = (NSscalar) * ((latDeg) + (latMin/60) );
		System.out.println("lat = " + latitude);

    	return latitude;
		
	}

    private double processDlong(String longCordString){
    	int EWscalar = 0;
    	longCordString= longCordString.replaceAll("\\s+","");    			
		System.out.println(longCordString);
		
		if (longCordString.indexOf("E") >= 0 ){
			
			System.out.println("E");
			longCordString = longCordString.replaceAll("E", " ");
			EWscalar = 1;
			
			
		}else {
			
			System.out.println("W");
			longCordString= longCordString.replaceAll("W", " ");
			System.out.println(longCordString);
			EWscalar = -1;
		
		}
		
		longCordString = longCordString.replaceAll("[^0-9.]", " ");
		System.out.println(longCordString);
		longCordString = longCordString.replaceAll("\\s+"," ");
		System.out.println(longCordString);
		
		String[] longCordArray = longCordString.split(" ");
		double longDeg = Double.parseDouble(longCordArray[0]);
		double longitude = (EWscalar) * (longDeg) ;    			

		return longitude;
    	
    }

    
    private double processDlat(String latCordString){
    	int NSscalar = 0 ;
    	latCordString = latCordString.replaceAll("\\s+","");    			
		System.out.println(latCordString);
		
		if (latCordString.indexOf("N") >= 0 ){
			
			System.out.println("N");
			latCordString = latCordString.replaceAll("N", "");
			System.out.println(latCordString);
			NSscalar = 1;
			
			
		}else {
			
			System.out.println("S");
			latCordString = latCordString.replaceAll("S", " ");
			System.out.println(latCordString);
			NSscalar = -1;
		
		}
		
		latCordString = latCordString.replaceAll("[^0-9.]", " ");
		System.out.println(latCordString);
		latCordString = latCordString.replaceAll("\\s+"," ");
		System.out.println(latCordString);
		
		String[] latCordArray = latCordString.split(" ");
		double latDeg = Double.parseDouble(latCordArray[0]);
		double latitude = (NSscalar) * (latDeg) ;
		return latitude;
    	
    }

    //parse the lines from the CSV and create locations from the lines
	private ArrayList<Location> generateLocationArray(String headerline, ArrayList<String> csvLines) {
    	
		//return arraylist of locations
		ArrayList<Location> returnLocationArray = new ArrayList();
    	
    	//split the header line to get the different fields
    	String[] headerLineArray = headerline.split(",");
    	int idIndex = -1;
    	int nameIndex = -1;
    	int longIndex = -1;
    	int latIndex = -1;
    	
    	//save the index of the columns we care about
    	for(int i = 0; i < headerLineArray.length; i++ ){
  
    		if(headerLineArray[i].toUpperCase().equals("ID")){ idIndex = i; }
    		if(headerLineArray[i].toUpperCase().equals("NAME")){ nameIndex = i; }
    		if(headerLineArray[i].toUpperCase().equals("LONGITUDE")){ longIndex = i; }
    		if(headerLineArray[i].toUpperCase().equals("LATITUDE")){ latIndex = i; }
    		
    	}
    	
    	//extract a sample coordinate, to test for format
    	String cordCheck = csvLines.get(2);
		String[]  cordCheckArray = cordCheck.split(",");
		String cord = cordCheckArray[longIndex];
		cord = cord.replaceAll("\\s+","");
		
		System.out.println(cord);

		//test the coordinate to check if  in DMS. DM, or D format
		
		if (cord.indexOf("\"") >= 0 ){
			
			System.out.println("doublequote");
			
			//read all the lines form the CSV file
			for(int i = 0; i < csvLines.size(); i++){

				// get each line and split on commas
    			String line = csvLines.get(i);
    			String[] lineArray = line.split(",");
    			
    			//extract name and id from columns as strings
    			String id = lineArray[idIndex];
    			String name = lineArray[nameIndex];
    			
    			//process coordinates based on format
    			//LONG
    			String longCordString = lineArray[longIndex];
    			double longitude = processDMSlong(longCordString);
				
				
				//LAT
    			String latCordString = lineArray[latIndex];
    			double latitude = processDMSlat(latCordString);	
				//    			

    			//create the location and add to the return array
    			Location l = new Location(id, name, longitude, latitude);
    			System.out.println("adding location: [ " + l + " ]");
    			returnLocationArray.add(l);
    		
			}
			
			
			
			
			
		}else if (cord.indexOf("\'") >= 0 ){
			
			System.out.println("singlequote");
			
			
			for(int i = 0; i < csvLines.size(); i++){
    			String line = csvLines.get(i);
    			String[] lineArray = line.split(",");
    			
    			String id = lineArray[idIndex];
    			String name = lineArray[nameIndex];
    			
    			//LONG
    			String longCordString = lineArray[longIndex];
    			double longitude = processDMlong(longCordString);	
				//
				
				//LAT
				String latCordString = lineArray[latIndex];
    			double latitude = processDMlat(latCordString);
				//
    			Location l = new Location(id, name, longitude, latitude);
    			System.out.println("adding location: [ " + l + " ]");
    			returnLocationArray.add(l);
    		
			}
			
			
			
		}else if (cord.indexOf("°") >= 0 ){
			
			System.out.println("degree");
			
			
			for(int i = 0; i < csvLines.size(); i++){
    			String line = csvLines.get(i);
    			String[] lineArray = line.split(",");
    			
    			String id = lineArray[idIndex];
    			String name = lineArray[nameIndex];
    			
    			//LONG
    			String longCordString = lineArray[longIndex];
    			double longitude = processDlong(longCordString);	
				//
				
				//LAT
				String latCordString = lineArray[latIndex];
    			double latitude = processDlat(latCordString);
				//
    			Location l = new Location(id, name, longitude, latitude);
    			System.out.println("adding location: [ " + l + " ]");
    			returnLocationArray.add(l);
    		
			}
			
			
			
		}else{
			
			System.out.println("nothing");
			for(int i = 0; i < csvLines.size(); i++){
        		
    			String line = csvLines.get(i);
    			String[] lineArray = line.split(",");
    
    			String id = lineArray[idIndex];
    			String name = lineArray[nameIndex];
    			double longitude = Double.parseDouble(lineArray[longIndex]); 
    			double latitude = Double.parseDouble(lineArray[latIndex]); 
    			

    			Location l = new Location(id, name, longitude, latitude);
    			System.out.println("adding location: [ " + l + " ]");
    			returnLocationArray.add(l);
    		
			}

		}
			
		
        
		return returnLocationArray;
	}

	

	//getters and setters
	public String getStatus() {return status;}
	public Location getLocation(int i) {return Locations.get(i);}
	public Leg getLeg(int i) {return Legs.get(i);}
	public int getLegsLength() {return Legs.size();}
	public int getLocationsLength() {return Locations.size();}
	public ArrayList<Leg> getLegs() {return Legs;}
	public ArrayList<Location> getLocations() {return Locations;}




	
	public static void main(String[] args) {
		
		
		Model m = new Model("src/Model/inputDM.csv");
		
		for(int i = 0; i < m.Locations.size(); i++){
			System.out.println("Location [" + i + "] : " + m.getLocation(i));
		}
		for(int i = 0; i < m.Legs.size(); i++){
			System.out.println("Leg [" + i + "] : " + m.getLeg(i));
		}

		
			
	}



}