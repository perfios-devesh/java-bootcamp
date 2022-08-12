package augustassignment;

import java.util.Scanner;

public class TargetSum {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the size of array.");
		int n = sc.nextInt();
		//setting array size as n
		int arr[] = new int[n];
		
		//initializing array from user input
		System.out.println("Enter "+n+" numbers.");
		for(int i=0;i<n;i++)
		{
			arr[i] = sc.nextInt();
		}
		
		//accepting target sum
		System.out.println("Enter target sum.");
		int target  = sc.nextInt();
		
		int flag = 0;
		for(int i=0;i<n-1;i++)
		{
			int needed = target - arr[i];
			for(int j = i+1; j<n; j++)
			{
				if(arr[j] == needed)
				{
					flag = 1;
					System.out.println("Found by adding values " + arr[i] + " and "+ arr[j]);
				}
			}
		}
		if(flag == 0)
			System.out.println("Not Found");
		
		sc.close();
	}

}
