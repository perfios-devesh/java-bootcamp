package augustassignment;

class Student {

	private String name;
	private int id;
	private long phoneNumber;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public void display()
	{
		System.out.println("Id: " +id+ " Name: " + name+ " Phone Number: " + phoneNumber);
	}
}

public class StudentExample{
	public static void main(String[] args) {
		Student[] allStudents = new Student[5];
		
		Student student = new Student();
		student.setId(1);
		student.setName("Devesh");
		student.setPhoneNumber(7073253488l);
		allStudents[0] = student;
		
		Student student1 = new Student();
		student1.setId(2);
		student1.setName("Sahil");
		student1.setPhoneNumber(6387136135l);
		allStudents[1] = student1;
		
		Student student2 = new Student();
		student2.setId(3);
		student2.setName("Arijit");
		student2.setPhoneNumber(7006028654l);
		allStudents[2] = student2;
		
		for(int i = 0 ; i< 3 ; i++)
		{
			allStudents[i].display();
		}
		
	}
}