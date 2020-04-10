
package ch.hearc.dice.gui.menu;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import ch.hearc.c_gui.tools.JCenterH;
import ch.hearc.c_gui.tools.JComponents;
import ch.hearc.dice.moo.specifications.DiceVariable_I;
import ch.hearc.dice.tools.ImageShop;
import ch.hearc.tools.DiceBuilder;
import ch.hearc.tools.EtatAlgo;
import ch.hearc.tools.IterationEvent;
import ch.hearc.tools.IterationListener_I;

public class JMenu extends Box
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JMenu()
		{
		super(BoxLayout.Y_AXIS);

		//important : builder is necessary during creation of components - do it before anything else
		diceBuilder = new DiceBuilder();

		geometry();
		control();
		appearance();

		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public static Component createVSpacing()
		{
		return Box.createVerticalStrut(SPACING);
		}

	public static Component createHSpacing()
		{
		return Box.createHorizontalStrut(SPACING);
		}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void geometry()
		{
		//init tools
		initializeComponents();

		//setup the vertical arangment of the frame
		//title
		this.add(new JCenterH(labelTitle));

		//processing choice "box"
		this.add(Box.createVerticalGlue());
		this.add(new JCenterH(processingChoice));

		//spinner range for the dice number of faces
		this.add(Box.createVerticalGlue());
		this.add(new JCenterH(rangeIntegerSpinbox));

		//Slider for the number of experience
		this.add(Box.createVerticalGlue());
		this.add(new JCenterH(sliderTitled));

		//start/stop/kill buttons, relative to current process
		this.add(Box.createVerticalGlue());
		this.add(new JCenterH(buttonline));

		//quit button, at the end
		this.add(Box.createVerticalGlue());
		this.add(new JCenterH(buttonQuit));
		}

	private void control()
		{
		doStop = new AtomicBoolean(false); //at first, we want the algo to be able to run

		//quit button
		buttonQuit.addActionListener(new ActionListener()
			{

			@Override
			public void actionPerformed(ActionEvent e)
				{
				System.exit(0); // 0 normal
				}
			});

		//start button
		buttonStart.addActionListener(new ActionListener()
			{

			@Override
			public void actionPerformed(ActionEvent e)
				{
				diceVariable = diceBuilder.build();
				diceVariable.addIterationListener(createIterationListener());
				doStop.set(false);

				diceThread = new Thread(diceVariable);
				diceThread.start();

				buttonline.switchButtonEnabled();
				}

			});

		//stop button
		buttonStop.addActionListener(new ActionListener()
			{

			@Override
			public void actionPerformed(ActionEvent e)
				{
				doStop.set(true);
				}
			});

		//kill button
		buttonKill.addActionListener(new ActionListener()
			{

			@SuppressWarnings("deprecation") //stop is depreciated, we never use it normally (see - PConc courses)
			@Override
			public void actionPerformed(ActionEvent e)
				{
				diceThread.stop();
				buttonline.switchButtonEnabled();
				}
			});
		}

	private void appearance()
		{
		//UI helper for the user, as start, stop and kill might seem obscure
		buttonStart.setToolTipText("Start the process experience with given parameters");
		buttonStop.setToolTipText("Gracefully stop the computing");
		buttonKill.setToolTipText("Brutally stop the computing");

		buttonStart.setEnabled(true); //useless, just to be sure
		buttonStop.setEnabled(false);
		buttonKill.setEnabled(false);

		standardizeComponentSizes();
		}

	private static JButton createStandardButton(String text, Image img)
		{
		JButton button = new JButton(text);
		button.setPreferredSize(new Dimension(BUTTON_WIDTH, button.getPreferredSize().height));

		//resize the icon to fit inside the button (Height), let the width be determined auto
		int iconHeight = (int)(BUTTON_ICON_HEIGHT_RATIO * button.getPreferredSize().height);
		Image newimg = img.getScaledInstance(-1, iconHeight, Image.SCALE_SMOOTH);
		ImageIcon icon = new ImageIcon(newimg);
		button.setIcon(icon);

		return button;
		}

	private IterationListener_I createIterationListener()
		{
		return new IterationListener_I()
			{

			@Override
			public void iterationPerformed(IterationEvent it)
				{
				System.out.println("Algo " + it.getI());
				System.out.println("  State : " + it.getEtatAlgo().toString());
				if (doStop.get())
					{
					it.getAlgoIteratif().stop();
					}
				if (it.getEtatAlgo() == EtatAlgo.END)
					{
					buttonline.switchButtonEnabled();
					}
				}
			};
		}

	/**
	 * Little helper function, initialize all components
	 * Must be called before any operation on tools
	 * avoid congestion of geometry()
	 */
	private void initializeComponents()
		{
		labelTitle = new JLabelTitle("Menu");
		processingChoice = new JProcessingChoice(diceBuilder);
		rangeIntegerSpinbox = new JRangeIntegerSpinbox("Dice faces choice", "Minimun faces : ", "Maximum faces :", 0, 10, diceBuilder);
		sliderTitled = new JLogScaleSlider("Number of experiences", BASE, SCALE_BASE_10, diceBuilder);

		buttonStart = createStandardButton("Start", ImageShop.START_ICON.getImage());
		buttonStop = createStandardButton("Stop", ImageShop.STOP_ICON.getImage());
		buttonKill = createStandardButton("Kill", ImageShop.KILL_ICON.getImage());
		buttonline = new JButtonLine(buttonStart, buttonStop, buttonKill);

		buttonQuit = createStandardButton("Quit", ImageShop.EXIT_ICON.getImage());
		}

	private void standardizeComponentSizes()
		{
		JComponents.setWidth(processingChoice, COMPONENT_WIDTH);
		JComponents.setWidth(rangeIntegerSpinbox, COMPONENT_WIDTH);
		JComponents.setWidth(sliderTitled, COMPONENT_WIDTH);
		JComponents.setWidth(buttonline, COMPONENT_WIDTH);
		}
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Inputs

	// Tools
	//components
	private JLabelTitle labelTitle;
	private JProcessingChoice processingChoice;
	private JRangeIntegerSpinbox rangeIntegerSpinbox;
	private JLogScaleSlider sliderTitled;
	private JButtonLine buttonline;
	private JButton buttonStart;
	private JButton buttonStop;
	private JButton buttonKill;
	private JButton buttonQuit;

	//builder pattern, dice related objects
	private DiceBuilder diceBuilder;
	private DiceVariable_I diceVariable;
	private Thread diceThread;
	AtomicBoolean doStop;

	//general standardisation of UI
	private static final int SPACING = 10;
	private static final int BUTTON_WIDTH = 100;
	private static final int COMPONENT_WIDTH = 350;
	private static final double BUTTON_ICON_HEIGHT_RATIO = 0.7;

	//avoid magic numbers
	private static final int BASE = 10;
	private static final int MAX_NUMBER_EXPERIENCE = 1000000000;
	private static final int SCALE_BASE_10 = (int)Math.ceil(Math.log10(MAX_NUMBER_EXPERIENCE));

	}
