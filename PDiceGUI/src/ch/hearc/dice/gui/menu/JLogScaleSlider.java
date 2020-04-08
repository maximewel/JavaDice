
package ch.hearc.dice.gui.menu;

import java.util.Dictionary;
import java.util.Hashtable;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ch.hearc.c_gui.tools.JCenter;

public class JLogScaleSlider extends Box
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JLogScaleSlider(String title, int base, int scale)
		{

		super(BoxLayout.Y_AXIS);

		this.scale = scale;
		this.base = base;
		this.title = title;

		geometry();
		control();
		appearance();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void geometry()
		{
		//init slider from 0 to scale
		//tick precision is there to simulate a double value from the int slider :
		//the value is multiplied by tickvalue on the slider. then when we read it, we divide it by
		//the tick value. as such, 0<->tickvalue on the slider => 0.0<->1.0 read.
		slider = new JSlider(SwingConstants.HORIZONTAL, 0, scale*TICK_PRECISION, 0);


		JLabel titleLabel = new JLabel(title);

		Box boxCurrentValue = new Box(BoxLayout.X_AXIS);
		labelCurrentValue = new JLabel("1");
		boxCurrentValue.add(new JLabel("Current : "));
		boxCurrentValue.add(labelCurrentValue);

		this.add(new JCenter(titleLabel));
		this.add(slider);
		this.add(boxCurrentValue);
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
				double logValue = Math.pow(base, doubleValue);

				labelCurrentValue.setText(Integer.toString((int)logValue));
				}
			});
		}

	private void appearance()
		{
		//set the logaritmic scale labels
		Dictionary<Integer, JLabel> labelTable = new Hashtable<Integer, JLabel>();
		for(int i = 0; i <= this.scale; i++)
			{
			int scaledValue = (int)Math.pow(base, i);
			labelTable.put(i * TICK_PRECISION, new JLabel(Integer.toString(scaledValue)));
			}
		slider.setLabelTable(labelTable);
		slider.setPaintLabels(true);
		slider.setPaintTicks(true);

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

	private static final int TICK_PRECISION = 100;

	}
