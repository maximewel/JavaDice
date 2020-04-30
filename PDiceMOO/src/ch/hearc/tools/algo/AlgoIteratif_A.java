
package ch.hearc.tools.algo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import ch.hearc.dice.moo.specifications.DiceVariable_I;

public abstract class AlgoIteratif_A implements Runnable, DiceVariable_I
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	public AlgoIteratif_A()
		{
		this.listIterationListener = new ArrayList<IterationListener_I>();
		this.isStop = new AtomicBoolean();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public abstract void onBegin();

	public abstract void iterationStep(int i);

	public abstract void onEnd();

	public abstract boolean isFinished(int i);

	public void stop()
		{
		isStop.set(true);
		}

	@Override
	public void run()
		{
		isStop.set(false);
		// onBegin
			{
			onBegin();
			IterationEvent iterationEvent = new IterationEvent(this, -1, EtatAlgo.BEGIN);
			avertirListener(iterationEvent);
			}
		// iteration
			{
			int i = 0;
			while(!isFinished(i) && !isStop.get())
				{
				iterationStep(i);
				IterationEvent iterationEvent = new IterationEvent(this, i, EtatAlgo.RUNNING);
				avertirListener(iterationEvent);
				i++;
				}
			}
		// onEnd()
			{
			onEnd();
			IterationEvent iterationEvent = new IterationEvent(this, -1, EtatAlgo.END);
			avertirListener(iterationEvent);
			}

		}

	@Override
	public synchronized void addIterationListener(IterationListener_I iterationListener)
		{
		listIterationListener.add(iterationListener);
		}

	@Override
	public synchronized void removeIterationListener(IterationListener_I iterationListener)
		{
		listIterationListener.remove(iterationListener);
		}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	private synchronized void avertirListener(IterationEvent iterationEvent)
		{
		for(IterationListener_I iterationListener:listIterationListener)
			{
			iterationListener.iterationPerformed(iterationEvent);
			}
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	// Tools
	private List<IterationListener_I> listIterationListener;
	private AtomicBoolean isStop;
	}
