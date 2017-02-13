package TripCo;


import Model.Model;
import Presenter.Presenter;
import View.View;

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
