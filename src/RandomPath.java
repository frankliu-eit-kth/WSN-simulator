import java.util.ArrayList;
import java.util.LinkedList;

public class RandomPath {
	
	private LinkedList<Node> nodes;
	
	public RandomPath(int id, int pathLength,MultiNode startNode) {
		// TODO Auto-generated constructor stub
		
		this.nodes=new LinkedList<Node>();
		
		int currentLength=0;
		
		MultiNode currentNode=startNode;
		
		while(currentLength<=pathLength) {
			nodes.add(currentNode);
			spreadPath(currentNode);
			if(currentNode==null) {
				break;
			}
		}
		
		
	}
	
	public void spreadPath(MultiNode currentNode) {
		
		MultiNode neighbor=(MultiNode)currentNode.getRandomNeighbor();
		if(neighbor==null) {
			return;
		}
		else {
			currentNode=neighbor;
		}
		
	}
	
	
}
