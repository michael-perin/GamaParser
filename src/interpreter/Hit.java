package interpreter;

/* Michael PÉRIN, Verimag / Univ. Grenoble Alpes, may 2019 */

public class Hit extends IAction {
	Direction direction ;
	int power;
	
	Hit(Direction direction, Integer power){
		this.direction = direction ;
		this.power = power;
	}
	Hit(Direction direction){
		this.direction = direction ;
		this.power = 1 ; // valeur par défaut
	}
	Hit(){
		this.direction = Direction.Front ; // Front par défaut
		this.power = 1 ; // puissance par défaut
	}
	
	public void exec(Entity e){
		e.hit(this.direction, Math.max(e.power,this.power));
	}
}