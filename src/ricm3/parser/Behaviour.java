package ricm3.parser;
import java.util.List;
import java.util.ListIterator;

public class Behaviour {
	State source ;
	List<Transition> transitions ;
	
	Behaviour(State source, List<Transition> transitions){
		this.source = source ; 
		this.transitions = transitions ;
	}
	
	State step() {
		// - selectionne la première transition faisable
		// - lève une exception si aucune transition possible
		return // l'état cible de la transition choisie
	}
}
