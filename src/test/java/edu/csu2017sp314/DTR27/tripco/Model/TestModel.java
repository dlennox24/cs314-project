package test.java.edu.csu2017sp314.DTR27.tripco.Model;

import static org.junit.Assert.*;
import org.junit.Test;

import main.java.edu.csu2017sp314.DTR27.tripco.Model.Model;

public class TestModel {
	
	Model m = new Model("src/input.csv","NN");

  
    @Test
    public void testStatus1()
    {
    	assertNotNull(m.getStatus());
    }
    
    @Test 
    public void testStatus2(){
    	assertEquals("OK", m.getStatus());
    }
    
    
    @Test
    public void testGetLegs1()
    {
    	assertNotNull(m.getLegs());
    }
    
    @Test 
    public void testGetLegs2(){
    	assertTrue (0 < m.getLegs().size());
    }
    
    @Test
    public void testGetLegsLength1()
    {
    	assertNotNull(m.getLegsLength());
    }
    
    @Test 
    public void testGetLegsLength2(){
    	assertTrue (0 < m.getLegsLength());
    }
  
    @Test
    public void testGetLocations1()
    {
    	assertNotNull(m.getLocations());
    }
    
    @Test 
    public void testGetLocations2(){
    	assertTrue (0 < m.getLocations().size());
    }
    @Test
    public void testGetLocationsLength1()
    {
    	assertNotNull(m.getLocationsLength());
    }
    
    @Test 
    public void testGetLocationsLength2(){
    	assertTrue (0 < m.getLocationsLength());
    }
    @Test
    public void testGetLeg1()
    {
    	assertNotNull(m.getLeg(0));
    }
    
    @Test 
    public void testGetLeg2()
    {
    	assertTrue(0 <= m.getLeg(0).getDistance());
    }
    @Test
    public void testGetLocation1()
    {
    	assertNotNull(m.getLocation(0));
    }
    
    @Test 
    public void testGetLocation2()
    {
    	assertTrue(0 <= m.getLocation(0).getId().length());
    }
    
    
  


}
