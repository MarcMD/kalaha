package tm.kalaha.serverInterface;

/**
 * KalahaException wird benutzt, um Fehler im Spiel abzufangen. 
 * @author marc
 *
 */
public class KalahaException extends Exception {

	private static final long serialVersionUID = 5949469468624791139L;

	public KalahaException(String msg) {
		super(msg);
	}
}
