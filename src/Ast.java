import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;


/* Michael PÉRIN, Verimag / Univ. Grenoble Alpes, june 2018
 *
 * Constructors of the Abstract Syntax Tree of Game Automata
 */

public class Ast {
	
	/* All this is only for the graphical .dot output of the Abstract Syntax Tree */
	public String kind ; /* the name of the non-terminal node */
	public int id = Id.fresh(); /* its unique id as a graph node */
	
	public String tree_edges() {
		return "undefined" ;
	}
	public String as_tree_son_of(Ast father) {
		return Dot.edge(father.id,this.id) + as_dot_tree();
	}
	public String as_tree_node(){
		return Dot.non_terminal_node(this.id,this.kind);
	}
	public String as_dot_tree() {
		return this.as_tree_node() + this.tree_edges() ;
	}	
	public String as_dot_automata() {
		return "undefined" ;
	}
}


class Expression extends Ast { }


class Terminal extends Ast {
	String value ;

	Terminal(String string){
		this.value = string ;
	}
	public String as_tree_son_of(Ast father) {
		return Dot.terminal_edge(father.id,value);
	}
}


class Constant extends Expression {
	
	Terminal value;
	
	Constant(String string) {
		this.kind = "Constant" ;
		this.value = new Terminal(string) ;
	}
	
	public String tree_edges() {
		return value.as_tree_son_of(this) ;
	}
}


class Variable extends Expression {
	
	Terminal name;
	
	Variable(String string) {
		this.kind = "Variable" ;
		this.name = new Terminal(string) ;
	}

	public String tree_edges(){
		return name.as_tree_son_of(this);
	}
}


class Direction extends Expression {
			
	Expression value;
	
	Direction(Expression expression) {
		this.kind = "Direction" ;
		this.value = expression ;
	}
	
	public String tree_edges(){
		return value.as_tree_son_of(this);
	}
}


class Entity extends Expression {
			
	Expression value;
	
	Entity(Expression expression) {
		this.kind = "Entity" ;
		this.value = expression ;
	}
	
	public String tree_edges(){
		return value.as_tree_son_of(this);
	}
}


class UnaryOp extends Expression {
	
	Terminal operator ;
	Expression operand ;

	UnaryOp(String operator, Expression operand) {
		this.kind = "UnaryOp" ;
		this.operator = new Terminal(operator) ;
		this.operand  = operand ;
	}
	
	public String tree_edges() { 
		return operator.as_tree_son_of(this) + operand.as_tree_son_of(this) ;
	}
}


class BinaryOp extends Expression {
	
	Terminal operator ;
	Expression left_operand ;
	Expression right_operand ;

	BinaryOp(Expression l, String operator, Expression r) {
		this.kind = "BinaryOp" ;
		this.operator = new Terminal(operator) ;
		this.left_operand  = l ;
		this.right_operand = r;
	}

	public String tree_edges() { 
		return left_operand.as_tree_son_of(this) + operator.as_tree_son_of(this) + right_operand.as_tree_son_of(this);
	}
/*	
 * class Interpreter {
 *    	bool eval(){ ... }
 * }
 * class Conjonction extends Interpreter {
 * 	  Interpreter left ; 
 *    Interpreter right ;
 * 	  bool eval(){
 * 	 	return left.eval() & right.eval() ;
 *    }
 * }
 * 
    Interpreter make() {
	  if (operator = "&")
		return new conjonction( left_operatand.make(), right_operand.make() ) ;
	  else ...	
	}
*/	
}


class FunCall extends Expression {

	Terminal name ;
	List<Expression> parameters ;
	
	FunCall(String name, List<Expression> parameters){ 
		this.kind = "FunCall" ;
		this.name = new Terminal(name) ;
		this.parameters = parameters ;
	}
	
	public String tree_edges() { 
		String output = new String();
		output += name.as_tree_son_of(this) ;
		ListIterator<Expression> Iter = this.parameters.listIterator();
		while(Iter.hasNext()){
			Expression expression = Iter.next();
			output += expression.as_tree_son_of(this);
		}
		return output ; 
	}
}


class Condition extends Ast {

	Expression expression ;
	
