
package ch.hearc.tools;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestChrono
	{

	private static void sleepMS(long duration) {
		try
			{
			Thread.sleep(duration);
			}
		catch (InterruptedException e)
			{
			e.printStackTrace();
			}
	}

	@Test
	public void testChronoDice() {

	long timeMS = 2020;

	Chrono chrono = new Chrono();
	sleepMS(timeMS);
	chrono.stop();

	Assertions.assertEquals(timeMS, chrono.getTimeMS());
	}

	}

