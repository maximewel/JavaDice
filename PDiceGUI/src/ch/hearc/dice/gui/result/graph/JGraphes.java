
package ch.hearc.dice.gui.result.graph;

import javax.swing.Box;
import javax.swing.BoxLayout;

import ch.hearc.c_gui.tools.JMarge;
import ch.hearc.dice.moo.specifications.DiceVariable_I;
import ch.hearc.tools.IterationEvent;
import ch.hearc.tools.IterationListener_I;

/**
 * <pre>
 * JGraphes
 * <br>
 * Component displaying the result of experiences within 2 graphes
 * @author maxime.welcklen, mendesreis.steve
 *
 */
public class JGraphes extends Box
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JGraphes()
		{
		super(BoxLayout.X_AXIS);
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

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void geometry()
		{
		graphNbLancer = new JGrapheLancerMoyen();
		graphChrono = new JGrapheChrono();

		add(new JMarge(graphNbLancer, 5));
		add(new JMarge(graphChrono, 5));
		}

	private void control()
		{
		//pass
		}

	private void appearance()
		{
		//pass
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
	}
