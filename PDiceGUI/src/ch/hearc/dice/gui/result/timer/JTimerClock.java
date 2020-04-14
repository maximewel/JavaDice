
package ch.hearc.dice.gui.result.timer;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.font.FontRenderContext;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

public class JTimerClock extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JTimerClock()
		{
		geometry();
		control();
		appearance();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	@Override
	protected void paintComponent(Graphics g)
		{
		super.paintComponent(g);
		paint((Graphics2D)g);
		}

	private void paint(Graphics2D g2d)
		{
		//usefull to have a nice round clock
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		//center
		g2d.translate(getWidth() / 2, getHeight() / 2);

		g2d.setStroke(new BasicStroke(PEN_DIAL_STROKE));
		g2d.draw(dial);

		Rectangle2D stringBounds = getStringBounds("00", g2d);
		for(int i = 0; i < 60; i++)
			{

			if (i % 5 == 0)
				{
				g2d.setStroke(new BasicStroke(PEN_BIGTICK_STROKE));
				g2d.draw(bigTick);

				String number = String.format("%02d", i);
				g2d.drawString(number, -(int)(stringBounds.getWidth() / 2), (int)(-dial.getHeight() / 2 + tickSize + stringBounds.getHeight()));
				}
			else
				{
				g2d.setStroke(new BasicStroke(PEN_BIGTICK_STROKE / 2));
				g2d.draw(littleTick);
				}
			if (i == seconds)
				{
				Color stockColor = g2d.getColor();

				g2d.setStroke(new BasicStroke(PEN_BIGTICK_STROKE));
				g2d.setColor(Color.red);
				g2d.draw(pointerStick);
				g2d.fill(pointerRound);

				g2d.setColor(stockColor);
				}
			g2d.rotate(ANGLE_SECOND);
			}

		//put transform back
		g2d.translate(-getWidth() / 2, -getHeight() / 2);
		}

	public void setSeconde(int s)
		{
		this.seconds = s;
		repaint();
		}

	private void geometry()
		{
		// TODO Auto-generated method stub
		}

	private Rectangle2D getStringBounds(String string, Graphics2D g2d)
		{
		FontRenderContext frc = g2d.getFontRenderContext();
		return g2d.getFont().getStringBounds(string, frc);
		}

	private void control()
		{
		this.addComponentListener(new ComponentAdapter()
			{

			@Override
			public void componentResized(ComponentEvent e)
				{
				super.componentResized(e);
				updateSize();
				}
			});
		}

	private void appearance()
		{
		updateSize();
		}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void updateSize()
		{
		int squareSize = Math.min(getWidth(), getHeight()) - CLOCK_MARGIN;

		dial = new Ellipse2D.Double(-squareSize / 2, -squareSize / 2, squareSize, squareSize);

		double borderDial = dial.getHeight() / 2;
		tickSize = borderDial / 10;

		bigTick = new Line2D.Double(0, borderDial - tickSize, 0, borderDial);
		littleTick = new Line2D.Double(0, borderDial - tickSize / 2, 0, borderDial);

		double pointerSize = 0.7 * borderDial;
		double pointerRoundRaidus = tickSize;

		pointerRound = new Ellipse2D.Double(-pointerRoundRaidus / 2, -(pointerSize + pointerRoundRaidus), pointerRoundRaidus, pointerRoundRaidus);
		pointerStick = new Line2D.Double(0, 0, 0, -pointerSize);

		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	// Tools
	private int seconds;

	//drawing variables
	private Ellipse2D.Double dial;
	private Line2D.Double littleTick;
	private Line2D.Double bigTick;

	private Ellipse2D.Double pointerRound;
	private Line2D.Double pointerStick;

	private double tickSize;

	private static final double ANGLE_SECOND = 2 * Math.PI / 60;
	private static final int PEN_DIAL_STROKE = 5;
	private static final int PEN_BIGTICK_STROKE = 2;
	private static final int CLOCK_MARGIN = 10;

	}
