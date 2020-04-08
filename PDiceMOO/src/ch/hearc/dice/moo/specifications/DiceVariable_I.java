package ch.hearc.dice.moo.specifications;

import java.util.Map;

import ch.hearc.b_poo.thread.vecteur.Interval;
import ch.hearc.tools.Chrono;
import ch.hearc.tools.IterationListener_I;

public interface DiceVariable_I extends Runnable
	{
		// Outputs
		public Map<Integer, Integer> getMapFaceLancer();

		public Map<Integer, Chrono> getMapFaceChrono();

		// Inputs
		public Interval getNbFaces(); // Interval du WDreamTeam
		public int getNbExperience();

		public void addIterationListener(IterationListener_I iterationListener ) ;
		public void removeIterationListener(IterationListener_I iterationListener ) ;
	}
