package assign09;

import java.awt.Color;
import javax.swing.JButton;

/**
 * This class is a subclass of JButton and is intended to be used as the main
 * interactive feature in the Lights Out Program.
 * 
 * @author Elijah Potter
 * @version 11/13/23
 */
@SuppressWarnings("serial")
public class LightsOutButton extends JButton {
	private int columnIndex;
	private int rowIndex;
	private boolean status;

	/**
	 * The main constructor for a LightsOutButton. It takes in row and column
	 * parameters, and is "off" when first created.
	 * 
	 * @param rowIndex    - The row the button resides in
	 * @param columnIndex - The column the button resides in
	 */
	public LightsOutButton(int rowIndex, int columnIndex) {
		super("⚬");
		this.rowIndex = rowIndex;
		this.columnIndex = columnIndex;
		this.setBackground(Color.GRAY);
		this.setForeground(Color.BLUE);
		status = false;
	}

	/**
	 * Toggles a given LightsOutButton "on" or "off"
	 */
	public void toggle() {
		if (status) {
			status = false;
			setText("⚬");
			this.setBackground(Color.GRAY);
			this.setForeground(Color.BLUE);
		} else {
			status = true;
			setText("⛭");
			this.setBackground(Color.WHITE);
			this.setForeground(Color.YELLOW);
		}
	}

	/**
	 * Returns the row of a LightsOutButton
	 * 
	 * @return An integer representing the row index
	 */
	public int getRow() {
		return rowIndex;
	}

	/**
	 * Returns the column of a LightsOutButton
	 * 
	 * @return An integer representing the column index
	 */
	public int getColumn() {
		return columnIndex;
	}

	/**
	 * Returns the status of a LightsOutButton (whether it is "on" or "off")
	 * 
	 * @return A boolean value representing the status of a LightsOutButton
	 */
	public boolean isOn() {
		return status;
	}

}
