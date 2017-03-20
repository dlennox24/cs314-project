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

import View.IteneraryDisplay;

import javax.swing.JRadioButton;
import javax.swing.JCheckBox;

public class OptionsGUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
	}

	/**
	 * Create the frame.
	 */
	public OptionsGUI() {
		
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
			
			int count = 0;
			@Override
			public void actionPerformed(ActionEvent e) {
				String cmd = e.getActionCommand();
					
					if(cmd.equals("Mileage")){
						count++;
						System.out.println(count);
					
					}
					if(count % 2 == 1){
						System.out.println("true");
					}else{
						System.out.println("false");
					}
				
				}
			
			
			});
		
	}
	public void run() {
		try {
			OptionsGUI frame = new OptionsGUI();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
