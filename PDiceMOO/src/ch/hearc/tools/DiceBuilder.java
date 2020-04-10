
package ch.hearc.tools;

import ch.hearc.b_poo.thread.vecteur.Interval;
import ch.hearc.dice.moo.implementation.TypeProcessing;
import ch.hearc.dice.moo.specifications.DiceVariable_I;
import ch.hearc.dice.moo.specifications.FactoryDiceVariable;

/**
 * DiceBuilder
 *
 * patter builder, seen in design pattern course
 * used to build a dice more easily (you can set the parameters one by one)
 * uses the DiceFactory
 * @author maxime.welcklen
 *
 */
public class DiceBuilder
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public DiceBuilder()
		{

		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public DiceVariable_I build()
		{
		System.out.println("faces " + interval + " exp " + nbExperience + " process " + typeProcessing);
		return FactoryDiceVariable.create(interval, nbExperience, typeProcessing);
		}

	public void setInterval(int min, int max)
		{
		this.interval = new Interval(min, max);
		}

	public void setMin(int min)
		{
		this.setInterval(min, interval.getB());
		}

	public void setMax(int max)
		{
		this.setInterval(interval.getA(), max);
		}

	public void setNbExperience(int nbExperience)
		{
		this.nbExperience = nbExperience;
		}

	public void setTypeProcessing(TypeProcessing typeProcessing)
		{
		this.typeProcessing = typeProcessing;
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	//inputs
	private Interval interval;
	private int nbExperience;
	private TypeProcessing typeProcessing;
	}
