
package ch.hearc.dice.gui.result;

import javax.swing.Box;
import javax.swing.BoxLayout;

import ch.hearc.c_gui.tools.JCenter;
import ch.hearc.dice.gui.result.timer.JTimer;

public class JProgressLine extends Box
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JProgressLine(JProgressBars progressBars, JTimer timer)
		{
		super(BoxLayout.X_AXIS);

		this.progressBars = progressBars;
		this.timer = timer;

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
		this.add(Box.createHorizontalGlue());
		this.add(new JCenter(progressBars));
		this.add(timer);
		this.add(Box.createHorizontalGlue());
		}

	private void control()
		{
		// rien
		}

	private void appearance()
		{
		// rien
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Inputs
	private JProgressBars progressBars;
	private JTimer timer;

	// Tools

	}
