package desktop.http;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.KeyEvent;

public class EventController extends Robot{
	private boolean altStatus;
	private static int firstDigitCharVal = 48;
	
	public EventController() throws AWTException{
		super();
		altStatus = false;
	}
	
	void run(String op){
		if(op == null){
			return;
		}else if(op.equalsIgnoreCase("next")){
			this.nextSlide();
		} else if(op.equalsIgnoreCase("prev")){
			this.prevSlide();
		} else if(op.equalsIgnoreCase("full")){
			this.fullScreen();
		}  else if(op.equalsIgnoreCase("switch")){
			this.escape();
		}
	}
	
	private void typeNumber(int n){
    	int curDigit;
    	if(n<0)
    		return;
    	do{
    		curDigit = n % 10; 
    		n /= 10;
    		this.keyPress(curDigit+firstDigitCharVal);
    		this.keyRelease(curDigit+firstDigitCharVal);
    	}while(n>0);
    }
    
    public void nextSlide() {
			this.keyPress(KeyEvent.VK_DOWN); // press A-key
	    	this.keyRelease(KeyEvent.VK_DOWN); // release A-key
    }
	
	public void prevSlide(){
		this.keyPress(KeyEvent.VK_UP); // press A-key
    	this.keyRelease(KeyEvent.VK_UP); // release A-key
	}

	public void moveMouse(int x, int y) {
		Point coord = MouseInfo.getPointerInfo().getLocation();
		System.out.print(coord);
		this.mouseMove(coord.x + x, coord.y + y);
		
	}

	public void alternateWindow(boolean hold){
		if(!altStatus){
			this.keyPress(KeyEvent.VK_ALT);
		}
		if(!hold){
			if(!altStatus){
				this.keyPress(KeyEvent.VK_TAB);
			}
			this.keyRelease(KeyEvent.VK_ALT);
			altStatus = false;
		}else{
			this.keyPress(KeyEvent.VK_TAB);
			altStatus = true;
		}
		this.keyRelease(KeyEvent.VK_TAB);
		
	}

	public void fullScreen(){
		this.keyPress(KeyEvent.VK_F5);
		this.keyRelease(KeyEvent.VK_F5);
		
	}

	public void escape(){
		this.keyPress(KeyEvent.VK_ESCAPE);
		this.keyRelease(KeyEvent.VK_ESCAPE);
		
	}

	public void setPage(int i){
		this.typeNumber(i);
		this.keyPress(KeyEvent.VK_ENTER);
		this.keyRelease(KeyEvent.VK_ENTER);
		
	}
}
