import java.sql.*;
import java.io.*;

public class JdbcDemo2
{
	public static void main(String[] args) 
	{
		try
		{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","tiger");
			
			PreparedStatement pstmt = con.prepareStatement("insert into TEACHERS values(?, ?, ?)");

			String name = "Kunal";
			int classNo = 14;
			String subject = "Music";

			pstmt.setString(2, name);
			pstmt.setInt(1, classNo);
			pstmt.setString(3, subject);

			pstmt.execute();
			pstmt.execute("commit");
			
			pstmt.close();
			con.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}