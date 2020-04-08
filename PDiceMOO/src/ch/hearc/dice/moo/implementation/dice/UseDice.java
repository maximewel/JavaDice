package ch.hearc.dice.moo.implementation.dice;

public class UseDice
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
		useDice();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private static void useDice()
		{
		System.out.println("Please wait ...\n");

		int nbFace = 6;
		int nbExperience = Integer.MAX_VALUE / 1000; // Cette technique garantit de ne pas deborder le type int (Wrapper : plein de methodes utiles!)

		Dice dice = new Dice(nbFace, nbExperience, TypeProcessing.PARALLELE);
		dice.run();

		System.out.println(dice);
		}

	}
