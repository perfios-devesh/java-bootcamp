package august;

class Constructors{

	public int a;
	private int b;
	protected int c;
	int d;
	
	public Constructors(int a , int b, int c, int d) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		System.out.println(this.b);
	}
}

public class ConstructorsUsage{
	public static void main(String[] args) {
		Constructors constructors = new Constructors(1, 2, 3, 4);
		System.out.println(constructors.a + " " + constructors.c + " " + constructors.d);
	}
}
