
package ch.hearc.dice.moo.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.Assertions;

import ch.hearc.b_poo.thread.vecteur.Interval;

public class Dice implements Runnable
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public Dice(int nbFace, int nbExperience, TypeProcessing typeProcessing)
		{
		this.nbExperience = nbExperience;
		this.nbFace = nbFace;
		this.typeProcessing = typeProcessing;
		this.nbLancerMoyen = 0;
		this.intervals = new ArrayList<Interval>();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	@Override
	public void run()
		{
		nbLancer = new AtomicInteger();

		int nbCores = Runtime.getRuntime().availableProcessors();
		// availableProcessors never return value under 1 so it's ok to not control it
		int part = nbExperience / nbCores;

		int i = 0;

		// part > 0 if the nbExp is too small we do everything in one interval!
		for(i = 0; i < nbCores-1 && part > 0; i++)
			{
			intervals.add(new Interval(part * i, part * (i + 1)));
			}

		intervals.add(new Interval(part * (i), nbExperience));

		switch(typeProcessing)
			{
			case PARALLELE:
				versionThread();
				break;
			case SEQUENTIEL:
				versionSequentiel();
				break;
			default:
				Assertions.fail();
				break;
			}

		double mean = nbLancer.get() / (double)nbExperience;

		this.nbLancerMoyen = (int)Math.ceil(mean);
		}

	private void versionSequentiel()
		{
		for(Interval interval:intervals)
			{
			addPortion(interval);
			}
		}

	private void versionThread()
		{
		List<Thread> threads = new ArrayList<Thread>();

		for(Interval interval:intervals)
			{
			Thread thread = createThread(interval);
			threads.add(thread);
			thread.start();
			}

		try
			{
			for(Thread thread:threads)
				{
					thread.join();
				}
			}
		catch (InterruptedException e)
			{
			e.printStackTrace();
			}
		}

	private Runnable createRunnable(Interval interval)
		{
		return new Runnable()
			{

			@Override
			public void run()
				{
				addPortion(interval);
				}
			};
		}

	private Thread createThread(Interval interval)
		{
		return new Thread(createRunnable(interval));
		}

	public void addPortion(Interval interval)
		{
		DiceExperience diceExperience = new DiceExperience(this.nbFace);

		int nbLancerTotal = 0;

		for(int i:interval)
			{
			diceExperience.run();
			nbLancerTotal += diceExperience.getNbLancer();
			}

		nbLancer.getAndAdd(nbLancerTotal);
		}

	@Override
	public String toString()
		{
		StringBuilder builder = new StringBuilder();

		builder.append("nbFace        = ");
		builder.append(this.nbFace);
		builder.append("\n");
		builder.append("nbExperience  = ");
		builder.append(this.nbExperience);
		builder.append("\n");
		builder.append("nbLancerMoyen = ");
		builder.append(this.nbLancerMoyen);

		return builder.toString();
		}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	public int getNbExperience()
		{
		return this.nbExperience;
		}

	public int getNbFace()
		{
		return this.nbFace;
		}

	public int getNbLancerMoyen()
		{
		return this.nbLancerMoyen;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	//Input
	private int nbExperience;
	private int nbFace;
	private TypeProcessing typeProcessing;
	private List<Interval> intervals;

	//Output
	private AtomicInteger nbLancer;
	private int nbLancerMoyen;

	}
