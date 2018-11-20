package Model;

import java.util.LinkedList;

public class Randompath {
	private LinkedList<Node> path;
	
	
	public Randompath() {
		this.path=new LinkedList<Node>();
	}
	
	public void addNode(Node n) {
		if(path.size()==0) {
		path.add(n);
		}else {
			Node lastNode=path.get(path.size()-1);
			lastNode.setNextRandomPathNode(n);
			n.setFormerRamdomPathNode(lastNode);
			path.add(n);
		}
	}
	
	public LinkedList<Node> getPath() {
		return path;
	}
	public void setPath(LinkedList<Node> path) {
		this.path = path;
	}
}
