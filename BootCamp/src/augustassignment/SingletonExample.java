package augustassignment;

public class SingletonExample {

	public static void main(String[] args) {
		SingletonClass x = SingletonClass.getInstance();
		  
        // Instantiating SingletonClass class with variable y
        SingletonClass y = SingletonClass.getInstance();
  
        // Instantiating SingletonClass class with variable z
        SingletonClass z = SingletonClass.getInstance();
  
        // Printing the hash code for above variables
        System.out.println("Hashcode of x is "
                           + x.hashCode());
        System.out.println("Hashcode of y is "
                           + y.hashCode());
        System.out.println("Hashcode of z is "
                           + z.hashCode());
  
        // Condition check
        if (x == y && y == z) {
  
            // Print statement
            System.out.println(
                "Three objects point to the same memory location on the heap i.e, to the same object");
        }
  
        else {
            // Print statement
            System.out.println(
                "Three objects DO NOT point to the same memory location on the heap");
        }
	}
}