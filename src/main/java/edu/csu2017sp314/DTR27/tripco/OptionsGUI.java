package main.java.edu.csu2017sp314.DTR27.tripco;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import main.java.edu.csu2017sp314.DTR27.tripco.View.IteneraryDisplay;
import main.java.edu.csu2017sp314.DTR27.tripco.View.SVGMapDisplay;

import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JCheckBox;

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
	public OptionsGUI(Options opp) {
		final Options op = opp;
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

	public void run(Options op) {
		try {
			OptionsGUI frame = new OptionsGUI(op);
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		
	}

}
