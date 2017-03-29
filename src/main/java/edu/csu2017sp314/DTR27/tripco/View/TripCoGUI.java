package main.java.edu.csu2017sp314.DTR27.tripco.View;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import javax.swing.JDesktopPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JTextField;

public class TripCoGUI extends JFrame {

	private JPanel contentPane;
	private JPanel contentPane1;
	private JTextField txtWelcomeToTripco;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
	}

	/**
	 * Create the frame.
	 */
	public TripCoGUI(String file) {
		final String fileName = file;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setTitle("TripCO");
		contentPane = new JPanel();
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JButton DisplayButton = new JButton("Display Map");
		JLabel sequenceNum = new JLabel();
		sequenceNum.setSize(233, -110);
		sequenceNum.setLocation(100, 162);
		DisplayButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				String cmd = e.getActionCommand();
				System.out.println(cmd);
				if(cmd.equals("Display Map")){
					
					JFrame frame = new JFrame();
					frame.setBounds(100, 100, 1280, 1024);
					SVGMapDisplay svg = new SVGMapDisplay(frame, fileName);
					svg.run();
					
				}
				
			}
		});
		DisplayButton.setBounds(12, 112, 220, 30);
		contentPane.add(DisplayButton);
		contentPane.add(sequenceNum);
		
		JButton btnDisplayItinerary = new JButton("Display Itinerary");
		btnDisplayItinerary.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				String cmd = e.getActionCommand();
				System.out.println(cmd);
				if(cmd.equals("Display Itinerary")){
					
					IteneraryDisplay id = new IteneraryDisplay(fileName);
					id.run(fileName);
					
				}
				
			}
		});
		btnDisplayItinerary.setBounds(12, 160, 220, 30);
		contentPane.add(btnDisplayItinerary);
		
		txtWelcomeToTripco = new JTextField();
		txtWelcomeToTripco.setFont(new Font("FreeMono", Font.BOLD, 25));
		txtWelcomeToTripco.setEditable(false);
		txtWelcomeToTripco.setText("Welcome To TripCo");
		txtWelcomeToTripco.setBounds(12, -24, 416, 124);
		contentPane.add(txtWelcomeToTripco);
		txtWelcomeToTripco.setColumns(10);
		
		
		
	}
	public void run(String fileName) {
		try {
			TripCoGUI frame = new TripCoGUI(fileName);
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
