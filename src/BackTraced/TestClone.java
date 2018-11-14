package BackTraced;

public class TestClone {

	public static void main(String args[]) {
		// TODO Auto-generated constructor stub
		BackTracedNode n1=new BackTracedNode(1,2,3);
		BackTracedNode n2=new BackTracedNode(2,2,3);
		BackTracedNode n3=new BackTracedNode(3,2,3);
		
		n1=n2;
		n2=n3;
		System.out.println(n1.toString());
		System.out.println(n2.toString());
		System.out.println(n3.toString());
	}

}
