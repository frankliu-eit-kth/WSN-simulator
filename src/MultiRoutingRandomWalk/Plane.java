package MultiRoutingRandomWalk;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;

import BackTraced.BackTracedNode;

public class Plane {
	private char[][] screen;
	PrintWriter writer ;
	
	public void initNodes(ArrayList<MultiRandomNode> nodes) {
		for(MultiRandomNode n:nodes) {
			int nodeX=n.getX();
			int nodeY=n.getY();
			this.screen[nodeX][nodeY]='N';
		}
	}
	
	public void initRandomPath(ArrayList<Randompath> paths) {
		int pathNum=1;
		for(Randompath p:paths) {
			LinkedList<MultiRandomNode> nodes=p.getPath();
			for(MultiRandomNode n:nodes) {
				int nodeX=n.getX();
				int nodeY=n.getY();
				char c=new String(""+pathNum).toCharArray()[0];
				this.screen[nodeX][nodeY]=c;
			}
			pathNum++;
		}
	}
	public Plane(int xLength, int yLength,ArrayList<MultiRandomNode> nodes, ArrayList<Randompath> paths,MultiRandomNode source,MultiRandomNode sink) {
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
	
	
	
	public void update(MultiRandomNode currentNode) {
		int x=currentNode.getX();
		int y=currentNode.getY();
		screen[x][y]='@';
		printScreen();
	}
	
	public void printScreen()  {
		
		
		for(int i=0;i<this.screen.length;i++) {
			try {
				writer.println(screen[i]);
			}catch( Exception e) {
				e.printStackTrace();
			}
			
			System.out.println(screen[i]);
		}
		System.out.println();
	}
	
	
	

}
