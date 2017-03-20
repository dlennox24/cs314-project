package test.java.edu.csu2017sp314.DTR27.tripco;

import Presenter.Presenter;
import Model.Model;
import View.View;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class TestTripCo {

	

	@Test
	public void getStart() {
		assertEquals("OK", new Model("/s/bach/n/under/gtjohnso/cs314/DTR-27/src/Model/input.csv").getStatus());
	}
	@Test
	public void getModel() {
		Model model = new Model("input.csv");
		ArrayList<String> a = null;
		View view = new View("header",100, "a", "a");
		Presenter Presenter = new Presenter(model,view, "aaa", "aaa");
		assertEquals(model, Presenter.getModel());
	
	}
}
