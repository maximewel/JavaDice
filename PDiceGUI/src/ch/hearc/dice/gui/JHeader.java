
package ch.hearc.dice.gui;

import javax.swing.Box;
import javax.swing.BoxLayout;

import ch.hearc.c_gui.tools.JComponents;
import ch.hearc.c_gui.tools.jimage.JImage;
import ch.hearc.c_gui.tools.jimage.TypeRendering;
import ch.hearc.dice.tools.ImageShop;

/**
 * <pre>
 * Header containing the he-arc logo
 * @author maxime.welcklen, mendesreis.steve
 *
 */
public class JHeader extends Box
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JHeader()
		{
		super(BoxLayout.X_AXIS);

		geometry();
		control();
		appearance();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void geometry()
		{

		imageHearc = new JImage(ImageShop.LOGO_HEARC.getImage(), TypeRendering.KEEP_RATIO);
		JComponents.setHeight(imageHearc, 50);

		this.add(Box.createHorizontalGlue());
		this.add(imageHearc);
		this.add(Box.createHorizontalGlue());

		}

	private void control()
		{
		//pass
		}

	private void appearance()
		{
		//pass
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Tools
	private JImage imageHearc;

	}
