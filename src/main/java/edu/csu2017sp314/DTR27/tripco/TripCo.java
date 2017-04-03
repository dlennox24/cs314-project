package main.java.edu.csu2017sp314.DTR27.tripco;

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

import main.java.edu.csu2017sp314.DTR27.tripco.Model.Leg;
import main.java.edu.csu2017sp314.DTR27.tripco.Model.Location;
import main.java.edu.csu2017sp314.DTR27.tripco.Model.Model;
import main.java.edu.csu2017sp314.DTR27.tripco.Presenter.Presenter;
import main.java.edu.csu2017sp314.DTR27.tripco.Presenter.Trip;
import main.java.edu.csu2017sp314.DTR27.tripco.View.View;
import main.java.edu.csu2017sp314.DTR27.tripco.View.TripCoGUI;

public class TripCo {
  public static void main(String[] args) {

    String option = "";
    String filename = null;
    String selectionFilename = null;
    String svgFilename = null;
    ArrayList<String> selectedIDs = new ArrayList<String>();
    String opt = "NN";
    boolean mFlag = false;
    boolean iFlag = false;
    boolean nFlag = false;
    boolean gFlag = false;
    boolean opt2Flag = false;
    boolean opt3Flag = false;


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
    String finalOptions = "";
    Options op = new Options();
    if(option.contains("g")){
      gFlag=true;
      System.out.println(filename);
      Model mo = new Model(filename, "");
      OptionsGUI gui = new OptionsGUI(op,mo);
      
      gui.run(op, mo);

    }else{
      finalOptions = option;
      op.done = true;
    }

    while(op.done != true){
      System.out.println(op.optionsString);
      option=op.optionsString;
      String str2 = option;
      String str3 = option;
      String strN = option;
      String strI = option;
      String strM = option;

      if(((option.length() - str2.replace("2", "").length()) % 2) == 1){
          opt = "2";
          opt2Flag=true;
        }
        if(((option.length() - str3.replace("3", "").length()) % 2) == 1){
          opt = "3";
          opt3Flag=true;
        }
        if(((option.length() - str3.replace("n", "").length()) % 2) == 1){
          finalOptions = finalOptions + "n";
          nFlag=true;
        }
        if(((option.length() - str3.replace("i", "").length()) % 2) == 1){
          finalOptions = finalOptions + "i";
          iFlag=true;
        }
        if(((option.length() - str3.replace("m", "").length()) % 2) == 1){
          finalOptions = finalOptions + "m";
          mFlag=true;
        }
   
    }

    Model model = null;
    selectionFilename = op.selectionFile;
    if(selectionFilename != null){
      try {
    	String tempSelectionsCSVfilename = "tempSELECTIONS.csv";
        makeSelectionCSV(filename, tempSelectionsCSVfilename, op.intArray, selectionFilename);
        model = new Model(tempSelectionsCSVfilename,opt);
        //Files.delete(tempSelectionsCSVfilename);
      } catch (IOException e) {
        e.printStackTrace();
      }


    }else{
    	model = new Model(filename,opt);

    }



    System.out.println("filename: " + filename + "\nselctionfilename: " + selectionFilename +"\nbacgroundsvg: " + svgFilename +"\noptions:" + option);
    String[] outputFileName = filename.split("/");
    String newOutPutFile = outputFileName[outputFileName.length-1];
    System.out.println("newOutputFile: " + newOutPutFile);

    int finalDistance = 0;
    for(int i = 0; i < model.getLegsLength(); i++){
      finalDistance += model.getLeg(i).getDistance();
    }
    Presenter P = new Presenter(model,new View("Colorado",finalDistance,finalOptions,newOutPutFile.substring(0, newOutPutFile.length()-4)),option,svgFilename);

    P.getTrip(P,option);
    P.finalView(svgFilename);
    System.out.println(option);
    System.out.println(newOutPutFile);
    TripCoGUI t = new TripCoGUI(newOutPutFile.substring(0, newOutPutFile.length()-4));
    t.run(newOutPutFile.substring(0, newOutPutFile.length()-4));

  }

  private static void makeSelectionCSV(String filename, String tempSelectionsCSVfilename, ArrayList<String> selectedIDs, String selectionFilename) throws IOException {
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
          selectedIDs.add(line);
        }
       System.out.println(selectedIDs);
      }



    String[] headerArray = headerLine.split(",");
    int idIndex = -1;
    int count = 0;
    System.out.println();
    for(String s : headerArray){
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
        String id = (stringTempArray[idIndex]);
        System.out.println("id = " + id + selectedIDs);
        if(selectedIDs.contains(id)){
        	System.out.println(s);
          System.out.println("writing: " + s + "\tid: " + id);
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
