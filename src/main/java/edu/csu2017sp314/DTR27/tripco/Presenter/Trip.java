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
	
void createTrip(String options){
	
		for(int i = 0; i < this.Presenter.getModel().getLegsLength(); i++){
			
			double lat1 = Presenter.getModel().getLeg(i).getLocations().get(0).getLat();
			double lon1 = Presenter.getModel().getLeg(i).getLocations().get(0).getLong();
			double lat2 = Presenter.getModel().getLeg(i).getLocations().get(1).getLat();
			double lon2 = Presenter.getModel().getLeg(i).getLocations().get(1).getLong();
			int distance = Presenter.getModel().getLeg(i).getDistance();
			String name1 = Presenter.getModel().getLeg(i).getLocations().get(0).getName();
			String name2 = Presenter.getModel().getLeg(i).getLocations().get(1).getName();
			Presenter.getView().addLeg(lat1, lon1, lat2, lon2, distance, name1, name2);
		
		}	

		
	
}
public String toString(){
	return this.TripList.toString();			
}
}

