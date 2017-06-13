package principal;

import java.awt.AWTException;
import java.awt.Component;
import java.awt.Graphics;
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
		rob.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	}
	
	public void leftClick(Point location) {
		leftClick(location.x, location.y);
	}
	
	public void leftClickComponent(Component target) {
		Point location = target.getLocationOnScreen();
		leftClick(location);
	}
	
	public void rightClick(int x, int y) {
		rob.mouseMove(x, y);
		rob.mousePress(InputEvent.BUTTON2_DOWN_MASK);
		rob.mouseRelease(InputEvent.BUTTON2_DOWN_MASK);
	}
	
	public void rightClick(Point location) {
		leftClick(location.x, location.y);
	}
	
	public void rightClickComponent(Component target) {
		Point location = target.getLocationOnScreen();
		leftClick(location);
	}
	
	public void moveToComponentAnimated(Component target, int pixelSpeed) {
		Point location = target.getLocationOnScreen();
		moveToAnimated(location, pixelSpeed);
	}
	
	public void moveToAnimated(Point location, int pixelSpeed) {
		moveToAnimated(location.x, location.y, pixelSpeed);
	}
	
	public void moveToAnimated(int x, int y, int pixelSpeed) {
		
		if(pixelSpeed <= 0)
			pixelSpeed = 1;
		
		int sx = 0;
		int sy = 0;
		moveTo(sx, sy);
		
		while(sx != x || sy != y)
		{
			sx += pixelSpeed;
			if(sx >= x)
				sx = x;
			sy += pixelSpeed;
			if(sy >= y)
				sy = y;
			moveTo(sx, sy);
			delay(20);
		}
	}
	
	public void moveTo(int x, int y) {
		rob.mouseMove(x, y);
	}
	
	public void moveTo(Point location) {
		moveTo(location.x, location.y);
	}
	
	public void moveToComponent(Component target) {
		Point location = target.getLocationOnScreen();
		moveTo(location);
	}
	
	public void wheelClick(int x, int y) {
		rob.mouseMove(x, y);
		rob.mousePress(InputEvent.BUTTON3_DOWN_MASK);
		rob.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
	}
	
	public void wheelClick(Point location) {
		leftClick(location.x, location.y);
	}
	
	public void wheelClickComponent(Component target) {
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
