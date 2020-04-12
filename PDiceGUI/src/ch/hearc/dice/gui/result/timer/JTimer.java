
package ch.hearc.dice.gui.result.timer;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import ch.hearc.c_gui.tools.JCenterH;

public class JTimer extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JTimer()
		{
		geometry();
		control();
		appearance();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public void start()
		{
		if (!tickThread.isAlive())
			{
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
		this.setLayout(new BorderLayout());

		timerClock = new JTimerClock();
		timerDigit = new JTimerDigit();

		initThread();

		this.add(timerClock, BorderLayout.CENTER);
		this.add(new JCenterH(timerDigit), BorderLayout.SOUTH);
		}

	private void control()
		{
		// rien
		}

	private void appearance()
		{
		// rien
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
