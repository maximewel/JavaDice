
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

/**
 * <pre>
 * JProcessingChoice
 * <br>
 * JRadionbuttons to select the processing choice
 * @author maxime.welcklen, Mendes Reis Steve
 *
 */
public class JProcessingChoice extends Box
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JProcessingChoice(DiceBuilder diceBuilder)
		{
		super(BoxLayout.X_AXIS);

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
		//add buttons
		radioButtonSequential = new JRadioButton("Sequential");
		radioButtonParallel = new JRadioButton("Parallel");
		radioButtonRunnable = new JRadioButton("Runnable");

		//put them in an exclusive group
		ButtonGroup groupe = new ButtonGroup();
		groupe.add(radioButtonSequential);
		groupe.add(radioButtonParallel);
		groupe.add(radioButtonRunnable);

		//add them !
		Box boxV = new Box(BoxLayout.Y_AXIS);

		boxV.add(JMenu.createVSpacing());
		boxV.add(radioButtonSequential);
		boxV.add(JMenu.createVSpacing());
		boxV.add(radioButtonParallel);
		boxV.add(JMenu.createVSpacing());
		boxV.add(radioButtonRunnable);
		boxV.add(JMenu.createVSpacing());

		//center buttons horizontally
		this.add(Box.createHorizontalGlue());
		this.add(boxV);
		this.add(Box.createHorizontalGlue());
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

		//synchronize buttons and builder
		radioButtonSequential.setSelected(true);
		diceBuilder.setTypeProcessing(TypeProcessing.SEQUENTIEL);
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Inputs
	private DiceBuilder diceBuilder;

	// Tools
	private JRadioButton radioButtonSequential;
	private JRadioButton radioButtonParallel;
	private JRadioButton radioButtonRunnable;

	}
