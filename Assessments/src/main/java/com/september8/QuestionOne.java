package com.september8;

import java.sql.*;
import java.util.Scanner;

public class QuestionOne {
    public static void questionOne() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/worker_info", "root", "root");
        Scanner sc = new Scanner(System.in);
        PreparedStatement st = con.prepareStatement("SELECT CONCAT(UPPER(fname),' ',UPPER(lname)) as fullname from worker_table;");
        ResultSet rs = st.executeQuery();
        System.out.println("FULL_NAME");
        while (rs.next()) {
            System.out.println(rs.getString(1));
        }
    }
}