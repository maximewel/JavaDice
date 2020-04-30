
package ch.hearc.dice.gui.result;

import javax.swing.Box;
import javax.swing.BoxLayout;

import ch.hearc.c_gui.tools.JCenter;
import ch.hearc.dice.gui.result.timer.Implementation.JTimer;

/**
 * <pre>
 * JProgressLine
 * <br>
 * Horizontal line that displays the state, duration and progression of the experience (with progressbars and JTimer)
 * @author maxime.welcklen
 *
 */
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
	}
