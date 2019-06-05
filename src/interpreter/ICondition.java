package interpreter;

/* Michael PÉRIN, Verimag / Univ. Grenoble Alpes, may 2019 */

public class ICondition {

	ICondition(){}
	boolean eval(Entity e) { return true; } // à redéfinir dans chaque sous-classe

	public class True extends Condition {
		True(){}
		boolean eval(Entity e) { return true; }
	}

	public class Cell extends Condition {
		Direction direction ;
		Kind kind ;
		Distance distance ;
		
		Cell(Direction direction, Kind kind, Distance distance){
			this.direction = direction ;
			this.kind = kind ;
			this.distance = distance ;
		}
		
		Cell(Direction direction, Kind kind){
			this.direction = direction ;
			this.kind = kind ;
			this.distance = 1 ;
		}
		
		boolean eval(Entity e) { 
			return is_Kind(this.kind, this.direction, this.distance, e.position, e.map) ;
		}
	}
	
	public class GotPower extends Condition {
		GotPower(){}
		boolean eval(Entity e) {
			return (e.power > 0) ;
		}
	}

}