package tests;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import utilities.DBType;
import utilities.Database;

public class TestDatabaseUtility {

	@Test
	public void countTest() throws SQLException {
		
		Database.establishConnection(DBType.ORACLE);
		int rowCount = Database.getRowCount("select * from employees where job_id = 'IT_PROG'");
		System.out.println(rowCount);
		Assert.assertTrue(rowCount > 0);
		
	}
	
	@Test
	public void nameTestBy() throws SQLException {
		Database.establishConnection(DBType.ORACLE);
		System.out.println(Database.runSQLQuery("SELECT * From employees WHERE job_id =105"));
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
