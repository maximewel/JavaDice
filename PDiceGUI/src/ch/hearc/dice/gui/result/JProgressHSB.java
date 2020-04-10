
package ch.hearc.dice.gui.result;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;

import javax.swing.JProgressBar;

public class JProgressHSB extends JProgressBar
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JProgressHSB()
		{

		geometry();
		control();
		appearance();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	@Override
	public void setValue(int n)
		{
		super.setValue(n);
		updateImage();
		}

	@Override
	protected void paintComponent(Graphics g)
		{
		paint((Graphics2D)g);
		}

	private void paint(Graphics2D g2d)
		{
		if (bImage != null)
			{
			g2d.drawImage(bImage, 0, 0, null);
			}
		}
	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void geometry()
		{
		}

	private void control()
		{
		this.addComponentListener(new ComponentAdapter()
			{

			@Override
			public void componentResized(ComponentEvent e)
				{
				int w = getWidth();
				int h = getHeight();
				bImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
				Graphics2D g2d = bImage.createGraphics();
				g2d.setBackground(new Color(0, 0, 0, 0));
				g2d.setColor(Color.black);
				g2d.drawRect(0, 0, w - 1, h - 1);
				}

			});
		}

	private void appearance()
		{
		}

	private void updateImage()
		{
		int h = bImage.getHeight();
		int w = bImage.getWidth();
		double ratio = (double)getValue() / getMaximum();
		int widthColored = (int)(ratio * w);

		for(int i = 1; i < widthColored - 1; i++)
			{
			//we want he hue to go from 0 to 1 to have approx 1 round of HSB
			float hue = (float)i / w;
			int colorInt = Color.HSBtoRGB(hue, 1, 1); // HSB in [0,1]

			for(int j = 1; j < h - 1; j++)
				{
				bImage.setRGB(i, j, colorInt); // j i et pas i j
				}
			}
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Inputs

	// Tools
	private BufferedImage bImage;

	}
