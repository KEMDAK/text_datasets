import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.TreeSet;

public class Filter {

	private static class Pair implements Comparable<Pair> {
		String src, trg;

		public Pair(String src, String trg) {
			this.src = src;
			this.trg = trg;
		}

		@Override
		public int compareTo(Pair o) {
			// if(this.src.compareTo(o.src) != 0) {
			// 	return this.src.compareTo(o.src);
			// }
			return this.trg.compareTo(o.trg);
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader src = new BufferedReader(new FileReader("german_de_model.src"));
		BufferedReader trg = new BufferedReader(new FileReader("german_de_model.trg"));
		PrintWriter srcO = new PrintWriter("german_de_model1.src.set");
		PrintWriter trgO = new PrintWriter("german_de_model1.trg.set");

		TreeSet<Pair> set = new TreeSet<>();

		while(src.ready()) {
			Pair cur = new Pair(src.readLine(), trg.readLine());

			if(set.contains(cur))
				System.out.println("FUCK");

			set.add(cur);
		}

		Pair last = new Pair("-123214321", "-123214321") ;
		for (Pair pair : set) {
			if(!pair.trg.startsWith(last.trg)) {
				srcO.println(last.src);
				trgO.println(last.trg);
			}
			last = pair;
			// srcO.println(pair.src);
			// trgO.println(pair.trg);

		}

		srcO.println(last.src);
		trgO.println(last.trg);

		srcO.flush();
		trgO.flush();
		srcO.close();
		trgO.close();
	}
}
