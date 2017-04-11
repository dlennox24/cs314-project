package main.java.edu.csu2017sp314.DTR27.tripco.Model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.ResultSet;

public class SQLreader {

        final static String myDriver = "com.mysql.jdbc.Driver";
        final static String myUrl = "jdbc:mysql://faure.cs.colostate.edu/cs314";
        final static String count = "SELECT COUNT(1) ";
        final static String columns = "SELECT airports.id,airports.name,latitude,longitude,municipality,regions.name,countries.name,continents.name, airports.elevation_ft, airports.wikipedia_link, countries.wikipedia_link, regions.wikipedia_link ";
        final static String airports = "FROM airports ";
        final static String continents = "FROM continents ";
        final static String where =  "WHERE type='large_airport'";
        final static String ob = "ORDER BY airports.name DESC";
        final static String join =   "INNER JOIN countries ON countries.continent = continents.id "+
                        "INNER JOIN regions ON regions.iso_country = countries.code "+
                        "INNER JOIN airports ON airports.iso_region = regions.code ";
        final static String limit = "LIMIT 100";

	public static void main(String[] args){

        try	{ // connect to the database 
            Class.forName(myDriver); 
            Connection conn = DriverManager.getConnection(myUrl, args[0], args[1]);	

			try { // create a statement
				Statement st = conn.createStatement();
				
				try { // submit a query to count the results
					ResultSet rs = st.executeQuery(count+airports+where);

					try { // print the number of rows
						rs.next();
                        			int rows = rs.getInt(1);
                        			System.out.printf("Selected rows = %d\n", rows);
					} finally { rs.close(); }
				
					// submit a query to list all large airports
					 rs = st.executeQuery(columns+continents+join+where);

					try { // iterate through query results and print using column numbers
                        			System.out.println("id,name,latitude,longitude,municipality,region,country,continent");
                        			File file = new File("output.csv");
                        			 BufferedWriter writer = new BufferedWriter( new FileWriter(file));
                        		      writer.write("id,name,latitude,longitude,municipality,region,country,continent\n");
                        			
						while (rs.next()) {
                           				for (int i = 1; i <= 7; i++){ 
                                				System.out.printf("%s,", rs.getString(i));
                                				writer.write(String.format("%s,", rs.getString(i)));
                           				}
                            				System.out.printf("%s\n", rs.getString(8));
                            				writer.write(String.format("%s\n", rs.getString(8)));
                         			}
						writer.close();
					} finally { rs.close(); }
				} finally { st.close(); }
			} finally { conn.close(); }
		} catch (Exception e) {
			System.err.printf("Exception: ");
			System.err.println(e.getMessage());
		}
	}
	public File run(String name, String pass){
		  try	{ // connect to the database 
	            Class.forName(myDriver); 
	            Connection conn = DriverManager.getConnection(myUrl, name, pass);	

				try { // create a statement
					Statement st = conn.createStatement();

					try { // submit a query to count the results
						ResultSet rs = st.executeQuery(count+airports+where);

						try { // print the number of rows
							rs.next();
	                        			int rows = rs.getInt(1);
	                        			System.out.printf("Selected rows = %d\n", rows);
						} finally { rs.close(); }

						// submit a query to list all large airports
						rs = st.executeQuery(columns+continents+join+where+limit);

						try { // iterate through query results and print using column numbers
	                        			System.out.println("id,name,latitude,longitude,municipality,region,country,continent");
	                        			File file = new File("output.csv");
	                        			 BufferedWriter writer = new BufferedWriter( new FileWriter(file));
	                        		      writer.write("id,name,latitude,longitude,municipality,region,country,continent,elevation,airportwiki,countrywiki,regionwiki\n");
	                        			
							while (rs.next()) {
	                           				for (int i = 1; i <=11; i++){ 
	                                				System.out.printf("%s,", rs.getString(i));
	                                				writer.write(String.format("%s,", rs.getString(i)));
	                           				}
	                            				System.out.printf("%s\n", rs.getString(12));
	                            				writer.write(String.format("%s\n", rs.getString(12)));
	                         			}
							writer.close();
						} finally { rs.close(); }
					} finally { st.close(); }
				} finally { conn.close(); }
			} catch (Exception e) {
				System.err.printf("Exception: ");
				System.err.println(e.getMessage());
			}
		
		return null;
	}
	public ArrayList<String> getContinents(String name, String pass,String query, BufferedWriter writer){
		ArrayList<String> idArray = new ArrayList();
		  try	{ // connect to the database 
	            Class.forName(myDriver); 
	            Connection conn = DriverManager.getConnection(myUrl, name, pass);	

				try { // create a statement
					Statement st = conn.createStatement();

					try { // submit a query to count the results
						ResultSet rs = st.executeQuery(count+continents+join+query+limit);

						try { // print the number of rows
							rs.next();
	                        			int rows = rs.getInt(1);
	                        			System.out.printf("Selected rows = %d\n", rows);
						} finally { rs.close(); }

						// submit a query to list all large airports
						 rs = st.executeQuery(columns+continents+join+query+limit);

						 try { // iterate through query results and print using column numbers
                 			System.out.println("id,name,latitude,longitude,municipality,region,country,continent");
                 			
                 			
					while (rs.next()) {
								String line = "";
                    				for (int i = 1; i <= 7; i++){ 
                         				System.out.printf("%s,", rs.getString(i));
                         				writer.write(String.format("%s,", rs.getString(i)));
                         				line += String.format("%s,", rs.getString(i));
                         				
                    				}
                     				System.out.printf("%s\n", rs.getString(8));
                     				writer.write(String.format("%s\n", rs.getString(8)));
                     				line+= String.format("%s\n", rs.getString(8));
                     				idArray.add(line);
                  			}
							
							return idArray;
							
						} finally { rs.close(); }
					} finally { st.close(); }
				} finally { conn.close(); }
			} catch (Exception e) {
				System.err.printf("Exception: ");
				System.err.println(e.getMessage());
			}
		return null;
		
		
	}
	public String buildQueryType(String[] selected, int[] ids, String query, boolean isSelected){
		if(ids.length == 0){
			return query;
		}
		if(isSelected == true){
			query += " AND ";
		}
		query += "type = '";
		query+= selected[ids[0]];
		for(int i =1; i < ids.length; i++){
			query += "OR" + selected[ids[i]];
		}
		query+="'";
		System.out.println(query);
		return query;
	}
	public String buildQueryContinent(String[] selected, int[] ids, String query, boolean isSelected){
		if(ids.length == 0){
			return query;
		}
		if(isSelected == true){
			query += " AND ";
		}
		query += "continents.name = '";
		query+= selected[ids[0]];
		for(int i =1; i < ids.length; i++){
			query += "' OR '" + selected[ids[i]];
		}
		query+="'";
		System.out.println(query);
		return query;
	}
	public String buildQueryRegion(String input, String query, boolean isSelected){
		if(input.isEmpty()){
			return query;
		}
		if(isSelected == true){
			query += " OR ";
		}
		query += "regions.name LIKE '";
		query+= "%"+input+"%";
		
		query+="'";
		System.out.println(query);
		return query;
	}
	public String buildQueryMunicipality(String input, String query, boolean isSelected){
		if(input.isEmpty()){
			return query;
		}
		if(isSelected == true){
			query += " OR ";
		}
		query += "airports.municipality LIKE '";
		query+= "%"+input+"%";
		
		query+="'";
		System.out.println(query);
		return query;
	}
	public String buildQueryCountry(String input, String query, boolean isSelected){
		if(input.isEmpty()){
			return query;
		}
		if(isSelected == true){
			query += " OR ";
		}
		query += "countries.name LIKE '";
		query+= "%"+input+"%";
		
		query+="'";
		System.out.println(query);
		return query;
	}
	public String buildQueryAirport(String input, String query, boolean isSelected){
		if(input.isEmpty()){
			return query;
		}
		if(isSelected == true){
			query += " OR ";
		}
		query += "airports.name LIKE '";
		query+= "%"+input+"%";
		
		query+="'";
		System.out.println(query);
		return query;
	}
	public String buildQuerySelections(ArrayList<String> selected, String query){
		
		query += "airports.id = '";
		query+= selected.get(0);
		for(int i = 0; i < selected.size()-1; i++){
			query += "' OR airports.id = '"+selected.get(i);
			
		}
		query+="' OR ";
		query += " airports.id = '"+selected.get(selected.size()-1);
		query+="'";
		System.out.println(query);
		return query;
	}
	}
