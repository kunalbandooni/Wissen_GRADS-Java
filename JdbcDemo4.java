import java.sql.*;

public class JdbcDemo4
{
	public static void main(String[] args) 
	{
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","tiger");
			
			Statement stmt = con.createStatement();
			
			stmt.addBatch("insert into EMP values('Kunal', 27)");
			stmt.addBatch("insert into EMP values('James', 25)");

			stmt.executeBatch();

			stmt.close();
			con.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}