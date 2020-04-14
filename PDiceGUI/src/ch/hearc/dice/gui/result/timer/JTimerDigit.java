
package ch.hearc.dice.gui.result.timer;

import java.awt.Font;

import javax.swing.JLabel;

public class JTimerDigit extends JLabel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JTimerDigit()
		{
		geometry();
		control();
		appearance();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public void setTime(int h, int m, int s)
		{
		//%02d means an integer always displayed on 2 digits (more pleasing, as it will not change from 1 to 2 digits - stability)
		Character split = (s % 2 == 0 ? ':' : ' ');
		this.setText(String.format("%02d:%02d%c%02d", h, m, split, s));
		}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void geometry()
		{
		// TODO
		}

	private void control()
		{
		// rien
		}

	private void appearance()
		{
		this.setFont(new Font(this.getFont().getFontName(), Font.BOLD, 30));

		//avoid beeing empty at start
		this.setTime(0, 0, 0);
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Inputs

	// Tools

	}
