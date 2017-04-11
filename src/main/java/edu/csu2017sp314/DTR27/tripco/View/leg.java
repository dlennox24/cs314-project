package main.java.edu.csu2017sp314.DTR27.tripco.View;

class Leg {
	String startCityName;
	String endCityName;
	int dist;
	String ID1;
	String LAT1;
	String LONG1;
	String ELEVATION1;
	String MUNICIPALITY1;
	String REGION1;
	String COUNTRY1;
	String CONTINENT1;
	String AIRPORTURL1;
	String REGIONURL1;
	String COUNTRYURL1;
	String ID2;
	String LAT2;
	String LONG2;
	String ELEVATION2;
	String MUNICIPALITY2;
	String REGION2;
	String COUNTRY2;
	String CONTINENT2;
	String AIRPORTURL2;
	String REGIONURL2;
	String COUNTRYURL2;
	String units;

	Leg(String name1, String name2, int dist, String ID1, String LAT1, String LONG1, String ELEVATION1, String MUNICIPALITY1, String REGION1, String COUNTRY1, String CONTINENT1, String AIRPORTURL1, String REGIONURL1, String COUNTRYURL1, String ID2, String LAT2, String LONG2,String ELEVATION2, String MUNICIPALITY2, String REGION2, String COUNTRY2, String CONTINENT2, String AIRPORTURL2, String REGIONURL2, String COUNTRYURL2, String units){
		this.startCityName=name1;
		this.endCityName=name2;
		this.dist=dist;
		this.ID1=ID1;
		this.LAT1=LAT1;
		this.LONG1=LONG1;
		this.ELEVATION1=ELEVATION1;
		this.MUNICIPALITY1=MUNICIPALITY1;
		this.REGION1=REGION1;
		this.COUNTRY1=COUNTRY1;
		this.CONTINENT1=CONTINENT1;
		this.AIRPORTURL1=AIRPORTURL1;
		this.REGIONURL1=REGIONURL1;
		this.COUNTRYURL1=COUNTRYURL1;
		this.ID2=ID2;
		this.LAT2=LAT2;
		this.LONG2=LONG2;
		this.ELEVATION2=ELEVATION2;
		this.MUNICIPALITY2=MUNICIPALITY2;
		this.REGION2=REGION2;
		this.COUNTRY2=COUNTRY2;
		this.CONTINENT2=CONTINENT2;
		this.AIRPORTURL2=AIRPORTURL2;
		this.REGIONURL2=REGIONURL2;
		this.COUNTRYURL2=COUNTRYURL2;
		this.units=units;
	}
}
