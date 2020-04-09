
package ch.hearc.dice.moo.implementation.dice;

import ch.hearc.b_poo.thread.vecteur.Interval;
import ch.hearc.tools.AlgoIteratif_A;
import ch.hearc.tools.IterationEvent;
import ch.hearc.tools.IterationListener_I;

public class UseDiceVariableListener
	{

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public static void main(String[] args)
		{
		main();
		}

	public static void main()
		{
		System.out.println("Please wait ...\n");

		Interval interval = new Interval(3, 12);
		int nbAlgo = interval.getB()-interval.getA();
		int nbExperience = Integer.MAX_VALUE / 5000; // Cette technique garantit de ne pas deborder le type int (Wrapper : plein de methodes utiles!)

		AlgoIteratif_A diceVar = new DiceVariable(interval, nbExperience, TypeProcessing.PARALLELE);

		diceVar.addIterationListener(new IterationListener_I()
			{
			@Override
			public void iterationPerformed(IterationEvent it)
				{
				System.out.println("Algo " + it.getI() + "/" + (nbAlgo-1));
				System.out.println("  State : " + it.getEtatAlgo().toString());

				}
			});

		Thread thread = new Thread(diceVar);
		thread.start();

		try
			{
			thread.join();
			}
		catch (InterruptedException e)
			{
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	}

