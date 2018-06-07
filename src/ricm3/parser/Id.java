package ricm3.parser;
/* Michael PÉRIN, Verimag / Univ. Grenoble Alpes, december 2017 */

public final class Id {
	static int counter = 10;
	
	public static int fresh() {
		int n = counter;
		counter++;
		return n;
	}
}
