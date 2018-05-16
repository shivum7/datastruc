package apps;

import java.io.IOException;

import apps.*;
import structures.*;

public class MSTDriver {
	public static void main(String[] args) throws IOException {
		Graph graph = new Graph("graph2.txt");
		
		graph.print();
		
		PartialTreeList test = MST.initialize(graph);
		
		for(PartialTree t:test) {
			System.out.println(t.toString());
		}
		System.out.println(MST.execute(test).toString());
		
	}
}
