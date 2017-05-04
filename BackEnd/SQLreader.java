import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.ResultSet;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import java.io.IOException;


public class SQLreader {

        final static String myDriver = "com.mysql.jdbc.Driver";
        final static String myUrl = "jdbc:mysql://faure.cs.colostate.edu/cs314";
        final static String count = "SELECT COUNT(1) ";
        final static String columns = "SELECT airports.id,airports.name,latitude,longitude,municipality,regions.name,countries.name,continents.name, airports.elevation_ft, airports.wikipedia_link, countries.wikipedia_link, regions.wikipedia_link ";
        final static String airports = "FROM airports ";
        final static String continents = "FROM continents ";
        final static String municipality = "FROM municipality ";
        final static String countries = "FROM countries ";
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
                        		    //  writer.write("id,name,latitude,longitude,municipality,region,country,continent\n");
                        			
						while (rs.next()) {
                           				for (int i = 1; i <= 7; i++){ 
                                				System.out.printf("%s,", rs.getString(i));
                                				//writer.write(String.format("%s,", rs.getString(i)));
                           				}
                            				System.out.printf("%s\n", rs.getString(8));
                            				//writer.write(String.format("%s\n", rs.getString(8)));
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
	public ArrayList settingQuery(String query, String select, String from){
            ArrayList<String> idArray = new ArrayList();
		  try	{ // connect to the database 
	            Class.forName(myDriver); 
	            Connection conn = DriverManager.getConnection(myUrl, "gtjohnso", "830103947");	

				try { // create a statement
					Statement st = conn.createStatement();

					try { // submit a query to count the results
						//ResultSet rs = st.executeQuery(count+select+query);

						try { // print the number of rows
							//rs.next();
	                        			//int rows = rs.getInt(1);
	                        			//System.out.printf("Selected rows = %d\n", rows);
						} finally { }

						// submit a query to list all large airports
						ResultSet rs = st.executeQuery(select+from+query+limit);

						try { // iterate through query results and print using column numbers
	                        			System.out.println("name");
	                        			
	                        		     // writer.write("id,name,latitude,longitude,municipality,region,country,continent,elevation,airportwiki,countrywiki,regionwiki\n");
	                        			///System.out.println("id,name,latitude,longitude,municipality,region,country,continent,elevation,airportwiki,countrywiki,regionwiki\n");
							while (rs.next()) {
	                           				for (int i = 1; i <=1; i++){ 
	                                				System.out.printf("%s,", rs.getString(i));
                                                                        idArray.add(String.format("%s", rs.getString(i)));
	                                				//writer.write(String.format("%s,", rs.getString(i)));
	                           				}
	                            				//System.out.printf("%s\n", rs.getString(12));
	                            				//writer.write(String.format("%s\n", rs.getString(12)));
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
	public ArrayList<String> getContinents(String name, String pass,String query, boolean isIT){
		ArrayList<String> idArray = new ArrayList();
		  try	{ // connect to the database 
	            Class.forName(myDriver); 
	            Connection conn = DriverManager.getConnection(myUrl, name, pass);	

				try { // create a statement
					Statement st = conn.createStatement();

					try { // submit a query to count the results
						//ResultSet rs = st.executeQuery(count+continents+join+query+limit);

						try { // print the number of rows
							//rs.next();
	                        			//int rows = rs.getInt(1);
	                        			//System.out.printf("Selected rows = %d\n", rows);
						} finally {  }

						// submit a query to list all large airports
                                                ResultSet rs;
                                                if(isIT){
						  rs = st.executeQuery(columns+continents+join+query);
                                                }else{
                                                     rs = st.executeQuery(columns+continents+join+query+limit);
                                                }
						 try { // iterate through query results and print using column numbers
                 			System.out.println("id,name,latitude,longitude,municipality,region,country,continent");
                 			
                 			
					while (rs.next()) {
								String line = "";
                    				for (int i = 1; i <= 11; i++){ 
                         				System.out.printf("%s,", rs.getString(i));
                         				//writer.write(String.format("%s,", rs.getString(i)));
                         				idArray.add(String.format("%s", rs.getString(i)));
                         				
                    				}
                     				System.out.printf("%s\n", rs.getString(12));
                     				//writer.write(String.format("%s\n", rs.getString(12)));
                     				idArray.add(String.format("%s", rs.getString(12)));
                     				
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
	public String buildQueryType(String[] selected, String query, boolean isSelected){
		if(isSelected == true){
			query += " OR ";
		}
		query += "type IN ('";
		query+= selected[0];
                query+= "'";
		for(int i =1; i < selected.length; i++){
			query += ",'" + selected[i];
                        query+= "'";
		}
		query+=") ";
		System.out.println(query);
		return query;
	}
        
        public String buildQuerySize(String input, String query, boolean isSelected){
		if(input.isEmpty()){
			return query;
		}
		if(isSelected == true){
			query += " OR ";
		}
		query += "type LIKE '";
		query+= "%"+input+"%";
		
		query+="'";
		System.out.println(query);
		return query;
	}
	public String buildQueryContinentSet(String input, String query, boolean isSelected){
		
		if(isSelected == true){
			query += " OR ";
		}
		query += "continents.name LIKE '";
		query+= "%"+input+"%";
		
		query+="'";
		System.out.println(query);
		return query;
	}
        public String buildQueryContinent(String[] selected, String query, boolean isSelected){
		if(isSelected == true){
			query += " AND ";
		}
		query += "continents.name IN ('";
		query+= selected[0];
                query+= "'";
		for(int i =1; i < selected.length; i++){
			query += ",'" + selected[i];
                        query+= "'";
		}
		query+=") ";
		System.out.println(query);
		return query;
	}
	public String buildQueryRegionSet(String input, String query, boolean isSelected){
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
        public String buildQueryRegion(String[] selected, String query, boolean isSelected){
		if(isSelected == true){
			query += " AND ";
		}
		query += "regions.name IN ('";
		query+= selected[0];
                query+= "'";
		for(int i =1; i < selected.length; i++){
			query += ",'" + selected[i];
                        query+= "'";
		}
		query+=") ";
		System.out.println(query);
		return query;
	}
	public String buildQueryMunicipalitySet(String input, String query, boolean isSelected){
		if(input.isEmpty()){
			return query;
		}
		
		query += "airports.municipality LIKE '";
		query+= "%"+input+"%";
		
		query+="'";
		System.out.println(query);
		return query;
	}
        public String buildQueryMunicipality(String[] selected, String query, boolean isSelected){
		if(isSelected == true){
			query += " AND ";
		}
		query += "airports.municipality IN ('";
		query+= selected[0];
                query+= "'";
		for(int i =1; i < selected.length; i++){
			query += ",'" + selected[i];
                        query+= "'";
		}
		query+=") ";
		System.out.println(query);
		return query;
	}
        public String buildQueryCountrySet(String input, String query, boolean isSelected){
		if(input.isEmpty()){
			return query;
		}
		
		query += "countries.name LIKE '";
		query+= "%"+input+"%";
		
		query+="'";
		System.out.println(query);
		return query;
	}
	public String buildQueryCountry(String[] selected, String query, boolean isSelected){
		
		if(isSelected == true){
			query += " AND ";
		}
		query += "countries.name IN ('";
		
		query+= selected[0];
                query+= "'";
		for(int i =1; i < selected.length; i++){
			query += ",'" + selected[i];
                        query+= "'";
		}
		query+=") ";
		System.out.println(query);
		return query;
	}
	public String buildQueryAirport(String input, String query, boolean isSelected){
		
		if(isSelected == true){
			query += " AND ";
		}
		query += "airports.name LIKE '";
		query+= "%"+input+"%";
		
		query+="'";
		System.out.println(query);
		return query;
	}
	public String buildQuerySelections(String[] selected, String query){
		
		query += "airports.id = '";
		query+= selected[0];
		for(int i = 0; i < selected.length-1; i++){
			query += "' OR airports.id = '"+selected[i];
			
		}
		query+="' OR ";
		query += " airports.id = '"+selected[selected.length-1];
		query+="'";
		System.out.println(query);
		return query;
	}
        
	}

