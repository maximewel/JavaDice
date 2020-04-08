
package ch.hearc.dice.gui;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

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
		// Set cross-platform Java L&F (also called "Metal")
		try
			{
			UIManager.setLookAndFeel(looknFeelName);
			}
		catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e)
			{
			System.out.println("Couldn't load look and feel " + looknFeelName + ", going with standard");
			}

		new JFrameDice(new JDice());
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	}
