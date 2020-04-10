
package ch.hearc.dice.tools;

import javax.swing.ImageIcon;

import ch.hearc.c_gui.tools.image.ImageLoader;

/**
* <pre>
* Les images doivent se trouver dans un jar, et le jar dans le classapth!
* Le jar doit contenir le folder ressources/cours.
* A l'interieur du folder cours doit se trouver les images aux formats (jpg, voir mieux png pour la transparance)
* </pre>
*/
public class ImageShop
	{

	/*------------------------------------------------------------------*\
	|*		 Version Synchrone (bloquant)								*|
	\*------------------------------------------------------------------*/

	public static final ImageIcon DICE = ImageLoader.loadSynchroneJar("ressources/gui/dice.png");
	public static final ImageIcon LOGO_HEARC = ImageLoader.loadSynchroneJar("ressources/gui/logoHearc.png");
	public static final ImageIcon KILL_ICON = ImageLoader.loadSynchroneJar("ressources/gui/kill.png");
	public static final ImageIcon START_ICON = ImageLoader.loadSynchroneJar("ressources/gui/start.png");
	public static final ImageIcon STOP_ICON = ImageLoader.loadSynchroneJar("ressources/gui/stop.png");
	public static final ImageIcon EXIT_ICON = ImageLoader.loadSynchroneJar("ressources/gui/exit.png");

	/*------------------------------------------------------------------*\
	|*		Version Assynchrone	(non bloquant)							*|
	\*------------------------------------------------------------------*/

	}
