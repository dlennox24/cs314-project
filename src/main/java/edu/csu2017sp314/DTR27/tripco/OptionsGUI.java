package main.java.edu.csu2017sp314.DTR27.tripco;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import main.java.edu.csu2017sp314.DTR27.tripco.Model.Model;
import main.java.edu.csu2017sp314.DTR27.tripco.Model.Optimizer;
import main.java.edu.csu2017sp314.DTR27.tripco.Model.SQLreader;
import main.java.edu.csu2017sp314.DTR27.tripco.View.IteneraryDisplay;
import main.java.edu.csu2017sp314.DTR27.tripco.View.SVGMapDisplay;

import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

public class OptionsGUI extends JFrame implements Runnable{

	private JPanel contentPane;
	private int[] selectedID = null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
	}

	/**
	 * Create the frame.
	 * @param op 
	 * @param op 
	 * @throws IOException 
	 */
	public OptionsGUI(Options opp, Model mo, String selectionFileName) throws IOException {
		final Options op = opp;
		Model model = mo;
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JTextField txtWelcomeToTripco = new JTextField();
		txtWelcomeToTripco.setBounds(5, 5, 440, 29);
		txtWelcomeToTripco.setFont(new Font("FreeMono", Font.BOLD, 25));
		txtWelcomeToTripco.setEditable(false);
		txtWelcomeToTripco.setText("Welcome To TripCo");
		contentPane.add(txtWelcomeToTripco);
		txtWelcomeToTripco.setColumns(10);
		
		JCheckBox chckbxKilometer = new JCheckBox("Kilometers");
		chckbxKilometer.setBounds(205, 36, 129, 23);
		contentPane.add(chckbxKilometer);
		chckbxKilometer.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String cmd = e.getActionCommand();
					
					if(cmd.equals("Kilometers")){
						op.optionsString = op.optionsString + "k"; 
						}
					}
				});
			
		
		JCheckBox chckbxMileage = new JCheckBox("Miles");
		chckbxMileage.setBounds(15, 36, 129, 23);
		contentPane.add(chckbxMileage);
		chckbxMileage.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String cmd = e.getActionCommand();
					
					if(cmd.equals("Miles")){
						op.optionsString = op.optionsString + "m"; 
						}
					}
				});

		JCheckBox name = new JCheckBox("Name");
		name.setBounds(15, 55, 129, 23);
		contentPane.add(name);
		name.addActionListener(new ActionListener(){
		
		@Override
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();
				
				if(cmd.equals("Name")){
					op.optionsString = op.optionsString + "n"; 
					}
				}
			});
		JCheckBox id = new JCheckBox("id");
		id.setBounds(15, 75, 129, 23);
		contentPane.add(id);
		id.addActionListener(new ActionListener(){
		
		@Override
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();
				
				if(cmd.equals("id")){
					op.optionsString = op.optionsString + "i"; 
					}
				}
			});
		JCheckBox opt3 = new JCheckBox("3-opt");
		opt3.setBounds(15, 95, 129, 23);
		contentPane.add(opt3);
		opt3.addActionListener(new ActionListener(){
		
		@Override
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();
				System.out.println(cmd);
				if(cmd.equals("3-opt")){
					op.optionsString = op.optionsString + "3"; 
					}
				}
			});
		JCheckBox opt2 = new JCheckBox("2-opt");
		opt2.setBounds(15, 115, 129, 23);
		contentPane.add(opt2);
		opt2.addActionListener(new ActionListener(){
		
		@Override
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();
				System.out.println(cmd);
				if(cmd.equals("2-opt")){
					op.optionsString = op.optionsString + "2"; 
					}
				}
			});
		JButton loadButton = new JButton("Load");
		loadButton.setBounds(180, 500, 140, 30);
		contentPane.add(loadButton);
		TextArea list = new TextArea();
		 list.setBounds(180, 70, 400, 200);
		 contentPane.add(list);
		loadButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				String cmd = e.getActionCommand();
				System.out.println(cmd);
				if(cmd.equals("Load")){
					
					Populate(list,mo,op.id);
			      			
				}
				
			}
		});
		JButton selectButton = new JButton("Select Destinations");
		selectButton.setBounds(350, 500, 200, 30);
		contentPane.add(selectButton);
		File xmlOutput = new File("selections.xml");
		
		
			final FileWriter fWriter = new FileWriter(xmlOutput);
			startOutput(fWriter);
		
	
		selectButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				String cmd = e.getActionCommand();
				System.out.println(cmd);
				if(cmd.equals("Select Destinations")){
					//System.out.println(cmd);
			      try {
					addSelections(model, selectionFileName, op, fWriter);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SAXException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ParserConfigurationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					
				}
				
			}
		});
		JButton selectContinent = new JButton("Select Type");
		selectContinent.setBounds(350, 300, 200, 30);
		contentPane.add(selectContinent);
		String[] Continents = new String[7];
		Continents[0] = "large_airport";
		Continents[1] = "small_airport";
		Continents[2] = "medium_airport";
		
		int[] ids = null;
		selectContinent.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				String cmd = e.getActionCommand();
				System.out.println(cmd);
				if(cmd.equals("Select Type")){
			
					//String[] LocationTitles = new String[model.getLocationsLength()];
					//populateStringArray(Continents, model);
					JList list = new JList(Continents);
			        list.setSelectionModel(new DefaultListSelectionModel(){
			        	
			        	public void setSelectionInterval(int index0, int index1){
			        		if(super.isSelectedIndex(index0)){
			        			super.removeSelectionInterval(index0, index1);
			        		}else{
			        			super.addSelectionInterval(index0,index1);
			        		}
			        	}
			        });
			        list.setBounds(15, 170, 300, 23);
			        
			        JOptionPane.showMessageDialog (null,new JScrollPane(list), "Continents", JOptionPane.QUESTION_MESSAGE);
			      
			        
			        ArrayList<String> intArray = new ArrayList<String>();
			    	 int[] selected = list.getSelectedIndices();
			        for(int i = 0; i < selected.length; i++){
			        	System.out.println(selected[i]);
			        }
			        //list.setSelectedIndices(selected);
			       //selected = list.getSelectedIndices();
			     
			        selectedID = list.getSelectedIndices();
			        SQLreader sql = new SQLreader();
				     ArrayList<String> rs = sql.getContinents("gtjohnso", "830103947", Continents, selected);
				    
						System.out.println(rs);
					
				}
			}
		});
		  
		JButton DisplayButton = new JButton("Finish");
		DisplayButton.setBounds(12, 500, 140, 30);
		contentPane.add(DisplayButton);
		DisplayButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				String cmd = e.getActionCommand();
				System.out.println(cmd);
				if(cmd.equals("Finish")){
					try {
						endOutput(fWriter);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					op.done = true;
					
					
				}
				
			}
		});
		
		}

	public void run(Options op, Model model, String selectionFile) {
		try {
			OptionsGUI frame = new OptionsGUI(op, model, selectionFile);
			
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private String[] populateStringArray(String[] LocationArray, Model model){
		for(int i = 0; i < model.getLocationsLength();i++){
			LocationArray[i] = model.getLocation(i).getId();
			//System.out.println(model.getLocation(i).toString());

		}
		return LocationArray;
	}
	public void Populate(TextArea textArea, Model model, int[] ids){
		for(int i=0;i<ids.length;i++){
			textArea.append("Selected Destination: "+ model.getLocation(ids[i]-1)+"\n");
			selectedID = ids;
					
		}
	}
	public void addSelections(Model model, String selectionFile, Options op, FileWriter fWriter) throws IOException, SAXException, ParserConfigurationException{
		String[] LocationTitles = new String[model.getLocationsLength()];
		populateStringArray(LocationTitles, model);
		JList list = new JList(LocationTitles);
        list.setSelectionModel(new DefaultListSelectionModel(){
        	
        	public void setSelectionInterval(int index0, int index1){
        		if(super.isSelectedIndex(index0)){
        			super.removeSelectionInterval(index0, index1);
        		}else{
        			super.addSelectionInterval(index0,index1);
        		}
        	}
        });
        list.setBounds(15, 170, 300, 23);
        
        JOptionPane.showMessageDialog (null,new JScrollPane(list), "Select Destinations", JOptionPane.QUESTION_MESSAGE);
      
        int[] selected = list.getSelectedIndices();
        ArrayList<String> intArray = new ArrayList<String>();
        for(int i = 0; i < selected.length; i++){
        	System.out.println(selected[i]);
        	selected[i] += 1;
        	intArray.add(""+selected[i]);
        }
        //list.setSelectedIndices(selected);
        op.intArray = intArray;
        op.id = selected;
        writeSelectionXML(model,op.id,selectionFile, fWriter);
        op.selectionFile = "selections.xml";
	}
	private void writeSelectionXML(Model model, int[] ids, String selectionFile, FileWriter fWriter) throws IOException, SAXException, ParserConfigurationException{
		
		for(int i=0;i<ids.length;i++){
			fWriter.write("\t\t<id>"+model.getLocation(ids[i]-1).getId()+"</id>\n");
					
		}
		if(selectionFile != null){
		File file = new File(selectionFile);
		
			
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
		        .newInstance();
		DocumentBuilder documentBuilder = null;
		
		documentBuilder = documentBuilderFactory.newDocumentBuilder();
		
		Document document = documentBuilder.parse(file);
		int i = 0;
		
		while(document.getElementsByTagName("id").item(i) != null){
			String id = document.getElementsByTagName("id").item(i).getTextContent();
			//System.out.println(id);
			fWriter.write("\t\t<id>"+id+"</id>\n");
			i++;
			
		
		}
		}
		
	}
	public FileWriter startOutput(FileWriter fWriter) throws IOException{
		File xmlOutput = new File("selections.xml");
		//FileWriter fWriter = new FileWriter(xmlOutput);
		fWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<selection>\n");
		fWriter.write("<filename> file.csv </filename>\n");
		fWriter.write("<destinations>\n");
		return fWriter;
	}
	public void endOutput(FileWriter fWriter) throws IOException{
		fWriter.write("</destinations>");
		//adding 
		fWriter.write("</selection>");
		fWriter.close();
	}
	@Override
	public void run() {
		
	}

}
