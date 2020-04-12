
package ch.hearc.dice.gui.result.timer;

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
		this.setText(h + ":" + m + ":" + s);
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
		// rien
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Inputs

	// Tools

	}
