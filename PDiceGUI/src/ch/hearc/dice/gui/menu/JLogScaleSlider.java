
package ch.hearc.dice.gui.menu;

import java.awt.Color;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Dictionary;
import java.util.Hashtable;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ch.hearc.c_gui.tools.JCenterH;
import ch.hearc.tools.DiceBuilder;

/**
 * <pre>
 * JSlider on a logaritmic scale
 *
 * Base, Scale : Goes from 1 to base ^ scale
 * Appearence : Put a tick every base^n for n in 0...scale
 * <br>
 * @author maxime.welcklen, Mendes Reis Steve
 *
 */
public class JLogScaleSlider extends Box
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JLogScaleSlider(String title, int base, int scale, DiceBuilder diceBuilder)
		{

		super(BoxLayout.Y_AXIS);

		this.scale = scale;
		this.base = base;
		this.title = title;
		this.diceBuilder = diceBuilder;

		geometry();
		control();
		appearance();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void geometry()
		{
		//init slider from 0 to base^scale
		//tick precision is there to simulate a double value from the integer slider :
		//the value is multiplied by tickvalue on the slider. then when we read it, we divide it by
		//the tick value. as such, 0<->tickvalue on the slider will be read as (0<->tickvalue)/tickvalue => 0.0<->1.0
		slider = new JSlider(SwingConstants.HORIZONTAL, 0, scale * TICK_PRECISION, 0);

		Box boxCurrentValue = new Box(BoxLayout.X_AXIS);
		labelCurrentValue = new JLabel("1");
		boxCurrentValue.add(new JLabel("Current : "));
		boxCurrentValue.add(labelCurrentValue);

		this.add(slider);
		this.add(JMenu.createVSpacing());
		this.add(new JCenterH(boxCurrentValue));
		}

	private void control()
		{
		slider.addChangeListener(new ChangeListener()
			{

			@Override
			public void stateChanged(ChangeEvent e)
				{
				int rawValue = slider.getValue();
				double doubleValue = (double)rawValue / TICK_PRECISION;
				int logValue = (int)Math.pow(base, doubleValue);

				//set current value as text, a bit more human readable
				String numberAsString = decimalFormat.format(logValue);
				labelCurrentValue.setText(numberAsString);

				//set current value to builder
				diceBuilder.setNbExperience(logValue);
				}

			DecimalFormat decimalFormat = new DecimalFormat("#,##0");
			});
		}

	private void appearance()
		{
		//set the logaritmic scale labels
		Dictionary<Integer, JLabel> labelTable = new Hashtable<Integer, JLabel>();
		NumberFormat formatter = new DecimalFormat("0.##E0"); //allow for compact display of big numbers
		for(int i = 0; i <= this.scale; i++)
			{
			int scaledValue = (int)Math.pow(base, i);
			labelTable.put(i * TICK_PRECISION, new JLabel(formatter.format(scaledValue)));
			}
		slider.setLabelTable(labelTable);
		slider.setPaintLabels(true);
		slider.setPaintTicks(true);

		//set initial value to builder
		diceBuilder.setNbExperience(1);

		//set the padding + external border with title inside the box
		Border lineBorder = BorderFactory.createLineBorder(Color.BLACK);
		Border outsideBorder = BorderFactory.createTitledBorder(lineBorder, title, TitledBorder.CENTER, TitledBorder.TOP);
		Border marginBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		this.setBorder(BorderFactory.createCompoundBorder(outsideBorder, marginBorder));

		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Inputs
	private int scale;
	private int base;
	private String title;

	// Tools
	private JSlider slider;
	private JLabel labelCurrentValue;
	private DiceBuilder diceBuilder;

	private static final int TICK_PRECISION = 100;

	}
