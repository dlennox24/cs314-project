package main.java.edu.csu2017sp314.DTR27.tripco.Model;



public class Location {

	private String id;
	private String name;
	private Double longitude;
	private Double latitude;
	public String elevation;
	public String municipality;
	public String region;
	public String country;
	public String continent;
	public String airportURL;
	public String regionURL;
	public String countryURL;

	
	
	public Location(String ID, String NAME, Double LONG, Double LAT, String ELEVATION, String MUNICIPALITY, String REGION, String COUNTRY, String CONTINENT, String AIRPORTURL, String REGIONURL, String COUNTRYURL){
		
		this.id = ID;
		this.name = NAME;
		this.longitude = LONG;
		this.latitude = LAT;
		this.elevation = ELEVATION;
		this.municipality = MUNICIPALITY;
		this.region = REGION;
		this.country = COUNTRY;
		this.continent = CONTINENT;
		this.airportURL = AIRPORTURL;
		this.regionURL = REGIONURL;
		this.countryURL = COUNTRYURL;
		
	}
	
	public Location(String ID, String NAME, Double LONG, Double LAT, String EXTRA){
		
		this.id = ID;
		this.name = NAME;
		this.longitude = LONG;
		this.latitude = LAT;
		
	}
	
	public String toString(){
//		return this.id + " , " + this.name + " , " + this.longitude.toString() + " , "+ this.latitude.toString();
		return this.name;
	}
	
	public String getId(){return this.id;}
	public void setId(String ID){this.id = ID;}
	
	public String getName(){return this.name;}
	public void setName(String NAME){this.name = NAME;}
	
	public Double getLong(){return this.longitude;}
	public void setLong(Double LONG){this.longitude = LONG;}

	public Double getLat(){return this.latitude;}
	public void setLat(Double LAT){this.latitude = LAT;}
	
	
	
	
	public static void main(String[] args) {
		
		String s = "ABCDMSFSDFasdasdasd122344567890!@#!$@$#%^$&&&*".replaceAll("[A-Za-z]", " ");
		System.out.println(s);
	}

}
