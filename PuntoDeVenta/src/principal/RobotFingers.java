package principal;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JComponent;

public class RobotFingers {
	private Robot rob;
	public RobotFingers()
	{
		super();
		try {
			rob = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void leftClick(int x, int y) {
		rob.mouseMove(x, y);
		rob.mousePress(InputEvent.BUTTON1_DOWN_MASK);
	}
	
	public void leftClick(Point location) {
		leftClick(location.x, location.y);
	}
	
	public void leftClickComponent(JComponent target) {
		Point location = target.getLocationOnScreen();
		leftClick(location);
	}
	
	public void rightClick(int x, int y) {
		rob.mouseMove(x, y);
		rob.mousePress(InputEvent.BUTTON2_DOWN_MASK);
	}
	
	public void rightClick(Point location) {
		leftClick(location.x, location.y);
	}
	
	public void rightClickComponent(JComponent target) {
		Point location = target.getLocationOnScreen();
		leftClick(location);
	}
	
	public void wheelClick(int x, int y) {
		rob.mouseMove(x, y);
		rob.mousePress(InputEvent.BUTTON3_DOWN_MASK);
	}
	
	public void wheelClick(Point location) {
		leftClick(location.x, location.y);
	}
	
	public void wheelClickComponent(JComponent target) {
		Point location = target.getLocationOnScreen();
		leftClick(location);
	}
	
	public void writeString(String text) {
		for (int i = 0; i < text.length(); i++) {
	        char c = text.charAt(i);
	        if (Character.isUpperCase(c)) {
	            rob.keyPress(KeyEvent.VK_SHIFT);
	        }
	        rob.keyPress(Character.toUpperCase(c));
	        rob.keyRelease(Character.toUpperCase(c));

	        if (Character.isUpperCase(c)) {
	            rob.keyRelease(KeyEvent.VK_SHIFT);
	        }
	    }
	}
	
	public void delay(int milliseconds) {
		rob.delay(milliseconds);
	}
	
	public void setDelayInterval(int delay) {
		rob.setAutoDelay(delay);
	}
}
