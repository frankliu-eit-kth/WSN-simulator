package MultiRoutingRandomWalk;

import java.util.ArrayList;

public class Simulator {
	private ArrayList<Node> nodes;
	private ArrayList<Randompath> randomPaths;
	private final int xLength=10;
	private final int yLength=10;
	private final int numNodes=64;
	private final int numPaths=5;
	private final int maxLengthRandomPath=5;
	private static int routingStep=1;
	private final double radioRange=5;	
	private Node source;
	private Node sink;

	public Simulator() {
		// TODO Auto-generated constructor stub
		super();
		this.nodes=new ArrayList<Node>();
		this.randomPaths=new ArrayList<Randompath>();
		initNodes();
		initRandomPaths();
		this.source=nodes.get(GenerateRandomInt.randomInt(0, numNodes-1));
		this.sink=nodes.get(GenerateRandomInt.randomInt(0, numNodes-1));
		this.sink.setSink(true);
	}
	
	private void initNodes() {
		//generate new nodes
		for(int i=1;i<=this.numNodes;) {
			int nodeX=GenerateRandomInt.randomInt(0, xLength);
			int nodeY=GenerateRandomInt.randomInt(0, yLength);
			Node newNode=new Node(i,nodeX,nodeY);
			if(nodes.contains(newNode)) {
				continue;
			}else {
				nodes.add(newNode);
				i++;
			}
		}
		for(Node n:nodes) {
			n.findNeighbors(nodes, radioRange);
		}
		
	}
	
	private void initRandomPaths() {
		for(int i=0;i<numPaths;i++) {
			
			//generate one ramdom path
			int pathLength=GenerateRandomInt.randomInt(1, maxLengthRandomPath);
			Randompath newPath=new Randompath();
			
			
			//generate a path with length pathLength
			Node startNode=null;
			while(startNode==null) {
				Node n=nodes.get(GenerateRandomInt.randomInt(0, numNodes-1));
				if(n.getNextRandomPathNode()==null&& n.getFormerRamdomPathNode()==null) {
					startNode=n;
				}
			}
			
			newPath.addNode(startNode);
			Node currentNode=startNode;
			
			while(newPath.getPath().size()<pathLength) {
				Node next=currentNode.getRandomNeighbor();
				if(next==null) {
					break;
				}
				if(next.getFormerRamdomPathNode()==null && next.getNextRandomPathNode()==null) {
					newPath.addNode(next);
					currentNode=next;
				}
			}
			randomPaths.add(newPath);
		}
	}
	
	
	public void routing() {
		
		if(source.isSink()) {
			System.out.println("reach sink"+source.toString());
		    return;
		}
		
		//this.source.setOnMessagePath(true);
		Node currentNode=this.source;
		System.out.println("start from "+currentNode.toString());
		Node nextNode=currentNode.getRandomNeighbor();
		
		while(!nextNode.isSink()) {
			if(!nextNode.isOnMessagePath()) {
				currentNode.setOnMessagePath(true);
				//System.out.println("step "+(routingStep++) +" "+ currentNode.toString());
				//currentNode.setOnMessagePath(true);
				if(nextNode.getNextRandomPathNode()==null){
					currentNode=nextNode;
					nextNode=nextNode.getRandomNeighbor();
				}
				else {
					currentNode=nextNode;
					nextNode=nextNode.getNextRandomPathNode();
				}
				System.out.println("step "+(routingStep++)+" "+currentNode.toString());
				continue;
			}
			nextNode=currentNode.getRandomNeighbor();
			
		}
		System.out.println("reach sink"+ nextNode.toString());
	}

}
