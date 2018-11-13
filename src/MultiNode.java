import java.util.ArrayList;

public class MultiNode extends Node {
	
	public MultiNode(int id, int x, int y) {
		super(id, x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void routingAlgorithm() {
		// TODO Auto-generated method stub
		MultiNode nextNeighbor=(MultiNode)this.getNeighbors().get(GenerateRandomInt.randomInt(0, this.getNeighbors().size()-1));
		if(nextNeighbor.getIsSink()==false) {
			if(nextNeighbor.getOnPath()==false) {
				if(nextNeighbor.getRandomPath()==0) {
					nextNeighbor.setOnPath();
					nextNeighbor.routingAlgorithm();
				}
				else {
					nextNeighbor.setOnPath();
					followPath(nextNeighbor);
				}
			}
		}
		System.out.println("reach sink "+nextNeighbor);
	}
	
	private int followPath(RandomPath randomPath, MultiNode currentNode) {
		int numPath=currentNode.getRandomPath();
		ArrayList<Node> neighbors=currentNode.getNeighbors();
		for(Node n:neighbors) {
			
		}
	}

	
	
}
