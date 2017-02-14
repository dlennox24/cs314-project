package Model;

import static org.junit.Assert.*;
import org.junit.Test;

public class TestModel {
	
  
    @Test
    public void testModel()
    {
    	
    	Model m = new Model("/s/bach/l/under/pello/school/DTR-27/src/Model/input.csv", "Decimal");
        assertEquals("OK", m.getStatus());

    }
  


}
