package desktop;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {

    private Client() {}

    public static void main(String[] args) {
   
	String host = (args.length < 1) ? null : args[0];
	String port = (args.length < 2) ? null : args[1];
	//Integer x = (args.length < 4) ? null : Integer.parseInt(args[2]);
	//int y = (args.length < 4) ? null : Integer.parseInt(args[3]);
	try {
		String regAddr = "//"+host+":"+port + "/" + "controller";
		//System.out.println(regAddr);
		//Hello stub = (Hello)Naming.lookup(regAddr);
	    Registry registry = LocateRegistry.getRegistry(host, Integer.parseInt(port));
	    KeyEventGenerator stub = (KeyEventGenerator) registry.lookup("controller");
	    //String response = stub.nextSlide();
	    
	    	//stub.mouveMouse(x, y);
	  /*for(int i=0;i<5;i++){
		  stub.alternateWindow(true);
		  Thread.sleep(1000);
	   }*/
//	  stub.alternateWindow(false);
//	  Thread.sleep(1000);
//	  stub.fullScreen();
//	  Thread.sleep(1000);
//	  stub.escape();
//	  Thread.sleep(1000);
	  stub.alternateWindow(false);
	  Thread.sleep(1000);
	  stub.fullScreen();
	  Thread.sleep(1000);
	  stub.setPage(11);
	    //System.out.println("response: " + response);
	} catch (Exception e) {
	    System.err.println("Client exception: " + e.toString());
	    e.printStackTrace();
	}
    }
}