
package ch.hearc.tools;

public class Chrono
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	public Chrono()
		{
		start();
		}
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	public void start()
		{
		// System.currentTimeMillis() is not as accurate as nanoTime();
		this.isStopped = false;
		this.startTime = System.nanoTime();
		}

	public long stop()
		{
		long endTime = System.nanoTime();
		this.isStopped = true;

		this.timeMS = (endTime-startTime)/1000000; // nano to seconds

		return this.timeMS;
		}

	@Override
	public String toString()
		{
		return "Time in [ms] : " + getTimeMS();
		}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/
	public long getTimeMS()
		{
		if (isStopped) return this.timeMS;

		return (System.nanoTime()-startTime)/1000000; //nano to seconds
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	//tools
	private long startTime = 0;
	private boolean isStopped = false;

	//ouput
	private long timeMS;
	}
