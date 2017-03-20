package main.java.edu.csu2017sp314.DTR27.tripco.Presenter;

import java.io.IOException;
import java.util.ArrayList;

import Model.Leg;
import Model.Location;
import Model.Model;
import View.View;

public class Presenter {
	private View view;
	private static Model model;
	String bgFile = null;
	public Presenter(Model model,View view, String options, String bgFile) {
		
	this.view
	= view;
	this.model
	= model;
	this.bgFile = bgFile;
	}
	public void start() {
	 model.getStatus();
	}
	public Model getModel(){
		return model;
	}
	public View getView(){
		return view;
	}
	public void finalView(String fileName){
		try {
			
			view.finTrip(fileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Trip getTrip(Presenter Presenter, String options){
		Trip trip = new Trip(Presenter);
		trip.createTrip(options);
		return trip;
	}
	
}
