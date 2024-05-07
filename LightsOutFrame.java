package assign09;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This class serves the Lights Out Program as the main source of the game's
 * programming and mechanics. It is a subclass of JFrame and implements
 * ActionListener to allow the use of a mouse within the program.
 * 
 * @author Elijah Potter
 * @version 11/13/23
 */
@SuppressWarnings("serial")
public class LightsOutFrame extends JFrame implements ActionListener {
	private LightsOutButton[][] lightGrid;
	private JButton statusRandomizer;
	private JButton manualSetup;
	private JLabel msgLabel;
	private boolean isManual;

	/**
	 * This method is the main constructor of the LightsOutFrame class and besides
	 * formatting the frame, configures all the LightsOutButton's in a random order,
	 * defines the use of the Manual-Setup and Randomize buttons, and formats all
	 * the elements into one window.
	 */
	public LightsOutFrame() {
		isManual = false;
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(5, 5));
		lightGrid = new LightsOutButton[5][5];

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				LightsOutButton button = new LightsOutButton(i, j);
				button.setFont(new Font("Dialog", Font.PLAIN, 75));
				panel.add(button);
				lightGrid[i][j] = button;
				button.addActionListener(this);
			}
		}

		// Make the label
		msgLabel = new JLabel("Turn off all the lights!", JLabel.CENTER);
		msgLabel.setFont(new Font("Dialog", Font.PLAIN, 32));

		// Make randomize button
		statusRandomizer = new JButton("Randomize!");
		statusRandomizer.addActionListener(this);

		// Make set-up button
		manualSetup = new JButton("Enter Manual Set-Up");
		manualSetup.addActionListener(this);

		// Set up panel
		JPanel panel3 = new JPanel();
		panel3.setLayout(new GridLayout(1, 2));
		panel3.add(manualSetup);
		panel3.add(statusRandomizer);
		JPanel panel2 = new JPanel();
		panel2.setLayout(new BorderLayout());
		panel2.add(panel, BorderLayout.CENTER);
		panel2.add(msgLabel, BorderLayout.SOUTH);
		panel2.add(panel3, BorderLayout.NORTH);
		panel = panel2;

		this.setTitle("Lights Out!!!");
		this.setContentPane(panel);
		this.setPreferredSize(new Dimension(600, 600));
		this.pack();

		randomize();
	}

	/**
	 * This method toggles several buttons at random to produce a certain
	 * configuration of buttons that is both challenging and possible to solve.
	 */
	public void randomize() {
		Random randomGen = new Random();
		for (int i = 0; i < 9; i++) {
			int ranRow = randomGen.nextInt(5);
			int ranColumn = randomGen.nextInt(5);
			lightGrid[ranRow][ranColumn].toggle();
			try {
				lightGrid[ranRow + 1][ranColumn].toggle();
			} catch (IndexOutOfBoundsException e) {

			}
			try {
				lightGrid[ranRow - 1][ranColumn].toggle();
			} catch (IndexOutOfBoundsException e) {

			}
			try {
				lightGrid[ranRow][ranColumn + 1].toggle();
			} catch (IndexOutOfBoundsException e) {

			}
			try {
				lightGrid[ranRow][ranColumn - 1].toggle();
			} catch (IndexOutOfBoundsException e) {

			}
		}

	}

	/**
	 * This method returns the status of a given button.
	 * 
	 * @param row    - The row index of the button
	 * @param column - The column index of the button
	 * @return The status of the button (whether or not it is on)
	 * @throws IndexOutOfBoundsException If the given button doesn't exist
	 */
	public boolean lightIsOn(int row, int column) {
		try {
			return lightGrid[row][column].isOn();
		} catch (IndexOutOfBoundsException e) {
			throw e;
		}
	}

	/**
	 * This method is programmatically very similar to randomize. One button is
	 * toggled, and all its neighbors are toggled as well.
	 * 
	 * @param row    - The row index of the button
	 * @param column - The column index of the button
	 * @throws IndexOutOfBoundsException If the given button doesn't exist
	 */
	public void toggleLight(int row, int column) {
		try {
			lightGrid[row][column].toggle();
		} catch (IndexOutOfBoundsException e) {
			throw e;
		}
		try {
			lightGrid[row + 1][column].toggle();
		} catch (IndexOutOfBoundsException e) {

		}
		try {
			lightGrid[row - 1][column].toggle();
		} catch (IndexOutOfBoundsException e) {

		}
		try {
			lightGrid[row][column + 1].toggle();
		} catch (IndexOutOfBoundsException e) {

		}
		try {
			lightGrid[row][column - 1].toggle();
		} catch (IndexOutOfBoundsException e) {

		}
	}

	/**
	 * This private helper method checks to see if all the buttons are off.
	 * 
	 * @return True if all the buttons are off, and false if otherwise.
	 */
	private boolean checkWin() {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (lightGrid[i][j].isOn()) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * This private helper method checks to see if all the buttons are on.
	 * 
	 * @return True if all the buttons are on, and false if otherwise.
	 */
	private boolean checkUtterFailure() {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (!(lightGrid[i][j].isOn())) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * This method handles what happens after any type of button is clicked. It
	 * utilizes several different methods to toggle lights, check win-states, etc.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();
		if (button instanceof LightsOutButton && isManual == false) {
			LightsOutButton lightButton = (LightsOutButton) button;
			int buttRow = lightButton.getRow();
			int buttColumn = lightButton.getColumn();
			this.toggleLight(buttRow, buttColumn);
			if (checkWin()) {
				msgLabel.setText("You won!");
			} else {
				if (checkUtterFailure()) {
					msgLabel.setText("Interesting strategy...");
				} else
					msgLabel.setText("Turn off all the lights!");
			}
		} else if (button instanceof LightsOutButton && isManual == true) {
			LightsOutButton lightButton = (LightsOutButton) button;
			lightButton.toggle();
		} else if (button == manualSetup) {
			if (isManual) {
				isManual = false;
				manualSetup.setText("Enter Manual Set-Up");
			} else {
				isManual = true;
				manualSetup.setText("Exit Manual Set-Up");
			}
		} else if (button == statusRandomizer) {
			randomize();
		}
	}

}
