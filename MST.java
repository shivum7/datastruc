package apps;

import structures.*;
import java.util.ArrayList;

public class MST {
	
	/**
	 * Initializes the algorithm by building single-vertex partial trees
	 * 
	 * @param graph Graph for which the MST is to be found
	 * @return The initial partial tree list
	 */
	public static PartialTreeList initialize(Graph graph) {
		PartialTreeList tr = new PartialTreeList();
		Vertex[] vert = graph.vertices;//array of type vertex of all the vertices
		//traverse through the vertices
		for(int i = 0; i<vert.length; i++) {
			PartialTree temp = new PartialTree(vert[i]);
			
			//with the given vertex, create an ARC and place that in a heap
			
			for (Vertex.Neighbor n = vert[i].neighbors; n != null; n = n.next) {
				int weight = n.weight;
				Vertex v = n.vertex;
				//creation of the Arc
				PartialTree.Arc arc = new PartialTree.Arc(vert[i], v, weight);
				//insert that arc into the PartialTree
				temp.getArcs().insert(arc);
			}
			tr.append(temp);
		}
		
		return tr;
	}

	/**
	 * Executes the algorithm on a graph, starting with the initial partial tree list
	 * 
	 * @param ptlist Initial partial tree list
	 * @return Array list of all arcs that are in the MST - sequence of arcs is irrelevant
	 */
	public static ArrayList<PartialTree.Arc> execute(PartialTreeList ptlist) {
		
		ArrayList<PartialTree.Arc> finaltree = new ArrayList<PartialTree.Arc>();
		
		//loop for while the pt list is greater than 1
		while(ptlist.size() > 1) {
			//perform the algorithm
			PartialTree ptx = ptlist.remove();//removes 1st tree
			MinHeap<PartialTree.Arc> pqx = ptx.getArcs();
			//minimum weighted arc from minheap
			PartialTree.Arc min = pqx.deleteMin();
			
			while (min != null) {
				//take out first 2 verices
				Vertex v1 = min.v1;
				Vertex v2 = min.v2;
				
				//have to perform the check if the v1 or v2 are in other partial trees
				PartialTree contains = ptlist.removeTreeContaining(v1);
				if (contains == null) {
					contains = ptlist.removeTreeContaining(v2);
				} 
				
				if (contains != null) {
					//perform the merge function
					ptx.merge(contains);
					finaltree.add(min);
					ptlist.append(ptx);
					
					//break after the merge
					break;
				} else {
					
				}
				
				min = pqx.deleteMin();
			}
			
		}
		
		/* COMPLETE THIS METHOD */

		return finaltree;
	}
}
