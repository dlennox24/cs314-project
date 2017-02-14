package Presenter;
import Model.Model;
import View.View;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class TestPresenter {

	@Test
	public void notNull() {
		assertNotNull(new Presenter(new Model("/s/bach/n/under/gtjohnso/cs314/DTR-27/src/Model/input.csv", "Decimal")));
	}
	@Test
	public void getStart() {
		assertEquals("OK", new Model("/s/bach/n/under/gtjohnso/cs314/DTR-27/src/Model/input.csv", "Decimal").getStatus());
	}
	
}
