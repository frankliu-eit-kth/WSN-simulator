package BackTraced;

import java.util.ArrayList;



public class Simulator {
	private ArrayList<BackTracedNode> nodes;
	private ArrayList<Randompath> randomPaths;
	private final int xLength=20;
	private final int yLength=20;
	private final int numNodes=64;
	private final int numPaths=5;
	private final int maxLengthRandomPath=5;
	private static int routingStep=1;
	private final double radioRange=5;	
	private BackTracedNode source;
	private BackTracedNode sink;
	private Plane plane;

	public Simulator() {
		// TODO Auto-generated constructor stub
		super();
		this.nodes=new ArrayList<BackTracedNode>();
		this.randomPaths=new ArrayList<Randompath>();
		initNodes();
		initRandomPaths();
		this.source=nodes.get(GenerateRandomInt.randomInt(0, numNodes-1));
		this.sink=nodes.get(GenerateRandomInt.randomInt(0, numNodes-1));
		this.sink.setSink(true);
		this.plane=new Plane(xLength,yLength,nodes,randomPaths,source,sink);
	}
	
	private void initNodes() {
		//generate new nodes
		for(int i=1;i<=this.numNodes;) {
			int nodeX=GenerateRandomInt.randomInt(0, xLength-1);
			int nodeY=GenerateRandomInt.randomInt(0, yLength-1);
			BackTracedNode newBackTracedNode=new BackTracedNode(i,nodeX,nodeY);
			if(nodes.contains(newBackTracedNode)) {
				continue;
			}else {
				nodes.add(newBackTracedNode);
				i++;
			}
		}
		for(BackTracedNode n:nodes) {
			n.findNeighbors(nodes, radioRange);
		}
		
	}
	
	private void initRandomPaths() {
		for(int i=0;i<numPaths;i++) {
			
			//generate one ramdom path
			int pathLength=GenerateRandomInt.randomInt(1, maxLengthRandomPath);
			Randompath newPath=new Randompath();
			
			
			//generate a path with length pathLength
			BackTracedNode startNode=null;
			while(startNode==null) {
				BackTracedNode n=nodes.get(GenerateRandomInt.randomInt(0, numNodes-1));
				if(n.getNextRandomPathNode()==null&& n.getFormerRamdomPathNode()==null) {
					startNode=n;
				}
			}
			
			newPath.addNode(startNode);
			BackTracedNode currentNode=startNode;
			
			while(newPath.getPath().size()<pathLength) {
				BackTracedNode next=currentNode.getRandomNeighbor();
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
		
		
		BackTracedNode currentBackTracedNode=this.source;
		System.out.println("start from "+currentBackTracedNode.toString());
		BackTracedNode nextNode=currentBackTracedNode.getRandomNeighbor();
		
		while(!nextNode.isSink()) {
			if(!nextNode.isOnMessagePath()) {
				
				currentBackTracedNode.setOnMessagePath(true);
				plane.update(currentBackTracedNode);
				
				if(nextNode.getNextRandomPathNode()==null){
					
					nextNode.setPreviousNode(currentBackTracedNode);
					
					currentBackTracedNode=nextNode;
					
					nextNode=nextNode.getRandomNeighbor();
				}
				else {
					
					nextNode.setPreviousNode(currentBackTracedNode);
					
					currentBackTracedNode=nextNode;
					
					nextNode=nextNode.getNextRandomPathNode();
				}
				System.out.println("step "+(routingStep++)+" "+currentBackTracedNode.toString());
				continue;
			}
			System.out.println();
			nextNode=currentBackTracedNode.getRandomNeighbor();
			System.out.println("current: "+currentBackTracedNode.toString());
			System.out.println("next: "+nextNode.toString());
			System.out.println("previous: "+currentBackTracedNode.getPreviousNode().toString());
			
			//only difference of the multi and back traced
			if(currentBackTracedNode.checkAvailableNeighbor()==false&&nextNode.isSink()==false) {
				
				System.out.println(currentBackTracedNode.checkAvailableNeighbor());
				System.out.println(nextNode.isSink());
				System.out.println("routing get stuck");
				
				
				BackTracedNode previousNode=currentBackTracedNode.getPreviousNode();
				
				
				System.out.println("back trace to"+previousNode.toString());
				
				
				currentBackTracedNode.setOnMessagePath(true);
				plane.update(currentBackTracedNode);
				currentBackTracedNode=previousNode;
				nextNode=currentBackTracedNode.getRandomNeighbor();
			}
		}
		System.out.println("reach sink"+ nextNode.toString());
		System.out.println();
	}

}
