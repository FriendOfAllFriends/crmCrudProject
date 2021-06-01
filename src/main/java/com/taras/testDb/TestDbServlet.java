package com.taras.testDb;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/TestDbServlet")
public class TestDbServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user = "hbstudent";
        String password = "hbstudent";

        String url = "jdbc:mysql://localhost:3306/web_customer_tracker?useSSL=false&serverTimezone=UTC";
        String driver = "com.mysql.cj.jdbc.Driver";

        //get connection

        try {
            PrintWriter out = response.getWriter();

            out.println("Connecting to DB: " + url);

            Class.forName(driver);

            Connection connection = DriverManager.getConnection(url, user, password);

            out.println("Success");

            connection.close();
        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}
