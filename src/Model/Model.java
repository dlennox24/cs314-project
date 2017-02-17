package Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Model {

    private String status;
    private ArrayList<Location> Locations;
    private ArrayList<Leg> Legs;


    // Constructor
    public Model(String filename) {
    	this.Locations = readCSV(filename);
        this.Legs = generateLegs(this.Locations);
        this.status = "OK";
    }


	private ArrayList<Leg> generateLegs(ArrayList<Location> locations) {
		ArrayList<Leg> ret = new ArrayList();

		for(int i = 0; i < locations.size()-1;){

			Location a = locations.get(i);
			i++;
			Location b = locations.get(i);
			Leg l = new Leg(a,b);
			ret.add(l);
			
		}
		
		Leg l = new Leg(locations.get(locations.size()-1),locations.get(0));
		ret.add(l);
		
//		for(int i = 0; i < locations.size(); i++){
//			for(int j = i+1; j < locations.size(); j++){
//				Leg l = new Leg(locations.get(i),locations.get(j));
//				ret.add(l);
//				
//			}
//		}

		return ret;
	}

	private String getHeaderLine(Scanner scanner) {
		String ret = null;
		if(scanner.hasNextLine()){ 
			String line = scanner.nextLine();
			ret = line;
		}
		return ret;
	}
	
	private ArrayList<String> getCSVlines(Scanner scanner2) {
		ArrayList<String> ret = new ArrayList();
		
		if(scanner2.hasNextLine()){ 
			scanner2.nextLine();
		}	
	
		while(scanner2.hasNextLine()){	
			String line = scanner2.nextLine();
			if (!line.equals("")){
				ret.add(line); 
			}
		}
		
		return ret;
	}
    
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

	private ArrayList<Location> generateLocationArray(String headerline, ArrayList<String> csvLines) {
    	ArrayList<Location> returnLocationArray = new ArrayList();
    	
    	String[] headerLineArray = headerline.split(",");
    	int idIndex = -1;
    	int nameIndex = -1;
    	int longIndex = -1;
    	int latIndex = -1;
    	
    	for(int i = 0; i < headerLineArray.length; i++ ){
  
    		if(headerLineArray[i].toUpperCase().equals("ID")){ idIndex = i; }
    		if(headerLineArray[i].toUpperCase().equals("NAME")){ nameIndex = i; }
    		if(headerLineArray[i].toUpperCase().equals("LONGITUDE")){ longIndex = i; }
    		if(headerLineArray[i].toUpperCase().equals("LATITUDE")){ latIndex = i; }
    		
    	}
    	
    	
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


		return returnLocationArray;
	}

	public String getStatus() {return status;}

	public Location getLocation(int i) {return this.Locations.get(i);}
	public Leg getLeg(int i) {return this.Legs.get(i);}

	
	public static void main(String[] args) {
		
		
		Model m = new Model("/s/bach/l/under/pello/school/DTR-27/src/Model/input.csv");
		
		for(int i = 0; i < m.Locations.size(); i++){
			System.out.println("Location [" + i + "] : " + m.Locations.get(i));
		}
		for(int i = 0; i < m.Legs.size(); i++){
			System.out.println("Leg [" + i + "] : " + m.Legs.get(i));
		}
		
	
		
	}


}
