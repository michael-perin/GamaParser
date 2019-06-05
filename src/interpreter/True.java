package interpreter;

/* Michael PÉRIN, Verimag / Univ. Grenoble Alpes, may 2019 */

public class True extends ICondition {
	True(){}
	boolean eval(Entity e) { return true; }
}