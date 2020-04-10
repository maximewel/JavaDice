package ch.hearc.dice.moo.implementation;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class DiceExperience implements Runnable
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public DiceExperience(int nbFace)
		{
		this.nbFace = nbFace;
		this.setFaceObtenu = new HashSet<Integer>();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	@Override
	public void run()
		{
		this.nbLancer = 0;
		this.setFaceObtenu.clear();

		while(this.setFaceObtenu.size() < this.nbFace)
			{
			int faceTirer = ThreadLocalRandom.current().nextInt(1, this.nbFace+1);

			this.setFaceObtenu.add(faceTirer);

			nbLancer++;
			}
		}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	public int getNbLancer()
		{
		return this.nbLancer;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Inputs
	private int nbFace;

	// Output
	private int nbLancer;

	// Tools
	private Set<Integer> setFaceObtenu;
	}
