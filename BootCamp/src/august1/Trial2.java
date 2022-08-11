package august1;
import java.util.Scanner;

import august.*;
public class Trial2 implements Cloneable{
	public static void main(String[] args) throws CloneNotSupportedException {
		Trial1 trial1 = new Trial1();
		System.out.println("Enter a number");
		Scanner sc = new Scanner(System.in);
		trial1.b = sc.nextInt();
		System.out.println(trial1.b);
		trial1.biggest();
		
//		Trial1 trial12;
//		trial12 = trial1;
//		System.out.println(trial12.hashCode());
//		System.out.println(trial1.hashCode());			//Same Hash code
		
//		Trial1 trial12;
//		trial12 = Trial2.getObj();
//		System.out.println(trial12.hashCode());
//		System.out.println(trial1.hashCode());			//different Hash code
		
//		Trial2 trial2 = new Trial2();
//		Trial2 trial22 = (Trial2) trial2.clone();
//		System.out.println(trial2.hashCode());
//		System.out.println(trial22.hashCode());			//different Hash code
		

		Trial1 trial12;
		trial12 = Trial2.getObj1(trial1);
		System.out.println(trial12.hashCode());
		System.out.println(trial1.hashCode());			//different Hash code
		
		sc.close();
	}
	
	//factory method
	public static Trial1 getObj()
	{
		return new Trial1();
	}
	
	//factory method
	public static Trial1 getObj1(Trial1 obj)
	{
		return obj;
	}
}
