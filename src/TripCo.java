import java.util.ArrayList;

import Model.Leg;
import Model.Model;
import Presenter.Presenter;
import Presenter.Trip;
import View.View;

public class TripCo {

	
	public static void main(String[] args) {



		
		String option = "";
		String filename = null;
		
		for (int i = 0; i < args.length; i++){
			String temp = args[i];
			if(temp.contains("-")){
				option += temp;
			}else{
				filename=temp;
			}
			
		}
		String[] outputFileName = filename.split("/");
		String newOutPutFile = outputFileName[outputFileName.length-1];
		Model model = new Model(filename);
		int finalDistance = 0;
		for(int i = 0; i < model.getLegsLength(); i++){
			finalDistance += model.getLeg(i).getDistance();
		}
		Presenter P = new Presenter(new Model(filename),new View("Colorado",finalDistance,option,newOutPutFile.substring(0, newOutPutFile.length()-4)),option);
		
		P.getTrip(P,option);
		P.finalView();
		System.out.println(option);
		System.out.println(filename);

		

		
		
		
	}

}
