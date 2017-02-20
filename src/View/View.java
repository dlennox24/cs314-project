package View;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

class View {
	private String status;
	private ArrayList<String> flags;
	private String title;
	private int totalDist;
	private String outputFilename;

	private ArrayList<Stroke> legs = new ArrayList<Stroke>();
	private ArrayList<Stroke> borders = new ArrayList<Stroke>();
	private ArrayList<Label> titles = new ArrayList<Label>();
	private ArrayList<Label> locations = new ArrayList<Label>();
	private ArrayList<Label> distances = new ArrayList<Label>();

	public View(String title, int totalDist, ArrayList<String> flags, String filename) {
		this.title = title;
		this.totalDist = totalDist;
		this.flags = flags;
		this.outputFilename = filename;

		String borderColor = "#333";
		int borderWidth = 3;
		this.borders.add(new Stroke("north",borderWidth,50,50,1230,50,borderColor));
		this.borders.add(new Stroke("east",borderWidth,1230,50,1230,975,borderColor));
		this.borders.add(new Stroke("south",borderWidth,1230,975,50,975,borderColor));
		this.borders.add(new Stroke("west",borderWidth,50,975,50,50,borderColor));

		this.titles.add(new Label("state",title,640,40,"middle",20));
		this.titles.add(new Label("distance",totalDist+" miles",640,1000,"middle",20));

		this.status = "OK";
	}

	// Has labels
	public void addLeg(String x1, String y1, String x2, String y2, int dist, String name1, String name2){
		// add leg data to the correct arraylists
	}

	// Doesn't have labels
	public void addLeg(String x1, String y1, String x2, String y2, int dist){
		// add leg data to the correct arraylists
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

		// Close SVG syntax; file should be complete
		fWriterSvg.write("</svg>");

		File xmlOutput = new File(this.outputFilename+".xml");
		FileWriter fWriterXml = new FileWriter(xmlOutput);

		fWriterSvg.close();
		fWriterXml.close();
	}

	private void addLine(){
		// add line to a specific arraylist
	}

	public void addLabel(){
		// add label to a specific arraylist
	}

	public String getStatus(){
		return this.status;
	}

	public static void main(String[] args) {
		View v = new View("Custom Titles!",15454,new ArrayList<String>(),"fname");
		try {
			v.finTrip();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
