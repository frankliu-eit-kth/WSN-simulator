package MultiRoutingRandomWalk;

public class TestClone {

	public static void main(String args[]) {
		// TODO Auto-generated constructor stub
		MultiRandomNode n1=new MultiRandomNode(1,2,3);
		MultiRandomNode n2=new MultiRandomNode(2,2,3);
		MultiRandomNode n3=new MultiRandomNode(3,2,3);
		
		n1=n2;
		n2=n3;
		System.out.println(n1.toString());
		System.out.println(n2.toString());
		System.out.println(n3.toString());
	}

}
