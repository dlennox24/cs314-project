import java.util.ArrayList;

import Model.Leg;
import Model.Model;
import Presenter.Presenter;
import Presenter.Trip;
import View.View;

public class TripCo {

	
	public static void main(String[] args) {



		
		ArrayList<String> option = new ArrayList<String>();
		String filename = null;
		
		for (int i = 0; i < args.length; i++){
			String temp = args[i];
			if(temp.contains("-")){
				option.add(temp);
			}else{
				filename=temp;
			}
			
		}
		System.out.println(option);
		System.out.println(filename);
		Presenter P = new Presenter(new Model(filename),new View("header",100,option));
		P.getTrip(P);
		

		

		
		
		
	}

}
