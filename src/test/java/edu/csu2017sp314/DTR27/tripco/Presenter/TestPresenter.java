package test.java.edu.csu2017sp314.DTR27.tripco.Presenter;
import main.java.edu.csu2017sp314.DTR27.tripco.Model.Model;
import main.java.edu.csu2017sp314.DTR27.tripco.View.View;
import main.java.edu.csu2017sp314.DTR27.tripco.Presenter.Presenter;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class TestPresenter {


  @Test
  public void getStart() {
    assertEquals("OK", new Model("/s/bach/n/under/gtjohnso/cs314/DTR-27/src/Model/input.csv").getStatus());
  }
  @Test
  public void getModel() {
    Model model = new Model("input.csv");
    ArrayList<String> a = null;
    View view = new View("Colorado",100,"anotherString", "FinalString");
    Presenter Presenter = new Presenter(model,view,"aaa","aaa");
    assertEquals(model, Presenter.getModel());

  }
  public void getView() {
    Model model = new Model("input.csv");
    ArrayList<String> a = null;
    View view = new View("Colorado",100,"anotherString", "FinalString");
    Presenter Presenter = new Presenter(model,view,"aaa","aaa");
    assertEquals(view, Presenter.getView());

  }

}
