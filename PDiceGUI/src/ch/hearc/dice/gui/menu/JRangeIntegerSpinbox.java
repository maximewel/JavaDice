
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

public class JRangeIntegerSpinbox extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JRangeIntegerSpinbox(String title, String descMinSpinner, String descMaxspinner, int min, int max)
		{
		super();

		this.descMinspinner = descMinSpinner;
		this.descMaxspinner = descMaxspinner;
		this.title = title;
		this.min = min;
		this.max = max;

		geometry();
		control();
		appearance();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

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
		this.add(new JLabel(descMinspinner));
		this.add(spinMin);
		this.add(new JLabel(descMaxspinner));
		this.add(spinMax);

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
		Border lineBorder = BorderFactory.createLineBorder(Color.black);
		Border outsideBorder = BorderFactory.createTitledBorder(lineBorder, title, TitledBorder.CENTER, TitledBorder.BELOW_TOP);
		Border marginBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		this.setBorder(BorderFactory.createCompoundBorder(outsideBorder, marginBorder));
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

				//Juste a little security verifivation
				if (spinMinValue < min)
					{
					spinMin.setValue(min);
					}

				//UI : We want the min spinner to never be above the max one
				//we take action if it is the case
				if (spinMinValue >= spinMaxValue)
					{

					// '<' because we need at least "1" room for the max spinner value
					if (spinMinValue < max)
						{
						spinMax.setValue(spinMinValue + 1);
						}
					else //user tries to go too far : stop the action, go to max
						{
						spinMin.setValue(max - 1);
						spinMax.setValue(max);
						}

					}
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

				//Juste a little security verifivation
				if (spinMaxValue > max)
					{
					spinMax.setValue(max);
					}

				//UI : We want the max spinner to always be above the min one
				//we take action if it is not the case
				if (spinMaxValue <= spinMinValue)
					{

					// '<' because we need at least "1" room for the max spinner value
					if (spinMaxValue > min)
						{
						spinMin.setValue(spinMaxValue - 1);
						}
					else //user tries to go too far : stop the action, go to max
						{
						spinMax.setValue(min + 1);
						spinMin.setValue(min);
						}

					}
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

	// Tools
	private JSpinner spinMin;
	private JSpinner spinMax;
	GridLayout gridLayout;

	}
