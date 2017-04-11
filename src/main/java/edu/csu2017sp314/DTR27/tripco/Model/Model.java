package main.java.edu.csu2017sp314.DTR27.tripco.Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Model {
	private String status;
	private ArrayList<Location> Locations;
	private ArrayList<Leg> Legs;
	public String units;

	// Constructor
	// param: filename of the CSV file. 
	public Model(String filename, String opt, String units) {
		//readCSV takes the filepath, and returns an arrayList of all locations
		this.units=units;
		this.Locations = readCSV(filename);

		//generateLegs takes the Locations arrayList and generates arrayList of all Legs
		//    all trips and calculations happen withtin generate legs
		//    may need to be moved outside the constructor, and called to support options.
		this.Legs = generateLegs(this.Locations, opt);

		this.status = "OK";
	}

	// generateLegs: 
	// param: arrayList of Locations
	//       Lost of heavy lifting within generateLegs. Trip order and coordinate calculation happen within

	private ArrayList<Leg> generateLegs(ArrayList<Location> locations, String opt) {
		// return arrayList of Legs
		ArrayList<Leg> ret = new ArrayList<Leg>();

		// distances is a 2d array table of all possible distances between legs.
		// should be 0's down the asymtope.

		System.out.println("\n\ndistances array\n\n--------------------------------------------------------------------------------");
		int [][] distances = new int [ this.Locations.size()][ this.Locations.size()];
		for(int i = 0; i < this.Locations.size(); i++){
			for(int j = 0; j < this.Locations.size(); j++){
				Location a = this.Locations.get(i);
				Location b = this.Locations.get(j);
				Leg l = new Leg(a,b, this.units);
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

		ArrayList<Integer> nearestNeighbor = new ArrayList<Integer>();

		//set a high value to test against, every next low will replace this.
		int low = 99999;
		ArrayList<ArrayList<Leg>> allTours = new ArrayList<ArrayList<Leg>>();

		for(int i = 0; i < this.Locations.size(); i++){
			//arrayList of the visited locations indexes.
			//size check against the size of the locations array to tell when all nodes have been visited.
			ArrayList<Integer> visited = new ArrayList<Integer>();

			int total = 0;
			int startingIndex = i;

			//start the the nearest neighbor at each element in the list.
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

			//add final leg from the ending location to the starting location.
			//not a value in the distances table, so have to make two locations and get the distance and add
			Location a = this.Locations.get(currentIndex);
			Location b = this.Locations.get(startingIndex);
			Leg l = new Leg(a,b, this.units);
			total = total + l.getDistance();

			System.out.print(visited + " ");
			System.out.print(l.getDistance() + "\t");
			System.out.println(total);
			ArrayList<Leg> temp = createLegArrayFromInt(visited);
			allTours.add(temp);

			//if the total distance is less then the other nearest tours. save the arraylist of the order of the trip
			//save the lowest distance
			if(total < low ){
				low = total;
				nearestNeighbor = visited;
			}
		}

		System.out.println();
		ArrayList<Leg> temp2 = createLegArrayFromInt(nearestNeighbor);
		System.out.println(calcTotalDistLegs(temp2));
		
		Optimizer optimize = new Optimizer();
		if(opt == "3"){
			return optimize.threeOpt(allTours);
		}else if(opt == "2"){
			return optimize.twoOpt(allTours);
		}else{
			return createLegArrayFromInt(nearestNeighbor);
		}
	}

	private ArrayList<Leg> createLegArrayFromInt(ArrayList<Integer> nearestNeighbor) {
		long t = 0;
		ArrayList<Leg> ret = new ArrayList<Leg>();
		for(int i = 0; i < nearestNeighbor.size()-1;){
			//make two locations, starting at index 0 of the nearest arrayList
			Location a = this.Locations.get(nearestNeighbor.get(i));
			i++;
			Location b = this.Locations.get(nearestNeighbor.get(i));

			//make a leg and add it to the return array lsit of legs.
			Leg l = new Leg(a,b, this.units);
			//System.out.print(l.getDistance() + " | ");
			t = t + l.getDistance();
			ret.add(l);

		}

		//add the final leg, from the last location to the starting location.
		//have to look up the indexes from the nearest array list
		Location a = this.Locations.get(nearestNeighbor.get(nearestNeighbor.size()-1));
		Location b = this.Locations.get(nearestNeighbor.get(0));
		Leg l = new Leg(a,b, this.units);
		ret.add(l);
		t = t + l.getDistance();
		return ret;
	}

	
	public int calcTotalDistLegs(ArrayList<Leg> nodes){
		ArrayList<Location> n = new ArrayList<Location>();

		for(int i=0;i<nodes.size();i++){
			n.add(nodes.get(i).getLocations().get(0));
		}
		System.out.println(n);
		int count=0;
		
		for(int i=0;i<nodes.size()-1;i++){
			Leg leg = new Leg(n.get(i),n.get(i+1), this.units);
			count+=leg.getDistance();
			//System.out.print(leg.getDistance() + " ");
		}
		Leg leg = new Leg(n.get(n.size()-1),n.get(0), this.units);
		count = count + leg.getDistance();
		return count;
	}
	public int calcTotalDist(ArrayList<Location> nodes){
		int count=0;
		for(int i=0;i<nodes.size()-1;i++){
			Leg leg = new Leg(nodes.get(i),nodes.get(i+1), this.units);
			count+=leg.getDistance();
		}
		Leg leg = new Leg(nodes.get(nodes.size()-1),nodes.get(0), this.units);
		count = count + leg.getDistance();
		return count;
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
		ArrayList<String> ret = new ArrayList<String>();

		if(scanner2.hasNextLine()){ 
			scanner2.nextLine();
		}	

		while(scanner2.hasNextLine()){	
			String line = scanner2.nextLine();
			System.out.println("Line = " +line);
			if (!line.isEmpty()){
				ret.add(line); 
			}
		}

		return ret;
	}

	//read the CSV file, generate a locationArray.
	private ArrayList<Location> readCSV(String filename) {
		String headerLine = null;
		ArrayList<String> csvLines = new ArrayList<String>();

		try {
			System.out.println(filename);
			Scanner scanner = new Scanner(new File(filename));
			Scanner scanner2 = new Scanner(new File(filename));

			headerLine = getHeaderLine(scanner);
			//scanner = null;
			csvLines = getCSVlines(scanner2);
			//scanner2 = null;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return generateLocationArray(headerLine, csvLines);
	}
	
	
	//parse the lines from the CSV and create locations from the lines
	private ArrayList<Location> generateLocationArray(String headerline, ArrayList<String> csvLines) {
		//return arraylist of locations
		ArrayList<Location> returnLocationArray = new ArrayList<Location>();

		//split the header line to get the different fields
		String[] headerLineArray = headerline.split(",");
		int idIndex = -1;
		int nameIndex = -1;
		int longIndex = -1;
		int latIndex = -1;
		int elevationIndex = -1;
		int municipalityIndex = -1;
		int regionIndex = -1;
		int countryIndex = -1;
		int continentIndex = -1;
		int airportwikiIndex = -1;
		int regionwikiIndex = -1;
		int countrywikiIndex = -1;
		

		//save the index of the columns we care about
		for(int i = 0; i < headerLineArray.length; i++ ){
			if(headerLineArray[i].toUpperCase().equals("ID")){ idIndex = i; }
			if(headerLineArray[i].toUpperCase().equals("NAME")){ nameIndex = i; }
			if(headerLineArray[i].toUpperCase().equals("LONGITUDE")){ longIndex = i; }
			if(headerLineArray[i].toUpperCase().equals("LATITUDE")){ latIndex = i; }
			if(headerLineArray[i].toUpperCase().equals("ELEVATION")){ elevationIndex = i; }
			if(headerLineArray[i].toUpperCase().equals("MUNICIPALITY")){ municipalityIndex = i; }
			if(headerLineArray[i].toUpperCase().equals("REGION")){ regionIndex = i; }
			if(headerLineArray[i].toUpperCase().equals("COUNTRY")){ countryIndex = i; }
			if(headerLineArray[i].toUpperCase().equals("CONTINENT")){ continentIndex = i; }
			if(headerLineArray[i].toUpperCase().equals("AIRPORTWIKI")){ airportwikiIndex = i; }
			if(headerLineArray[i].toUpperCase().equals("COUNTRYWIKI")){ countrywikiIndex = i; }
			if(headerLineArray[i].toUpperCase().equals("REGIONWIKI")){ regionwikiIndex = i; }

		
		
		}

		//extract a sample coordinate, to test for format
		System.out.println(csvLines.size());
		String cordCheck = csvLines.get(0);
		String[]  cordCheckArray = cordCheck.split(",");
		String cord = cordCheckArray[longIndex];
		cord = cord.replaceAll("\\s+","");

		System.out.println(cord);
		//test the coordinate to check if  in DMS. DM, or D format

			System.out.println("nothing");
			for(int i = 0; i < csvLines.size(); i++){
				String line = csvLines.get(i);
				String[] lineArray = line.split(",");

				String id = lineArray[idIndex];
				String name = lineArray[nameIndex];
				
				double longitude = Double.parseDouble(lineArray[longIndex]);
				System.out.println(lineArray[latIndex]);
				double latitude = 0;
				try{
				latitude = Double.parseDouble(lineArray[latIndex]); 
				}catch(Exception e){
					
				}

				//Location l = new Location(id, name, longitude, latitude);

				String elevation;
				try{
					 elevation = lineArray[elevationIndex];
					}catch(Exception e){
						elevation = "";
					};
				
				String municipality;
				try{
					 municipality = lineArray[municipalityIndex];
					}catch(Exception e){
						municipality = "";
					};
				
				String region;
				try{
					 region = lineArray[regionIndex];
					}catch(Exception e){
						region = "";
					};
				
				String country;
				try{
					 country = lineArray[countryIndex];
					}catch(Exception e){
						country = "";
					};
				
				String continent;
				try{
					 continent = lineArray[continentIndex];
					}catch(Exception e){
						continent = "";
					};
				
				String airportURL;
				try{
					 airportURL = lineArray[airportwikiIndex];
					}catch(Exception e){
						airportURL = "";
					};
				
				String regionURL;
				try{
				 regionURL = lineArray[regionwikiIndex];
				}catch(Exception e){
					regionURL = "";
				};
				
				String countryURL;
				try{
					 countryURL = lineArray[countrywikiIndex];
					}catch(Exception e){
						countryURL = "";
					};

				

				System.out.println(id + " "  + name + " "  + longitude + " " + latitude + " " + elevation + " " + municipality +  " " + region + " " + country + " " + continent + " " + airportURL + " " + regionURL + " " + " " + countryURL);
				Location l = new Location(id, name, longitude, latitude, elevation, municipality, region, country, continent, airportURL, regionURL, countryURL);
				System.out.println("adding location: [ " + l + " ]");
				returnLocationArray.add(l);
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
}
