package interpreter;

/* Michael PÉRIN, Verimag / Univ. Grenoble Alpes, may 2019 */

public class GotPower extends ICondition {
	GotPower(){}
	boolean eval(Entity e) {
		return (e.power > 0) ;
	}
}
