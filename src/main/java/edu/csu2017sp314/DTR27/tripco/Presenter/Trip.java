package main.java.edu.csu2017sp314.DTR27.tripco.Presenter;

import java.util.ArrayList;

import main.java.edu.csu2017sp314.DTR27.tripco.Model.Leg;
import main.java.edu.csu2017sp314.DTR27.tripco.Model.Model;

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
      String ID1 =  Presenter.getModel().getLeg(i).getLocations().get(0).getId();
      String LAT1 = Presenter.getModel().getLeg(i).getLocations().get(0).getLat().toString();
      String LONG1 = Presenter.getModel().getLeg(i).getLocations().get(0).getLong().toString();
      String ELEVATION1 = Presenter.getModel().getLeg(i).getLocations().get(0).elevation; 
      String MUNICIPALITY1 = Presenter.getModel().getLeg(i).getLocations().get(0).municipality;
      String REGION1 = Presenter.getModel().getLeg(i).getLocations().get(0).region;
      String COUNTRY1 = Presenter.getModel().getLeg(i).getLocations().get(0).country;
      String CONTINENT1 = Presenter.getModel().getLeg(i).getLocations().get(0).continent;
      String AIRPORTURL1 = Presenter.getModel().getLeg(i).getLocations().get(0).airportURL;
      String REGIONURL1 = Presenter.getModel().getLeg(i).getLocations().get(0).regionURL;
      String COUNTRYURL1 = Presenter.getModel().getLeg(i).getLocations().get(0).countryURL;
      String ID2 =  Presenter.getModel().getLeg(i).getLocations().get(1).getId();
      String LAT2 = Presenter.getModel().getLeg(i).getLocations().get(1).getLat().toString();
      String LONG2 = Presenter.getModel().getLeg(i).getLocations().get(1).getLong().toString();
      String ELEVATION2 = Presenter.getModel().getLeg(i).getLocations().get(1).elevation; 
      String MUNICIPALITY2 = Presenter.getModel().getLeg(i).getLocations().get(1).municipality;
      String REGION2 = Presenter.getModel().getLeg(i).getLocations().get(1).region;
      String COUNTRY2 = Presenter.getModel().getLeg(i).getLocations().get(1).country;
      String CONTINENT2 = Presenter.getModel().getLeg(i).getLocations().get(1).continent;
      String AIRPORTURL2 = Presenter.getModel().getLeg(i).getLocations().get(1).airportURL;
      String REGIONURL2 = Presenter.getModel().getLeg(i).getLocations().get(1).regionURL;
      String COUNTRYURL2 = Presenter.getModel().getLeg(i).getLocations().get(1).countryURL;
      String units = Presenter.getModel().units;
      Presenter.getView().addLeg(lat1, lon1, lat2, lon2, distance, name1, name2,
    		  ID1, LAT1, LONG1, ELEVATION1, MUNICIPALITY1, REGION1, COUNTRY1, CONTINENT1,
    		  AIRPORTURL1, REGIONURL1, COUNTRYURL1, ID2, LAT2, LONG2, ELEVATION2, MUNICIPALITY2,
    		  REGION2, COUNTRY2, CONTINENT2, AIRPORTURL2, REGIONURL2, COUNTRYURL2,units);

    }



}
public String toString(){
  return this.TripList.toString();
}
}
