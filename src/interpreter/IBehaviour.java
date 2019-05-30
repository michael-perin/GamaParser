package intepreter;
import java.util.List;
import java.util.ListIterator;

/* Michael PÉRIN, Verimag / Univ. Grenoble Alpes, may 2019 */

public class IBehaviour {
	IState source ;
	List<ITransition> transitions ;
	
	IBehaviour(State source, List<Transition> transitions){
		this.source = source ; 
		this.transitions = transitions ;
	}
	
	IState step() {
		// - selectionne la première transition faisable
		// - lève une exception si aucune transition possible
		return // l'état cible de la transition choisie
	}
}
