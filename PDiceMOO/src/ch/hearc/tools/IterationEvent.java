
package ch.hearc.tools;



public class IterationEvent
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	public IterationEvent(AlgoIteratif_A source, int i, EtatAlgo etatAlgo)
		{
		this.source = source;
		this.i = i;
		this.etatAlgo = etatAlgo;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	public int getI()
		{
		return this.i;
		}

	public EtatAlgo getEtatAlgo()
		{
		return this.etatAlgo;
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

	//input / output
	private AlgoIteratif_A source;

	private int i;
	private EtatAlgo etatAlgo;
	}

