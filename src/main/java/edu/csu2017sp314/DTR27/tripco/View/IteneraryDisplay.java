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
			while(document.getElementsByTagName("sequence").item(i) != null){
				String sequence = document.getElementsByTagName("sequence").item(i).getTextContent();
				String start = document.getElementsByTagName("start").item(i).getTextContent();
			
					System.out.println(start);
				
				String finish = document.getElementsByTagName("finish").item(i).getTextContent();
				
				String mileage = document.getElementsByTagName("mileage").item(i).getTextContent();
				System.out.println(sequence+'\n'+start+' '+finish+'\n'+mileage+'\n');
				textArea.append("Sequence Number "+sequence+"\nStart Location "+start+"\nFinish Location "+finish+"\nMileage "+mileage+'\n'+'\n');
				i++;
			}
	    }
	    catch (Exception e){
	        System.out.println("Error reading configuration file:");
	        //System.out.println(e.getMessage());
	    }
	    
	}
	}

