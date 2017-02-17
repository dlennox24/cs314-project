import Presenter.Presenter;
import Model.Model;
import View.View;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class TestTripCo {

	@Test
	public void notNull() {
		assertNotNull(new Presenter(new Model("input.csv", "Decimal")));
	}
	@Test
	public void getStart() {
		assertEquals("OK", new Model("input.csv", "Decimal").getStatus());
	}
	
}
