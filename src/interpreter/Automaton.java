package interpreter;
import java.util.List;
import java.util.ListIterator;

/* Michael PÉRIN, Verimag / Univ. Grenoble Alpes, may 2019 */

public class Automaton {
	State current ;
	List<Behaviour> behaviours ;
	
	Automaton(State initial, List<Behaviour> behaviours){
		this.current = initial ;
		this.behaviours = behaviours ;
	}
	
	boolean step() {
		// - selectionne le comportement en fonction de l'état courant
		// - effectue une transition
		// - met à jour l'état courant
		// - gère l'exception "aucune transition possible"
		return false ; // true si une transition effectuée, false si aucune transition possible (=?= mort de l'automate ?) 
	}
}
