// Any table can be printed through this...
import java.sql.*;
import java.util.*;

public class JdbcDemo6
{
	public static void main(String[] args) 
	{
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","tiger");
			
			Statement stmt = con.createStatement();

			System.out.print("Enter Table Name : ");
			String tname = new Scanner(System.in).next();

			ResultSet rs = stmt.executeQuery("select * from " + tname);

			ResultSetMetaData rsmd = rs.getMetaData();

			int n = rsmd.getColumnCount();

			while(rs.next())
			{
				for(int i = 1; i <= n; i++)
				{
					System.out.println(rsmd.getColumnName(i) + " : "+ rs.getString(i));
				}
				System.out.println();
			}

			rs.close();
			stmt.close();
			con.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}