package com.august12.package1;

import java.util.Scanner;

public class VoterIdValidation {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter name of candidate.");
		String name = sc.nextLine();
		System.out.println("Enter age of candidate.");
		int age = sc.nextInt();
		System.out.println("Enter citizenship of candidate.");
		sc.nextLine();
		String citizenship = sc.nextLine();

		if (age >= 18) {
			if (citizenship.equals("Indian"))
				System.out.println(name + " is eligible.");
			else
				System.out.println(name + " is not eligible as not Indian.");
		} else
			System.out.println(name + " is not eligible as age has to be more than 18.");

		sc.close();

	}
}
