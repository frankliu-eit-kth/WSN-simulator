
public class FloodingNode extends Node {
	boolean routingEnd=false;
	
	public FloodingNode(int id, int x, int y) {
		super(id, x, y);
		// TODO Auto-generated constructor stub
	}

	
	protected void routingAlgorithm() {
		// TODO Auto-generated method stub
		for(Node n:this.getNeighbors()) {
			if(n.getIsSink()==true) {
				System.out.println("reach sink:"+n.toString());
				routingEnd=true;
				System.exit(1);
			}
			if(n.getOnPath()==false) {
				System.out.println("routing to "+ n.toString());
				n.setOnPath();
				n.routingAlgorithm();
			}
		}
	}

}
