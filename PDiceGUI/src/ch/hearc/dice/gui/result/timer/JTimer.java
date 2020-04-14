
package ch.hearc.dice.gui.result.timer;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;

import ch.hearc.c_gui.tools.JCenterH;

public class JTimer extends Box
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JTimer()
		{
		super(BoxLayout.Y_AXIS);

		geometry();
		control();
		appearance();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public void start()
		{
		if (tickThread != null && tickThread.isAlive())
			{
			//pass
			}
		else
			{
			initThread();
			tickThread.start();
			}
		}

	public void stop()
		{
		runnableTimer.stop();
		}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void geometry()
		{
		timerClock = new JTimerClock();
		timerDigit = new JTimerDigit();

		this.add(timerClock);
		this.add(new JCenterH(timerDigit));
		}

	private void control()
		{
		// rien
		}

	private void appearance()
		{
		this.setMinimumSize(new Dimension(200, 200));
		this.setPreferredSize(new Dimension(300, 300));
		}

	private void initThread()
		{
		runnableTimer = new RunnableTimer(timerClock, timerDigit);
		tickThread = new Thread(runnableTimer);
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Inputs

	// Tools
	private JTimerClock timerClock;
	private JTimerDigit timerDigit;

	private Thread tickThread;
	private RunnableTimer runnableTimer;
	}
