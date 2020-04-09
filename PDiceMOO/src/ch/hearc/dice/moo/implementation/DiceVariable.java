
package ch.hearc.dice.moo.implementation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ch.hearc.b_poo.thread.vecteur.Interval;
import ch.hearc.dice.moo.specifications.DiceVariable_I;
import ch.hearc.tools.AlgoIteratif_A;
import ch.hearc.tools.Chrono;

public class DiceVariable extends AlgoIteratif_A implements DiceVariable_I
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public DiceVariable(Interval nbFaces, int nbExperience, TypeProcessing type)
		{
		super();
		this.nbExperience = nbExperience;
		this.nbFaces = nbFaces;
		this.typeProcessing = type;
		this.mapFaceLancer = new HashMap<Integer, Integer>();
		this.mapChronoLancer = new HashMap<Integer, Chrono>();
		this.dices = new ArrayList<Dice>();

		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	@Override
	public void onBegin()
		{
		this.dices.clear();
		this.mapFaceLancer.clear();
		this.mapChronoLancer.clear();

		for(Integer i:nbFaces)
			{
			this.dices.add(new Dice(i, nbExperience, this.typeProcessing));
			}
		}

	@Override
	public void iterationStep(int i)
		{
		Chrono chrono = new Chrono();

		chrono.start();
		this.dices.get(i).run();
		chrono.stop();

		this.mapChronoLancer.put(i, chrono);
		this.mapFaceLancer.put(i, this.dices.get(i).getNbLancerMoyen());

		if (i+1 >= nbFaces.getB()-nbFaces.getA())
			{
				this.stop();
			}
		}

	@Override
	public void onEnd()
		{
		// TODO Auto-generated method stub

		}

	@Override
	public boolean isFinished(int i)
		{
		return this.mapFaceLancer.keySet().contains(i);
		}

	@Override
	public Map<Integer, Integer> getMapFaceLancer()
		{
		return mapFaceLancer;
		}

	@Override
	public Map<Integer, Chrono> getMapFaceChrono()
		{
		return mapChronoLancer;
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
	private TypeProcessing typeProcessing;

	private Map<Integer, Integer> mapFaceLancer;
	private Map<Integer, Chrono> mapChronoLancer;
	}
