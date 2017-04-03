package main.java.edu.csu2017sp314.DTR27.tripco;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import main.java.edu.csu2017sp314.DTR27.tripco.Model.Model;
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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
	}

	/**
	 * Create the frame.
	 * @param op 
	 * @param op 
	 */
	public OptionsGUI(Options opp, Model mo) {
		final Options op = opp;
		Model model = mo;
		setBounds(100, 100, 450, 300);
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
		
		JCheckBox chckbxMileage = new JCheckBox("Mileage");
		chckbxMileage.setBounds(15, 36, 129, 23);
		contentPane.add(chckbxMileage);
		chckbxMileage.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String cmd = e.getActionCommand();
					
					if(cmd.equals("Mileage")){
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
		JButton DisplayButton = new JButton("Finish");
		DisplayButton.setBounds(12, 200, 220, 30);
		contentPane.add(DisplayButton);
		DisplayButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				String cmd = e.getActionCommand();
				System.out.println(cmd);
				if(cmd.equals("Finish")){
					op.done = true;
					
					
				}
				
			}
		});
		
		}

	public void run(Options op, Model model) {
		try {
			OptionsGUI frame = new OptionsGUI(op, model);
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
	        //you are here trying to get selections to work based on indecies
	        int[] selected = list.getSelectedIndices();
	        ArrayList<String> intArray = new ArrayList<String>();
	        for(int i = 0; i < selected.length; i++){
	        	System.out.println(selected[i]);
	        	selected[i] += 1;
	        	intArray.add(""+selected[i]);
	        }
	        //list.setSelectedIndices(selected);
	        op.intArray = intArray;
	        writeSelectionXML(model,selected);
	        op.selectionFile = "selections.xml";
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private String[] populateStringArray(String[] LocationArray, Model model){
		for(int i = 0; i < model.getLocationsLength();i++){
			LocationArray[i] = model.getLocation(i).toString();
			//System.out.println(model.getLocation(i).toString());

		}
		return LocationArray;
	}
	private void writeSelectionXML(Model model, int[] ids) throws IOException{
		File xmlOutput = new File("selections.xml");
		FileWriter fWriter = new FileWriter(xmlOutput);
		fWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<selection>\n");
		fWriter.write("<filename> file.csv </filename>\n");
		fWriter.write("<destinations>\n");
		for(int i=0;i<ids.length;i++){
			fWriter.write("\t\t<id>"+model.getLocation(ids[i]-1)+"</id>\n");
					
		}
		fWriter.write("</destinations>");
		fWriter.write("</selection>");
		fWriter.close();
	}

	@Override
	public void run() {
		
	}

}
