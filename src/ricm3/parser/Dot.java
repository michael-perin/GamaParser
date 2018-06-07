package ricm3.parser;

public final class Dot {

		public static String graph(String name, String content) {
			String output = new String();
			output += "digraph " + name + "{" ;
			output += "\n" + "node[shape=box, fontsize=6, color=gray];" ;
			output += "\n" + content ;
			output += "\n}" ; 
			return output ;
		}
		public static String node_id(int i) {
			return "\"" + i + "\"";
		}
		public static String node_label(String label, String options) {
			return " [label=" + "\"" + label + "\"" + ", " + options + "]" ;
		}
		public static String value(int i, String label){
			return "\n" + node_id(i) + node_label(label, "shape=none, fontsize=10, fontcolor=blue") + ";" ;
		}
		public static String non_terminal_node(int i, String label){
			return "\n" + node_id(i) + node_label(label, "") + ";" ;
		}
		public static String edge(int i, int j) {
			return "\n" + node_id(i) + " -> " + node_id(j) + ";" ;
		}
		public static String terminal_edge(int id, String value){
			String output = new String();
			output += Dot.value(-id, value) ;
			output += Dot.edge(id, -id);
			return output;
		}
}
