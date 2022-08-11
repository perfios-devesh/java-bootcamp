package august;

public class Trial1 {
	private int a = 24;
	public int b =35;
	protected int c = 85;
	int d = 52;
	
	public void biggest()
	{
		if(a>b && a>c && a>d)
			System.out.println(a);
		if(b>a && b>c && b>d)
			System.out.println(b);
		if(c>a && c>b && c>d)
			System.out.println(c);
		if(d>a && d>b && d>c)
			System.out.println(d);
	}
}
