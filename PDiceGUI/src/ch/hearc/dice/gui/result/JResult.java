
package ch.hearc.dice.gui.result;

import javax.swing.Box;
import javax.swing.BoxLayout;

import ch.hearc.dice.gui.result.graph.JGraphes;
import ch.hearc.dice.gui.result.timer.Implementation.JTimer;
import ch.hearc.dice.gui.result.timer.Implementation.ThreadedAnimator;
import ch.hearc.dice.gui.result.timer.specifications.IAnimator;
import ch.hearc.dice.moo.specifications.DiceVariable_I;
import ch.hearc.tools.algo.IterationEvent;
import ch.hearc.tools.algo.IterationListener_I;

/**
 * <pre>
 * JResult
 * <br>
 * Pannel displaying the result of the experience using graph, progressbars and clock
 * @author maxime.welcklen, Mendes Reis Steve
 *
 */
public class JResult extends Box
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JResult()
		{
		super(BoxLayout.Y_AXIS);

		geometry();
		control();
		appearance();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public void refreshExperience(DiceVariable_I diceVariable_I)
		{
		progressBars.refreshDice(diceVariable_I);
		graphes.refreshResults(diceVariable_I);
		diceVariable_I.addIterationListener(createIterationListener());
		}

	public void experienceKilled()
		{
		progressBars.experienceKilled();
		animator.stop();
		}
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void geometry()
		{
		progressBars = new JProgressBars("Experience running : ", "Current task advancement :");
		graphes = new JGraphes();
		animator = new ThreadedAnimator(1000);
		timer = new JTimer(animator);
		progressLine = new JProgressLine(progressBars, timer);

		this.add(progressLine);
		this.add(graphes);
		}

	private void control()
		{
		//pass
		}

	private void appearance()
		{
		//pass
		}

	private IterationListener_I createIterationListener()
		{
		return new IterationListener_I()
			{

			@Override
			public void iterationPerformed(IterationEvent iterationEvent)
				{
				switch(iterationEvent.getEtatAlgo())
					{
					case BEGIN:
						//reset timer at start, so that the user can see the time while analysing result
						timer.reset();
						animator.start();
						break;
					case RUNNING:
						break;
					case END:
						animator.stop();
						break;
					default:
						break;
					}
				}
			};
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	// Tools
	private JProgressBars progressBars;
	private JGraphes graphes;
	private JTimer timer;
	private JProgressLine progressLine;
	private IAnimator animator;
	}
