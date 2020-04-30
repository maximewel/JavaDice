
package ch.hearc.dice.gui.result;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import ch.hearc.dice.moo.specifications.DiceVariable_I;
import ch.hearc.tools.AlgoIteratif_A;
import ch.hearc.tools.IterationEvent;
import ch.hearc.tools.IterationListener_I;

/**
 * <pre>
 * JProgressBars
 * <br>
 * Component that display the state and progression of the current experience using a 'running' progress bar, and a 'current' progress bar<br>
 * Current has a toggle on/off HSB display
 * @author maxime.welcklen, Mendes Reis Steve
 *
 */
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
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void geometry()
		{
		gridLayout = new GridLayout(3, 2);
		this.setLayout(gridLayout);

		progressBarGlobal = new JProgressBar();
		progressHSB = new JProgressHSB();
		checkBoxHSB = new JCheckBox();
		checkBoxHSB.setSelected(progressHSB.isHSBEnabled());

		this.add(new JLabel(descTop));
		this.add(progressBarGlobal);
		this.add(new JLabel(descBot));
		this.add(progressHSB);
		this.add(new JLabel("HSB display : "));
		this.add(checkBoxHSB);
		}

	private void control()
		{
		checkBoxHSB.addActionListener(new ActionListener()
			{

			@Override
			public void actionPerformed(ActionEvent e)
				{
				progressHSB.setHSBEnabled(checkBoxHSB.isSelected());
				}
			});
		}

	private void appearance()
		{
		//set the padding + external border with title inside the box
		Border lineBorder = BorderFactory.createLineBorder(Color.BLACK);
		Border outsideBorder = BorderFactory.createTitledBorder(lineBorder, "Processus execution", TitledBorder.CENTER, TitledBorder.TOP);
		Border marginBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		this.setBorder(BorderFactory.createCompoundBorder(outsideBorder, marginBorder));

		progressHSB.setStringPainted(true);
		progressHSB.setString("Not started");

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
						progressHSB.setString("0/" + progressHSB.getMaximum());
						break;
					case RUNNING:
						//"1 +" because the first one is 0, and we start from 0
						step = 1 + iterationEvent.getI();
						progressHSB.setValue(step);
						progressHSB.setString(step + "/" + progressHSB.getMaximum());
						break;
					case END:
						progressBarGlobal.setIndeterminate(false);
						if (step == progressHSB.getMaximum())
							{
							progressHSB.setString("Finished");
							}
						else
							{
							progressHSB.setString("Stopped");
							}
						break;
					default:
						//should never happen
						break;
					}
				}

			AlgoIteratif_A algoIteratif_A;
			int step;
			});
		}

	public void experienceKilled()
		{
		progressBarGlobal.setIndeterminate(false);
		progressHSB.setString("Killed");
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
	private JCheckBox checkBoxHSB;

	private GridLayout gridLayout;

	}
