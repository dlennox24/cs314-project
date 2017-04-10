package main.java.edu.csu2017sp314.DTR27.tripco.Model;

import java.util.ArrayList;
import java.util.Collections;

public class Optimizer {
	//	TODO: Refactor
	ArrayList<Leg> nearestNeighbor(ArrayList<Leg> route){
		return route;
	}
	// Convert and arraylist of legs to an arraylist of locations
	ArrayList<Location> legsToLocs(ArrayList<Leg> legs){
		ArrayList<Location> locs = new ArrayList<Location>();
		for(int i=0;i<legs.size();i++){
			locs.add(legs.get(i).locA);
		}
		return locs;
	}

	// Convert and arraylist of locations to an arraylist of legs
	ArrayList<Leg> locsToLegs(ArrayList<Location> locs){
		ArrayList<Leg> newRoute = new ArrayList<Leg>();
		for(int i=0;i<locs.size()-1;i++){
			newRoute.add(new Leg(locs.get(i),locs.get(i+1), "M"));
		}
		newRoute.add(new Leg(locs.get(locs.size()-1),locs.get(0)));
		return newRoute;
	}

	double calcDist(Location start, Location end){
		return new Leg().greatCircleDistanceDouble(start, end);
	}
	
	double[] calcSwapDist2(ArrayList<Location> locs, int i, int j){
		double[] dist = new double[8];
		//		base (i,i+1) (j,j+1)
		dist[0] = calcDist(locs.get(i),locs.get(i+1))
				+ calcDist(locs.get(j),locs.get(j+1));
		//		2-opt
		//		opt2_0 (i,j) (i+1,j+1)
		dist[1] = calcDist(locs.get(i),locs.get(j))
				+ calcDist(locs.get(i+1),locs.get(j+1));
		return dist;
	}

	ArrayList<Leg> twoOpt(ArrayList<ArrayList<Leg>> allTours) {
		System.out.println("=======2OPT START=======");
		double bestDist = -1;
		ArrayList<Leg> bestRoute = null;
		for(int r=0;r<allTours.size();r++){
			ArrayList<Location> locs = this.legsToLocs(allTours.get(r));
			double minChange;
			do{
				minChange = 0;
				for(int i=1;i<locs.size()-3;i++){
					for(int j=i+2;j<locs.size()-1;j++){
						double[] dist = calcSwapDist2(locs,i,j);
						minChange = dist[1]-dist[0];
						if(minChange < 0){
//							System.out.println("Swap: "+minChange);
//							System.out.println("\tExisting: \n\t\t"+locs.get(i)+" --> "+locs.get(i+1)+"\n\t\t"+locs.get(j)+" --> "+locs.get(j+1));
//							System.out.println("\tNew: \n\t\t"+locs.get(i)+" --> "+locs.get(j)+"\n\t\t"+locs.get(i+1)+" --> "+locs.get(j+1));
							Collections.swap(locs, i+1, j);
						}
					}
				}
			}while(minChange < 0);
			ArrayList<Leg> newRoute = this.locsToLegs(locs);
			double endDist = 0;
			for(int i=0;i<newRoute.size();i++){
				endDist+=newRoute.get(i).getDistance();
			}
			if(bestDist < 0 || endDist < bestDist){
				bestRoute = newRoute;
				bestDist = endDist;
			}
		}
		System.out.println("=======2OPT STOP=======");
		return bestRoute;
	}

	double[] calcSwapDist3(ArrayList<Location> locs, int i, int j, int k){
		double[] dist = new double[8];
		//		base (i,i+1) (j,j+1) (k,k+1)
		dist[0] = calcDist(locs.get(i),locs.get(i+1))
				+ calcDist(locs.get(j),locs.get(j+1))
				+ calcDist(locs.get(k),locs.get(k+1));
		//		2-opt
		//		opt2_0 (i,i+1) (j,k) (j+1,k+1)
		dist[1] = calcDist(locs.get(i),locs.get(i+1))
				+ calcDist(locs.get(j),locs.get(k))
				+ calcDist(locs.get(j+1),locs.get(k+1));
		//		opt2_1 (k+1,i+1) (j,j+1) (k,i)
		dist[2] = calcDist(locs.get(j),locs.get(j+1))
				+ calcDist(locs.get(k),locs.get(i))
				+ calcDist(locs.get(k+1),locs.get(i+1));
		//		opt2_2 (i,j) (i+1,j+1) (k,k+1)
		dist[3] = calcDist(locs.get(k),locs.get(k+1))
				+ calcDist(locs.get(i),locs.get(j))
				+ calcDist(locs.get(i+1),locs.get(j+1));
		//		3-opt
		//		opt3_0 (i,j+1) (j,k+1) (k,i+1)
		dist[4] = calcDist(locs.get(k),locs.get(i+1))
				+ calcDist(locs.get(i),locs.get(j+1))
				+ calcDist(locs.get(j),locs.get(k+1));
		//		opt3_1 (i,k) (j,k+1) (j+1,i+1)
		dist[5] = calcDist(locs.get(i),locs.get(k))
				+ calcDist(locs.get(j),locs.get(k+1))
				+ calcDist(locs.get(j+1),locs.get(i+1));
		//		opt3_2 (i,j+1) (i+1,k+1) (k,j)
		dist[6] = calcDist(locs.get(k),locs.get(j))
				+ calcDist(locs.get(i),locs.get(j+1))
				+ calcDist(locs.get(i+1),locs.get(k+1));
		//		opt3_3 (k+1,j+1) (j,i) (k,i+1)
		dist[7] = calcDist(locs.get(j),locs.get(i))
				+ calcDist(locs.get(k),locs.get(i+1))
				+ calcDist(locs.get(k+1),locs.get(j+1));
		return dist;
	}

