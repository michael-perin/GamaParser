package ricm3.parser;
import java.util.List;
import java.util.ListIterator;

public class Automaton {
	State current ;
	List<Behaviour> behaviours ;
	
	boolean step() {
		// - selectionne le comportement en fonction de l'état courant
		// - effectue une transition
		// - met à jour l'état courant
		// - gère l'exception "aucune transition possible"
		return false ; // true si une transition effectuée, false si aucune transition possible (=?= mort de l'automate ?) 
	}
}
