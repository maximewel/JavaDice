
package ch.hearc.dice.gui.result.timer.Implementation.UseCase;

import java.awt.GridLayout;

import javax.swing.JPanel;

import ch.hearc.c_gui.tools.JFrameBaseLine;
import ch.hearc.dice.gui.result.timer.Implementation.JTimer;
import ch.hearc.dice.gui.result.timer.Implementation.ThreadedAnimator;
import ch.hearc.dice.gui.result.timer.specifications.IAnimator;

/**
 * <pre>
 * UseObserverPattern
 * <br>
 * Verify that the observer pattern is working : create 9 clokcs, all on the same animator
 * @author maxime.welcklen
 */
public class UseObserverPattern
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
		GridLayout gridlayout = new GridLayout(SIZE_LINE, SIZE_LINE);
		JPanel jPanel = new JPanel();
		jPanel.setLayout(gridlayout);
		IAnimator animator = new ThreadedAnimator(1 * S_TO_MS);
		for(int i = 0; i < SIZE_LINE * SIZE_LINE; i++)
			{
			jPanel.add(new JTimer(animator));
			}
		new JFrameBaseLine(jPanel);
		animator.start();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private static int SIZE_LINE = 3;
	private static int S_TO_MS = 1000;
	}
