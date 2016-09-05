package mine.util;

import java.util.Timer;
import java.util.TimerTask;


public class TimerTest {

	/**
	 * @param args
	 */
	public static int a=1;
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Timer timer = new Timer();
		TestTimer testTimer = new TestTimer();
		timer.schedule(testTimer, 3000,3000);
	}
	 
	static class TestTimer extends TimerTask{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			a++;
		  System.out.println(a);	
		}
		
	}

}
