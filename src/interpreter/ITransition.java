package interpreter;

/* Michael PÉRIN, Verimag / Univ. Grenoble Alpes, may 2019 */

public class ITransition {
	ICondition condition ;
	IAction action ;
	IState target ;
	
	ITransition(ICondition condition, IAction action, IState target){
		this.condition = condition ;
		this.action = action ;
		this.target = target ;
	}
	
	boolean feasible(Entity e) {
		// teste si la condition de la transition est satisfaite
		return ... ;
	}
	
	IState exec(Entity e) {
		// execute l'action
		// return l'état cible de la transition 
		return ... ;
	}
}