	ArrayList<Leg> threeOpt(ArrayList<ArrayList<Leg>> allTours){
		System.out.println("=======3OPT START=======");
		double bestDist = -1;
		ArrayList<Leg> bestRoute = null;
		for(int r=0;r<allTours.size();r++){
			ArrayList<Location> locs = this.legsToLocs(allTours.get(r));
			System.out.println(locs);
			double minChange;
			do{
				minChange = 0;
				for(int i=1;i<locs.size()-5;i++){
					for(int j=i+2;j<locs.size()-3;j++){
						for(int k=j+2;k<locs.size()-1;k++){
							double[] dist = calcSwapDist3(locs,i,j,k);
							int minId = 0;
							for(int d=1;d<8;d++){
								if(dist[d]<dist[minId]){
									minId = d;
								}
							}
							//minDistance - basePathDistance; gets total distance improvement
							minChange = dist[minId] - dist[0];
//							if(minId !=0){
//								for(int c=0;c<8;c++){
//									System.out.print(dist[c]+", ");
//								}
//								System.out.println("\nCASE: "+minId);
//								System.out.println(locs);
//							}
							switch (minId) {
							case 1:								//(i,i+1) (j,j+1) (k,k+1)
								Collections.swap(locs, k, j+1); 	//(i,i+1) (j,k) (j+1,k+1)
								break;
							case 2: 								//(i,i+1) (j,j+1) (k,k+1)
								Collections.swap(locs, i+1, k);		//(i,k) (j,j+1) (i+1,k+1)
								Collections.swap(locs, j, j+1);		//(i,k) (j+1,j) (i+1,k+1)
								//(i,k)(j+1,j)(i+1,k+1)
								break;
							case 3: 								//(i,i+1) (j,j+1) (k,k+1)
								Collections.swap(locs, i+1, j); 	//(i,j) (i+1,j+1) (k,k+1)
								Collections.swap(locs, k, i+1);		//(i,j) (k,j+1) (i+1,k+1)
								Collections.swap(locs, j+1, k+1);	//(i,j) (k,k+1) (i+1,j+1)
								break;
							case 4: 								//(i,i+1) (j,j+1) (k,k+1)
								Collections.swap(locs, k+1, i+1); 	//(i,k+1) (j,j+1) (k,i+1)
								Collections.swap(locs, k+1, j+1); 	//(i,j+1) (j,k+1) (k,i+1)
								Collections.swap(locs, j, k);		//(i,j+1) (k,k+1) (j,i+1)
								Collections.swap(locs, k+1, i+1);	//(i,j+1) (k,i+1) (j,k+1)
								break;
							case 5: 								//(i,i+1) (j,j+1) (k,k+1)
								Collections.swap(locs, i+1, k); 	//(i,k) (j,j+1) (i+1,k+1)
								Collections.swap(locs, k+1, j+1); 	//(i,k) (j,k+1) (i+1,j+1)
								Collections.swap(locs, j+1, i+1); 	//(i,k) (j,k+1) (j+1,i+1) 	 	needed for correct order
								break;
							case 6: 								//(i,i+1) (j,j+1) (k,k+1)
								Collections.swap(locs, i+1, j+1); 	//(i,j+1) (j,i+1) (k,k+1)
								Collections.swap(locs, i+1, k);		//(i,j+1) (j,k) (i+1,k+1)
								Collections.swap(locs, k, j);		//(i,j+1) (k,j) (i+1,k+1)
								break;
							case 7: 								//(i,i+1) (j,j+1) (k,k+1)
								Collections.swap(locs, i+1, j);		//(i,j) (i+1,j+1) (k,k+1)
								Collections.swap(locs, j+1, k);		//(i,j) (i+1,k) (j+1,k+1)
								//(i,j)(i+1,k)(j+1,k+1)
								break;
							default:
								//Case 0: already shortest path. no improvements can be made
								break;
							}
						}
					}
				}
			}while(minChange<0);
			
			ArrayList<Leg> newRoute = this.locsToLegs(locs);
			double endDist = 0;
			for(int i=0;i<newRoute.size();i++){
				endDist+=newRoute.get(i).getDistance();
			}
			if(bestDist < 0 || endDist < bestDist){
				bestRoute = newRoute;
				bestDist = endDist;
			}
			System.out.println("endDist: "+endDist);
		}
		System.out.println("=======3OPT END=======");
		return bestRoute;
	}
}
