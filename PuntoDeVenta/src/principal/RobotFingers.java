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
	
	public void leftClickComponentCenter(Component target) {
		Point location = target.getLocationOnScreen();
		location.setLocation(location.x + target.getWidth() / 2, location.y + target.getHeight() / 2);
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
	
	public void rightClickComponentCenter(Component target) {
		Point location = target.getLocationOnScreen();
		location.setLocation(location.x + target.getWidth() / 2, location.y + target.getHeight() / 2);
		leftClick(location);
	}
	
	public void moveToComponentAnimated(Component target, int pixelSpeed) {
		Point location = target.getLocationOnScreen();
		moveToAnimated(location, pixelSpeed);
	}
	
	public void moveToComponentCenterAnimated(Component target, int pixelSpeed) {
		Point location = target.getLocationOnScreen();
		location.setLocation(location.x + target.getWidth() / 2, location.y + target.getHeight() / 2);
		moveToAnimated(location, pixelSpeed);
	}
	
	public void moveToAnimated(Point location, int pixelSpeed) {
		moveToAnimated(location.x, location.y, pixelSpeed);
	}
	
	public void moveToAnimated(int x, int y, int pixelSpeed) {
		
		if(pixelSpeed <= 0)
			pixelSpeed = 1;
		
		Point s = MouseInfo.getPointerInfo().getLocation();
		int sx = s.x;
		int sy = s.y;
		boolean xl = sx < x;
		boolean yl = sy < y;
		
		while(sx != x || sy != y)
		{
			if(xl) {
				sx += pixelSpeed;
				if(sx >= x)
					sx = x;
			} else {
				sx -= pixelSpeed;
				if(sx <= x)
					sx = x;
			}
			
			if(yl) {
				sy += pixelSpeed;
				if(sy >= y)
					sy = y;
				moveTo(sx, sy);
			} else {
				sy -= pixelSpeed;
				if(sy <= y)
					sy = y;
				moveTo(sx, sy);
			}

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
	
	public void moveToComponentCenter(Component target) {
		Point location = target.getLocationOnScreen();
		location.setLocation(location.x + target.getWidth() / 2, location.y + target.getHeight() / 2);
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
	
	public void wheelClickComponentCenter(Component target) {
		Point location = target.getLocationOnScreen();
		location.setLocation(location.x + target.getWidth() / 2, location.y + target.getHeight() / 2);
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
