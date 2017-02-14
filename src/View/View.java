package View;

import Presenter.Presenter;

public class View {
	private Presenter presenter;
	private String status;
	public View() {
//		this.presenter = presenter;
		this.status = "OK";
	}
	
	public String getStatus(){
		return this.status;
	}
	public String display(String text) {
		System.out.println(text);
		return text;
	}
}