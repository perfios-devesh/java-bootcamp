package august;

import java.util.Scanner;

class Student {
		private int id;
		private String city;
		private String name;
		private String emailId;
		
		public Student(int id, String city, String name, String emailId) {
			this.id = id;
			this.city = city;
			this.name = name;
			this.emailId = emailId;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getEmailId() {
			return emailId;
		}
		public void setEmailId(String emailId) {
			this.emailId = emailId;
		}
		
}

public class ArrayPractice{
	
	public static void main(String[] args) {
		Student data[] = new Student[5];
		Student std1 = new Student(1, "BLR", "Sahil","sahil.s@perfios.com");
		Student std2 = new Student(2, "LKO", "Devesh" , "devesh.b@perfios.com");
		Student std3 = new Student(3, "JAI", "Arijit", "arijit.r@perfios.com");
		Student std4 = new Student(4, "CHN", "Adithya", "adithya.a@perfios.com");
		
		data[0] = std1;
		data[1] = std2;
		data[2] = std3;
		data[3] = std4;
		data[4] = std4;
		
		System.out.println("Enter a mail id.");
		Scanner sc = new Scanner(System.in);
		String mailId = sc.nextLine();
		int flag = 0;
		
		for(Student s : data)
		{
			if(s.getEmailId().equals(mailId))
			{
				flag = 1;
				break;
			}
		}
		if(flag == 1)
			System.out.println("Already Present.");
		else
		{
			System.out.println("Enter id, name and city");
			int id = sc.nextInt();
			sc.nextLine();
			String name = sc.nextLine();
			String city = sc.nextLine();
			
			Student stdnew = new Student(id, city, name, mailId);
			data[4] = stdnew;
			System.out.println("Successfully Registered " + data[4].getName() + " having mail id: " + data[4].getEmailId() + " living in " + data[4].getCity());
		}
		
		sc.close();
	}
}