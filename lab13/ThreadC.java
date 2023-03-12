package lab13;

import java.util.concurrent.TimeUnit;

public class ThreadC extends Thread {
	Threadlab window = new Threadlab();
	boolean state = true;
	public void stateC() { state = false; }
	public void run() {
		while( state ) {
			this.window.text = window.text + "C" ;
			System.out.print( this.window.text );
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
