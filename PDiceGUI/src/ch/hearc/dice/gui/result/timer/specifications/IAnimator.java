
package ch.hearc.dice.gui.result.timer.specifications;

/**
 * observer pattern animator/animable
 * Publisher : Can be started/stopped, will update every listener
 * @author maxime.welcklen, Mendes Reis Steve
 *
 */
public interface IAnimator
	{

	public void start();

	public void stop();

	//Ianimator can serve multiple animable if necessary (Observer pattern)
	public void addAnimable(IAnimable animable);

	public void removeAnimable(IAnimable animable);

	}
