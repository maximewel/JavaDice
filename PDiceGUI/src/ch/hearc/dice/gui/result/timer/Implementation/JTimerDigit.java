
package ch.hearc.dice.gui.result.timer.Implementation;

import java.awt.Font;

import javax.swing.JLabel;

/**
 * <pre>
 * JTimerDigit
 * <br>
 * Display digit similarly to a digital clock
 * @author maxime.welcklen, Mendes Reis Steve
 *
 */
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
		//add a cheap time-passing animation :-)
		Character split = (s % 2 == 0 ? ':' : ' ');
		//%02d means an integer always displayed on 2 digits (more pleasing, as it will not change from 1 to 2 digits - stability)
		this.setText(String.format("%02d:%02d%c%02d", h, m, split, s));
		}
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void geometry()
		{
		//pass
		}

	private void control()
		{
		//pass
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
	}
