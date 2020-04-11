
package ch.hearc.dice.gui.menu;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;

import ch.hearc.c_gui.tools.JCenter;

/**
 *<pre>
 *JButtonLine
 *
 *Form a line of already created buttons (any given number)
 *Buttons are spaced evenly-between each one
 * <br>
 * @author maxime.welcklen, Mendes Reis Steve
 *
 */
public class JButtonLine extends Box
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JButtonLine(JButton... buttons)
		{
		super(BoxLayout.X_AXIS);

		this.buttons = buttons;

		geometry();
		control();
		appearance();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	/**
	 * Toggle all buttons [on]<->[off]
	 */
	public void switchButtonEnabled()
		{
		for(JButton button:buttons)
			{
			button.setEnabled(!button.isEnabled());
			}
		}
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void geometry()
		{
		//place button / glue / button / glue / etc... (Not glue before the first, no glue after the last)
		for(JButton button:buttons)
			{

			if (first)
				{
				first = !first;
				}
			else
				{
				this.add(Box.createHorizontalGlue());
				}

			this.add(new JCenter(button));
			}
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
	private JButton[] buttons;
	private boolean first = true;

	// Tools

	}
