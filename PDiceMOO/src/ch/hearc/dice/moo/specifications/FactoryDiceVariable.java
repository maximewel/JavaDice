
package ch.hearc.dice.moo.specifications;

import ch.hearc.b_poo.thread.vecteur.Interval;
import ch.hearc.dice.moo.implementation.DiceVariable;
import ch.hearc.dice.moo.implementation.TypeProcessing;

public class FactoryDiceVariable
	{
	public static DiceVariable_I create(Interval nbFace, int nbExperience, TypeProcessing type)
		{
			return new DiceVariable(nbFace, nbExperience, type);
		}
	}

