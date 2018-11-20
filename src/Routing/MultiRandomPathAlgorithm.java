package Routing;

import java.util.ArrayList;

import Model.Node;
import Model.Plane;
import Model.Randompath;
import Util.GenerateRandomInt;

public class MultiRandomPathAlgorithm {
	//private Plane plane;
	private int reachSink=0;

	public MultiRandomPathAlgorithm() {
		super();
	}
	
	public ArrayList<Node> routing(ArrayList<Node> nodes, Node source,Node sink) {
		ArrayList<Node> steps=new ArrayList<Node>();
		if(source.isSink()) {
			steps.add(source);
			this.reachSink=1;
			System.out.println("reach sink"+source.toString());
		    return steps;
		}
		
		
		Node currentBackTracedNode=source;
		System.out.println("start from "+currentBackTracedNode.toString());
		Node nextNode=currentBackTracedNode.getRandomNeighbor();
		if(nextNode==null) {
			return steps;
		}
		
		while(!nextNode.isSink()) {
			if(!nextNode.isOnMessagePath()) {
				
				currentBackTracedNode.setOnMessagePath(true);
				//plane.update(currentBackTracedNode);
				steps.add(currentBackTracedNode);
				
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
				//System.out.println("step "+(routingStep++)+" "+currentBackTracedNode.toString());
				continue;
			}
			System.out.println();
			nextNode=currentBackTracedNode.getRandomNeighbor();
			//System.out.println("current: "+currentBackTracedNode.toString());
			//System.out.println("next: "+nextNode.toString());
			//if(currentBackTracedNode.getPreviousNode()!=null) {
				//System.out.println("previous: "+currentBackTracedNode.getPreviousNode().toString());
			//}
			
			//only difference between the multi and back traced
			if(currentBackTracedNode.checkAvailableNeighbor()==false&&nextNode.isSink()==false) {
				
				//System.out.println(currentBackTracedNode.checkAvailableNeighbor());
				//System.out.println(nextNode.isSink());
				//System.out.println("routing get stuck");
				return steps;
			}
		}
		steps.add(nextNode);
		this.reachSink=1;
		System.out.println("reach sink"+ nextNode.toString());
		System.out.println();
		return steps;
	}
	
	public int isSuccessful() {
		return this.reachSink;
	}

}
