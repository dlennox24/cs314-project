package test.java.edu.csu2017sp314.DTR27.tripco.Model;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import main.java.edu.csu2017sp314.DTR27.tripco.Model.Model;
import main.java.edu.csu2017sp314.DTR27.tripco.Model.Optimizer;

public class TestOptimizer {
	Model m = new Model("src/testFiles/3optCase1.csv","NN", "M");
	Optimizer o = new Optimizer();
	  
    @Test
    public void test3opt1()
    {
//    	assertEquals(,());
    }
}
