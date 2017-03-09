package Presenter;

import java.io.IOException;
import java.util.ArrayList;

import Model.Leg;
import Model.Location;
import Model.Model;
import View.View;

public class Presenter {
	private View view;
	private static Model model;
	
	public Presenter(Model model,View view, String options) {
		
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
	public View getView(){
		return view;
	}
	public void finalView(){
		try {
			view.finTrip();
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
