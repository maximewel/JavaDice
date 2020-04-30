
package ch.hearc.dice.gui;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * <pre>
 * Start JDice on the JFrameDice
 * @author maxime.welcklen, mendesreis.steve
 *
 */
public class UseJDice
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
		String looknFeelName = "javax.swing.plaf.nimbus.NimbusLookAndFeel";
		// Try to set cross-platform Java L&F
		try
			{
			UIManager.setLookAndFeel(looknFeelName);
			}
		catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e)
			{
			//didn't work : alert the user, but nothing else
			System.out.println("Couldn't load look and feel " + looknFeelName + ", going with standard");
			}
		finally
			{
			//start anyway
			new JFrameDice(new JDice());
			}
		}
	}
