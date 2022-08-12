package augustassignment;

import java.util.Scanner;

public class RotateArray {

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
		
		//accepting number of right rotations
		System.out.println("Enter number of rotations.");
		int rotations  = sc.nextInt();
		
		System.out.println("Original Array: ");
		for(int i = 0; i<n; i++)
		{
			System.out.print(arr[i] + " ");
		}
		
		rotate(arr, rotations, n);
		
		//printing rotated array
		System.out.println("Rotated Array: ");
		for(int i = 0; i<n; i++)
		{
			System.out.print(arr[i] + " ");
		}
		
		sc.close();
	}

	public  static void rotate(int arr[], int rotations, int n)
	{
		int requiredRotations = rotations%n;
		//loop for number of rotations
		for(int i = 0; i< requiredRotations ; i++)
		{
			//storing last index number
			int extra = arr[n-1];
			
			//moving values to the right
			for(int j = n-1; j>0 ; j--)
			{
				arr[j] = arr[j-1];
			}
			
			//setting previous last index value to the head of the array
			arr[0] = extra;
		}
	}
}
