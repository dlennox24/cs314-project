package Presenter;

import java.util.ArrayList;

import Model.Leg;
import Model.Location;
import Model.Model;
import View.View;

public class Presenter {
	private View view;
	private static Model model;
	
	public Presenter(Model model,View view) {
		
	this.view
	= view;
	this.model
	= model;
	}
	public void start() {
	 model.getStatus();
	}
	public Model getModel(){
		return model;
	}
	public Trip getTrip(Presenter Presenter){
		Trip trip = new Trip(Presenter);
		trip.createTrip();
		return trip;
	}
	
}
