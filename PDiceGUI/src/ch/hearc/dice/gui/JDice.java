
package ch.hearc.dice.gui;

import java.awt.BorderLayout;
import java.awt.Toolkit;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import ch.hearc.c_gui.tools.JComponents;
import ch.hearc.dice.gui.menu.JMenu;
import ch.hearc.dice.gui.result.JResult;

public class JDice extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JDice()
		{
		geometry();
		control();
		appearance();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void geometry()
		{
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		BorderLayout borderlayout = new BorderLayout();
		this.setLayout(borderlayout);

		//init left and right part of splitpane
		result = new JResult();
		menu = new JMenu(result);

		//init split pane with the two components
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new JScrollPane(menu), result);
		this.add(splitPane);

		}

	private void control()
		{
		}

	private void appearance()
		{
		splitPane.setOneTouchExpandable(true);

		//frame start with 1:3 ratio between menu and graphs
		int menuLocation = this.getWidth() / 4;
		JComponents.setWidth(menu, menuLocation);
		splitPane.setDividerLocation(menuLocation + splitPane.getDividerSize());

		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Tools
	private JSplitPane splitPane;
	private JMenu menu;
	private JResult result;

	}
