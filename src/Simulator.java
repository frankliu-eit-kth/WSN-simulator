import java.util.ArrayList;

public class Simulator {
	private int planeX=100;
	private int planeY=100;
	
	private CoordinatePlane plane;
	private ArrayList<Node> nodes;
	private Node source;
	private Node sink;
	
	public Simulator() {
		this.plane=new CoordinatePlane(planeX, planeY);
		this.nodes=new ArrayList<Node>();
		this.source=null;
		this.sink=null;
	}
	
	private void init(int nodeNumber,int radioRange) {
		for(int i=1;i<=nodeNumber;i++) {
			int nodeX=GenerateRandomInt.randomInt(0, planeX);
			int nodeY=GenerateRandomInt.randomInt(0, planeY);
			FloodingNode newNode=new FloodingNode(i,nodeX,nodeY);
			nodes.add(newNode);
		}
		for(Node n:nodes) {
			n.findNeighbors(nodes, radioRange);
		}
		this.source=(FloodingNode)nodes.get(GenerateRandomInt.randomInt(1,nodeNumber)-1);
		this.source.setOnPath();
		this.sink=(FloodingNode)nodes.get(GenerateRandomInt.randomInt(1,nodeNumber)-1);
		while(source.equals(sink)) {
			sink=(FloodingNode)nodes.get(GenerateRandomInt.randomInt(1,nodeNumber)-1);
		}
		sink.setSink(true);
	}
	
	public void startSimulation() {
		this.source.routingAlgorithm();
	}
	public static void main(String args[]) {
		Simulator simulator=new Simulator();
		simulator.init(100,20);
		simulator.startSimulation();
	}
}
