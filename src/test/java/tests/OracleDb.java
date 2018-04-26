package tests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OracleDb {

	private static Connection connection;
	private static Statement statement;
	private static String oracleDbURL = "jdbc:oracle:thin:@ec2-18-220-139-49.us-east-2.compute.amazonaws.com:1521:xe"; // Config.getProperty("serverURl");
	private static String oracleDbUsername = "hr";
	private static String oracleDbPassword = "hr";

	
	public static void establichConnection() {
		try {
			connection = DriverManager.getConnection(oracleDbURL, oracleDbUsername, oracleDbPassword);
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

		} catch (SQLException e) {
			System.out.println("Couldn't connect to database: " + oracleDbURL);
			e.printStackTrace();
		}

	}

	public static List<Map<String, Object>> runSqlQuery(String sqlQuery) {
		if(connection == null || statement == null) {
			establichConnection();
		}
		try {
			ResultSet resultSet = statement.executeQuery(sqlQuery);
			ResultSetMetaData rsMeta = resultSet.getMetaData();

			List<Map<String, Object>> queryData = new ArrayList<>();
			int columnCount = rsMeta.getColumnCount();

			while (resultSet.next()) {
				Map<String, Object> row = new HashMap<>();

				for (int column = 1; column <= columnCount; column++) {
					row.put(rsMeta.getColumnName(column), resultSet.getObject(column));
				}
				queryData.add(row);
			}

			resultSet.close();
			return queryData;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void closeConnection() {

		try {

			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
