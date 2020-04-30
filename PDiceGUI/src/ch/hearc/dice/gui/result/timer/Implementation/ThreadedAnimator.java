
package ch.hearc.dice.gui.result.timer.Implementation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicBoolean;

import ch.hearc.dice.gui.result.timer.specifications.IAnimable;
import ch.hearc.dice.gui.result.timer.specifications.IAnimator;

/**
 * <pre>
 * ThreadedAnimator
 * <br>
 * IAnimator, starting in a separate thread
 * @author maxime.welcklen, Mendes Reis Steve
 *
 */
public class ThreadedAnimator implements IAnimator
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	public ThreadedAnimator(long updateTimeMS, long precisionMS)
		{
		//there is very little chance we will be adding/removing animable furiously, just once before the timer beins. Rapid access is important
		this.animables = new ArrayList<IAnimable>();
		doStop = new AtomicBoolean(false);

		this.precisionMS = precisionMS;
		this.updateTimeNS = updateTimeMS * NS_TO_MS;
		}

	public ThreadedAnimator(long updateTimeMS)
		{
		this(updateTimeMS, 10);
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	@Override
	public void addAnimable(IAnimable animable)
		{
		animables.add(animable);
		}

	@Override
	public void removeAnimable(IAnimable animable)
		{
		animables.remove(animable);
		}

	@Override
	public void stop()
		{
		doStop.set(true);
		}

	@Override
	public void start()
		{
		doStop.set(false);
		Thread thread = new Thread(createRunnable());
		thread.start();
		}

	private Runnable createRunnable()
		{
		return new Runnable()
			{

			@Override
			public void run()
				{
				//init time values
				lastTime = System.nanoTime();
				elapsedTime = 0;

				//go !
				while(!doStop.get())
					{
					//sleep for "precision" time, precision given by the user (default 10ms)
					sleepMS(precisionMS);

					//compute time that has passed (we can't rely on sleep, nanotime is way more precise)
					currentTime = System.nanoTime();
					elapsedTime += (currentTime - lastTime);
					lastTime = currentTime;

					//convert to MS to be in harmony with values asked from user (values in NS would be weird)
					if (elapsedTime >= updateTimeNS)
						{
						//elapsedTime is in NS, we need to convert updatetime to decrement the nice amount of ns.
						//we don't do "elapsed=0" because we would always lose a bit of precision in NS, and it can add up
						elapsedTime -= updateTimeNS;
						for(IAnimable animable:animables)
							{
							//observer pattern : update every animable that asked to listen to us
							animable.update();
							}
						}
					}
				}
			};
		}

	//tool function
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

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	//input
	private long precisionMS;
	private long updateTimeNS;

	//tools
	protected Collection<IAnimable> animables;
	private AtomicBoolean doStop;

	//timer time count
	private long currentTime;
	private long lastTime;
	private long elapsedTime;

	private static final int NS_TO_MS = 1_000_000;
	}
