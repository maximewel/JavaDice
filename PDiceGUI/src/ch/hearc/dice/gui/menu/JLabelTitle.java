
package ch.hearc.dice.gui.menu;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.Border;

/**
 * <pre>
 * just a fancy title label - encase the string within a 5x5 padding and a little black border
 * <br>
 * @author maxime.welcklen, Mendes Reis Steve
 *
 */
public class JLabelTitle extends JLabel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JLabelTitle(String text)
		{

		super(text);

		appearance();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void appearance()
		{
		this.setFont(new Font("Arial", Font.BOLD, 30));

		//set the padding + external border with title
		Border outsideBorder = BorderFactory.createLineBorder(Color.black);
		Border marginBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		this.setBorder(BorderFactory.createCompoundBorder(outsideBorder, marginBorder));
		}

	}
