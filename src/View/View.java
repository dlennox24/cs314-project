package View;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class View {
	private String status;
	private String flags;
	private String outputFilename;
	private double h = 674.29;
	private double w = 1180;
	private double b = 174.855;

	private ArrayList<Leg> legsData = new ArrayList<Leg>();
	private ArrayList<Stroke> legs = new ArrayList<Stroke>();
	private ArrayList<Stroke> borders = new ArrayList<Stroke>();
	private ArrayList<Label> titles = new ArrayList<Label>();
	private ArrayList<Label> locations = new ArrayList<Label>();
	private ArrayList<Label> distances = new ArrayList<Label>();

	public View(String title, int totalDist, String flags, String filename) {
		this.flags = flags;
		this.outputFilename = filename;

		String borderColor = "#333";
		int borderWidth = 3;
		int width = (int) ((int) this.h+this.b);
		this.borders.add(new Stroke("north",borderWidth,50,(int)this.b,1230,(int)this.b,borderColor));
		this.borders.add(new Stroke("east",borderWidth,1230,(int)this.b,1230,width,borderColor));
		this.borders.add(new Stroke("south",borderWidth,1230,width,50,width,borderColor));
		this.borders.add(new Stroke("west",borderWidth,50,width,50,(int)this.b,borderColor));

		this.titles.add(new Label("state",title,640,(int)this.b-((int)this.b/2),"middle",20));
		this.titles.add(new Label("distance",totalDist+" miles",640,(int)this.b+(int)this.h+40,"middle",20));

		this.status = "OK";
	}

	// Has labels
	public void addLeg(double x1, double y1, double x2, double y2, int dist, String name1, String name2){
		// add leg data to the correct arraylists
		int[] coord1 = coord2Pixel(x1,y1);
		int[] coord2 = coord2Pixel(x2,y2);
		this.legsData.add(new Leg(name1,name2,dist));
		this.legs.add(new Stroke("leg"+this.legs.size(),2,coord1[0],coord1[1],coord2[0],coord2[1],"#333"));
		this.locations.add(new Label("label"+name1,name1,coord1[0],coord1[1],"left",16));
		this.locations.add(new Label("label"+name2,name2,coord2[0],coord2[1],"left",16));
		this.distances.add(new Label("label"+dist,""+dist,(coord1[0]+coord2[0])/2,(coord1[1]+coord2[1])/2,"middle",16));
	}

	public void finTrip() throws IOException{
		// builds the SVG and XML files based off legs,borders,titles,locations,distances arraylists
		File svgOutput = new File(this.outputFilename+".svg");
		FileWriter fWriterSvg = new FileWriter(svgOutput);

		// Adding SVG file init syntax
		fWriterSvg.write("<?xml version=\"1.0\"?>\n"
				+"<svg width=\"1280\" height=\"1024\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:svg=\"http://www.w3.org/2000/svg\">\n"
				+"\n");

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

		// Adding location id for map
		if(this.flags.contains("i")){
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
						+(i+1)+"</text>\n");
			}
			fWriterSvg.write("\t</g>\n");
		}
		// /location id

		// Adding distance labels for map
		if(this.flags.contains("m")){
			fWriterSvg.write("\t<g>\n\t\t<title>Distances</title>\n");
			for(int i=0;i<this.distances.size();i++){
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
			fWriterSvg.write("\t\t<line"
					+" id=\""+this.legs.get(i).id+"\""
					+" x1=\""+this.legs.get(i).x1+"\""
					+" y1=\""+this.legs.get(i).y1+"\""
					+" x2=\""+this.legs.get(i).x2+"\""
					+" y2=\""+this.legs.get(i).y2+"\""
					+" stroke-width=\""+this.legs.get(i).strokeWidth+"\""
					+" stroke=\""+this.legs.get(i).color+"\"/>\n");
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
					+"\t\t<start>"+this.legsData.get(i).startCityName+"</start>\n"
					+"\t\t<finish>"+this.legsData.get(i).endCityName+"</finish>\n"
					+"\t\t<mileage>"+this.legsData.get(i).dist+"</mileage>\n"
					+"\t</leg>\n");
		}
		fWriterXml.write("</trip>");

		fWriterSvg.close();
		fWriterXml.close();
	}

	private int[] coord2Pixel(double lat, double lon){
		double sx = 50+((lon+109)/7)*this.w;
		double sy = this.b + ((41-lat)/4)*this.h;

		return new int[]{(int)sx,(int)sy};
	}

	public String getStatus(){
		return this.status;
	}

	public static void main(String[] args) {
		View v = new View("Custom Titles!",15454,"","fname");
		v.addLeg(37.094,-102.252,40.879,-108.948,909,"start","end");
		try {
			v.finTrip();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("DONE");
	}
}
