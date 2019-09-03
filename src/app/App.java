package app;

import java.awt.AWTException;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class App extends Frame {
	private static final long serialVersionUID = 1L;
	private Button buttonOn;
	private Button buttonOff;
	private Thread thread;

	private static long getRandomDelay(int value) {
		return (long) (Math.random() * value);
	}

	public App() throws AWTException {
		this.setLayout(new FlowLayout());
		this.setTitle("For Leon!");
		this.setSize(250, 100);
		
		Robot robot = new Robot();

		buttonOn = new Button("On");
		buttonOn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				thread = new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							Thread.sleep(2000 + getRandomDelay(5000));
							
							robot.keyPress(KeyEvent.VK_A);
							Thread.sleep(getRandomDelay(100));
							robot.keyRelease(KeyEvent.VK_A);

							Thread.sleep(2000 + getRandomDelay(5000));

							robot.keyPress(KeyEvent.VK_D);
							Thread.sleep(getRandomDelay(100));
							robot.keyRelease(KeyEvent.VK_D);

							Thread.sleep(100 + getRandomDelay(2000));

							robot.keyPress(KeyEvent.VK_SPACE);
							
							// BIG BRAKE: wait 1 minute + 1-60 seconds
							Thread.sleep(60000 + getRandomDelay(60000));

							robot.keyPress(KeyEvent.VK_W);
							Thread.sleep(getRandomDelay(100));
							robot.keyRelease(KeyEvent.VK_W);

							Thread.sleep(2000 + getRandomDelay(5000));

							robot.keyPress(KeyEvent.VK_S);
							Thread.sleep(getRandomDelay(100));
							robot.keyRelease(KeyEvent.VK_S);
							
							if (!Thread.interrupted()) {
								run();
							}
							
						} catch (InterruptedException e) {
							// ...
						}
					}
				});
				thread.start();
			}
		});
		

		buttonOff = new Button("Off");
		buttonOff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				thread.interrupt();
			}
		});
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				if (thread != null) {
					thread.interrupt();
				}
				
				dispose();
			}
		});
		
		this.add(buttonOn);
		this.add(buttonOff);
		
		this.setVisible(true);
	}

	public static void main(String[] args) throws AWTException {
		@SuppressWarnings("unused")
		App app = new App();
	}

}
