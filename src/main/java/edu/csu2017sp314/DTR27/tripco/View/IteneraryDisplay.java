package main.java.edu.csu2017sp314.DTR27.tripco.View;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import java.awt.TextArea;

public class IteneraryDisplay extends JFrame {

	private JPanel contentPane;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
	}

	/**
	 * Create the frame.
	 */
public IteneraryDisplay(String fileName) {
	
		setBounds(100, 100, 450, 300);
		setTitle("Itinerary");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		TextArea textArea = new TextArea();
		
		Populate(textArea, fileName);
		textArea.setEditable(false);
		textArea.setBounds(20, 10, 410, 243);
		contentPane.add(textArea);
		
	}
	public void run(String fileName) {
		try {
			IteneraryDisplay frame = new IteneraryDisplay(fileName);
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void Populate(TextArea textArea, String fileName){
		try {
			
			File file = new File(fileName+".xml");
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
			        .newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(file);
			int i = 0;
			System.out.println("ZZZZZZZZZZZZ");
			while(document.getElementsByTagName("sequence").item(i) != null){
				String sequence = document.getElementsByTagName("sequence").item(i).getTextContent();
				NodeList start = document.getElementsByTagName("start").item(i).getChildNodes();

				//System.out.println(start);
//				for(int z = 0; z<start.getLength();z++){
//					String ss = start.item(z).getTextContent();
//					if(!ss.contains("\n")){
//						System.out.println(z +" |" +start.item(z).getTextContent()+ "|");
//					}
//				}

				NodeList finish = document.getElementsByTagName("finish").item(i).getChildNodes();
				//System.out.println(finish);
//				for(int z = 0; z<finish.getLength();z++){
//					String ss = finish.item(z).getTextContent();
//					if(!ss.contains("\n")){
//						System.out.println(z +" |" +finish.item(z).getTextContent()+ "|");
//					}
//				}
				
				String mileage = document.getElementsByTagName("distance").item(i).getTextContent();
				String units = document.getElementsByTagName("units").item(i).getTextContent();

				//System.out.println(sequence+'\n'+start+' '+finish+'\n'+mileage+'\n');
				textArea.append("Sequence Number "+sequence
						+"\n   Start Location ID: "+start.item(1).getTextContent()
						+"\n\tName: "+start.item(3).getTextContent()
						+"\n\tLatitude: "+start.item(5).getTextContent()
						+"\n\tLongitude: "+start.item(7).getTextContent()
						+"\n\tElevation: "+start.item(9).getTextContent()
						+"\n\tMunicipality: "+start.item(11).getTextContent()
						+"\n\tRegion: "+start.item(13).getTextContent()
						+"\n\tCountry: "+start.item(15).getTextContent()
						+"\n\tContinent: "+start.item(17).getTextContent()
						+"\n\tAirport URL: "+start.item(19).getTextContent()
						+"\n\tRegion URL: "+start.item(21).getTextContent()
						+"\n\tCountry URL: "+start.item(23).getTextContent()
						+"\n   Finish Location ID: "+finish.item(1).getTextContent()
						+"\n\tName: "+finish.item(3).getTextContent()
						+"\n\tLatitude: "+finish.item(5).getTextContent()
						+"\n\tLongitude: "+finish.item(7).getTextContent()
						+"\n\tElevation: "+finish.item(9).getTextContent()
						+"\n\tMunicipality: "+finish.item(11).getTextContent()
						+"\n\tRegion: "+finish.item(13).getTextContent()
						+"\n\tCountry: "+finish.item(15).getTextContent()
						+"\n\tContinent: "+finish.item(17).getTextContent()
						+"\n\tAirport URL: "+finish.item(19).getTextContent()
						+"\n\tRegion URL: "+finish.item(21).getTextContent()
						+"\n\tCountry URL: "+finish.item(23).getTextContent()
						+"\n    Mileage: "+mileage
						+"\n    Units: "+units
						+'\n'+'\n');
				i++;
			}
	    }
	    catch (Exception e){
	        System.out.println("Error reading configuration file:");
	        System.out.println(e.getMessage());
	    }
	    
	}
	}

