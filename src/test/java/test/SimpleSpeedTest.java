package test;

import junit.framework.TestCase;

import java.io.IOException;
import java.util.Random;

import util.Base64;

/**
 * @author Mikael Grev, MiG InfoCom AB
 *         Date: 4/1/12
 *         Time: 21:35 PM
 */
public class SimpleSpeedTest extends TestCase
{
	private byte[] randomData;

	protected void setUp() throws Exception
	{
		randomData = new byte[1000000];
		new Random().nextBytes(randomData);
	}

	protected void tearDown() throws Exception
	{
		randomData = null;
	}

	public void test01() throws IOException
	{
		long n = System.nanoTime();

		for (int i = 0; i < 1000; i++) {
			byte[] enc = Base64.encodeToByte(randomData, true);
			byte[] dec = Base64.decode(enc);
		}

		System.out.println("Timed: " + ((System.nanoTime() - n) / 1000000f));
	}
}