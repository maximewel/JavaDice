
package ch.hearc.dice.gui.result;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import ch.hearc.dice.moo.specifications.DiceVariable_I;
import ch.hearc.tools.AlgoIteratif_A;
import ch.hearc.tools.IterationEvent;
import ch.hearc.tools.IterationListener_I;

public class JProgressBars extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JProgressBars(String descTop, String descBot)
		{

		this.descTop = descTop;
		this.descBot = descBot;

		geometry();
		control();
		appearance();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public void refreshDice(DiceVariable_I diceVariable_I)
		{
		this.diceVariable_I = diceVariable_I;
		updateListener();
		}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void geometry()
		{
		gridLayout = new GridLayout(2, 2);
		this.setLayout(gridLayout);

		progressBarGlobal = new JProgressBar();
		progressHSB = new JProgressHSB();

		this.add(new JLabel(descTop));
		this.add(progressBarGlobal);
		this.add(new JLabel(descBot));
		this.add(progressHSB);
		}

	private void control()
		{
		// rien
		}

	private void appearance()
		{
		//set the padding + external border with title inside the box
		Border lineBorder = BorderFactory.createLineBorder(Color.BLACK);
		Border outsideBorder = BorderFactory.createTitledBorder(lineBorder, "Processus execution", TitledBorder.CENTER, TitledBorder.TOP);
		Border marginBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		this.setBorder(BorderFactory.createCompoundBorder(outsideBorder, marginBorder));

		gridLayout.setHgap(5);
		gridLayout.setVgap(5);
		}

	private void updateListener()
		{
		diceVariable_I.addIterationListener(new IterationListener_I()
			{

			@Override
			public void iterationPerformed(IterationEvent iterationEvent)
				{
				switch(iterationEvent.getEtatAlgo())
					{
					case BEGIN:
						progressBarGlobal.setIndeterminate(true);
						algoIteratif_A = iterationEvent.getAlgoIteratif();
						int min = algoIteratif_A.getNbFaces().getA();
						int max = algoIteratif_A.getNbFaces().getB();
						progressHSB.getModel().setRangeProperties(0, 0, 0, max - min, true);
						break;
					case RUNNING:
						//"1 +" because the first one is 0, and we start from 0
						int current = 1 + iterationEvent.getI();
						progressHSB.setValue(current);
						break;
					case END:
						progressBarGlobal.setIndeterminate(false);
						break;
					default:
						//should never happen
						break;
					}
				}

			AlgoIteratif_A algoIteratif_A;
			});
		}

	public void experienceKilled()
		{
		progressBarGlobal.setIndeterminate(false);
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Inputs
	private DiceVariable_I diceVariable_I; //not through constructor, but still needed as regular input
	private String descTop;
	private String descBot;

	// Tools
	private JProgressBar progressBarGlobal;
	private JProgressHSB progressHSB;

	private GridLayout gridLayout;

	}
