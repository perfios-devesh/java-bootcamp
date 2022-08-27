package com.august12.package1;

import java.util.Scanner;
import com.august12.package2.AccessModifiers;
import com.august12.package2.StudentClass;

public class Test extends AccessModifiers {
	public static void main(String[] args) {
		StudentClass obj1 = new StudentClass();
		Scanner sc = new Scanner(System.in);
		System.out.println("Please Enter the name");
		String name = sc.nextLine();
		System.out.println("Please Enter the age");
		int age = obj1.y = sc.nextInt();
		System.out.println("Please Enter the marks");
		int marks = obj1.x = sc.nextInt();
		obj1.checkAge(age);
		obj1.displayName(name);
		obj1.checkMarks(marks);

		sc.close();
	}

}