import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import Model.Leg;
import Model.Location;
import Model.Model;
import Presenter.Presenter;
import Presenter.Trip;
import View.View;
import View.TripCoGUI;
public class TripCo {

	
	public static void main(String[] args) {



		
		String option = "";
		String filename = null;
		String selectionFilename = null;
		String svgFilename = null;
		ArrayList<Integer> selectedIDs = new ArrayList<Integer>();

		for (int i = 0; i < args.length; i++){
			String temp = args[i];
			if (temp.contains(".xml")){
				selectionFilename = temp;				
			}else if(temp.contains(".svg")){
				svgFilename= temp;
			}else if(temp.contains("-")){
				option += temp;
			}else{
				filename=temp;
			}
			
		}
		
		
		Model model = null; 
		if(selectionFilename != null){	
			String tempSelectionsCSVfilename = "tempSELECTIONS.csv";
			try {
				makeSelectionCSV(filename, tempSelectionsCSVfilename, selectedIDs, selectionFilename);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			Path tempSelectionsCSV = Paths.get("tempSELECTIONS.csv");
			model = new Model(tempSelectionsCSVfilename);
			try {
				Files.delete(tempSelectionsCSV);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else{
			model = new Model(filename);

		}
		
		System.out.println("filename: " + filename + "\nselctionfilename: " + selectionFilename +"\nbacgroundsvg: " + svgFilename +"\noptions:" + option);
		String[] outputFileName = filename.split("/");
		String newOutPutFile = outputFileName[outputFileName.length-1];
		System.out.println("newOutputFile: " + newOutPutFile);
		
		int finalDistance = 0;
		for(int i = 0; i < model.getLegsLength(); i++){
			finalDistance += model.getLeg(i).getDistance();
		}
		Presenter P = new Presenter(model,new View("Colorado",finalDistance,option,newOutPutFile.substring(0, newOutPutFile.length()-4)),option);
		
		P.getTrip(P,option);
		P.finalView();
		System.out.println(option);
		System.out.println(newOutPutFile);
		TripCoGUI t = new TripCoGUI(newOutPutFile.substring(0, newOutPutFile.length()-4));
		t.run(newOutPutFile.substring(0, newOutPutFile.length()-4));

	}

	private static void makeSelectionCSV(String filename, String tempSelectionsCSVfilename, ArrayList<Integer> selectedIDs, String selectionFilename) throws IOException {
		ArrayList<String> csvStrings = null;
		String headerLine = null;
		

			Scanner scanner = new Scanner(new File(filename));
			Scanner scanner2 = new Scanner(new File(filename));
			
			headerLine = getHeaderLine(scanner);
			scanner = null;
			csvStrings = getCSVlines(scanner2);
			scanner2 = null;

		
		
		//read .xml for selected ids
			scanner = new Scanner(new File(selectionFilename));

			while(scanner.hasNextLine()){	
				String line = scanner.nextLine();
				if (line.contains("<id>")){
					System.out.println(line);
					line=line.replaceAll("<id>", "");
					line=line.replaceAll("</id>", "");
			    	line=line.replaceAll("\\s+","");   
					System.out.println(line);
					selectedIDs.add(Integer.parseInt(line));
				}
			}
		

		System.out.println();
		for(Integer ID : selectedIDs){
			System.out.println(ID);
		}
		String[] headerArray = headerLine.split(",");
		int idIndex = -1;
		int count = 0;
		System.out.println();
		for(String s : headerArray){
			System.out.println(s);
			if(s.toLowerCase().equals("id")){
				idIndex=count;
			}
			count++;

		}
		
		System.out.println();
		BufferedWriter writer;
			writer = new BufferedWriter( new FileWriter(tempSelectionsCSVfilename));
			writer.write(headerLine);
			writer.newLine();
			for(String s : csvStrings){
				String[] stringTempArray = s.split(",");
				int id = Integer.parseInt(stringTempArray[idIndex]);
				if(selectedIDs.contains(id)){
					System.out.println(id);
					System.out.println("writing: " + s);
					writer.write(s);
					writer.newLine();

				}
			
			}
			
			writer.close();
		

		
		
	}

	//get the header line of the CSV file and return as string (id,name,longitude,latitude)
	private static String getHeaderLine(Scanner scanner) {
		String ret = null;
		if(scanner.hasNextLine()){ 
			String line = scanner.nextLine();
			ret = line;
		}
		return ret;
	}
	
	//get the content lines of the CSV file and return as stringArray
	private static ArrayList<String> getCSVlines(Scanner scanner2) {
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
    
	
	
	
	
}
