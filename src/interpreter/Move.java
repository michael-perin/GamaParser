package interpreter;

/* Michael PÉRIN, Verimag / Univ. Grenoble Alpes, may 2019 */

public class Move extends IAction {
	Direction direction ;
	
	Move(Direction direction){
		this.direction = direction ;
	}
	Move(){
		this.direction = Direction.Front ; // Front par défaut
	}
	
	void exec(Entity e){
		e.move(this.direction) ;
	}
}
