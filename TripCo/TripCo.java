package TripCo;

import TripCo.*;
import TripCo.Model.Model;
import TripCo.Presenter.Presenter;
import TripCo.View.View;

public class TripCo {

	
	public static void main(String[] args) {

//		String filePath = args[0];
//		String coordinateFormat = args[1];
//		
		Model m = new Model("/home/ap/Documents/DTR-27/TRIPCO/src/TripCo/Model/input.csv", "Decimal");
		
		for(int i = 0; i < m.Locations.size(); i++){
			System.out.println("Location [" + i + "] : " + m.Locations.get(i));
		}
		for(int i = 0; i < m.Legs.size(); i++){
			System.out.println("Leg [" + i + "] : " + m.Legs.get(i));
		}
		
		
	}

}
