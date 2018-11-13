package BackTraced;

public class TestClone {

	public static void main(String args[]) {
		// TODO Auto-generated constructor stub
		Node n1=new Node(1,2,3);
		Node n2=new Node(2,2,3);
		Node n3=new Node(3,2,3);
		
		n1=n2;
		n2=n3;
		System.out.println(n1.toString());
		System.out.println(n2.toString());
		System.out.println(n3.toString());
	}

}
