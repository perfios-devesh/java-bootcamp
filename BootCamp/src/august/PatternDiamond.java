package august;
import java.util.*;

public class PatternDiamond {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Hello World!!");
		System.out.println("Enter n...");
		int n = sc.nextInt();
		int k = n/2 + 1;
		for(int i = 1; i <= n; i ++)
		{
			if(i<= (n/2)+1)
			{
				k--;
				for(int l = 1; l<= k ; l++)
					System.out.print(" ");
				for(int l = 1; l<= (i*2) -1; l++)
					System.out.print("*");
			}
			else
			{
				k++;
				for(int l = 1; l<= k ; l++)
					System.out.print(" ");
				for(int l = 1; l<= ((n-i)*2)+1; l++)
					System.out.print("*");
			}
			System.out.println();
		}
		
		System.out.println("Different Approach.........");
		printPattern(n);
		
		sc.close();
	}
	
	static void printPattern(int size) {
        int space=size/2,j,count;
        for(int i=1;i<=size;i++) {
            count=size-space;
            j=0;
            while(j<count) {
                while(j<space) {
                    System.out.print(" ");
                    j++;
                
            }
                while(j<count) {
                    System.out.print("*");
                    j++;
                }
        }
            if(i<=size/2)
                space--;
            else
                space++;
            System.out.print("\n");
    
    }
        }

}
