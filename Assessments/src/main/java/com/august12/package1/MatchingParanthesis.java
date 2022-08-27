package com.august12.package1;

import java.util.Scanner;

public class MatchingParanthesis {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the expression.");

		// storing expression
		String exp = sc.nextLine();
		int br1 = 0, br2 = 0, br3 = 0;

		int flag = 0;
		for (int i = 0; i < exp.length(); i++) {
			char ch = exp.charAt(i);

			if (ch == '(')
				br1++;
			else if (ch == ')')
				br1--;
			else if (ch == '{')
				br2++;
			else if (ch == '}')
				br2--;
			else if (ch == '[')
				br3++;
			else if (ch == ']')
				br3--;

			// checking if at any point closing braces have come before opening braces
			if (br1 < 0 || br2 < 0 || br3 < 0) {
				flag = 1;
				break;
			}
		}

		// values will be zero only if all braces are matching
		if (flag == 0 && br1 == 0 && br2 == 0 && br3 == 0)
			System.out.println("Matching Paranthesis.");
		else
			System.out.println("Not Matching Paranthesis.");

		sc.close();
	}
}
