
package ch.hearc.dice.gui.result;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;

import ch.hearc.c_gui.tools.JComponents;
import ch.hearc.dice.gui.menu.JMenu;
import ch.hearc.dice.gui.result.timer.JTimer;
import ch.hearc.dice.moo.specifications.DiceVariable_I;
import ch.hearc.tools.IterationEvent;
import ch.hearc.tools.IterationListener_I;

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
		timer.stop();
		}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void geometry()
		{
		progressBars = new JProgressBars("Experience running : ", "Current task advancement :");
		graphes = new JGraphes();
		timer = new JTimer();

		this.add(graphes);
		Box line = new Box(BoxLayout.X_AXIS);

		line.add(Box.createHorizontalGlue());
		line.add(progressBars);
		line.add(JMenu.createHSpacing());
		line.add(timer);
		line.add(Box.createHorizontalGlue());

		this.add(line);

		}

	private void control()
		{
		this.addComponentListener(new ComponentAdapter()
			{

			@Override
			public void componentShown(ComponentEvent e)
				{
				JComponents.setWidth(timer, getWidth() / 3);
				JComponents.setHeight(timer, timer.getWidth());
				}

			@Override
			public void componentResized(ComponentEvent e)
				{
				JComponents.setWidth(timer, getWidth() / 3);
				JComponents.setHeight(timer, timer.getWidth());
				}

			});
		}

	private void appearance()
		{
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
						timer.start();
						break;
					case RUNNING:
						break;
					case END:
						timer.stop();
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

	// Inputs

	// Tools
	private JProgressBars progressBars;
	private JGraphes graphes;
	private JTimer timer;
	}
