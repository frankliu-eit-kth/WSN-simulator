package Model;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;

public class Plane {
	private char[][] screen;
	PrintWriter writer ;
	
	public void initNodes(ArrayList<Node> nodes) {
		for(Node n:nodes) {
			int nodeX=n.getX();
			int nodeY=n.getY();
			this.screen[nodeX][nodeY]='N';
		}
	}
	
	public void initRandomPath(ArrayList<Randompath> paths) {
		int pathNum=1;
		for(Randompath p:paths) {
			LinkedList<Node> nodes=p.getPath();
			for(Node n:nodes) {
				int nodeX=n.getX();
				int nodeY=n.getY();
				char c=new String(""+pathNum).toCharArray()[0];
				this.screen[nodeX][nodeY]=c;
			}
			pathNum++;
		}
	}

	
	public Plane(int xLength, int yLength,ArrayList<Node> nodes, ArrayList<Randompath> paths,Node source,Node sink) {
		// TODO Auto-generated constructor stub
		
		screen =new char[xLength][yLength];
		for(int i=0;i<xLength;i++) {
			for(int j=0;j<yLength;j++) {
				screen[i][j]=' ';
			}
		}
		this.initNodes(nodes);;
		this.initRandomPath(paths);
		
		int sourceX=source.getX();
		int sourceY=source.getY();
		screen[sourceX][sourceY]='*';
		
		int sinkX=sink.getX();
		int sinkY=sink.getY();
		screen[sinkX][sinkY]='#';
		
		this.printScreen();
	}
	
	
	public void update(Node currentNode) {
		int x=currentNode.getX();
		int y=currentNode.getY();
		screen[x][y]='@';
		printScreen();
	}
	
	public void printScreen()  {
		
		
		for(int i=0;i<this.screen.length;i++) {
			
			
			System.out.println(screen[i]);
		}
		System.out.println();
	}
	
	
	

}
