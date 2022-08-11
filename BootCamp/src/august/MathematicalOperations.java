package august;

import java.util.Scanner;

public class MathematicalOperations {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter 2 numbers");
		int a = sc.nextInt();
		int b = sc.nextInt();
		
		System.out.println("Select One of the following cases.");
		System.out.println("1 - Addition");
		System.out.println("2 - Subtraction");
		System.out.println("3 - Multiplication");
		System.out.println("4 - Division");
		int choice = sc.nextInt();
		
		MathematicalOperations obj = new MathematicalOperations();
		switch(choice)
		{
		case 1: 
			obj.add(a,b);
			break;
		case 2: 
			obj.sub(a,b);
			break;
		case 3: 
			obj.mul(a,b);
			break;
		case 4: 
			obj.div(a,b);
			break;
		default:
			System.out.println("Try Again!!");
		}
		
		sc.close();
	}
	
	public void add(int a, int b)
	{
		System.out.println(a+b);
	}

	public void sub(int a, int b)
	{
		System.out.println(a-b);
	}
	
	public void mul(int a, int b)
	{
		System.out.println(a*b);
	}
	
	public void div(int a, int b)
	{
		if(b!=0)
			System.out.println(a/b);
		else
			System.out.println("Cannot divide by zero.");
	}
}
