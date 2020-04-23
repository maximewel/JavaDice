
package ch.hearc.dice.gui.menu;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ch.hearc.c_gui.tools.JCenter;
import ch.hearc.tools.DiceBuilder;

public class JRangeIntegerSpinbox extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JRangeIntegerSpinbox(String title, String descMinSpinner, String descMaxspinner, int min, int max, DiceBuilder diceBuilder)
		{
		super();

		this.descMinspinner = descMinSpinner;
		this.descMaxspinner = descMaxspinner;
		this.title = title;
		this.min = min;
		this.max = max;
		this.diceBuilder = diceBuilder;

		geometry();
		control();
		appearance();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void geometry()
		{
		//default : spinners are at min and max value, so that the user
		//know what range he can work through at first glance
		spinMin = createRangedSpinner(min, min, max);
		spinMax = createRangedSpinner(max, min, max);

		//prepare internal grid layout
		gridLayout = new GridLayout(2, 2);
		this.setLayout(gridLayout);

		//add components to grid pannel
		this.add(new JCenter(new JLabel(descMinspinner)));
		this.add(new JCenter(spinMin));
		this.add(new JCenter(new JLabel(descMaxspinner)));
		this.add(new JCenter(spinMax));

		}

	private void control()
		{
		//add change listener to both spinners
		//see methods to see exactly how they are done (refactored for clarity)
		spinMin.addChangeListener(createMinListener());
		spinMax.addChangeListener(createMaxListener());
		}

	private void appearance()
		{
		//set nice gaps to aerate the UI
		gridLayout.setHgap(5);
		gridLayout.setVgap(5);

		//set the padding + external border with title inside the box
		Border lineBorder = BorderFactory.createLineBorder(Color.BLACK);
		Border outsideBorder = BorderFactory.createTitledBorder(lineBorder, title, TitledBorder.CENTER, TitledBorder.TOP);
		Border marginBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		this.setBorder(BorderFactory.createCompoundBorder(outsideBorder, marginBorder));

		//set initial value to builder
		diceBuilder.setInterval(min, max);
		}

	/*
	 * Helper tool, create an Integer range spinner going from min to max value
	 * that reacts on user input
	 */
	private static JSpinner createRangedSpinner(int initialValue, int min, int max)
		{
		SpinnerModel spinnerModel = new SpinnerNumberModel(initialValue, min, max, 1); //default value,lower bound,upper bound,increment by
		return new JSpinner(spinnerModel);
		}

	/**
	 * Convenient function, used locally (not static as we need access to local spinners)
	 * @return the change listener for minSpinner
	 */
	private ChangeListener createMinListener()
		{
		return new ChangeListener()
			{

			//called when the min spinner value is changed
			@Override
			public void stateChanged(ChangeEvent e)
				{
				int spinMinValue = (int)spinMin.getValue();
				int spinMaxValue = (int)spinMax.getValue();

				if (spinMinValue >= spinMaxValue) { // verify if the min spin goes upper the max limit and adjust the value if necessary
					spinMinValue = spinMaxValue-1;
					spinMin.setValue(spinMinValue);
				}

				diceBuilder.setInterval(spinMinValue, spinMaxValue);
				}

			};
		}

	/**
	 * Convenient location, used locally (not static as we need access to local spinners)
	 * @return the change listener for maxspinner
	 */
	private ChangeListener createMaxListener()
		{
		return new ChangeListener()
			{

			//called when the max spinner value is changed
			@Override
			public void stateChanged(ChangeEvent e)
				{
				int spinMinValue = (int)spinMin.getValue();
				int spinMaxValue = (int)spinMax.getValue();

				if (spinMaxValue <= spinMinValue) { // verify if the max spin goes under the min limit and adjust the value if necessary
					spinMaxValue = spinMinValue+1;
					spinMax.setValue(spinMaxValue);
				}

				diceBuilder.setInterval(spinMinValue, spinMaxValue);
				}

			};

		}
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Inputs
	private String descMinspinner;
	private String descMaxspinner;
	private int min;
	private int max;
	private String title;
	private DiceBuilder diceBuilder;

	// Tools
	private JSpinner spinMin;
	private JSpinner spinMax;
	GridLayout gridLayout;

	}
