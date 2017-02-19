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
			System.out.println(line);
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
    
    private double processDMSlong(String longCordString){
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
		double longSec = Double.parseDouble(longCordArray[2]);
		
		longitude = (EWscalar) * (longDeg) + (longMin/60) + (longSec/3600);

    	return longitude;
    	
    }
    private double processDMSlat(String latCordString){
    	double latitude;
    	int NSscalar = 0;
    	
    	latCordString= latCordString.replaceAll("\\s+","");    			
		System.out.println(latCordString);
		
		if (latCordString.indexOf("E") >= 0 ){
			
			System.out.println("E");
			latCordString = latCordString.replaceAll("E", "");
			NSscalar = 1;
			
			
		}else {
			
			System.out.println("W");
			latCordString = latCordString.replaceAll("W", " ");
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
		
		latitude = (NSscalar) * (latDeg) + (latMin/60) + (latSec/3600);

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
    	
    	String cordCheck = csvLines.get(2);
		String[]  cordCheckArray = cordCheck.split(",");
		
		String cord = cordCheckArray[longIndex];
		cord = cord.replaceAll("\\s+","");
		System.out.println(cord);

		
		if (cord.indexOf("\'") >= 0 ){
			
			System.out.println("backquote");
			
			for(int i = 0; i < csvLines.size(); i++){

    			String line = csvLines.get(i);
    			String[] lineArray = line.split(",");
    			
    			String id = lineArray[idIndex];
    			String name = lineArray[nameIndex];
    			
    			//LONG
    			String longCordString = lineArray[longIndex];
    			double longitude = processDMSlong(longCordString);
				//
				
				//LAT
    			String latCordString = lineArray[latIndex];
    			double latitude = processDMSlat(latCordString);	
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

	public String getStatus() {return status;}

	public Location getLocation(int i) {return this.Locations.get(i);}
	public Leg getLeg(int i) {return this.Legs.get(i);}

	
	public static void main(String[] args) {
		
		
		Model m = new Model("/home/ap/Documents/DTR-27/src/Model/inputDMS.csv");
		
		for(int i = 0; i < m.Locations.size(); i++){
			System.out.println("Location [" + i + "] : " + m.Locations.get(i));
		}
		for(int i = 0; i < m.Legs.size(); i++){
			System.out.println("Leg [" + i + "] : " + m.Legs.get(i));
		}
		
	
		
	}


}
