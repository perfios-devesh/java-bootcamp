package com.september8;

import java.sql.*;
import java.util.Scanner;

public class QuestionThree {
    public static void questionThree() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/worker_info", "root", "root");
        Scanner sc = new Scanner(System.in);
        PreparedStatement st = con.prepareStatement("SELECT fname, POSITION('a' IN fname) AS position FROM worker_table;");
        ResultSet rs = st.executeQuery();
        System.out.println("FNAME   POSITION");
        while (rs.next()) {
            System.out.println(rs.getString(1)+"  "+ rs.getInt(2));
        }
    }
}
