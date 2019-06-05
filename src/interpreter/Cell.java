package interpreter;

/* Michael PÉRIN, Verimag / Univ. Grenoble Alpes, may 2019 */

public class Cell extends ICondition {
	Direction direction ;
	Kind kind ;
	double distance ;
	
	Cell(Direction direction, Kind kind, double distance){
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
		return e.is_Kind(this.kind, this.direction, this.distance, e.position, e.map) ;
	}
}