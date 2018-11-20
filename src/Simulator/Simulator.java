package Simulator;

import java.io.File;
import java.util.ArrayList;

import Model.Node;
import Model.Randompath;
import Routing.BackTracedAlgorithm;
import Routing.MultiRandomPathAlgorithm;
import Util.FileOutput;
import Util.GenerateRandomInt;

public class Simulator {
	private int area;
	private int numNodes;
	private int radioRange;
	
	private final int numPath=5;
	private final int maxLengthRandomPath=5;
	private final int numSimulationsForEachSimulator=20;
	
	//private long timeStopper;
	//private int numStepsToSink;
	//private double deliveryRatio;
	//private int routingSteps;
	
	
	private ArrayList<Node> nodes;
	private ArrayList<Randompath> randomPaths;
	private Node source;
	private Node sink;

	private static final File summarizeMultiRandom= new File("multi random summarize.txt");
	private static final File summarizeBackTrace= new File("back traced summarize.txt");
	private FileOutput output=new FileOutput();
	private static int fileId=1;
	
	
	public static void main(String args[]) {
		Simulator simulator=new Simulator();
		simulator.simulate();
	}
	public void simulate() {
		this.area=20;
		this.numNodes=100;
		this.radioRange=5;
		init(area,numNodes,radioRange);
		initNodes();
		initRandomPaths();
		/*
		for(int i=0;i<numSimulationsForEachSimulator;i++) {
			MultiRandomPathAlgorithm multiRouting=new MultiRandomPathAlgorithm();
			long startTime=System.nanoTime();
			ArrayList<Node> steps=multiRouting.routing(this.nodes, this.source, this.sink);
			long stopTime=System.nanoTime();
			output.outPutSummarize(summarizeMultiRandom, area, numNodes, numPath, maxLengthRandomPath, multiRouting.isSuccessful(), steps.size(), stopTime-startTime);
			output.outputSimulation("multi-random", fileId++, this.area, this.numNodes, this.numPath, this.nodes, this.source, this.sink, this.randomPaths, steps);
		}*/
		for(int i=0;i<numSimulationsForEachSimulator;i++) {
			BackTracedAlgorithm backtracedRouting=new BackTracedAlgorithm();
			long startTime=System.nanoTime();
			ArrayList<Node> steps=backtracedRouting.routing(this.nodes, this.source, this.sink);
			System.out.println("1");
			long stopTime=System.nanoTime();
			output.outPutSummarize(summarizeBackTrace, area, numNodes, numPath, maxLengthRandomPath, backtracedRouting.isSuccessful(), steps.size(), stopTime-startTime);
			System.out.println("2");
			output.outputSimulation("back-traced", fileId++, this.area, this.numNodes, this.numPath, this.nodes, this.source, this.sink, this.randomPaths, steps);
			System.out.println("3");
		}	
		
	}
	
	
	

	public void init(int area, int numNodes, int radioRange) {
		this.area=area;
		this.numNodes=numNodes;
		this.radioRange=radioRange;
		this.nodes=new ArrayList<Node>();
		this.randomPaths=new ArrayList<Randompath>();
		initNodes();
		initRandomPaths();
		this.source=nodes.get(GenerateRandomInt.randomInt(0, numNodes-1));
		this.sink=nodes.get(GenerateRandomInt.randomInt(0, numNodes-1));
		this.sink.setSink(true);
	}
	
	private void initRandomPaths() {
		for(int i=0;i<numPath;i++) {
			
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
	

	private void initNodes() {
		
		for(int i=1;i<=this.numNodes;) {
			int nodeX=GenerateRandomInt.randomInt(0, area-1);
			int nodeY=GenerateRandomInt.randomInt(0, area-1);
			Node newBackTracedNode=new Node(i,nodeX,nodeY);
			if(nodes.contains(newBackTracedNode)) {
				continue;
			}else {
				nodes.add(newBackTracedNode);
				i++;
			}
		}
		for(Node n:nodes) {
			n.findNeighbors(nodes, radioRange);
		}
		
	}
	
	
}
 