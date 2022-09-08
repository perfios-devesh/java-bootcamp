package com.september8;

import java.sql.*;
import java.util.Scanner;

public class WorkerMain {
    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/worker_info", "root", "root");
        Scanner sc = new Scanner(System.in);
        int flag =1;
        while (flag == 1) {
            System.out.println("Enter your choice 1.Create 2.Update 3.Delete 4.Read 5.Full-Name 6. Unique Department 7.Position of letter 'a' 8. Exit");
            int choice = sc.nextInt();
            switch (choice) {
                case 1: {
                    PreparedStatement st = con.prepareStatement("Delete from worker_table;");
                    st.execute();
                    PreparedStatement st1 = con.prepareStatement("INSERT INTO `worker_info`.`worker_table` VALUES ('1', 'Monika', 'Arora', '100000', '2014-02-20 09:00:00', 'HR'),\n" +
                            "('2','Niharika','Verma','80000','2014-06-11 09:00:00','Admin'),\n" +
                            "('3','Vishal','Singhal', '300000', '2014-02-20 09:00:00', 'HR'),\n" +
                            "('4','Amitabh', 'Singh', '500000', '2014-02-20 09:00:00', 'Admin'),\n" +
                            "('5','Vivek', 'Bhati', '500000', '2014-06-11 09:00:00', 'Admin'),\n" +
                            "('6','Vipul', 'Diwan', '200000', '2014-06-11 09:00:00', 'Account'),\n" +
                            "('7', 'Satish', 'Kumar', '75000','2014-01-20 09:00:00', 'Account'),\n" +
                            "('8', 'Geetika', 'Chauhan', '90000', '2014-04-11 09:00:00', 'Admin');");
                    st1.execute();
                    System.out.println("Data inserted in the table.");
                    break;
                }
                case 2: {
                    System.out.println("Enter id to be updated.");
                    int workerId = sc.nextInt();
                    System.out.println("Enter new first name");
                    String fName = sc.next();
                    System.out.println("Enter new last name");
                    String lName = sc.next();
                    System.out.println("Enter new salary");
                    Double salary = sc.nextDouble();
                    sc.nextLine();
                    System.out.println("Enter new joining date");
                    String sDate = sc.nextLine();
                    Date date= Date.valueOf(sDate);
                    System.out.println("Enter new department");
                    String department = sc.next();
                    PreparedStatement st = con.prepareStatement("UPDATE worker_table set fname=?,lname=?,salary=?,joining_date=?,department=? where worker_id=?");
                    st.setString(1, fName);
                    st.setString(2, lName);
                    st.setDouble(3, salary);
                    st.setDate(4, date);
                    st.setString(5, department);
                    st.setInt(6, workerId);
                    st.execute();
                    System.out.println("Worker with id "+ workerId +" updated");
                    break;
                }
                case 3: {
                    System.out.println("Enter worker id to be deleted");
                    int workerId = sc.nextInt();
                    PreparedStatement st = con.prepareStatement("delete from worker_table where worker_id=?");
                    st.setInt(1, workerId);
                    st.execute();
                    System.out.println("Worker with id " + workerId + "deleted");
                    break;
                }
                case 4: {
                    PreparedStatement st = con.prepareStatement("select * from worker_table");
                    ResultSet rs = st.executeQuery();
                    System.out.println("WORKER_ID  FNAME  LNAME  SALARY  JOINING_DATE  DEPARTMENT");
                    while (rs.next()) {
                        System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getDouble(4) + " " + rs.getDate(5) + " " + rs.getString(6));
                    }
                    break;
                }
                case 5:
                    QuestionOne.questionOne();
                break;
                case 6: QuestionTwo.questionTwo();
                break;
                case 7:
                    QuestionThree.questionThree();
                    break;
                case 8: {
                    System.exit(1);
                }
                default:
                    System.out.println("Wrong choice");
            }
        }
    }
}

