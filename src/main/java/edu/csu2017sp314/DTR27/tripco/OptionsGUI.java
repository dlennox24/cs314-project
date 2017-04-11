package main.java.edu.csu2017sp314.DTR27.tripco;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
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
import javax.swing.JTextArea;
import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

public class OptionsGUI extends JFrame implements Runnable{

	private JPanel contentPane;
	private int[] selectedID = null;
	String query = "where ";
	boolean isSelected = false;
	ArrayList<String> out= null;
	ArrayList<String> idsFromSelections = new ArrayList();
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
					//Model mo = new Model("output.csv", "", "M");
					SQLreader sql = new SQLreader();
			    	
					Populate(list,op.id, out, idsFromSelections);
			      			
				}
				
			}
		});
		JButton selectButton = new JButton("Select Destinations");
		selectButton.setBounds(350, 500, 200, 30);
		contentPane.add(selectButton);
		File xmlOutput = new File("selections.xml");
		
		
			final FileWriter fWriter = new FileWriter(xmlOutput);
			startOutput(fWriter);
		
			 File file = new File("output.csv");
  			 BufferedWriter writer = new BufferedWriter( new FileWriter(file));
  		      writer.write("id,name,latitude,longitude,municipality,region,country,continent,elevation,airportwiki,countrywiki,regionwiki\n");
		selectButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				String cmd = e.getActionCommand();
				System.out.println(cmd);
				if(cmd.equals("Select Destinations")){
					//System.out.println(cmd);
			      try {
			    	  SQLreader sql = new SQLreader();
			    	 
			    	out = sql.getContinents("gtjohnso", "830103947", query, writer);
			    	if(selectionFileName != null){
			    		runSelectedXML(selectionFileName);
			    		String newq = "where ";
			    		newq = sql.buildQuerySelections(idsFromSelections, newq);
			    		idsFromSelections = sql.getContinents("gtjohnso", "830103947", newq, writer);
			    	}else{
			    		//idsFromSelections.add("");
			    	}
			    	//writer.write(out.get(0));
			    	System.out.println(out.get(0));
			    	writer.close();
			    	//Model mo = new Model("output.csv", "", "M");
					addSelections(selectionFileName, op, fWriter, out);
					query = "where ";
					isSelected = false;
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
		JButton selectType = new JButton("Select Type");
		selectType.setBounds(350, 300, 200, 30);
		contentPane.add(selectType);
		String[] type = new String[4];
		type[0] = "large_airport";
		type[1] = "small_airport";
		type[2] = "medium_airport";
		type[3] = "heliport";
		
		//int[] ids = null;
		selectType.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				String cmd = e.getActionCommand();
				System.out.println(cmd);
				if(cmd.equals("Select Type")){
			
					//String[] LocationTitles = new String[model.getLocationsLength()];
					//populateStringArray(Continents, model);
					JList list = new JList(type);
			        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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
			        
			        query=sql.buildQueryType(type, selected, query, isSelected);
			        System.out.println(query);
			        if(selected.length != 0){
			        	isSelected = true;
			        }
				     //ArrayList<String> rs = sql.getContinents("gtjohnso", "830103947", type, selected);
				    
						//System.out.println(rs);
					
				}
			}
		});
		JButton selectContinent = new JButton("Select Continent");
		selectContinent.setBounds(350, 350, 200, 30);
		contentPane.add(selectContinent);
		String[] Continents = new String[7];
		Continents[0] = "North America";
		Continents[1] = "South America";
		Continents[2] = "Europe";
		Continents[3] = "Asia";
		Continents[4] = "Oceania";
		Continents[5] = "Africa";
		Continents[6] = "Antarctica";
		
		//int[] ids = null;
		selectContinent.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				String cmd = e.getActionCommand();
				System.out.println(cmd);
				if(cmd.equals("Select Continent")){
			
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
				   query = sql.buildQueryContinent(Continents, selected, query, isSelected);
				   if(selected.length != 0){
					   isSelected = true;
				   }
				   System.out.println(query);
				 
				   
					
				}
			}
		});
		JButton inputButton = new JButton("Add filter");
		inputButton.setBounds(580, 400, 100, 30);
		contentPane.add(inputButton);
	    JTextArea editTextArea = new JTextArea("Input Region");
	    editTextArea.setBounds(400, 400, 150, 30);
	    contentPane.add(editTextArea);
	    inputButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				String cmd = e.getActionCommand();
				System.out.println(cmd);
				if(cmd.equals("Send")){
					
					String input = editTextArea.getText();
					System.out.println(input);
					SQLreader sql = new SQLreader();
					query = sql.buildQueryRegion(input,query, isSelected);
					isSelected = true;
				}
				
			}
		});
	    JButton inputButton2 = new JButton("Add filter");
		inputButton2.setBounds(580, 450, 100, 30);
		contentPane.add(inputButton2);
	    JTextArea editTextArea2 = new JTextArea("Input Manicupality");
	    editTextArea2.setBounds(400, 450, 150, 30);
	    contentPane.add(editTextArea2);
	    inputButton2.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				String cmd = e.getActionCommand();
				System.out.println(cmd);
				if(cmd.equals("Send")){
					
					String input = editTextArea2.getText();
					System.out.println(input);
					SQLreader sql = new SQLreader();
					query = sql.buildQueryMunicipality(input,query, isSelected);
					isSelected = true;
				}
				
			}
		});
	    JButton inputButton3 = new JButton("Add filter");
		inputButton3.setBounds(180, 300, 100, 30);
		contentPane.add(inputButton3);
	    JTextArea editTextArea3 = new JTextArea("Input Country");
	    editTextArea3.setBounds(20, 300, 150, 30);
	    contentPane.add(editTextArea3);
	    inputButton3.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				String cmd = e.getActionCommand();
				System.out.println(cmd);
				if(cmd.equals("Send")){
					
					String input = editTextArea3.getText();
					System.out.println(input);
					SQLreader sql = new SQLreader();
					query = sql.buildQueryCountry(input,query, isSelected);
					isSelected = true;
				}
				
			}
		});
	    JButton inputButton4 = new JButton("Add filter");
		inputButton4.setBounds(180, 400, 100, 30);
		contentPane.add(inputButton4);
	    JTextArea editTextArea4 = new JTextArea("Input Airport Name");
	    editTextArea4.setBounds(20, 400, 150, 30);
	    contentPane.add(editTextArea4);
	    inputButton4.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				String cmd = e.getActionCommand();
				System.out.println(cmd);
				if(cmd.equals("Send")){
					
					String input = editTextArea4.getText();
					System.out.println(input);
					SQLreader sql = new SQLreader();
					query = sql.buildQueryAirport(input,query, isSelected);
					isSelected = true;
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
	private String[] populateStringArray(String[] LocationArray, ArrayList<String> selected){
		for(int i = 0; i < selected.size();i++){
			LocationArray[i] = selected.get(i);
			System.out.println(selected.get(i));

		}
		return LocationArray;
	}
	public void Populate(TextArea textArea, int[] ids, ArrayList<String> out2, ArrayList<String> out1){
		for(int i=0;i<ids.length;i++){
			textArea.append("Selected Destination: "+ out2.get(ids[i]-1)+"\n");
			System.out.println(out2.get(ids[i]-1));
			selectedID = ids;
					
		}
		for(int i=0;i<out1.size();i++){
			textArea.append("Selected Destination: "+ out1.get(i)+"\n");
			//System.out.println(out2.get(ids[i]-1));
			//selectedID = ids;
					
		}
	}
	public void addSelections(String selectionFile, Options op, FileWriter fWriter, ArrayList<String>selections) throws IOException, SAXException, ParserConfigurationException{
		String[] LocationTitles = new String[selections.size()];
		populateStringArray(LocationTitles, selections);
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
        	System.out.println("selected: " + selected[i]);
        	selected[i] += 1;
        	intArray.add(""+selected[i]);
        }
        //list.setSelectedIndices(selected);
        op.intArray = intArray;
        op.id = selected;
        
        writeSelectionXML(op.id,selectionFile, fWriter, selections,idsFromSelections);
        op.selectionFile = "selections.xml";
	}
	private void writeSelectionXML(int[] ids, String selectionFile, FileWriter fWriter, ArrayList<String> selected, ArrayList<String> alreadyThere) throws IOException, SAXException, ParserConfigurationException{
		
		for(int i=0;i<ids.length;i++){
			fWriter.write("\t\t<id>"+selected.get(ids[i]-1)+"</id>\n");
					
		}
		//System.out.println(alreadyThere.size());
		for(int i = 0; i < alreadyThere.size(); i++){
			fWriter.write("\t\t<id>"+alreadyThere.get(i)+"</id>\n");
		}
		
		
	}
	public ArrayList<String> runSelectedXML(String filename) throws ParserConfigurationException, SAXException, IOException{
		idsFromSelections = new ArrayList();
		File file = new File(filename);
		
		
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
		        .newInstance();
		DocumentBuilder documentBuilder = null;
		
		documentBuilder = documentBuilderFactory.newDocumentBuilder();
		
		Document document = documentBuilder.parse(file);
		int i = 0;
		
		while(document.getElementsByTagName("id").item(i) != null){
			String id = document.getElementsByTagName("id").item(i).getTextContent();
			System.out.println("id "+id);
			if(id ==null){
				System.out.println("NULLLLL");
			}
			if(idsFromSelections ==null){
				System.out.println("NU");
			}
			idsFromSelections.add(id);
			//System.out.println("id "+id);
			i++;
			
		
		}
		return idsFromSelections;
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
