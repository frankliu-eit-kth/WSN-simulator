import java.util.ArrayList;

public class Node {
	private int id;
	private int x;
	private int y;
	private boolean onMessagePath=false;
	private boolean isSink=false;
	private ArrayList<Node> neighbors=new ArrayList<Node>();
	//protected abstract void routingAlgorithm();
	
	
	public boolean getIsSink() {
		return isSink;
	}


	public void setSink(boolean isSink) {
		this.isSink = isSink;
	}


	public Node(int id,int x, int y) {
		this.id=id;
		this.x=x;
		this.y=y;
		this.neighbors=new ArrayList<Node>();
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public void setOnPath() {
		this.onMessagePath=true;
	}
	public boolean getOnPath() {
		return this.onMessagePath;
	}
	public void findNeighbors(ArrayList<Node> allNodes, double distanceBound) {
		for(Node n:allNodes) {
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
	
	public Node getRandomNeighbor() {
		if(this.getNeighbors().size()==0) {
			return null;
		}
		return (this.getNeighbors().get(GenerateRandomInt.randomInt(0, this.getNeighbors().size()-1)));
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
	public ArrayList<Node> getNeighbors() {
		return neighbors;
	}
	public void setNeighbors(ArrayList<Node> neighbors) {
		this.neighbors = neighbors;
	}

	@Override
	public String toString() {
		return "Node [id=" + id + ", x=" + x + ", y=" + y +"]";
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
		Node other = (Node) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	
	
	
	
	
}
