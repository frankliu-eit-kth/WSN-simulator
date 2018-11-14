package MultiRoutingRandomWalk;
import java.util.ArrayList;

public class MultiRandomNode {
	private int id;
	private int x;
	private int y;
	
	private boolean onMessagePath=false;
	private boolean isSink=false;
	
	private ArrayList<MultiRandomNode> neighbors=new ArrayList<MultiRandomNode>();
	private MultiRandomNode nextRandomPathNode;
	private MultiRandomNode formerRamdomPathNode;
	//protected abstract void routingAlgorithm();
	
	public MultiRandomNode(int id,int x, int y) {
		this.id=id;
		this.x=x;
		this.y=y;
		this.neighbors=new ArrayList<MultiRandomNode>();
		this.nextRandomPathNode=null;
		this.formerRamdomPathNode=null;
	}
	
	public void findNeighbors(ArrayList<MultiRandomNode> allNodes, double distanceBound) {
		for(MultiRandomNode n:allNodes) {
			int nodeX=n.getX();
			int nodeY=n.getY();
			if(nodeX==this.x && nodeY==this.y) {
				continue;
			}
			 double distance = Math.sqrt((this.x-nodeX)*(this.x-nodeX)+(this.y-nodeY)*(this.y-nodeY));
			 if(distance<distanceBound) {
				 neighbors.add(n);
			 }
		}
	}
	
	public MultiRandomNode getRandomNeighbor() {
		if(this.getNeighbors().size()==0) {
			return null;
		}
		return (this.getNeighbors().get(GenerateRandomInt.randomInt(0, this.getNeighbors().size()-1)));
	}

	
	
	public ArrayList<MultiRandomNode> getNeighbors() {
		return neighbors;
	}

	public void setNeighbors(ArrayList<MultiRandomNode> neighbors) {
		this.neighbors = neighbors;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean isOnMessagePath() {
		return onMessagePath;
	}

	public void setOnMessagePath(boolean onMessagePath) {
		this.onMessagePath = onMessagePath;
	}

	public boolean isSink() {
		return isSink;
	}

	public void setSink(boolean isSink) {
		this.isSink = isSink;
	}

	public MultiRandomNode getNextRandomPathNode() {
		return nextRandomPathNode;
	}

	public void setNextRandomPathNode(MultiRandomNode nextRandomPathNode) {
		this.nextRandomPathNode = nextRandomPathNode;
	}

	public MultiRandomNode getFormerRamdomPathNode() {
		return formerRamdomPathNode;
	}

	public void setFormerRamdomPathNode(MultiRandomNode formerRamdomPathNode) {
		this.formerRamdomPathNode = formerRamdomPathNode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MultiRandomNode other = (MultiRandomNode) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Node [id=" + id + ", x=" + x + ", y=" + y + ", onMessagePath=" + onMessagePath + ", isSink=" + isSink+"]";
	}

	
	
	
	
	
	
	
	
}
