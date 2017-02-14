
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class TestView {

	@Before
	public void initialize() {
	}

	@Test
	public void testConditional() {
		assertEquals(true, conditional));
	}

	@Test
	public void testFunction() {
		assertEquals(false, functionResult());
	}
}
