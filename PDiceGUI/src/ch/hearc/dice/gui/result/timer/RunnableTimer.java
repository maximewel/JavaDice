
package ch.hearc.dice.gui.result.timer;

import java.util.concurrent.atomic.AtomicBoolean;

public class RunnableTimer implements Runnable
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public RunnableTimer(JTimerClock timerClock, JTimerDigit timerDigit)
		{
		this.timerClock = timerClock;
		this.timerDigit = timerDigit;

		doStop = new AtomicBoolean(false);
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	/**
	 * stop the timer at the next interval
	 */
	public void stop()
		{
		doStop.set(true);
		}

	@Override
	public void run()
		{
		reset();
		lastTime = System.nanoTime();

		while(!doStop.get())
			{
			sleepMS(TICK_TIMER_MILLISEC);
			currentTime = System.nanoTime();
			elapsedTime += currentTime - lastTime;
			lastTime = currentTime;

			elapsedTimeInSecond = (double)elapsedTime / SECOND_IN_NANO;
			if (elapsedTimeInSecond >= 1)
				{
				elapsedTimeInSecond -= 1;
				elapsedTime -= SECOND_IN_NANO;

				incSecond();
				updateDisplay();
				}
			}

		}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private static void sleepMS(long duration)
		{
		try
			{
			Thread.sleep(duration);
			}
		catch (InterruptedException e)
			{
			e.printStackTrace();
			}
		}

	private void reset()
		{
		hours = minutes = seconds = 0;
		lastTime = elapsedTime = currentTime = 0;
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
	private AtomicBoolean doStop;

	//tools
	private int seconds;
	private int minutes;
	private int hours;

	//timer things
	private long currentTime;
	private long lastTime;
	private long elapsedTime;
	private double elapsedTimeInSecond;

	private static final int SECOND_IN_NANO = 1_000_000_000;
	private static final int TICK_TIMER_MILLISEC = 20;

	}
