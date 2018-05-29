package utilities;

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

public class Database {

	private static Connection connection;
	private static Statement statement;
	private static ResultSet resultSet;
	private static int rowCount;

	
	/**
	 * Establishes the Connection to Database
	 * Takes: Database URL, user name and password from properties file
	 * 
	 * @param dbType
	 */
	public static void establishConnection(DBType dbType) {
		switch (dbType) {
		case ORACLE:
			String oracleDbURL = Config.getProperty("oracleURL");
			String oracleDbUsername = Config.getProperty("oracleUser");
			String oracleDbPassword = Config.getProperty("oraclePassword");
			try {
				connection = DriverManager.getConnection(oracleDbURL, oracleDbUsername, oracleDbPassword);
				statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		default:
			connection = null;

		}

	}
	
	/**
	 * Returns count of displayed rows of data
	 * 
	 * @param sqlQuery
	 * @return rows
	 */
	public static int getRowCount(String sqlQuery) {
		try {
			resultSet = statement.executeQuery(sqlQuery);
			resultSet.last();
			return resultSet.getRow();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	
	/**
	 * Accepts SQL Query and Returns data from database in List<Map> format
	 * Where each row is a element of Maps in List 
	 * 
	 * @param sqlQuery
	 * @return
	 */
	public static List<Map<String, Object>> runSQLQuery(String sqlQuery){
		try {
			resultSet = statement.executeQuery(sqlQuery);
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
	
	
	/**
	 * Closes the connection to database
	 */
	public static void closeConnection() {
		
		try {
			
			if(statement != null) {
				statement.close();
			}
			if(connection != null) {
				connection.close();
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}

	}
	
	
	
	
	
	

}
