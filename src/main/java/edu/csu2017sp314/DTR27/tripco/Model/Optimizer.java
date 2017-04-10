package main.java.edu.csu2017sp314.DTR27.tripco.Model;

import java.util.ArrayList;
import java.util.Collections;

public class Optimizer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
//	TODO: Refactor
	ArrayList<Leg> nearestNeighbor(ArrayList<Leg> route){
		return route;
	}
	
	ArrayList<Leg> twoOpt(ArrayList<Leg> route){
		System.out.println("TWO OPT START");
		Leg calcDist = new Leg(route.get(0).locA, route.get(0).locB, "M");
		ArrayList<Location> locs = new ArrayList<Location>();
		for(int i=0;i<route.size();i++){
			locs.add(route.get(i).locA);
		}
		locs.add(route.get(0).locA); // Complete the tour
		double minChange;
		int mini=-1;
		int minj=-1;
		do{
			minChange = 0;
			for(int i=0;i<locs.size()-2;i++){
				for(int j=i+2;j<locs.size()-1;j++){
					double change = calcDist.greatCircleDistanceDouble(locs.get(i),locs.get(j))
							+ calcDist.greatCircleDistanceDouble(locs.get(i+1),locs.get(j+1))
							- calcDist.greatCircleDistanceDouble(locs.get(i),locs.get(i+1))
							- calcDist.greatCircleDistanceDouble(locs.get(j),locs.get(j+1));
					if(minChange > change){
						minChange = change;
						//mini=i;
						//minj=j;
						System.out.println("Swap");
						System.out.println("\tExisting: \n\t\t"+locs.get(i)+" --> "+locs.get(i+1)+"\n\t\t"+locs.get(j)+" --> "+locs.get(j+1));
						System.out.println("\tNew: \n\t\t"+locs.get(i)+" --> "+locs.get(j)+"\n\t\t"+locs.get(i+1)+" --> "+locs.get(j+1));
						Collections.swap(locs, i+1, j);

					}
				}
			}
			//Collections.swap(locs, mini, minj);
		}while(minChange < 0);
		ArrayList<Leg> newRoute = new ArrayList<Leg>();
		for(int i=0;i<locs.size()-1;i++){
			newRoute.add(new Leg(locs.get(i),locs.get(i+1), "M"));
		}
		System.out.println("TWO OPT STOP");
		return newRoute;
	}
	
//	TODO: Implement
	ArrayList<Leg> threeOpt(ArrayList<Leg> legs){
		return legs;
	}
	public ArrayList<Leg> twoOpt(ArrayList<ArrayList<Leg>> allTours, String a) {
		for(ArrayList<Leg> route: allTours){
		
		
			
		System.out.println("TWO OPT START");
		Leg calcDist = new Leg(route.get(0).locA, route.get(0).locB, a);
		ArrayList<Location> locs = new ArrayList<Location>();
		for(int i=0;i<route.size();i++){
			locs.add(route.get(i).locA);
		}
		locs.add(route.get(0).locA); // Complete the tour
		double minChange;
		int mini=-1;
		int minj=-1;
		do{
			minChange = 0;
			for(int i=0;i<locs.size()-2;i++){
				for(int j=i+2;j<locs.size()-1;j++){
					double change = calcDist.greatCircleDistanceDouble(locs.get(i),locs.get(j))
							+ calcDist.greatCircleDistanceDouble(locs.get(i+1),locs.get(j+1))
							- calcDist.greatCircleDistanceDouble(locs.get(i),locs.get(i+1))
							- calcDist.greatCircleDistanceDouble(locs.get(j),locs.get(j+1));
					if(minChange > change){
						minChange = change;
						//mini=i;
						//minj=j;
						System.out.println("Swap");
						System.out.println("\tExisting: \n\t\t"+locs.get(i)+" --> "+locs.get(i+1)+"\n\t\t"+locs.get(j)+" --> "+locs.get(j+1));
						System.out.println("\tNew: \n\t\t"+locs.get(i)+" --> "+locs.get(j)+"\n\t\t"+locs.get(i+1)+" --> "+locs.get(j+1));
						Collections.swap(locs, i+1, j);

					}
				}
			}
			//Collections.swap(locs, mini, minj);
		}while(minChange < 0);
		ArrayList<Leg> newRoute = new ArrayList<Leg>();
		for(int i=0;i<locs.size()-1;i++){
			newRoute.add(new Leg(locs.get(i),locs.get(i+1), "M"));
		}
		System.out.println("TWO OPT STOP");
		return newRoute;
		
	}
		return null;
	}
}
