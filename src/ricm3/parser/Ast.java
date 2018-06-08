package ricm3.parser;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/* Michael PÉRIN, Verimag / Univ. Grenoble Alpes, june 2018
 *
 * Constructors of the Abstract Syntax Tree of Game Automata
 */

public class Ast {

	// All this is only for the graphical .dot output of the Abstract Syntax Tree 

	public String kind; 	// the name of the non-terminal node 

	public int id = Id.fresh(); // a unique id used as a graph node 

	// AST as tree
	
	public String dot_id(){ 
		return Dot.node_id(this.id) ;
	}
	
	public String as_tree_son_of(Ast father) {
		return Dot.edge(father.dot_id(), this.dot_id()) + this.as_dot_tree() ;
	}
	
	public String as_dot_tree() {
		return this.as_tree_node() + this.tree_edges();
	}
	
	public String as_tree_node() {
		return Dot.declare_node(this.dot_id(), this.kind, "");
	}
	
	public String tree_edges() {
		return "TO BE DEFINED in each internal class";
	}

	// AST as automata in .dot format
	
	public String as_dot_automata() {
		return "undefined";
	}
	

	
	// AST as active automata (interpreter of transitions)
	
	public Object  make() {
		  return null; // TODO à définir dans la plupart des classes internes ci-dessous.
	}
	
	public static abstract class Expression extends Ast {}

	public static class Terminal extends Ast {
		String value;

		Terminal(String string) {
			this.kind = "Terminal" ;
			this.value = string;
		}

		public String toString() {
			return value ;
		}
		
		public String tree_edges(){
			String value_id = Dot.node_id( -this.id) ;
			return Dot.declare_node( value_id, value, "shape=none, fontsize=10, fontcolor=blue" ) + Dot.edge(this.dot_id(), value_id) ;
		}
	}

	public static class Constant extends Expression {

		Terminal value;

		Constant(String string) {
			this.kind = "Constant";
			this.value = new Terminal(string);
		}

		public String tree_edges() {
			return value.as_tree_son_of(this);
		}
	}

	public static class Variable extends Expression {

		Terminal name;

		Variable(String string) {
			this.kind = "Variable";
			this.name = new Terminal(string);
		}

		public String tree_edges() {
			return name.as_tree_son_of(this);
		}
	}

	public static class Key extends Expression {

		Terminal value;

		Key(String string) {
			this.kind = "Key";
			this.value = new Terminal(string);
		}

		public String tree_edges() {
			return value.as_tree_son_of(this);
		}
	}

	public static class Direction extends Expression {

		Expression value;

		Direction(Expression expression) {
			this.kind = "Direction";
			this.value = expression;
		}

		public String tree_edges() {
			return value.as_tree_son_of(this);
		}
	}

	public static class Entity extends Expression {

		Expression value;

		Entity(Expression expression) {
			this.kind = "Entity";
			this.value = expression;
		}

		public String tree_edges() {
			return value.as_tree_son_of(this);
		}
	}

	public static class UnaryOp extends Expression {

		Terminal operator;
		Expression operand;

		UnaryOp(String operator, Expression operand) {
			this.kind = "UnaryOp";
			this.operator = new Terminal(operator);
			this.operand = operand;
		}

		public String tree_edges() {
			return operator.as_tree_son_of(this) + operand.as_tree_son_of(this);
		}
	}

	public static class BinaryOp extends Expression {

		Terminal operator;
		Expression left_operand;
		Expression right_operand;

		BinaryOp(Expression l, String operator, Expression r) {
			this.kind = "BinaryOp";
			this.operator = new Terminal(operator);
			this.left_operand = l;
			this.right_operand = r;
		}

		public String tree_edges() {
			return left_operand.as_tree_son_of(this) + operator.as_tree_son_of(this)
					+ right_operand.as_tree_son_of(this);
		}
	}

	public static class FunCall extends Expression {

		Terminal name;
		List<Expression> parameters;

		FunCall(String name, List<Expression> parameters) {
			this.kind = "FunCall";
			this.name = new Terminal(name);
			this.parameters = parameters;
		}

