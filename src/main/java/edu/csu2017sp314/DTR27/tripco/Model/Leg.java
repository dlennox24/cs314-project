package main.java.edu.csu2017sp314.DTR27.tripco.Model;

import java.util.ArrayList;

public class Leg {
	Location locA = null;
	Location locB = null;
	private int distance;
	
	public Leg(Location A, Location B){
		this.locA = A;
		this.locB = B;
		this.distance = greatCircleDistance( A, B);
		//System.out.println("start: " + locA.getName() + "\nfinish: " + locB.getName() + "\ndis: " + distance) ;
		
	}
	
	public Leg(){}
	
	public ArrayList<Location> getLocations(){
		ArrayList<Location> ret = new ArrayList<Location>();
		ret.add(this.locA);
		ret.add(this.locB);
		return ret;
	}
	public int getDistance(){
		return this.distance;
	}
	
	public void setLocA(Location a){
		this.locA = a;
		this.distance = greatCircleDistance(a,this.locB);
	}
	
	public void setLocB(Location b){
		this.locB = b;
		this.distance = greatCircleDistance(this.locA,b);
	}
	
	public void setLocs(Location a, Location b){
		this.locA = a;
		this.locB = b;
		this.distance = greatCircleDistance(a,b);
	}
	
	public String toString(){
		return this.locA.getName() + " to " + this.locB.getName() + " : " + this.distance;
	}
	
	public int greatCircleDistance(Location locA, Location locB) {
			double x1 = Math.toRadians(locA.getLong());
	        double y1 = Math.toRadians(locA.getLat());
	        double x2 = Math.toRadians(locB.getLong());
	        double y2 = Math.toRadians(locB.getLat());

//	       /*************************************************************************
//	        * Compute using law of cosines
//	        *************************************************************************/
//	        // great circle distance in radians
//	        double angle1 = Math.acos(Math.sin(x1) * Math.sin(x2)
//	                      + Math.cos(x1) * Math.cos(x2) * Math.cos(y1 - y2));
//
//	        // convert back to degrees
//	        angle1 = Math.toDegrees(angle1);
//
//	        // each degree on a great circle of Earth is 60 nautical miles
//	        double distance1 = (3961) * angle1;
//
//	       // System.out.println(distance1 + " nautical miles");


	       /*************************************************************************
	        * Compute using Haverside formula
	        *************************************************************************/
	        //km =6371
	        final int R = 3959; // Radious of the earth
			Double lat1 =locA.getLat();
			Double lon1 =locA.getLong();
			Double lat2 = locB.getLat();
			Double lon2 = locB.getLong();
			Double latDistance = Math.toRadians(lat2-lat1);
			Double lonDistance = Math.toRadians(lon2-lon1);
			Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) + 
					   Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * 
					   Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
			Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
			Double distance = R * c;
			
			//System.out.println("start: " + locA.getName() + " lat: "+locA.getLat()+" long: " + locA.getLong()+ "\nfinish: " + locB.getName() + " lat: "+locB.getLat()+" long: " + locB.getLong() + "\ndis: " + distance) ;

		
		return (int) Math.ceil(distance);
	}
	public double greatCircleDistanceDouble(Location locA, Location locB) {
		double x1 = Math.toRadians(locA.getLong());
        double y1 = Math.toRadians(locA.getLat());
        double x2 = Math.toRadians(locB.getLong());
        double y2 = Math.toRadians(locB.getLat());

//       /*************************************************************************
//        * Compute using law of cosines
//        *************************************************************************/
//        // great circle distance in radians
//        double angle1 = Math.acos(Math.sin(x1) * Math.sin(x2)
//                      + Math.cos(x1) * Math.cos(x2) * Math.cos(y1 - y2));
//
//        // convert back to degrees
//        angle1 = Math.toDegrees(angle1);
//
//        // each degree on a great circle of Earth is 60 nautical miles
//        double distance1 = (3961) * angle1;
//
//       // System.out.println(distance1 + " nautical miles");


       /*************************************************************************
        * Compute using Haverside formula
        *************************************************************************/
        //km =6371
        final int R = 3959; // Radious of the earth
		Double lat1 =locA.getLat();
		Double lon1 =locA.getLong();
		Double lat2 = locB.getLat();
		Double lon2 = locB.getLong();
		Double latDistance = Math.toRadians(lat2-lat1);
		Double lonDistance = Math.toRadians(lon2-lon1);
		Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) + 
				   Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * 
				   Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
		Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		Double distance = R * c;
		
		//System.out.println("start: " + locA.getName() + " lat: "+locA.getLat()+" long: " + locA.getLong()+ "\nfinish: " + locB.getName() + " lat: "+locB.getLat()+" long: " + locB.getLong() + "\ndis: " + distance) ;

	
	return (distance);
}


	public static void main(String[] args) {
		

	}

}
