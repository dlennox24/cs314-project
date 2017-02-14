import Model.Model;
import Presenter.Presenter;
import View.View;

public class TripCo {

	
	public static void main(String[] args) {


		Model m = new Model("/s/bach/l/under/pello/school/DTR-27/src/Model/input.csv", "Decimal");
		
		for(int i = 0; i < m.Locations.size(); i++){
			System.out.println("Location [" + i + "] : " + m.Locations.get(i));
		}
		for(int i = 0; i < m.Legs.size(); i++){
			System.out.println("Leg [" + i + "] : " + m.Legs.get(i));
		}
		
		
	}

}
