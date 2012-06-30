package desktop;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface KeyEventGenerator extends Remote {
	void nextSlide() throws RemoteException;
    void prevSlide() throws RemoteException;
    void mouveMouse(int x, int y) throws RemoteException;
    void alternateWindow(boolean hold) throws RemoteException;
    void fullScreen() throws RemoteException;
    void escape() throws RemoteException;
    void setPage(int i) throws RemoteException;
}