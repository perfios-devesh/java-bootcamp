package august;

class Exp1{
	public Exp1(){
		System.out.println("Exp1 constructor");
	}
	
	static {
		System.out.println("Exp1 static block");
	}
	
	{
		System.out.println("Exp1 instance block");
	}
}


public class StaticInstanceBlocks {

	static {
		System.out.println("static block");
	}
	
	public static void main(String[] args) {
		System.out.println("main method");
	
		Exp1 exp1 = new Exp1();
		Exp1 exp2 = new Exp1();
		System.out.println(exp1 + "  "+ exp2);
	}
}
