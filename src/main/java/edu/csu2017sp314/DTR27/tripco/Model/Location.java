package main.java.edu.csu2017sp314.DTR27.tripco.Model;



public class Location {

	private String id;
	private String name;
	private Double longitude;
	private Double latitude;
	public String extraInfo;
	
	
	public Location(String ID, String NAME, Double LONG, Double LAT){
		
		this.id = ID;
		this.name = NAME;
		this.longitude = LONG;
		this.latitude = LAT;
		
	}
	
	public Location(String ID, String NAME, Double LONG, Double LAT, String EXTRA){
		
		this.id = ID;
		this.name = NAME;
		this.longitude = LONG;
		this.latitude = LAT;
		this.extraInfo=EXTRA;
		
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
