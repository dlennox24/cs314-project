package Model;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

public class Location {

	private String id;
	private String name;
	private Double longitude;
	private Double latitude;
	
	
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
		
	}
	
	public String toString(){
		
		return this.id + " , " + this.name + " , " + this.longitude.toString() + " , "+ this.latitude.toString();
		
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
