package tm.kalaha.server;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

import tm.kalaha.serverInterface.Schnittstelle;


public class RMIServer extends UnicastRemoteObject implements Schnittstelle {

	private static final long serialVersionUID = 1L;
	private static final String HOST = "localhost";
	private static final String SERVICE_NAME = "RMI-Server";
	
	public RMIServer() throws RemoteException {
		String bindURL = null;
		try {
			bindURL = "rmi://" +HOST + "/" + SERVICE_NAME;
			LocateRegistry.createRegistry(1099);
			Naming.rebind(bindURL, this);
			System.out.println("RMI-Server gebunden unter dem Namen: " +SERVICE_NAME);
			System.out.println("RMI-Server ist bereit ...");
		} catch (MalformedURLException e) {
			System.out.println("Ungültige URL: " +bindURL);
			System.out.println(e.getMessage());
			System.exit(1);
		}
	}
	

	public static void main(String[] args) {
		try {
			new RMIServer();
		} catch (RemoteException e) {
			System.out.println("Fehler während der Erzeugung des Server-Objektes");
			System.out.println(e.getMessage());
			e.printStackTrace();
			System.exit(1);
		}
	}

	public void setString(String s) throws RemoteException {
		System.out.println("nachricht vom Client erhalten " +s);
	}
	
	@Override
	public String verdoppeln(String str) throws RemoteException {
		return str+" "+str;
	}

	@Override
	public int verdoppeln(int i) throws RemoteException {
		return 2*i;
	}

}
