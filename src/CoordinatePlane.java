
public class CoordinatePlane {
	private int xLength;
	private int yLength;
	
	public CoordinatePlane(int xLength, int yLength) {
		this.xLength=xLength;
		this.yLength=yLength;
	}

	public int getxLength() {
		return xLength;
	}

	public void setxLength(int xLength) {
		this.xLength = xLength;
	}

	public int getyLength() {
		return yLength;
	}

	public void setyLength(int yLength) {
		this.yLength = yLength;
	}

	@Override
	public String toString() {
		return "CoordinatePlane [xLength=" + xLength + ", yLength=" + yLength + "]";
	}
	
	
}
