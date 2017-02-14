package Presenter;

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
	
}
