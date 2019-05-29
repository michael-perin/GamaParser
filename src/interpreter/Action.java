package interpreter;

/* Michael PÉRIN, Verimag / Univ. Grenoble Alpes, may 2019 */

public class Action {
	
	Action(){}
	... exec(Entity e){}
	
	
	public class Hit extends Action {
		Direction direction ;
		Integer power;
		
		Hit(Direction, Integer power){
			this.direction = direction ;
			this.power = power;
		}
		Hit(Direction direction){
			this.direction = direction ;
			this.power = 1 ; // valeur par défaut
		}
		Hit(){
			this.direction =  // Front par défaut
			this.power = 1 ; // puissance par défaut
		}
		
		... exec(Entity e){
			e.hit(this.direction, max(e.power,this.power));
		}
	}
	
	
	public class Move extends Action {
		Direction direction ;
		
		Move(Direction direction){
			this.direction = direction ;
		}
		Move(){
			this.direction = // Front par défaut
		}
		
		... exec(Entity e){
			e.move(this.direction) ;
		}
	}
	
}
