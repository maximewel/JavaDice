
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

	/*------------------------------------------------------------------*\
	|*		Version Assynchrone	(non bloquant)							*|
	\*------------------------------------------------------------------*/

	}
