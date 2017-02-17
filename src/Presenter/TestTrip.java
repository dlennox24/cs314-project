package Presenter;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import Model.Model;
import View.View;

public class TestTrip {
	@Test
	public void getTripList() {
		Model model = new Model("src/input.csv");
		ArrayList<String> a = null;
		View view = new View("header",100, a);
		Presenter Presenter = new Presenter(model,view);
		Trip t = new Trip(Presenter);
		t.createTrip();
		for(int i = 0; i < 4; i++){
			assertEquals(model.getLeg(i), t.getTripList().get(i));
		}
	
	}
}
