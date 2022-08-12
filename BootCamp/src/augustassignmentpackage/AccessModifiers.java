package augustassignmentpackage;

public class AccessModifiers {

	public static int x;
	protected static int y=6;
	int z;
	private int a;
	public void checkAge(int age) {
		if(age<18) {
			System.out.println("Under Age");

		}else {
			System.out.println("Correct Age");

		}
	}
	public void checkMarks(int marks) {
		if(marks>=60) {
			System.out.println("Pass");
		}else {
			System.out.println("Fail");
		}
	}
}
