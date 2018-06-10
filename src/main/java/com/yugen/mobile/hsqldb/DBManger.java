package com.yugen.mobile.hsqldb;

import java.sql.*;

/**
 * Description:
 * <p>
 * Created by A.T on 2018/06/09
 */
public class DBManger {
    private static String JDBC_URL = "jdbc:hsqldb:file:hsqldb/mobiledb";
    private static String USER = "muser";
    private static String PASSWORD = "muser";

    public static void executeUpdate(String sql) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)){
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ResultSet executeQuery(String sql) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)){
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            return statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean isExsit(String sql) {
        try {
            ResultSet rs = DBManger.executeQuery(sql);
            if (rs != null && rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String dump(String sql) {
        StringBuilder result = new StringBuilder();
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)){
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            if (rs != null) {
                try {
                    ResultSetMetaData md = rs.getMetaData();
                    for (int i = 1; i <= md.getColumnCount(); i++) {
                        result.append(md.getColumnName(i)+",  ");
                    }

                    result.append("\n");
                    while (rs.next()) {
                        for (int i = 1; i <= md.getColumnCount(); i++) {
                            result.append(rs.getObject(i)+",  ");
                        }
                        result.append("\n");
                    }
                } catch (SQLException e) {
                    result.append("SQLException occured\nError message:" + e.getMessage());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result.toString();
    }


    public static void demo() {
        DBHandler dbh = null;//DBHandler class to help database operations
        Connection connection = null;//Pure Connection class

        try {
            /*
             * Connect and use DBHandler class methods. DBHandler is a wrapper class for JDBC classes
             * Print all data in the table
             */
            String sql = "SELECT * FROM Employee";
            String connectionString = "jdbc:hsqldb:file:hsqldb/mobiledb";


            dbh = new DBHandler(connectionString);
            dbh.executeQuery(sql);
            dbh.printResultSet();
            //Randomly update department and title of the dummy entry
            sql = "UPDATE Employee SET Department='" + Math.random() + "',Title='" + Math.random() + "' WHERE Id=21";
            int rowCount = dbh.executeNonQuery(sql);
            System.out.printf("%d row(s) affected\n", rowCount);

            /*
             * Use Connection, Statement, ResultSet classes(alternative, jdbc way)
             * Write names of employees
             */
            connection = DriverManager.getConnection("jdbc:hsqldb:file:hsqldb/mobiledb");//Connect to database
            Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);//Create statement to obtain scrollable ResultSet
            ResultSet rs = stmt.executeQuery("SELECT * FROM Employee");//Make the query
            while (rs.next()) {//Print names in normal order
                System.out.println(rs.getObject("FirstName"));
            }
            while (rs.previous()) {//Print names in reverse order
                System.out.println(rs.getObject("FirstName"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (dbh != null) {
                    dbh.close();
                }
            } catch (SQLException e) {
                System.out.println("Can't close DBHandler object\nReason:" + e.getMessage());
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println("Can't close Connection object\nReason:" + e.getMessage());
            }
        }
    }
}