	Condition(Expression expression){
		this.kind = "Condition" ;
		this.expression = expression ;
	}
	
	public String tree_edges() {
		return expression.as_tree_son_of(this) ;
	}
}


class Action extends Ast {

	Expression expression ;
	
	Action(Expression expression){
		this.kind = "Action" ;
		this.expression = expression ;
	}
	
	public String tree_edges() {
		return expression.as_tree_son_of(this) ;
	}
}


class State extends Ast {
	
	Terminal name;
	
	State(String string) {
		this.kind = "State" ;
		this.name = new Terminal(string) ;
	}
	
	public String tree_edges() {
		return name.as_tree_son_of(this) ;
	}
}


class AI_Definitions extends Ast {
	
	List<Automaton> automata ;
	
	AI_Definitions(List<Automaton> list){
		this.kind = "AI_Definitions" ;
		this.automata = list ;
	}

	public String tree_edges(){
		String output = new String();
		ListIterator<Automaton> Iter = this.automata.listIterator();
		while(Iter.hasNext()){
			Automaton automaton = Iter.next();
			output += automaton.as_tree_son_of(this) ;
		}
		return output ;
	}
	
	public String as_dot_tree(){
		return Dot.graph("AST", this.as_tree_node() + this.tree_edges() );
	}
	
	public String as_dot_automata(){
		return Dot.graph("Automata", this.as_tree_node() );
	}
}


class Automaton extends Ast {
	
	Terminal name ;
	State entry ;
	List<Behaviour> behaviours ;
	
	Automaton(String name, State entry, List<Behaviour> behaviours){
		this.kind = "Automaton" ;
		this.name = new Terminal(name) ;
		this.entry = entry ;
		this.behaviours = behaviours ;
	}

	public String tree_edges() {
		String output = new String();
		output += name.as_tree_son_of(this) ;
		output += entry.as_tree_son_of(this) ;
		ListIterator<Behaviour> Iter = this.behaviours.listIterator();
		while(Iter.hasNext()){
			Behaviour behaviour = Iter.next() ;
			output += behaviour.as_tree_son_of(this) ;
		}
		return output;
	}
}


class Behaviour extends Ast {
	
	State source ;
	List<Transition> transitions ;

	Behaviour(State state, List<Transition> transitions){
		this.kind = "Behaviour" ;
		this.source = state ;
		this.transitions = transitions ;
	}

	public String tree_edges(){
		String output = new String();
		output += source.as_tree_son_of(this) ;
		ListIterator<Transition> Iter = this.transitions.listIterator();
		while(Iter.hasNext()){
			Transition transition = Iter.next();
			output += transition.as_tree_son_of(this) ;
		}
		return output;
	}
}


class Transition extends Ast {	

	Condition condition; 
	Action action ;
	State target ;
	
	Transition(Condition condition, Action action, State target){
		this.kind = "Transition" ;
		this.condition = condition ; 
		this.action = action ;
		this.target = target ;
	}
	
	public String tree_edges(){
		return condition.as_tree_son_of(this) + action.as_tree_son_of(this) + target.as_tree_son_of(this);
	}
}


//BOOLEAN EXPRESSION

/*

class BoolCst extends Expression {
	Boolean bool;

	BoolCst(String input_string){
		this.bool = (input_string.equals("True"));
	}

	public String toString() {
		return this.bool.toString();
	}
	public String toDot() { return toString(); }
}


class BoolExp extends Expression {
	Expression expr;
	
	BoolExp(Expression expr){
		this.expr = expr;
	}
	public String toString() {
		return this.expr.toString();
	}
	public String toDot() { return toString(); }
}


class Not extends Expression {
	Expression expr;
	
	Not(Expression expr){
		this.expr = expr;
	}
	public String toString() {
		return "not(" + this.expr.toString() + ")";
	}
	public String toDot() { return toString(); }
}


class Conjunction extends Expression{
	Expression conjunction;
	Conjunction(Expression e1, Expression e2){
		if (e1==null) this.conjunction = e2;
		if (e2==null) this.conjunction = e1;
		else this.conjunction = new BinaryOp(e1,"&&",e2);
	}
	public String toString(){
		return this.conjunction.toString();
	}
	public String toDot() { return toString(); }
}

*/