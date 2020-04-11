
package ch.hearc.dice.gui.result;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.concurrent.atomic.AtomicBoolean;

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
	public void setString(String string)
		{
		super.setString(string);
		if (bImage != null)
			{
			updateImage();
			}
		}

	public boolean isHSBEnabled()
		{
		return isHSBEnabled.get();
		}

	public void setHSBEnabled(Boolean enbabled)
		{
		isHSBEnabled.set(enbabled);
		this.repaint();
		}

	@Override
	public void setValue(int n)
		{
		super.setValue(n);
		if (bImage != null)
			{
			updateImage();
			}
		}

	@Override
	protected void paintComponent(Graphics g)
		{
		if (isHSBEnabled.get())
			{
			paint((Graphics2D)g);
			}
		else
			{
			super.paintComponent(g);
			}
		}

	private synchronized void paint(Graphics2D g2d)
		{
		g2d.drawImage(bImage, 0, 0, null);
		}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void updateString()
		{
		if (bImage != null)
			{
			Graphics2D g2d = (Graphics2D)bImage.getGraphics();
			g2d.setFont(new Font("Arial", Font.ROMAN_BASELINE, (int)(getHeight() * 0.7)));

			FontRenderContext frc = g2d.getFontRenderContext();
			Rectangle2D stringBounds = g2d.getFont().getStringBounds(progressString, frc);

			int stringWidth = (int)Math.round(stringBounds.getWidth());
			int stringHeight = (int)Math.round(stringBounds.getHeight());

			int x = getWidth() / 2 - stringWidth / 2;
			int y = (getHeight() - 2) / 2 + stringHeight / 2;

			g2d.setColor(Color.BLACK);
			g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
			g2d.drawString(progressString, x, y);
			}

		}

	private void geometry()
		{
		isHSBEnabled = new AtomicBoolean(false);
		}

	private void control()
		{
		this.addComponentListener(new ComponentAdapter()
			{

			@Override
			public void componentResized(ComponentEvent e)
				{
				//create new image with right dimensions
				createImage();

				//update, so that it displays the right value
				JProgressHSB.this.updateImage();
				}

			});
		}

	private synchronized void createImage()
		{
		int w = getWidth();
		int h = getHeight();
		bImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

		//draw the exterior border
		Graphics2D g2d = bImage.createGraphics();
		g2d.setColor(Color.black);
		g2d.drawRect(0, 0, w - 1, h - 1);

		clearImage(g2d);
		}

	private synchronized void clearImage(Graphics2D g2d)
		{
		g2d.setBackground(new Color(0, 0, 0, 0));
		g2d.clearRect(1, 1, bImage.getWidth() - 2, bImage.getHeight() - 2);
		}

	private void appearance()
		{
		}

	private synchronized void updateImage()
		{
		clearImage(bImage.createGraphics());

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
				bImage.setRGB(i, j, colorInt);
				}
			}

		updateString();
		repaint();
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Inputs

	// Tools
	private BufferedImage bImage;
	private AtomicBoolean isHSBEnabled;

	}