		public String tree_edges() {
			String output = new String();
			output += name.as_tree_son_of(this);
			ListIterator<Expression> Iter = this.parameters.listIterator();
			while (Iter.hasNext()) {
				Expression expression = Iter.next();
				output += expression.as_tree_son_of(this);
			}
			return output;
		}
	}

	public static class Condition extends Ast {

		Expression expression;

		Condition(Expression expression) {
			this.kind = "Condition";
			this.expression = expression;
		}

		public String tree_edges() {
			return expression.as_tree_son_of(this);
		}
		
	}

	public static class Action extends Ast {

		Expression expression;

		Action(Expression expression) {
			this.kind = "Action";
			this.expression = expression;
		}

		public String tree_edges() {
			return expression.as_tree_son_of(this);
		}
	}

	public static class State extends Ast {

		Terminal name;

		State(String string) {
			this.kind = "State";
			this.name = new Terminal(string);
		}

		public String tree_edges() {
			return name.as_tree_son_of(this);
		}
		
		public String dot_id(Automaton automaton){ 
			return Dot.name( automaton.id + "." + name.toString() ) ;
		}
		
		public String as_node_of(Automaton automaton){ 
			return this.dot_id(automaton) + Dot.node_label(name.toString(), "shape=circle, fontsize=4") ;
		}
	}

	public static class AI_Definitions extends Ast {

		List<Automaton> automata;

		AI_Definitions(List<Automaton> list) {
			this.kind = "AI_Definitions";
			this.automata = list;
		}

		public String tree_edges() {
			String output = new String();
			ListIterator<Automaton> Iter = this.automata.listIterator();
			while (Iter.hasNext()) {
				Automaton automaton = Iter.next();
				output += automaton.as_tree_son_of(this);
			}
			return output;
		}

		public String as_dot_tree() {
			return Dot.graph("AST", this.as_tree_node() + this.tree_edges());
		}

		public String as_dot_automata() {
			return Dot.graph("Automata", this.as_tree_node());
		}
	}

	public static class Automaton extends Ast {

		Terminal name;
		State entry;
		List<Behaviour> behaviours;

		Automaton(String name, State entry, List<Behaviour> behaviours) {
			this.kind = "Automaton";
			this.name = new Terminal(name);
			this.entry = entry;
			this.behaviours = behaviours;
		}

		public String tree_edges() {
			String output = new String();
			output += name.as_tree_son_of(this);
			output += entry.as_tree_son_of(this);
			ListIterator<Behaviour> Iter = this.behaviours.listIterator();
			while (Iter.hasNext()) {
				Behaviour behaviour = Iter.next();
				output += behaviour.as_tree_son_of(this);
			}
			return output;
		}
		
	/* HERE 
		String state_to_instruction(int aut, State state, Behaviour behaviour){
			String output = new String();
			output += Dot.dot_edge( state.dot_id(aut) , behaviour.dot_id() ) ;
			return output ;
		}
		instruction_to_state()
		
		public String as_dot_automata() {
			String content = new String();
			output += Terminal.as_dot_node() ;
			ouput  += entry.as_state_of(this) ;
			return Dot.subgraph(this.id, content) ;
		}
		*/
	}

	public static class Behaviour extends Ast {

		State source;
		List<Transition> transitions;

		Behaviour(State state, List<Transition> transitions) {
			this.kind = "Behaviour";
			this.source = state;
			this.transitions = transitions;
		}

		public String tree_edges() {
			String output = new String();
			output += source.as_tree_son_of(this);
			ListIterator<Transition> Iter = this.transitions.listIterator();
			while (Iter.hasNext()) {
				Transition transition = Iter.next();
				output += transition.as_tree_son_of(this);
			}
			return output;
		}
	}

	public static class Transition extends Ast {

		Condition condition;
		Action action;
		State target;

		Transition(Condition condition, Action action, State target) {
			this.kind = "Transition";
			this.condition = condition;
			this.action = action;
			this.target = target;
		}

		public String tree_edges() {
			return condition.as_tree_son_of(this) + action.as_tree_son_of(this) + target.as_tree_son_of(this);
		}
	}
}
