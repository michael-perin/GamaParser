package ricm3.parser;

public class Transition {
	Condition condition ;
	Action action ;
	State target ;
	
	boolean feasible(Entity e) {
		// teste si la condition de la transition est satisfaite
		return ... ;
	}
	
	State exec(Entity e) {
		// execute l'action
		// return l'état cible de la transition 
		return ... ;
	}
}
