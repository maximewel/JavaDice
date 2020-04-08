
package ch.hearc.dice.gui.menu;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;

import ch.hearc.c_gui.tools.JCenterH;
import ch.hearc.c_gui.tools.JComponents;

public class JMenu extends Box
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JMenu()
		{
		super(BoxLayout.Y_AXIS);

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

		//stop and kill buttons, relative to current process
		this.add(Box.createVerticalGlue());
		this.add(new JCenterH(buttonStop));
		this.add(JMenu.createVSpacing());
		this.add(new JCenterH(buttonKill));

		//quit button, at the end
		this.add(Box.createVerticalGlue());
		this.add(new JCenterH(buttonQuit));
		}

	private void control()
		{

		}

	private void appearance()
		{
		//UI helper for the user, as stop and kill might seem obscure
		buttonStop.setToolTipText("Gracefully stop the computing");
		buttonKill.setToolTipText("Brutally stop the computing");

		standardizeComponentSizes();
		}

	private static JButton createStandardButton(String text)
		{
		JButton button = new JButton(text);
		button.setPreferredSize(new Dimension(BUTTON_WIDTH, button.getPreferredSize().height));
		return button;
		}

	/**
	 * Little helper function, initialize all components
	 * Must be called before any operation on tools
	 */
	private void initializeComponents()
		{
		labelTitle = new JLabelTitle("Menu");
		processingChoice = new JProcessingChoice();
		rangeIntegerSpinbox = new JRangeIntegerSpinbox("Dice faces choice", "Minimun faces : ", "Maximum faces :", 0, 10);
		sliderTitled = new JLogScaleSlider("Number of experiences", BASE, SCALE_BASE_10);
		buttonStop = createStandardButton("Stop");
		buttonKill = createStandardButton("Kill");
		buttonQuit = createStandardButton("Quit");
		}

	private void standardizeComponentSizes()
		{
		JComponents.setWidth(processingChoice, COMPONENT_WIDTH);
		JComponents.setWidth(rangeIntegerSpinbox, COMPONENT_WIDTH);
		JComponents.setWidth(sliderTitled, COMPONENT_WIDTH);
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
	private JButton buttonStop;
	private JButton buttonKill;
	private JButton buttonQuit;
	private JLogScaleSlider sliderTitled;

	//general standardisation of UI
	private static final int SPACING = 10;
	private static final int BUTTON_WIDTH = 100;
	private static final int COMPONENT_WIDTH = 350;

	//avoid magic numbers
	private static final int BASE = 10;
	private static final int MAX_NUMBER_EXPERIENCE = 1000000;
	private static final int SCALE_BASE_10 = (int)Math.log10(MAX_NUMBER_EXPERIENCE);

	}
