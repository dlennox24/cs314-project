import Model.Model;
import Presenter.Presenter;
import View.View;

public class TripCo {

	
	public static void main(String[] args) {

		String fileName = args[0];
		System.out.println(fileName);
		Model m = new Model(fileName, "Decimal");
		
		for(int i = 0; i < m.Locations.size(); i++){
			//System.out.println("Location [" + i + "] : " + m.Locations.get(i));
		}
		for(int i = 0; i < m.Legs.size(); i++){
			//System.out.println("Leg [" + i + "] : " + m.Legs.get(i));
		}
		
		
	}

}
