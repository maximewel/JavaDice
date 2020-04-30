
package ch.hearc.dice.gui.result.timer.Implementation.UseCase;

import ch.hearc.c_gui.tools.JFrameBaseLine;
import ch.hearc.dice.gui.result.timer.Implementation.JTimer;
import ch.hearc.dice.gui.result.timer.Implementation.ThreadedAnimator;
import ch.hearc.dice.gui.result.timer.specifications.IAnimator;

/**
 * <pre>
 * simple use case, start a regular timer on a JFrame
 * @author maxime.welcklen, Mendes Reis Steve
 *
 */
public class UseJTimer
	{
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public static void main(String[] args)
		{
		main();
		}

	public static void main()
		{
		IAnimator animator = new ThreadedAnimator(1000);
		JTimer timer = new JTimer(animator);
		new JFrameBaseLine(timer);
		animator.start();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	}
