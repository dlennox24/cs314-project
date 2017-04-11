package main.java.edu.csu2017sp314.DTR27.tripco.View;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class View {
	private String status;
	private String flags;
	private String outputFilename;
	private double h = 692;//674.29 right val
	private double w =1377;// 1180 right val
	private double b =34.9122;//174.85


	private ArrayList<Leg> legsData = new ArrayList<Leg>();
	private ArrayList<Stroke> legs = new ArrayList<Stroke>();
	private ArrayList<Stroke> borders = new ArrayList<Stroke>();
	private ArrayList<Label> titles = new ArrayList<Label>();
	private ArrayList<Label> locations = new ArrayList<Label>();
	private ArrayList<Label> distances = new ArrayList<Label>();

	public View(String title, int totalDist, String flags, String filename) {
		this.flags = flags;
		this.outputFilename = filename;

	
		this.titles.add(new Label("DTR-27","DTR-27",512,485,"middle",20));
		this.titles.add(new Label("distance",totalDist+"Miles",512,505,"middle",20));
		this.status = "OK";
	}

	// Has labels
	public void addLeg(double x1, double y1, double x2, double y2, int dist, String name1, String name2, String ID1, String LAT1, String LONG1, String ELEVATION1, String MUNICIPALITY1, String REGION1, String COUNTRY1, String CONTINENT1, String AIRPORTURL1, String REGIONURL1, String COUNTRYURL1, String ID2, String LAT2, String LONG2,String ELEVATION2, String MUNICIPALITY2, String REGION2, String COUNTRY2, String CONTINENT2, String AIRPORTURL2, String REGIONURL2, String COUNTRYURL2, String units){
		// add leg data to the correct arraylists
		int[] coord1 = coord2Pixel(x1,y1);
		int[] coord2 = coord2Pixel(x2,y2);
		this.legsData.add(new Leg(name1.replaceAll("&", "and"),name2.replaceAll("&", "and"), dist, ID1, LAT1, LONG1,ELEVATION1, MUNICIPALITY1, REGION1, COUNTRY1, CONTINENT1, AIRPORTURL1, REGIONURL1, COUNTRYURL1, ID2, LAT2, LONG2, ELEVATION2, MUNICIPALITY2, REGION2, COUNTRY2, CONTINENT2, AIRPORTURL2, REGIONURL2,  COUNTRYURL2, units));
		this.legs.add(new Stroke("leg"+this.legs.size(),2,coord1[0],coord1[1],coord2[0],coord2[1],"#333"));
		this.locations.add(new Label("label"+name1.replaceAll("\\s+","").replaceAll("&","and"),name1.replaceAll("&","and"),coord1[0],coord1[1],"middle",16));
		this.locations.add(new Label("label"+name2.replaceAll("\\s+","").replaceAll("&","and"),name2.replaceAll("&","and"),coord2[0],coord2[1],"middle",16));
		this.distances.add(new Label("label"+dist,""+dist,(coord1[0]+coord2[0])/2,(coord1[1]+coord2[1])/2,"middle",16));
	}

	public void finTrip(String fileName) throws IOException{
		// builds the SVG and XML files based off legs,borders,titles,locations,distances arraylists
		File svgOutput = new File(this.outputFilename+".svg");
		FileWriter fWriterSvg = null; 
		if(fileName != null){
			 fWriterSvg = getBGFile(this.outputFilename+".svg",fileName );
		}else{
			 fWriterSvg = new FileWriter(svgOutput);
			 fWriterSvg.write("<?xml version=\"1.0\"?>\n"
						+"<svg width=\"1066\" height=\"783\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:svg=\"http://www.w3.org/2000/svg\">\n"
						+"\n");
		}
		// Adding SVG file init syntax
		
	
		
		String title;
		// TODO could be enhanced with a generic method
		// Adding titles for map
		
		fWriterSvg.write("\t<g>\n\t\t<title>Titles</title>\n");
		for(int i=0;i<this.titles.size();i++){
			fWriterSvg.write("\t\t<text"
					+" id=\""+this.titles.get(i).id+"\""
					+" x=\""+this.titles.get(i).x+"\""
					+" y=\""+this.titles.get(i).y+"\""
					+" text-anchor=\""+this.titles.get(i).textAnchor+"\""
					+" font-size=\""+this.titles.get(i).fontSize+"\""
					+" font-family=\""+this.titles.get(i).fontFamily+"\">"
					+this.titles.get(i).displayedText+"</text>\n");
		}
		fWriterSvg.write("\t</g>\n");
		// /titles

		// Adding location labels for map
		if(this.flags.contains("n")){
			fWriterSvg.write("\t<g>\n\t\t<title>Locations</title>\n");
			for(int i=0;i<this.locations.size();i++){
				// System.out.println("size:"+this.locations.size());
				// System.out.println(i);
				fWriterSvg.write("\t\t<text"
						+" id=\""+this.locations.get(i).id+"\""
						+" x=\""+this.locations.get(i).x+"\""
						+" y=\""+this.locations.get(i).y+"\""
						+" text-anchor=\""+this.locations.get(i).textAnchor+"\""
						+" font-size=\""+this.locations.get(i).fontSize+"\""
						+" font-family=\""+this.locations.get(i).fontFamily+"\">"
						+this.locations.get(i).displayedText+"</text>\n");
			}
			fWriterSvg.write("\t</g>\n");
		}
		// /location labels

		// Adding location id for map"coloradoBackGround.svg"
		if(this.flags.contains("i")){
			fWriterSvg.write("\t<g>\n\t\t<title>Locations</title>\n");
			for(int i=0;i<this.locations.size();i+=2){
				// System.out.println("size:"+this.locations.size());
				// System.out.println(i);
				fWriterSvg.write("\t\t<text"
						+" id=\""+this.locations.get(i).id+"\""
						+" x=\""+this.locations.get(i).x+"\""
						+" y=\""+this.locations.get(i).y+"\""
						+" text-anchor=\""+this.locations.get(i).textAnchor+"\""
						+" font-size=\""+this.locations.get(i).fontSize+"\""
						+" font-family=\""+this.locations.get(i).fontFamily+"\">"
						+(i+1)+"</text>\n");
			}
			fWriterSvg.write("\t</g>\n");
		}
		// /location id

		// Adding distance labels for map
		if(this.flags.contains("m")){
			fWriterSvg.write("\t<g>\n\t\t<title>Distances</title>\n");
			for(int i=0;i<this.distances.size();i+=2){
				fWriterSvg.write("\t\t<text"
						+" id=\""+this.distances.get(i).id+"\""
						+" x=\""+this.distances.get(i).x+"\""
						+" y=\""+this.distances.get(i).y+"\""
						+" text-anchor=\""+this.distances.get(i).textAnchor+"\""
						+" font-size=\""+this.distances.get(i).fontSize+"\""
						+" font-family=\""+this.distances.get(i).fontFamily+"\">"
						+this.distances.get(i).displayedText+"</text>\n");
			}
			fWriterSvg.write("\t</g>\n");
		}
		// /distance labels

		// Adding basic map borders
		fWriterSvg.write("\t<g>\n\t\t<title>Borders</title>\n");
		for(int i=0;i<this.borders.size();i++){
			fWriterSvg.write("\t\t<line"
					+" id=\""+this.borders.get(i).id+"\""
					+" x1=\""+this.borders.get(i).x1+"\""
					+" y1=\""+this.borders.get(i).y1+"\""
					+" x2=\""+this.borders.get(i).x2+"\""
					+" y2=\""+this.borders.get(i).y2+"\""
					+" stroke-width=\""+this.borders.get(i).strokeWidth+"\""
					+" stroke=\""+this.borders.get(i).color+"\"/>\n");
		}
		fWriterSvg.write("\t</g>\n");
		// /borders

		// Adding legs
		fWriterSvg.write("\t<g>\n\t\t<title>Legs</title>\n");
		for(int i=0;i<this.legs.size();i++){
			int mapWidth = 1024;
			int diff = this.legs.get(i).x1 - this.legs.get(i).x2;
			if(diff < -512 || diff > 512){
				String id = this.legs.get(i).id;
				Stroke leg = this.legs.get(i);
				if(this.legs.get(i).x1<mapWidth/2){
					//first set goes off to the left
					fWriterSvg.write("\t\t<line"
							+" id=\""+id+"-l\""
							+" x1=\""+leg.x1+"\""
							+" y1=\""+leg.y1+"\""
							+" x2=\""+0+"\""
							+" y2=\""+(int)calcIntercept(leg.x1,leg.y1,leg.x2-mapWidth,leg.y2,0)+"\""
							+" stroke-width=\""+this.legs.get(i).strokeWidth+"\""
							+" stroke=\""+this.legs.get(i).color+"\"/>\n");
					fWriterSvg.write("\t\t<line"
							+" id=\""+id+"-r\""
							+" x1=\""+mapWidth+"\""
							+" y1=\""+(int)calcIntercept(leg.x1,leg.y1,leg.x2-mapWidth,leg.y2,mapWidth)+"\""
							+" x2=\""+leg.x2+"\""
							+" y2=\""+leg.y2+"\""
							+" stroke-width=\""+this.legs.get(i).strokeWidth+"\""
							+" stroke=\""+this.legs.get(i).color+"\"/>\n");
				}else{
					//first set goes off to the right
					fWriterSvg.write("\t\t<line"
							+" id=\""+id+"-r\""
							+" x1=\""+leg.x1+"\""
							+" y1=\""+leg.y1+"\""
							+" x2=\""+mapWidth+"\""
							+" y2=\""+(int)calcIntercept(leg.x1,leg.y1,leg.x2,leg.y2,mapWidth)+"\""
							+" stroke-width=\""+this.legs.get(i).strokeWidth+"\""
							+" stroke=\""+this.legs.get(i).color+"\"/>\n");
					fWriterSvg.write("\t\t<line"
							+" id=\""+id+"-l\""
							+" x1=\""+0+"\""
							+" y1=\""+(int)calcIntercept(leg.x1,leg.y1,leg.x2,leg.y2,0)+"\""
							+" x2=\""+leg.x2+"\""
							+" y2=\""+leg.y2+"\""
							+" stroke-width=\""+this.legs.get(i).strokeWidth+"\""
							+" stroke=\""+this.legs.get(i).color+"\"/>\n");
				}
			}else{
				fWriterSvg.write("\t\t<line"
						+" id=\""+this.legs.get(i).id+"\""
						+" x1=\""+this.legs.get(i).x1+"\""
						+" y1=\""+(this.legs.get(i).y1 < 0 ? 5 : this.legs.get(i).y1)+"\""
						+" x2=\""+this.legs.get(i).x2+"\""
						+" y2=\""+(this.legs.get(i).y2 < 0 ? 5 : this.legs.get(i).y2)+"\""
						+" stroke-width=\""+this.legs.get(i).strokeWidth+"\""
						+" stroke=\""+this.legs.get(i).color+"\"/>\n");
			}
		}
		fWriterSvg.write("\t</g>\n");
		// /legs

		// Close SVG syntax; file should be complete
		fWriterSvg.write("</svg>");
		
		File xmlOutput = new File(this.outputFilename+".xml");
		FileWriter fWriterXml = new FileWriter(xmlOutput);
		fWriterXml.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<trip>\n");
		for(int i=0;i<this.legsData.size();i++){
			fWriterXml.write("\t<leg>\n"
					+"\t\t<sequence>"+(i+1)+"</sequence>\n"
					+"\t\t<start>\n"
						+"\t\t\t<id>"+this.legsData.get(i).ID1+"</id>\n"
						+"\t\t\t<name>"+this.legsData.get(i).startCityName.replaceAll("&", "and")+"</name>\n"
						+"\t\t\t<latitude>"+this.legsData.get(i).LAT1 + "</latitude>\n"
						+"\t\t\t<longitude>"+this.legsData.get(i).LONG1 + "</longitude>\n"
						+"\t\t\t<elevation>"+this.legsData.get(i).ELEVATION1 + "</elevation>\n"
						+"\t\t\t<municipality>"+this.legsData.get(i).MUNICIPALITY1 + "</municipality>\n"
						+"\t\t\t<region>"+this.legsData.get(i).REGION1 + "</region>\n"
						+"\t\t\t<country>"+this.legsData.get(i).COUNTRY1 + "</country>\n"
						+"\t\t\t<continent>"+this.legsData.get(i).CONTINENT1 + "</continent>\n"
						+"\t\t\t<airportURL>"+this.legsData.get(i).AIRPORTURL1 + "</airportURL>\n"
						+"\t\t\t<regionURL>"+this.legsData.get(i).REGIONURL1 + "</regionURL>\n"
						+"\t\t\t<countryURL>"+this.legsData.get(i).COUNTRYURL1 + "</countryURL>\n"
					+"\t\t</start>\n"
					+"\t\t<finish>\n"
						+"\t\t\t<id>"+this.legsData.get(i).ID2+"</id>\n"
						+"\t\t\t<name>"+this.legsData.get(i).endCityName.replaceAll("&", "and")+"</name>\n"
						+"\t\t\t<latitude>"+this.legsData.get(i).LAT2 + "</latitude>\n"
						+"\t\t\t<longitude>"+this.legsData.get(i).LONG2 + "</longitude>\n"
						+"\t\t\t<elevation>"+this.legsData.get(i).ELEVATION2 + "</elevation>\n"
						+"\t\t\t<municipality>"+this.legsData.get(i).MUNICIPALITY2 + "</municipality>\n"
						+"\t\t\t<region>"+this.legsData.get(i).REGION2 + "</region>\n"
						+"\t\t\t<country>"+this.legsData.get(i).COUNTRY2 + "</country>\n"
						+"\t\t\t<continent>"+this.legsData.get(i).CONTINENT2 + "</continent>\n"
						+"\t\t\t<airportURL>"+this.legsData.get(i).AIRPORTURL2 + "</airportURL>\n"
						+"\t\t\t<regionURL>"+this.legsData.get(i).REGIONURL2 + "</regionURL>\n"
						+"\t\t\t<countryURL>"+this.legsData.get(i).COUNTRYURL2 + "</countryURL>\n"
					+"\t\t</finish>\n"
					+"\t\t<distance>"+this.legsData.get(i).dist+"</distance>\n"
					+"\t\t<units>"+this.legsData.get(i).units+"</units>\n"

					+"\t</leg>\n");
		}
		fWriterXml.write("</trip>");

		fWriterSvg.close();
		fWriterXml.close();
	}

	private int[] coord2Pixel(double lat, double lon){
		double mapHeight = 512, mapWidth = 1024;
		
		double sx = (lon +180) * (mapWidth/360);
		double latRad = Math.toRadians(lat);
		double mercN = Math.log(Math.tan((Math.PI/4)+(latRad/2)));
		double sy = (mapHeight/2)-(mapWidth*mercN/(2*Math.PI));
		//sy = Math.toDegrees(sy);

		return new int[]{(int)Math.round(sx),(int)Math.round(sy)};
	}

	public String getStatus(){
		return this.status;
	}
	private FileWriter getBGFile(String fileName, String BGFile) throws IOException{
		FileReader fr = null;
        FileWriter fw = null;
        try {
            fr = new FileReader(BGFile);
            fw = new FileWriter(fileName);
         //   System.out.println(fileName);
            int c = fr.read();
            fw.write(c);
            while(c!=-1) {
            	c = fr.read();
            	//System.out.println("read: "+c);
            	if((char)c >= 0xEF){
            		//do nothing
            		System.out.println("non printable char");
            	}else if((char)c == '<'){
            		int a = fr.read();
            		if(a == '/'){
            			int b = fr.read();
            			if(b == 's'){
            				break;
            			}else{
            				fw.write(c);
            				fw.write(a);
            				fw.write(b);
            			}
            		}else{
            			fw.write(c);
            			fw.write(a);
            		}
            			
            	}else{
            		fw.write(c);
            	}
            }
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
           fr.close();
        }
        return fw;
	}
	
	private double calcIntercept(int x1, int y1, int x2, int y2, int xn){
		double slope = (y2-y1)/(x2-x1);
		double inter = y1 - slope*(x1);
		return slope*xn + inter;
	}
	
//	public static void main(String[] args) {
//		View v = new View("Custom Titles!",15454,"mn","fname");
//		v.addLeg(37.094,-102.252,40.879,-108.948,909,"start & test","end");
//		System.out.println("start & test".replaceAll("\\s+", ""));
//		
//		System.out.println("DONE");
//	}
}
