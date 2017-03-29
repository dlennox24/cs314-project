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
		System.out.println("\nTWO OPT START");
		Leg calcDist = new Leg(route.get(0).locA, route.get(0).locB);
		ArrayList<Location> locs = new ArrayList<Location>();
		for(int i=0;i<route.size();i++){
			locs.add(route.get(i).locA);
		}
		locs.add(route.get(0).locA); // Complete the tour
		int minChange;
		do{
			minChange = 0;
			for(int i=0;i<locs.size()-2;i++){
				for(int j=i+2;j<locs.size()-1;j++){
					int change = calcDist.greatCircleDistance(locs.get(i),locs.get(j))
							+ calcDist.greatCircleDistance(locs.get(i+1),locs.get(j+1))
							- calcDist.greatCircleDistance(locs.get(i),locs.get(i+1))
							- calcDist.greatCircleDistance(locs.get(j),locs.get(j+1));
					if(minChange > change){
						minChange = change;
						System.out.println("Swap");
						System.out.println("\tExisting: \n\t\t"+locs.get(i)+" --> "+locs.get(i+1)+"\n\t\t"+locs.get(j)+" --> "+locs.get(j+1));
						System.out.println("\tNew: \n\t\t"+locs.get(i)+" --> "+locs.get(j)+"\n\t\t"+locs.get(i+1)+" --> "+locs.get(j+1));
						Collections.swap(locs, i+1, j);
					}
				}
			}
		}while(minChange < 0);
		ArrayList<Leg> newRoute = new ArrayList<Leg>();
		for(int i=0;i<locs.size()-1;i++){
			newRoute.add(new Leg(locs.get(i),locs.get(i+1)));
		}
		System.out.println("TWO OPT STOP\n");
		return newRoute;
	}
	
//	TODO: Implement
	ArrayList<Leg> threeOpt(ArrayList<Leg> legs){
		return legs;
	}
}
