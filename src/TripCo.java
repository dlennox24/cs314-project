import java.util.ArrayList;

import Model.Leg;
import Model.Model;
import Presenter.Presenter;
import View.View;

public class TripCo {

	
	public static void main(String[] args) {


		
		String option = null;
		String filename = null;
		
		for (int i = 0; i < args.length; i++){
			String temp = args[i];
			if(temp.contains("-")){
				option = temp;
			}else{
				filename=temp;
			}
			
		}
		System.out.println(option);
		System.out.println(filename);
		Presenter P = new Presenter(new Model(filename, "Decimal"));
		ArrayList<Leg> legs = new ArrayList<Leg>();
		legs = P.getLegs();
		for(int i = 0; i < legs.size(); i++){
			System.out.println("Leg [" + i + "] : " + legs.get(i));
		}

		

		
		
		
	}

}
