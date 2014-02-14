import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class Main {
	static SearchGraph sg;
	static GraphConstructor gc = new GraphConstructor("city-edges.txt");
	static String ROOTID = "Alexandria";
	static String GOALID = "Luxor";

	public static void main(String [] args) throws IOException {
		sg = gc.generateGraph();
		LinkedList<Node> solution = search(sg, ROOTID);
		printSolution(solution);
	}

	public static LinkedList<Node> search(SearchGraph ss, String rootId) {
		Searcher sq = new Searcher();
		Node root = ss.getNode(rootId);
		sq.queueRoot(root);
		root.visited = true;
		while(!sq.empty()) {
			
			Node next = sq.queue.getFirst();
			next.visited = true;
			sq.pushStack(next);
			System.out.println("CHOSEN "+next.name);
			
			if(next.name.equals(GOALID)) {
				sq.pushStack(next);
				break;
			}
			
			//sq.queueDFS(next.expand());
			sq.queueBFS(next.expand());
		}
		return sq.solutionStack;
	}
	
	public static void printSolution(LinkedList<Node> solution) {
		for(int i = solution.size()-1; i >=0 ; i--) {
			System.out.println(solution.get(i).name);
		}
	}
}
