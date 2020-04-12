
package ch.hearc.dice.gui.result.timer;

import ch.hearc.c_gui.tools.JFrameBaseLine;

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
		JTimer timer = new JTimer();
		new JFrameBaseLine(timer);
		timer.start();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	}
