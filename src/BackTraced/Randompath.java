package BackTraced;

import java.util.LinkedList;

public class Randompath {
	private LinkedList<BackTracedNode> path;
	
	
	public Randompath() {
		this.path=new LinkedList<BackTracedNode>();
	}
	public Randompath(LinkedList<BackTracedNode> nodes) {
		// TODO Auto-generated constructor stub
		this.path=path;
	}
	
	
	public void addNode(BackTracedNode n) {
		if(path.size()==0) {
		path.add(n);
		}else {
			BackTracedNode lastNode=path.get(path.size()-1);
			lastNode.setNextRandomPathNode(n);
			n.setFormerRamdomPathNode(lastNode);
			path.add(n);
		}
	}
	public LinkedList<BackTracedNode> getPath() {
		return path;
	}
	public void setPath(LinkedList<BackTracedNode> path) {
		this.path = path;
	}
	
	

}
