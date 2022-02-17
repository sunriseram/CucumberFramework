package utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBUtils {
	// in order to work with JDBC and connect to a Database, we need to follow certain steps:
		// 1. we need to create a Connection reference with DriverManager object, followed by using the getConnection function
		// 2. in order to execute a SQL query, we need to create a statement reference variable
	    // object type is connection object, using createStatement function.
		// if we want to execute a query and store the data, we store data in ResultSet object. 
		
		private String dbHostName = "jdbc:mysql://database-1.cbf9mjnqgnfr.us-east-2.rds.amazonaws.com:3306/stock_trading_tracker";
		private String username = "admin";
		private String password = "Password123!";
		
		private Connection connection;
		private Statement statement;
		private ResultSet resultset;
		private ResultSetMetaData rsmd;
		
		// select query
		public List<String> selectARecord(String query){
			List<String> list = new ArrayList<>();
			try {
				connection = DriverManager.getConnection(dbHostName, username, password);
				System.out.println("DB connection established.");
				statement = connection.createStatement();
				resultset = statement.executeQuery(query);
				rsmd = resultset.getMetaData();
				
				resultset.next();
				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					list.add(resultset.getString(i));
				}
				
				connection.close();
			} catch (SQLException e) {
			System.out.println("DB connection Not established.");
				e.printStackTrace();
			}
			return list;
		}
		
		
		// insert query
		public void insertRecord(String query) {
			try {
				connection = DriverManager.getConnection(dbHostName, username, password);
				System.out.println("DB connection established.");
				statement = connection.createStatement();
				statement.executeUpdate(query);
				connection.close();
			} catch (SQLException e) {
				System.out.println("DB connection Not established.");
				e.printStackTrace();
			}
		}
		
		
		// delete query
		public void deleteRecord(String query) {
			try {
				connection = DriverManager.getConnection(dbHostName, username, password);
				System.out.println("DB connection established.");
				statement = connection.createStatement();
				statement.executeUpdate(query);
				connection.close();
			} catch (SQLException e) {
				System.out.println("DB connection Not established.");
				e.printStackTrace();
			}
		}
		
		// update query
		public void updateRecord(String query) {
			try {
				connection = DriverManager.getConnection(dbHostName, username, password);
				System.out.println("DB connection established.");
				statement = connection.createStatement();
				statement.executeUpdate(query);
				connection.close();
			} catch (SQLException e) {
				System.out.println("DB connection Not established.");
				e.printStackTrace();
			}
		} 
}
