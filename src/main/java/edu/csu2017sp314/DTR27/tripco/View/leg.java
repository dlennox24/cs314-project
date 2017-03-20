package main.java.edu.csu2017sp314.DTR27.tripco.View;

class Leg {
	String startCityName;
	String endCityName;
	int dist;

	Leg(String name1, String name2, int dist){
		this.startCityName=name1;
		this.endCityName=name2;
		this.dist=dist;
	}
}
