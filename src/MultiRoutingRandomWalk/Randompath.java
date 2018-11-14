package MultiRoutingRandomWalk;

import java.util.LinkedList;

public class Randompath {
	private LinkedList<MultiRandomNode> path;
	
	
	public Randompath() {
		this.path=new LinkedList<MultiRandomNode>();
	}
	public Randompath(LinkedList<MultiRandomNode> nodes) {
		// TODO Auto-generated constructor stub
		this.path=path;
	}
	
	
	public void addNode(MultiRandomNode n) {
		if(path.size()==0) {
		path.add(n);
		}else {
			MultiRandomNode lastNode=path.get(path.size()-1);
			lastNode.setNextRandomPathNode(n);
			n.setFormerRamdomPathNode(lastNode);
			path.add(n);
		}
	}
	public LinkedList<MultiRandomNode> getPath() {
		return path;
	}
	public void setPath(LinkedList<MultiRandomNode> path) {
		this.path = path;
	}
	
	

}
