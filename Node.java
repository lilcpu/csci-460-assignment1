import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;


public class Node {

	public String name;
	public boolean visited;
	public int cost;
	public List<Node> successors = new ArrayList<Node>();

	public Node(String name) {
		this.name = name;
		this.visited = false;
	}

	public List<Node> expand() {
		Comparator<Node> comparator = new NodeNameComparator();
		PriorityQueue<Node> neighbors = new PriorityQueue<Node>(10, comparator);
		List<Node> alphabeticSuccessors = new ArrayList<Node>();
		if(successors.size() > 0) {
			for(Node nn : successors) {
				neighbors.offer(nn); 
			}
			while(!neighbors.isEmpty()) {
				Node alpha = neighbors.poll();
				alphabeticSuccessors.add(alpha);
			}
			return alphabeticSuccessors;
		}
		return null;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public boolean successorExists(Node n) {
		for(Node nn : successors) {
			if(nn.name.equals(n.name)) {
				return true;
			}
		}
		return false;
	}

	public void addSuccessor(Node n) {
		if(!successorExists(n)) {
			successors.add(n);
		}
	}

	public int numSuccessors() {
		return successors.size();
	}

	public void print() {
		System.out.println("NAME: "+this.name);
		for(Node n : successors) {
			System.out.print(n.name+ ", ");
		}
		System.out.println(" total: "+numSuccessors());
	}

	public void printSuccessors(){
		for(Node n : successors){
			System.out.print(n.name+" ");
		}
	}

	public class NodeNameComparator implements Comparator<Node> {
		@Override
		public int compare(Node o1, Node o2) {
			// TODO Auto-generated method stub
			if(o1.name.compareTo(o2.name) < 0) {
				return 1;
			}
			if(o1.name.compareTo(o2.name) > 0 ) {
				return -1;
			}
			return 0;
		}
	}
}
