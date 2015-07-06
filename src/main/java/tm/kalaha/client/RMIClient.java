package tm.kalaha.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import tm.kalaha.serverInterface.Schnittstelle;

import java.rmi.Remote;

public class RMIClient {
	
	private static final String HOST ="localhost";
	private static final String BIND_NAME = "RMI-Server";
	
	public static void main (String[] args) {
		String bindURL ="rmi://"+HOST+"/" +BIND_NAME;
		try {
			Schnittstelle server = (Schnittstelle) Naming.lookup(bindURL);
			System.out.println("Remote-Referenz erfolgreich erhalten.");
			System.out.println("Server ist gebunden an: "+bindURL);
			//setString des Server Objektes aufrufen
			server.setString("HalloServer");
			System.out.println("Methode setString() des Servers aufgerufen");
			System.out.println("Methode verdoppeln wird aufgerufen:");
			System.out.println(server.verdoppeln("HalloWelt"));
			System.out.println("Methode verdoppeln (int) wird aufgerufen");
			System.out.println(server.verdoppeln(21));
		} catch (MalformedURLException e) {
			System.out.println("URL ungültig:");
			System.out.println(e.getMessage());
		} catch (RemoteException e) {
			System.out.println("Fehler während Kommunikation");
			System.out.println(e.getMessage());
		} catch (NotBoundException e) {
			System.out.println("Server ist nicht gebunden");
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		catch (Exception e) {
			System.out.println("Sonstiger Fehler"); 
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
	}

}
