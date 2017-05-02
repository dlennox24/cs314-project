
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import javax.json.stream.JsonParser;
import javax.json.Json;
import javax.json.JsonObject;
import java.io.StringReader;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import java.util.Map;
import java.util.HashMap;
import com.google.gson.GsonBuilder;
import com.google.gson.Gson;
import javax.servlet.FilterConfig;
import java.io.IOException;



import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/websocket")
public class WebsocketTest {

    // WORKDIR is the folder of the .class files
    private final static String WORKDIR = WebsocketTest.class.getProtectionDomain().getCodeSource().getLocation().getPath();
    // the FILEPATH we want to write to is a few folders up. This refers to the web folder
    private final static String FILEPATH = WORKDIR.substring(0, WORKDIR.indexOf("WEB-INF/"));
    
    
    
    
    private BufferedWriter bw;
    
    // websocket onmessage event. runs any time a message is received from the client
    @OnMessage
    public void onMessage(String message, Session session) throws IOException,
            InterruptedException {
            Map<String, String> myPair = new HashMap<String, String>();
            myPair = parseJson(message);
            boolean isSelected = false;
            System.out.println("New message recieved");
            String input = "";
             SQLreader sql = new SQLreader();
             ArrayList returnList = new ArrayList();
               String query = "where ";
               String[] tP = new String[100];
             if(myPair.get("endpoint").equals("destination")){
                 if(!(myPair.get("filter_airportSize").isEmpty())){
                     tP = myPair.get("filter_airportSize").split(",");
                     query = sql.buildQueryType(tP, query, isSelected);
                     isSelected = true;
                 }
                 if(!(myPair.get("filter_country").isEmpty())){
                     tP = myPair.get("filter_country").split(",");
                     query = sql.buildQueryCountry(tP, query, isSelected);
                     isSelected = true;
                 }
                 if(!(myPair.get("filter_municipality").isEmpty())){
                     tP = myPair.get("filter_municipality").split(",");
                     query = sql.buildQueryMunicipality(tP, query, isSelected);
                     isSelected = true;
                 } 
                 if(!(myPair.get("filter_region").isEmpty())){
                     tP = myPair.get("filter_region").split(",");
                     query = sql.buildQueryRegion(tP, query, isSelected);
                     isSelected = true;
                 }
                 if(!(myPair.get("filter_continent").isEmpty())){
                     tP = myPair.get("filter_continent").split(",");
                     query = sql.buildQueryContinent(tP, query, isSelected);
                     isSelected = true;
                 }
                 ArrayList mapList = new ArrayList();
                  input = myPair.get("data");
               
                 query = sql.buildQueryAirport(input, query, isSelected);
                 returnList = sql.getContinents("gtjohnso", "830103947", query);
                 Gson json = new GsonBuilder().create();
                 String tog = json.toJson(returnList);
            
                 System.out.println(tog.toString());
                 session.getBasicRemote().sendText(tog.toString());
                 //mapList = list_to_map(returnList,session);
                 //System.out.println("MapList = "+mapList.toString());
                
             }else if(myPair.get("endpoint").equals("country")){
                 ArrayList mapList = new ArrayList();
                 input = myPair.get("data");
                 query = "where ";
                 query = sql.buildQueryCountrySet(input, query, isSelected);
                 String select = "SELECT countries.name ";
                 String from = "FROM countries ";
                 returnList = sql.settingQuery(query, select, from);
                 Gson json = new GsonBuilder().create();
                 String tog = json.toJson(returnList);
            
                 System.out.println(tog.toString());
                 session.getBasicRemote().sendText(tog.toString());
                // mapList = countries_list_to_map(returnList,session);
             }else if(myPair.get("endpoint").equals("region")){
                  ArrayList mapList = new ArrayList();
                  input = myPair.get("data");
                 query = "where ";
                 query = sql.buildQueryRegionSet(input, query, isSelected);
                 String select = "SELECT regions.name ";
                 String from = "FROM regions ";
                 returnList = sql.settingQuery(query, select, from);
                 Gson json = new GsonBuilder().create();
                 String tog = json.toJson(returnList);
                 
            
                 System.out.println(tog.toString());
                 session.getBasicRemote().sendText(tog.toString());
                // mapList = countries_list_to_map(returnList,session);
             }else if(myPair.get("endpoint").equals("airportSize")){
                  ArrayList mapList = new ArrayList();
                  input = myPair.get("data");
                 query = "where ";
                 query = sql.buildQuerySize(input, query, isSelected);
                 String select = "SELECT DISTINCT type ";
                 String from = "FROM airports ";
                 returnList = sql.settingQuery(query, select, from);
                 Gson json = new GsonBuilder().create();
                 String tog = json.toJson(returnList);
            
                 System.out.println(tog.toString());
                 session.getBasicRemote().sendText(tog.toString());
                // mapList = countries_list_to_map(returnList,session);
             }else if(myPair.get("endpoint").equals("continent")){
                  ArrayList mapList = new ArrayList();
                  input = myPair.get("data");
                 query = "where ";
                 query = sql.buildQueryContinentSet(input, query, isSelected);
                 String select = "SELECT continents.name ";
                 String from = "FROM continents ";
                 returnList = sql.settingQuery(query, select, from);
                 Gson json = new GsonBuilder().create();
                 String tog = json.toJson(returnList);
            
                 System.out.println(tog.toString());
                 session.getBasicRemote().sendText(tog.toString());
                // mapList = countries_list_to_map(returnList,session);
             }else if(myPair.get("endpoint").equals("municipality")){
                  ArrayList mapList = new ArrayList();
                  input = myPair.get("data");
                 query = "where ";
                 query = sql.buildQueryMunicipalitySet(input, query, isSelected);
                 String select = "SELECT DISTINCT airports.municipality ";
                 String from = "FROM airports ";
                 returnList = sql.settingQuery(query, select, from);
                 Gson json = new GsonBuilder().create();
                 String tog = json.toJson(returnList);
            
                 System.out.println(tog.toString());
                 session.getBasicRemote().sendText(tog.toString());
                // mapList = countries_list_to_map(returnList,session);
             }else if(myPair.get("endpoint").equals("load")){
                 ArrayList list = new ArrayList();
                 tP = myPair.get("data").split(",");
                 query = "where ";
                 query = sql.buildQuerySelections(tP, query);
                 returnList = sql.getContinents("gtjohnso","830103947", query);
                 Gson json = new GsonBuilder().create();
                 String tog = json.toJson(returnList);
            
                 System.out.println(tog.toString());
                 session.getBasicRemote().sendText(tog.toString());
             }
            
        
    }
    private ArrayList countries_list_to_map(ArrayList<String> ret, Session session)throws IOException,InterruptedException{
         ArrayList list = new ArrayList();
         Map<String, String> myPair = new HashMap<String, String>();
         for(int i = 0; i < ret.size(); i++){
             myPair.put("name", ret.get(i));
             sendJson(myPair, session);
         }
         return list;
    }
    private ArrayList list_to_map(ArrayList<String> ret, Session session)throws IOException,InterruptedException{
        ArrayList mapList = new ArrayList();
        
        String[] cName = new String[] {"id","name","latitude","longitude","municipality","region","country","continent","elevation","airportURL","countryURL","regionURL"};
        System.out.println("return list "+ ret);
        Map<String, String> myPair = new HashMap<String, String>();
        //int count = 0;
        for(int i = 0; i < ret.size(); i++){
            //myPair.put(cName[i%12], ret.get(i));

            if(i != 0 && i %12 == 0){
                System.out.println("my pait" + myPair.toString());
                sendJson(myPair,session);
                 
                myPair.clear();
            }else if(i == ret.size()-1){
                System.out.println("my pait" + myPair.toString());
                sendJson(myPair,session);
            }
                 myPair.put(cName[i%12], ret.get(i));
            //System.out.println(myPair.get(cName[i%12]));
            
        }
        
        System.out.println("ret MapList = "+mapList.toString());

        return mapList;
    }
    private void sendJson(Map myPair, Session session)throws IOException,InterruptedException{
          Gson json = new GsonBuilder().create();
          String tog = json.toJson(myPair);
            
          System.out.println(tog.toString());
          session.getBasicRemote().sendText(tog.toString());
    }
    private Map parseJson(String Message){
        String[] mess = new String[20];
        
        JsonParser parser = Json.createParser(new StringReader(Message));

      String key = null;
      
       String temp = null;
       String data = null;
       Map<String, String> myPair = new HashMap<String, String>();
    while (parser.hasNext()) {
          
            JsonParser.Event event = parser.next();
          
            switch(event) {
                case START_ARRAY:
                case END_ARRAY:
                case START_OBJECT:
                case END_OBJECT:
                case VALUE_FALSE:
                case VALUE_NULL:
                case VALUE_TRUE:
                 System.out.println(event.toString());
                break;
                case KEY_NAME:
                System.out.print(event.toString() + " " +
                          parser.getString() + " - ");
                key = parser.getString();
                System.out.println(key);
                
               
                break;
                case VALUE_STRING:
                case VALUE_NUMBER:
                System.out.println(event.toString() + " " +
                            parser.getString());
                data = parser.getString();
                //mess[i] = parser.getString();
                //System.out.println(mess[i]);
                //i++;
                 break;
            }
            if(key != null && !(event.toString().contains("END_OBJECT"))){
                myPair.put(key, data);
                System.out.println(myPair.toString());
                data = null;
            }
        }
         
        //System.out.println(mess[0]+mess[1]+mess[2]);
        return myPair;
    }
    // called when the websocket connection is opened
    @OnOpen
    public void onOpen() throws IOException {
        System.out.println("Client connected");
    }

    // called when the weboscket connection is closed
    @OnClose
    public void onClose() {
        System.out.println("Connection closed");
    }
}
