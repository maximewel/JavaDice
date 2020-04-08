
package ch.hearc.dice.moo.implementation.dice;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ch.hearc.b_poo.thread.vecteur.Interval;
import ch.hearc.dice.moo.specifications.DiceVariable_I;
import ch.hearc.tools.AlgoIteratif_A;
import ch.hearc.tools.Chrono;
import ch.hearc.tools.IterationEvent;
import ch.hearc.tools.IterationListener_I;

public class DiceVariable extends AlgoIteratif_A implements IterationListener_I ,DiceVariable_I
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public DiceVariable(Interval nbFaces, int nbExperience)
		{
		super();
		this.nbExperience = nbExperience;
		this.nbFaces = nbFaces;
		this.dices = new ArrayList<Dice>();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	@Override
	public void iterationPerformed(IterationEvent iterationEvent)
		{
		// TODO Auto-generated method stub

		}

	@Override
	public void onBegin()
		{
		// TODO Auto-generated method stub

		}

	@Override
	public void iterationStep(int i)
		{
		// TODO Auto-generated method stub

		}

	@Override
	public void onEnd()
		{
		// TODO Auto-generated method stub

		}

	@Override
	public boolean isFinished(int i)
		{
		if (i >= dices.size())
			{
				return true;
				}
		// TODO Auto-generated method stub
		return false;
		}

	@Override
	public Map<Integer, Integer> getMapFaceLancer()
		{
		// TODO Auto-generated method stub
		return null;
		}

	@Override
	public Map<Integer, Chrono> getMapFaceChrono()
		{
		// TODO Auto-generated method stub
		return null;
		}

	@Override
	public Interval getNbFaces()
		{
		return nbFaces;
		}

	@Override
	public int getNbExperience()
		{
		return nbExperience;
		}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	//input
	private Interval nbFaces;
	private int nbExperience;
	private List<Dice> dices;
	}
