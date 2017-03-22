package com.chuyeu.training.myapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnectionTest {

	public static void main(String[] args) {
        Connection con = null;
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "pass");
        	
            System.out.println("Соединение установлено.");

            Statement createStatement = con.createStatement();
            createStatement.execute("select * from product order by id");

            ResultSet resultSet = createStatement.getResultSet();
            resultSet.next();
            int id = resultSet.getInt("id");
            System.out.println(id);

       // } catch (ClassNotFoundException e) {
       //    e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
            }
        }
    }
}
