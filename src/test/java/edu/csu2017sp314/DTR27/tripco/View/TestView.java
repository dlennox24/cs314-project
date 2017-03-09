package View;
import View.View;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestView {

	@Test
	public void testView() {
		View v = new View("Custom Titles!",15454,"mn","fname");
		assertEquals("OK",v.getStatus());
	}
}
