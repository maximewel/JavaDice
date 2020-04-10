
package ch.hearc.dice.gui.menu;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import ch.hearc.dice.moo.implementation.TypeProcessing;
import ch.hearc.tools.DiceBuilder;

public class JProcessingChoice extends Box
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JProcessingChoice(DiceBuilder diceBuilder)
		{
		super(BoxLayout.Y_AXIS);

		this.diceBuilder = diceBuilder;

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
		radioButtonSequential = new JRadioButton("Sequential");
		radioButtonParallel = new JRadioButton("Parallel");
		radioButtonRunnable = new JRadioButton("Runnable");

		ButtonGroup groupe = new ButtonGroup();
		groupe.add(radioButtonSequential);
		groupe.add(radioButtonParallel);
		groupe.add(radioButtonRunnable);

		add(JMenu.createVSpacing());
		add(radioButtonSequential);
		add(JMenu.createVSpacing());
		add(radioButtonParallel);
		add(JMenu.createVSpacing());
		add(radioButtonRunnable);
		add(JMenu.createVSpacing());

		}

	private void control()
		{
		radioButtonSequential.addActionListener(createActionListenerProcessing(TypeProcessing.SEQUENTIEL));
		radioButtonParallel.addActionListener(createActionListenerProcessing(TypeProcessing.PARALLELE));
		radioButtonRunnable.addActionListener(createActionListenerProcessing(TypeProcessing.RUNNABLE));
		}

	private ActionListener createActionListenerProcessing(TypeProcessing typeProcessing)
		{
		return new ActionListener()
			{

			@Override
			public void actionPerformed(ActionEvent e)
				{
				diceBuilder.setTypeProcessing(typeProcessing);
				}
			};
		}

	private void appearance()
		{
		//set the padding + external border with title inside the box
		Border lineBorder = BorderFactory.createLineBorder(Color.BLACK);
		Border outsideBorder = BorderFactory.createTitledBorder(lineBorder, "Processing method", TitledBorder.CENTER, TitledBorder.TOP);
		Border marginBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		this.setBorder(BorderFactory.createCompoundBorder(outsideBorder, marginBorder));

		radioButtonSequential.setSelected(true);
		diceBuilder.setTypeProcessing(TypeProcessing.SEQUENTIEL);
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Inputs
	DiceBuilder diceBuilder;

	// Tools
	private JRadioButton radioButtonSequential;
	private JRadioButton radioButtonParallel;
	private JRadioButton radioButtonRunnable;

	}
