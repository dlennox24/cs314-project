package Model;

public class Leg {
	Location locA = null;
	Location locB = null;
	int distance;
	
	public Leg(Location A, Location B){
		this.locA = A;
		this.locB = B;
		this.distance = greatCircleDistance( A, B);
		
	}
	
	public String toString(){
		return this.locA.getName() + " to " + this.locB.getName() + " : " + this.distance;			
	}
	
	private int greatCircleDistance(Location locA, Location locB) {
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
//	        double distance1 = 60 * angle1;
//
//	        System.out.println(distance1 + " nautical miles");
//

	       /*************************************************************************
	        * Compute using Haverside formula
	        *************************************************************************/
	        double a = Math.pow(Math.sin((x2-x1)/2), 2)
	                 + Math.cos(x1) * Math.cos(x2) * Math.pow(Math.sin((y2-y1)/2), 2);

	        // great circle distance in radians
	        double angle2 = 2 * Math.asin(Math.min(1, Math.sqrt(a)));

	        // convert back to degrees
	        angle2 = Math.toDegrees(angle2);

	        // each degree on a great circle of Earth is 60 nautical miles
	        double distance2 = 60 * angle2;

	        //System.out.println(distance2 + " nautical miles");
		
		
		return (int) Math.round(distance2);
	}

	public static void main(String[] args) {
		

	}

}
