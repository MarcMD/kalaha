package tm.kalaha.client;

public class RMIRun {
	
	public static void main(String[] args) {
//		if(args.length != 1) {
//			System.out.println("Aufruf: RMIChat Marc");
//			System.exit(1);
//		}
		
		try {
			System.out.println("Erstelle Thread ...");
			Thread t = new Thread (new RMIClient("tanja"));
			t.start();
			System.out.println("Thread gestartet");
			//warten bis der Thread zu Ende gelaufen ist
			t.join();
			System.exit(0);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
