import java.sql.*;

public class JdbcDemo
{
	public static void main(String[] args) 
	{
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","tiger");
			Statement stmt = con.createStatement();

			stmt.executeUpdate("insert into TEACHERS values(9, 'Sushma', 'SST')");
			stmt.executeUpdate("commit");

			/*ResultSet rs = stmt.executeQuery("select * from TEACHERS");
			while(rs.next())
			{
				System.out.println("Name : " + rs.getString(2));
				System.out.println("Class : " + rs.getInt(1));
				System.out.println("Subject : " + rs.getString(3));
			}
			rs.close();*/
			
			stmt.close();
			con.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}