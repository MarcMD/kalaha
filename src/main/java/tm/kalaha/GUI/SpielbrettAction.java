package tm.kalaha.GUI;

import tm.kalaha.server.Spielbrett;

/**
 * Dieses Interface wird benutzt, um den Client mit (bspw.) der GUI zu
 * verbinden. Der Client ruft die Methode spielbrettVeraendert() wenn sich das
 * Spielbrett verändert hat.
 * 
 * @author marc
 *
 */
public interface SpielbrettAction {

	public void spielbrettVeraendert(Spielbrett spielbrett);

	public void chatVeraendert(String nachricht);

}
