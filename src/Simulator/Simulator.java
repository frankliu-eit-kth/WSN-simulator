package Simulator;

import java.io.File;
import java.util.ArrayList;

import Model.Node;
import Model.Plane;
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

	private String folder;
	private File summarizeMultiRandom;
	private File summarizeBackTrace;
	private FileOutput output=new FileOutput();
	private static int fileId=001;
	
	
	public static void main(String args[]) {
		int[] area= {10,20};
		int[] numNodes= {10,20,30,50,100};
		int[] range= {5,10};
		for(int a:area) {
			for(int n:numNodes) {
				for(int r:range) {
					Simulator simulator=new Simulator(a,n,r);
					simulator.init();
					for(int i=1;i<6;i++) {
						simulator.folder="C:\\Software\\Java\\eclipse-workspace\\WSN simulator\\files\\area"+simulator.area+"nodes"+simulator.numNodes+"range"+simulator.radioRange+"\\group"+i+"\\";
						simulator.summarizeMultiRandom= new File(simulator.folder+"multi random summarize.txt");
						simulator.summarizeBackTrace= new File(simulator.folder+"back traced summarize.txt");
						new File(simulator.folder).mkdirs();
						simulator.simulate();
					}
				}
			}
		}
		
		
		
	}
	
		public Simulator(int area, int numNodes,int radioRange) {
			this.area=area;
			this.numNodes=numNodes;
			this.radioRange=radioRange;
		}
		
		public void simulate() {
			for(int i=0;i<numSimulationsForEachSimulator;i++) {
				//Plane plane=new Plane(area,area,this.nodes,this.randomPaths,this.source,this.sink);
				MultiRandomPathAlgorithm multiRouting=new MultiRandomPathAlgorithm();
				long startTime=System.nanoTime();
				ArrayList<Node> steps=multiRouting.routing(this.nodes, this.source, this.sink);
				for(Node n: steps) {
					System.out.println(n.getId());
				}
				long stopTime=System.nanoTime();
				output.outPutSummarize(summarizeMultiRandom, area, numNodes, numPath, maxLengthRandomPath, multiRouting.isSuccessful(), steps.size(), stopTime-startTime);
				output.outputSimulation(folder,"multi-random", fileId++, this.area, this.numNodes, this.numPath, this.nodes, this.source, this.sink, this.randomPaths, steps);
				refreshNodes();
			}
		
		for(int j=0;j<numSimulationsForEachSimulator;j++) {
			Plane plane=new Plane(area,area,this.nodes,this.randomPaths,this.source,this.sink);
			BackTracedAlgorithm backtracedRouting=new BackTracedAlgorithm();
			long startTime=System.nanoTime();
			ArrayList<Node> steps=backtracedRouting.routing(this.nodes, this.source, this.sink, plane);
			
			long stopTime=System.nanoTime();
			output.outPutSummarize(summarizeBackTrace, area, numNodes, numPath, maxLengthRandomPath, backtracedRouting.isSuccessful(), steps.size(), stopTime-startTime);
			
			output.outputSimulation(folder,"back-traced", fileId++, this.area, this.numNodes, this.numPath, this.nodes, this.source, this.sink, this.randomPaths, steps);
			refreshNodes();
		}	
	}
	
	
	

	public void init() {
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
			
			boolean hasNonRandomPathNode=false;
			for(Node n:nodes) {
				if(n.getFormerRamdomPathNode()==null&&n.getNextRandomPathNode()==null) {
					hasNonRandomPathNode=true;
				}
			}
			if(!hasNonRandomPathNode) {
				return;
			}
			
			//generate a path with length pathLength
			Node startNode=null;
			while(startNode==null) {
				for(Node n: this.nodes) {
					
				}
				Node n=nodes.get(GenerateRandomInt.randomInt(0, numNodes-1));
				if(n.getNextRandomPathNode()==null&& n.getFormerRamdomPathNode()==null) {
					startNode=n;
				}
			}
			
			newPath.addNode(startNode);
			Node currentNode=startNode;
			
			while(newPath.getPath().size()<pathLength) {
				ArrayList<Node> neighbor=currentNode.getNeighbors();
				boolean flag=false;
				for(Node n:neighbor) {
					if(n.getFormerRamdomPathNode()==null&&n.getNextRandomPathNode()==null) {
						hasNonRandomPathNode=true;
					}
				}
				if(!flag) {
					break;
				}
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
	
	private void refreshNodes() {
		for(Node n:this.nodes) {
			n.setOnMessagePath(false);
			n.setPreviousNode(null);
		}
	}
	
}
 