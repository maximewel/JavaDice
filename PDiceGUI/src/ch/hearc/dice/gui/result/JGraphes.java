
package ch.hearc.dice.gui.result;

import javax.swing.Box;
import javax.swing.BoxLayout;

import ch.hearc.dice.gui.graph.JGrapheChrono;
import ch.hearc.dice.gui.graph.JGrapheLancerMoyen;
import ch.hearc.dice.moo.specifications.DiceVariable_I;
import ch.hearc.tools.IterationEvent;
import ch.hearc.tools.IterationListener_I;

public class JGraphes extends Box
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JGraphes()
		{
		super(BoxLayout.Y_AXIS);
		geometry();
		control();
		appearance();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	public void refreshResults(DiceVariable_I diceVar)
		{
		this.diceVar = diceVar;
		updateListener();
		}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void geometry()
		{
		graphNbLancer = new JGrapheLancerMoyen();
		graphChrono = new JGrapheChrono();

		add(graphNbLancer);
		add(Box.createVerticalGlue());
		add(graphChrono);

		}

	private void control()
		{
		}

	private void appearance()
		{
		//rien
		}

	private void updateListener()
		{
		diceVar.addIterationListener(new IterationListener_I()
			{

			@Override
			public void iterationPerformed(IterationEvent iterationEvent)
				{
				graphNbLancer.updateData(iterationEvent.getAlgoIteratif().getMapFaceLancer());
				graphChrono.updateData(iterationEvent.getAlgoIteratif().getMapFaceChrono());
				}
			});
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Inputs
	private JGrapheLancerMoyen graphNbLancer;
	private JGrapheChrono graphChrono;
	private DiceVariable_I diceVar;

	// Tools

	//output

	}
