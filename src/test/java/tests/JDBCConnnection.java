package tests;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import utilities.Database;

public class JDBCConnnection {

	String oracleDbURL = "jdbc:oracle:thin:@ec2-18-220-139-49.us-east-2.compute.amazonaws.com:1521:xe"; // Config.getProperty("serverURl");
	String oracleDbUsername = "hr";
	String oracleDbPassword = "hr";

	@Test(enabled = false)
	public void oracleJDBC() throws SQLException {
		Connection connection = DriverManager.getConnection(oracleDbURL, oracleDbUsername, oracleDbPassword);
		Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet resultSet = statement.executeQuery("Select * from countries");
		resultSet.next();
		// System.out.println(resultSet.getString("country_name"));
		// System.out.println(resultSet.getInt("region_id"));
		// System.out.println(resultSet.getString(1));
		//
		// resultSet.next();
		// resultSet.next();
		// System.out.println(resultSet.getString("country_name"));
		// System.out.println(resultSet.getInt("region_id"));
		// System.out.println(resultSet.getString(1));

		// while(resultSet.next()) {
		// System.out.println(resultSet.getString("country_name"));
		// }

		System.out.println(resultSet.getRow());

		resultSet.previous();
		resultSet.first();
		resultSet.last();

		System.out.println(resultSet.getRow());

		resultSet.close();
		statement.close();
		connection.close();
	}

//	@Test
	public void oracleJDBC1() throws SQLException {

		Connection connection = DriverManager.getConnection(oracleDbURL, oracleDbUsername, oracleDbPassword);
		Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

//		String sqlQuery = "Select employee_id, last_name, salary from employees ";
		
		String sqlQuery = "Select * from employees";

		ResultSet resultSet = statement.executeQuery(sqlQuery);
		
//		DatabaseMetaData  dbMeta = connection.getMetaData();
//		System.out.println("User: "+dbMeta.getUserName());
//		System.out.println("Database Type: "+ dbMeta.getDatabaseProductName());
//		
//		ResultSetMetaData rsMeta = resultSet.getMetaData();
//		
//		System.out.println("columns count: "+ rsMeta.getColumnCount());
//		System.out.println(rsMeta.getColumnName(1));
//		
//		for(int i= 1 ; i <= rsMeta.getColumnCount(); i++) {
//			System.out.println("Columns: "+ rsMeta.getColumnName(i));
//		}
		
		List<Map<String, Object>> queryData = new ArrayList<>();
		ResultSetMetaData rsMeta = resultSet.getMetaData();
		int columnCount = rsMeta.getColumnCount();
		
		while(resultSet.next()) {
			Map <String, Object> row = new HashMap<>();
			
			for(int column = 1 ; column <= columnCount; column++) {
				row.put( rsMeta.getColumnName(column), resultSet.getObject(column));
			}
			
			queryData.add(row);
		}
		
		for(Map<String, Object> map : queryData) {
			System.out.println(map.get("EMPLOYEE_ID"));
		}
		


	}
	
	
	@Test
	public void dsg() {
		
		for(Map<String, Object> map : Database.runSQLQuery("SELECT *  FROM employees")) {
			System.out.println(map.get("EMPLOYEE_ID"));
		}
		
		
	}
	
	

	
	

}
