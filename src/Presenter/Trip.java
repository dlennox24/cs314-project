package Presenter;

import java.util.ArrayList;

import Model.Leg;
import Model.Model;

public class Trip{
	private ArrayList<Leg> TripList = new ArrayList<Leg>();
	private Presenter Presenter;
	public Trip(Presenter Presenter) {
		this.Presenter = Presenter;

		
	}
	
void createTrip(){
	
	for(int i = 0; i < 4; i++){
		TripList.add(this.Presenter.getModel().getLeg(i));
		System.out.println(TripList.get(i));
	}	
	}
public String toString(){
	return this.TripList.toString();			
}
public ArrayList<Leg> getTripList(){
		
		return this.TripList;
	}
}
