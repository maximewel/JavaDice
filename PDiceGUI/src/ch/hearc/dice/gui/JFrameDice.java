
package ch.hearc.dice.gui;

import java.awt.BorderLayout;
import java.awt.Frame;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import ch.hearc.c_gui.tools.JMarge;
import ch.hearc.dice.tools.ImageShop;

public class JFrameDice extends JFrame
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JFrameDice(JComponent component)
		{
		this.component = component;
		geometry();
		control();
		appearance();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void geometry()
		{
		BorderLayout borderLayout = new BorderLayout();
		setLayout(borderLayout);

		//add component with a slight margin, inside a scrollpane (same as contentpane, but with scrollbars, yay)
		add(new JHeader(), BorderLayout.NORTH);
		JScrollPane scrollPane = new JScrollPane(component);
		add(new JMarge(scrollPane, 10, 10), BorderLayout.CENTER);
		}

	private void control()
		{
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		}

	private void appearance()
		{
		// set JFrame to fill the screen (but allow resize and menubar)
		setExtendedState(Frame.MAXIMIZED_BOTH);

		//customize the frame
		setTitle("Dice experience");
		setIconImage(ImageShop.DICE.getImage());

		setLocationRelativeTo(null); // frame centrer
		setVisible(true); // last!
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Inputs
	private JComponent component;

	// Tools

	}
