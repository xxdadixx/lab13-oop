package lab13;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

import org.eclipse.swt.widgets.Label;

import java.util.concurrent.TimeUnit;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class Threadlab extends Thread {
	
	public class Threadouter {
		public class ThreadA extends Thread {
			Threadlab window = new Threadlab();
			int count = 0;
			boolean state = true;
			public void stateA() { state = false; }
			public void stateAstart() { state = true; }
			public void run() {
				while( state ) {
					this.window.text = text + "A" ;
					System.out.print( this.window.text );
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	int n = 0;
	String text = "" ;
	protected Shell shell;
	public static void main(String[] args) {
		try {
			Threadlab window = new Threadlab();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
		
	public void createContents() {
	
		shell = new Shell();
		shell.setSize(274, 261);
		shell.setText("SWT Application");
		
		Threadouter outer = new Threadouter();
		Threadouter.ThreadA threadA = outer.new ThreadA() ;
		ThreadB threadB = new ThreadB();
		ThreadC threadC = new ThreadC();
		
		Composite whiter = new Composite(shell, SWT.NONE);
		whiter.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		whiter.setBounds(10, 10, 238, 36);
		
		Label display1 = new Label(whiter , SWT.None);
		display1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		display1.setBounds(10, 10, 218, 15);
		
		Label ThreadA = new Label(shell, SWT.NONE);
		ThreadA.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		ThreadA.setBounds(20, 52, 85, 25);
		ThreadA.setText("Thread : A");
		
		Label ThreadB = new Label(shell, SWT.NONE);
		ThreadB.setText("Thread : B");
		ThreadB.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		ThreadB.setBounds(20, 83, 85, 25);
		
		Label ThreadC = new Label(shell, SWT.NONE);
		ThreadC.setText("Thread : C");
		ThreadC.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		ThreadC.setBounds(20, 114, 85, 25);
		
		Button StartA = new Button(shell, SWT.NONE);
		StartA.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				threadA.start();
				
			}
		});
		StartA.setBounds(131, 55, 52, 25);
		StartA.setText("Start");
		
		Button StartB = new Button(shell, SWT.NONE);
		StartB.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				threadB.start();
			}
		});
		StartB.setText("Start");
		StartB.setBounds(131, 86, 52, 25);
		
		Button StartC = new Button(shell, SWT.NONE);
		StartC.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				threadC.start();
				
			}
		});
		StartC.setText("Start");
		StartC.setBounds(131, 117, 52, 25);
		
		Button StopA = new Button(shell, SWT.NONE);
		StopA.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				threadA.stateA();
			}
		});
		StopA.setText("Stop");
		StopA.setBounds(189, 55, 52, 25);
		
		Button StopB = new Button(shell, SWT.NONE);
		StopB.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				threadB.stateB();
			}
		});
		StopB.setText("Stop");
		StopB.setBounds(189, 86, 52, 25);
		
		Button StopC = new Button(shell, SWT.NONE);
		StopC.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				threadC.stateC();
			}
		});
		StopC.setText("Stop");
		StopC.setBounds(189, 117, 52, 25);
		
		Button StartAll = new Button(shell, SWT.CENTER);
		StartAll.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				threadA.start();
				threadB.start();
				threadC.start();
			}
		});
		StartAll.setBounds(30, 153, 192, 25);
		StartAll.setText("Start All Thread");
		
		Button StopAll = new Button(shell, SWT.CENTER);
		StopAll.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				threadA.stateA();
				threadB.stateB();
				threadC.stateC();
			}
		});
		StopAll.setText("Stop All Thread");
		StopAll.setBounds(30, 184, 192, 25);

	}

}
