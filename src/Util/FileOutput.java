package Util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Random;
import java.util.StringJoiner;

import Model.Node;
import Model.Randompath;


public class FileOutput {
	
	public void outPutSummarize(File file, int area, int numNodes, int numPath, int maxLengthRandomPath, int isSuccessful, int steps, long time) {
		
		try {
			InputStreamReader reader=null;
			BufferedWriter out = new BufferedWriter(new FileWriter(file,true));
			reader = new InputStreamReader(new FileInputStream(file)); 
			BufferedReader br = new BufferedReader( reader); 
			String line = "";
			line = br.readLine();
			if(line==null) {
				out.write("area;node number;path number;max path length;deliver succeed;steps;time consuming;\n");
			}
			StringJoiner sj=new StringJoiner(";");
			sj.add(""+area);
			sj.add(""+numNodes);
			sj.add(""+numPath);
			sj.add(""+maxLengthRandomPath);
			sj.add(""+isSuccessful);
			sj.add(""+steps);
			sj.add(""+time);
			out.write(sj.toString()+"\n");
			br.close();
			out.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void outputSimulation(String algorithm,int fileId, int area, int numNodes, int numPath, ArrayList<Node> nodes,Node source, Node sink,ArrayList<Randompath> paths, ArrayList<Node> steps) {
		//create file name
		StringJoiner sj=new StringJoiner(";");
		sj.add("Simulation "+fileId);
		sj.add(algorithm);
		sj.add("area "+area);
		sj.add("node number"+numNodes);
		
		File file=new File(sj.toString()+".txt");
		
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(file));
			
			//write all nodes
			StringJoiner sj1=new StringJoiner(";");
			sj1.add("all nodes");
			for(Node n: nodes) {
				StringJoiner sj2=new StringJoiner(",");
				sj2.add(""+n.getId());
				sj2.add(""+n.getX());
				sj2.add(""+n.getY());
				sj1.add(sj2.toString());
			}
			out.write(sj1.toString()+"\n");
			
			//write source
			out.write("source;"+source.getId()+","+source.getX()+","+source.getY()+";"+"\n");
			
			//write sink
			out.write("sink;"+sink.getId()+","+sink.getX()+","+sink.getY()+";"+"\n");
			
			//write path
			StringJoiner sj3= new StringJoiner(";");
			sj3.add("all paths");
			for(int i=1;i<=paths.size();i++) {
				Randompath p=paths.get(i-1);
				StringJoiner sj4=new StringJoiner(",");
				sj4.add("p"+i);
				for(Node n:p.getPath()) {
					sj4.add(""+n.getId());
				}
				sj3.add(sj4.toString());
			}
			out.write(sj3.toString()+"\n");
			
			//write steps
			StringJoiner sj5= new StringJoiner(";");
			sj5.add("steps");
			for(Node n:steps) {
				StringJoiner sj6=new StringJoiner(",");
				sj6.add(""+n.getId());
				sj6.add(""+n.getX());
				sj6.add(""+n.getY());
				sj5.add(sj6.toString());
			}
			out.write(sj5.toString()+"\n");
			out.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FileOutput thisOut=new FileOutput();
		thisOut.outPutSummarize(new File("summarize.txt"), 10, 10, 5, 5, 1, 15, 15);
		/*
		Node n=new Node(1,1,1);
		ArrayList<Node> nodes=new ArrayList<Node>();
		nodes.add(n);
		Randompath p=new Randompath();
		p.addNode(n);
		ArrayList<Randompath> paths=new ArrayList<Randompath>();
		paths.add(p);
		thisOut.outputSimulation("backtraced", 1, 10, 1, 1, nodes, n, n,paths, nodes);*/
	}

}
