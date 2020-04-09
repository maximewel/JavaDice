
package ch.hearc.tools;

import ch.hearc.dice.moo.implementation.dice.Dice;
import ch.hearc.dice.moo.implementation.dice.TypeProcessing;

public class UseSpeedUp
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

		int nbFace = 6;
		int nbExperience = Integer.MAX_VALUE / 500; // Cette technique garantit de ne pas deborder le type int (Wrapper : plein de methodes utiles!)

		Dice dicePar = new Dice(nbFace, nbExperience, TypeProcessing.PARALLELE);
		Dice diceSeq = new Dice(nbFace, nbExperience, TypeProcessing.SEQUENTIEL);

		Chrono chronoPar = new Chrono();
		dicePar.run();
		chronoPar.stop();

		Chrono chronoSeq = new Chrono();
		diceSeq.run();
		chronoSeq.stop();

		System.out.println("Result Parallelism : ");
		System.out.println(dicePar);
		System.out.println(chronoPar);

		System.out.println("\n------------------- \n");

		System.out.println("Result Sequentiel : ");
		System.out.println(diceSeq);
		System.out.println(chronoSeq);

		float diff = ((float)chronoSeq.getTimeMS()/chronoPar.getTimeMS());

		//we print a int only, decimals aren't that useful for the comparison
		System.out.format("\n\nSpeed up: parallelism is %.03f times faster\n", diff);
		System.out.println("Nb logical cores: " + Runtime.getRuntime().availableProcessors());
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	}

