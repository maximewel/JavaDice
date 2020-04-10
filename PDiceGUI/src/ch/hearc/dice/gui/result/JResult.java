
package ch.hearc.dice.gui.result;

import javax.swing.JPanel;

import ch.hearc.dice.moo.specifications.DiceVariable_I;

public class JResult extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JResult()
		{
		geometry();
		control();
		appearance();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public void refreshExperience(DiceVariable_I diceVariable_I)
		{
		progressBars.refreshDice(diceVariable_I);
		}

	public void experienceKilled()
		{
		progressBars.experienceKilled();
		}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void geometry()
		{
		progressBars = new JProgressBars("Experience running : ", "Current task advancement :");
		graphes = new JGraphes();

		this.add(graphes);
		this.add(progressBars);
		}

	private void control()
		{
		// rien
		}

	private void appearance()
		{
		// rien
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Inputs

	// Tools
	private JProgressBars progressBars;
	private JGraphes graphes;

	}
