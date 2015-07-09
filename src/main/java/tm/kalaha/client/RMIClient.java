package tm.kalaha.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

import tm.kalaha.server.Mulde;
import tm.kalaha.server.Spielbrett;
import tm.kalaha.serverInterface.KalahaException;
import tm.kalaha.serverInterface.RMIClientInterface;
import tm.kalaha.serverInterface.ServerInterface;

public class RMIClient extends UnicastRemoteObject implements RMIClientInterface, Runnable {
	
	private static final long serialVersionUID = 6487865781693539839L;
	private static final String HOST ="localhost";
	private static final String BIND_NAME = "RMI-Server";
	
	private String spielerName;
	private Spielbrett spielbrett = new Spielbrett();
	
	public RMIClient (String n) throws RemoteException {
		spielerName = n;
		run();
	}
	
	public String getSpielerName() {
		return spielerName;
	}
	
	public void run() {
		ServerInterface server = null; 
		//Verbindung aufbauen 
		try {
			String bindURL = "rmi://"+HOST+"/" +BIND_NAME;
			server = (ServerInterface) Naming.lookup(bindURL);
		} catch (NotBoundException e) {
			System.out.println("Server ist nicht gebunden");
			System.out.println(e.getMessage());
		} catch (MalformedURLException e) {
			System.out.println("URL ungültig:");
			System.out.println(e.getMessage());
		} catch (RemoteException e) {
			System.out.println("Fehler während Kommunikation");
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println("Sonstiger Fehler"); 
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		
		try {
			server.anmelden(this);
			System.out.println("angemeldet");
//			server.muldeSpielen("marc", 2);
//			System.out.println("Neues Spiel");
//			server.neuesSpielStarten("marc");
//			while(true) {
//				
//			}
//			server.abmelden(this);
		} catch (KalahaException e) {
			System.out.println(e.getMessage());
		} catch (RemoteException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public Spielbrett getSpielbrett(){
		return spielbrett;
	}

	@Override
	public void spielbrettBekommen(Spielbrett spielbrett) throws RemoteException {
		this.spielbrett = spielbrett;
		brettAusgeben();
	}
	
	private void brettAusgeben() {
		System.out.println("SpielerA: "+ spielbrett.getSpielerA().getSpielerName());
		System.out.println("SpielerB: "+ spielbrett.getSpielerB().getSpielerName());
		Mulde[] mulden = spielbrett.getMulden();
		for(int i =0; i<12; i++) {
			System.out.print(mulden[i].getAnzahlSteine()+ " ");
			if(i == 5) {
				System.out.println("");
			}
		}
		System.out.println("");
	}
}
