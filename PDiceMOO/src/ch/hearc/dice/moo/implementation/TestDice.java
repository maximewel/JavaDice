
package ch.hearc.dice.moo.implementation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestDice
	{

	@Test
	public void testSeq()
		{
		int nbFace = 6;
		int nbExperience = Integer.MAX_VALUE / 1000; // Cette technique garantit de ne pas deborder le type int (Wrapper : plein de methodes utiles!)

		Dice dice = new Dice(nbFace, nbExperience, TypeProcessing.SEQUENTIEL);
		dice.run();
		int resultatEmpirique = dice.getNbLancerMoyen();

		int resultatTheorique = 15; // on suppose

		Assertions.assertEquals(resultatTheorique, resultatEmpirique);
		}

	@Test
	public void testParallele()
		{
		int nbFace = 6;
		int nbExperience = Integer.MAX_VALUE / 1000; // Cette technique garantit de ne pas deborder le type int (Wrapper : plein de methodes utiles!)

		Dice dice = new Dice(nbFace, nbExperience, TypeProcessing.PARALLELE);
		dice.run();
		int resultatEmpirique = dice.getNbLancerMoyen();

		int resultatTheorique = 15; // on suppose

		Assertions.assertEquals(resultatTheorique, resultatEmpirique);
		}

	}
