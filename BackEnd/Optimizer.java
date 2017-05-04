import java.time.Clock;
import java.util.ArrayList;
import java.util.Collections;

public class Optimizer {
	private String units = "m";
	//	TODO: Refactor
	


	
        public double greatCircleDistance(Location locA, Location locB) {
		//	      /*************************************************************************
		//	       * Compute using law of cosines
		//	       *************************************************************************/
		//	       // great circle distance in radians
		//	       double angle1 = Math.acos(Math.sin(x1) * Math.sin(x2)
		//	                     + Math.cos(x1) * Math.cos(x2) * Math.cos(y1 - y2));
		//
		//	       // convert back to degrees
		//	       angle1 = Math.toDegrees(angle1);
		//
		//	       // each degree on a great circle of Earth is 60 nautical miles
		//	       double distance1 = (3961) * angle1;
		//
		//	      // System.out.println(distance1 + " nautical miles");


		/*************************************************************************
		* Compute using Haverside formula
		*************************************************************************/
		int R;
		if(units.toUpperCase().equals("K")){
			R = 6371; //KILOMETER

		}else{
			//km =6371
			R = 3959; // MILES Radious of the earth
		}
		Double lat1 =locA.lat;
		Double lon1 =locA.lng;
		Double lat2 = locB.lat;
		Double lon2 = locB.lng;
		Double latDistance = Math.toRadians(lat2-lat1);
		Double lonDistance = Math.toRadians(lon2-lon1);
		Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) + 
				Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * 
				Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
		Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		Double distance = R * c;
		return (int) Math.ceil(distance);
	}
        double calcDist(Location start, Location end){
                
		return greatCircleDistance(start, end);
	}


	
        ArrayList twoOpt2(ArrayList<Location> tour) {
		System.out.println("=======2OPT START=======");
		double bestDist = -1;
                ArrayList<Location> locs = tour;
                double minChange;
                do{
                    minChange = 0;
                    //System.out.println(locs.size()+" "+tour.size());
                    for(int i=0;i<locs.size()-3;i++){
			for(int j=i+2;j<locs.size()-1;j++){
                            double[] dist = calcSwapDist2(locs,i,j);
                            minChange = dist[1]-dist[0];
                            //System.out.println(minChange + " Locations "+ locs.get(i).name);
                            if(minChange < 0){
				//System.out.println("Swap: "+minChange);
				//System.out.println("\tExisting: \n\t\t"+locs.get(i).name+" --> "+locs.get(i+1).name+"\n\t\t"+locs.get(j).name+" --> "+locs.get(j+1).name);		
                               // System.out.println("\tNew: \n\t\t"+locs.get(i).name+" --> "+locs.get(j).name+"\n\t\t"+locs.get(i+1).name+" --> "+locs.get(j+1).name);				
                                Collections.swap(locs, i+1, j);
                            }
                        }
                    }
                }while(minChange < 0);
                //System.out.println(locs.size());
		System.out.println("=======2OPT STOP=======");
		//String s = locs.get(0).id;
                for(int i = 0; i < locs.size()-1; i++){
                    locs.get(i).distance = greatCircleDistance(locs.get(i), locs.get(i+1));
                }
                    locs.get(locs.size()-1).distance = greatCircleDistance(locs.get(locs.size()-1),locs.get(0));
		return locs;
                
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
        ArrayList<Location> threeOpt(ArrayList<Location> tour){
		System.out.println("=======3OPT START=======");
			ArrayList<Location> locs = tour;
			//System.out.println(locs);
			double minChange;
			do{
				minChange = 0;
				for(int i=0;i<locs.size()-5;i++){
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
								Collections.swap(locs, j+1, i+1); 	//(i,k) (j,k+1) (j+1,i+1) 		needed for correct order
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
                        for(int i = 0; i < locs.size()-1; i++){
                            locs.get(i).distance = greatCircleDistance(locs.get(i), locs.get(i+1));
                          }
                            locs.get(locs.size()-1).distance = greatCircleDistance(locs.get(locs.size()-1),locs.get(0));
		
		System.out.println("=======3OPT END=======");
		return locs;
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
	

	
}
