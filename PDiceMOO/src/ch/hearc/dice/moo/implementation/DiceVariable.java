
package ch.hearc.dice.moo.implementation;

import java.util.HashMap;
import java.util.Iterator;
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
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	@Override
	public void onBegin()
		{
		this.mapFaceLancer.clear();
		this.mapChronoLancer.clear();


		currNbFace = nbFaces.iterator();
		}

	@Override
	public void iterationStep(int i)
		{
		Integer nbface = currNbFace.next();
		Chrono chrono = new Chrono();
		Dice dice = new Dice(nbface, nbExperience, this.typeProcessing);

		chrono.start();
		dice.run();
		chrono.stop();

		this.mapChronoLancer.put(nbFaces.getA() + i, chrono);
		this.mapFaceLancer.put(nbFaces.getA() + i, dice.getNbLancerMoyen());

		if (!currNbFace.hasNext())
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
		return this.mapFaceLancer.keySet().contains(nbFaces.getA() + i);
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
	private Iterator<Integer> currNbFace;
	private TypeProcessing typeProcessing;

	private Map<Integer, Integer> mapFaceLancer;
	private Map<Integer, Chrono> mapChronoLancer;
	}
