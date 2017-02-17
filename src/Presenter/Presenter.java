package Presenter;

import java.util.ArrayList;

import Model.Leg;
import Model.Location;
import Model.Model;
import View.View;

public class Presenter {
	private View view;
	private Model model;
	public Presenter(Model model) {
	this.view
	= view;
	this.model
	= model;
	}
	public void start() {
	 model.getStatus();
	}
	public ArrayList<Leg> getLegs() {
		ArrayList<Leg> ret = new ArrayList();
		ret = model.Legs;
		return ret;
		}
}
