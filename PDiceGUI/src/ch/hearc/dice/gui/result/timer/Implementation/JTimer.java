
package ch.hearc.dice.gui.result.timer.Implementation;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;

import ch.hearc.c_gui.tools.JCenterH;
import ch.hearc.dice.gui.result.timer.specifications.IAnimable;
import ch.hearc.dice.gui.result.timer.specifications.IAnimator;

/**
 * <pre>
 * JTimer : JComposant, composed of a clock and a digital display
 * Listen to the given animator - put update time et 1S for proper use
 * @author maxime.welcklen, Mendes Reis Steve
 *
 */
public class JTimer extends Box
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JTimer(IAnimator animator)
		{
		super(BoxLayout.Y_AXIS);

		//we don't create the animator, we make our animable listen to it
		this.animator = animator;

		geometry();
		control();
		appearance();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public void reset()
		{
		animable.reset();
		}
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void geometry()
		{
		timerClock = new JTimerClock();
		timerDigit = new JTimerDigit();

		this.add(timerClock);
		this.add(new JCenterH(timerDigit));

		//Add our animable timer to the given animator
		animable = new AnimableTimer(timerClock, timerDigit);
		animator.addAnimable(animable);
		}

	private void control()
		{
		//pass
		}

	private void appearance()
		{
		//if clock and digital display is too little, it's not nice. We want a minimum size
		this.setMinimumSize(new Dimension(200, 200));
		this.setPreferredSize(new Dimension(300, 300));
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	// Tools
	private JTimerClock timerClock;
	private JTimerDigit timerDigit;

	private IAnimator animator;
	private IAnimable animable;
	}
