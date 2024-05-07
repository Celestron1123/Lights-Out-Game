package assign09;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * This class contains tests for the LightsOutButton class.
 * 
 * @author Prof. Martin and Elijah Potter
 * @version 11/13/23
 */
public class LightsOutButtonTest {
	@Test
	public void testToggle() {
		LightsOutButton button = new LightsOutButton(1, 1);
		button.toggle();
		assertTrue(button.isOn());
	}

	@Test
	public void testToggleSwitchBack() {
		LightsOutButton button = new LightsOutButton(1, 1);
		button.toggle();
		button.toggle();
		assertFalse(button.isOn(), "Failed toggle switch-back test.");
	}

	@Test
	public void testGetRow() {
		LightsOutButton button = new LightsOutButton(3, 4);
		assertEquals(3, button.getRow());
	}

	@Test
	public void testGetColumn() {
		LightsOutButton button = new LightsOutButton(3, 4);
		assertEquals(4, button.getColumn(), "Failed column test.");
	}

	@Test
	public void testGetZeroRC() {
		LightsOutButton button = new LightsOutButton(0, 0);
		assertEquals(0, button.getRow(), "Failed zero row test.");
		assertEquals(0, button.getColumn(), "Failed zero column test.");
	}

	@Test
	public void testIsOn() {
		LightsOutButton button = new LightsOutButton(0, 3);
		assertFalse(button.isOn());
		button.toggle();
		assertTrue(button.isOn(), "Failed post-toggle isOn test.");
	}

}