
package ch.hearc.dice.gui.result.timer.Implementation;

import ch.hearc.dice.gui.result.timer.specifications.IAnimable;

/**
 * <pre>
 * AnimableTimer
 * <br>
 * IAnimable, animmate the clock and digit of JTimer at each update
 * Need to be added to a IAnimator listening queue
 * <br>
 * @author maxime.welcklen, Mendes Reis Steve
 *
 */
public class AnimableTimer implements IAnimable
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public AnimableTimer(JTimerClock timerClock, JTimerDigit timerDigit)
		{
		this.timerClock = timerClock;
		this.timerDigit = timerDigit;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	@Override
	public void update()
		{
		incSecond();
		updateDisplay();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	@Override
	public void reset()
		{
		hours = minutes = seconds = 0;
		updateDisplay();
		}

	private void incSecond()
		{
		if (++seconds >= 60)
			{
			seconds = 0;
			incMinute();
			}
		}

	private void incMinute()
		{
		if (++minutes >= 60)
			{
			minutes = 0;
			incHour();
			}
		}

	private void incHour()
		{
		if (++hours >= 24)
			{
			hours = minutes = seconds = 0;
			}
		}

	private void updateDisplay()
		{
		timerClock.setSeconde(seconds);
		timerDigit.setTime(hours, minutes, seconds);
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	//input
	private JTimerClock timerClock;
	private JTimerDigit timerDigit;

	//tools
	private int seconds;
	private int minutes;
	private int hours;
	}
