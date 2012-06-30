package desktop;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
	
public class Server extends Robot implements KeyEventGenerator {

	private boolean altStatus;
	private static int firstDigitCharVal = 48;
	
    public Server() throws AWTException {
    	super();
    	altStatus = false;
		
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
	
public static List<String> fun() throws SocketException{
	List<String> ips = new ArrayList<String>();
	for (
		    final Enumeration< NetworkInterface > interfaces =
		        NetworkInterface.getNetworkInterfaces( );
		    interfaces.hasMoreElements( );
		)
		{
		    final NetworkInterface cur = interfaces.nextElement( );

		    if ( cur.isLoopback( ) || cur.getName( ).contains("ham")) continue;

		    for ( final InterfaceAddress addr : cur.getInterfaceAddresses( ) )
		    {
		        final InetAddress inet_addr = addr.getAddress( );

		        if ( inet_addr instanceof Inet4Address )
		        	ips.add(inet_addr.getHostAddress( ));
		    }
		}
	return ips;
}   
    public static void main(String args[]) {
	
	try {
		String ip = fun().get(0);
		System.setProperty( "java.rmi.servethis.hostname", ip ); 
		LocateRegistry.createRegistry(1078);
	    Server obj = new Server();
	    KeyEventGenerator stub = (KeyEventGenerator) UnicastRemoteObject.exportObject(obj, 0);

	    // Bind the remote object's stub in the registry
	    Registry registry = LocateRegistry.getRegistry(1078);
	    registry.bind("controller", stub);

	    System.err.println("Server ready");
	} catch (Exception e) {
	    System.err.println("Server exception: " + e.toString());
	    e.printStackTrace();
	}
    }

	@Override
	public void prevSlide() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouveMouse(int x, int y) {
		Point coord = MouseInfo.getPointerInfo().getLocation();
		System.out.print(coord);
		this.mouseMove(coord.x + x, coord.y + y);
		
		
		
	}

	@Override
	public void alternateWindow(boolean hold) throws RemoteException {
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

	@Override
	public void fullScreen() throws RemoteException {
		this.keyPress(KeyEvent.VK_F5);
		this.keyRelease(KeyEvent.VK_F5);
		
	}

	@Override
	public void escape() throws RemoteException {
		this.keyPress(KeyEvent.VK_ESCAPE);
		this.keyRelease(KeyEvent.VK_ESCAPE);
		
	}

	@Override
	public void setPage(int i) throws RemoteException {
		this.typeNumber(i);
		this.keyPress(KeyEvent.VK_ENTER);
		this.keyRelease(KeyEvent.VK_ENTER);
		
	}
}