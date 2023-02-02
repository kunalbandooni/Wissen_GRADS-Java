import java.sql.*;
import java.io.*;

public class JdbcDemo7
{
	public static void main(String[] args) 
	{
		try
		{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","tiger");
			
			// when we do this, Transaction methods have started
			con.setAutoCommit(false);

			PreparedStatement pstmt = con.prepareStatement("insert into TEACHERS values(?, ?, ?)");

			String name = "Mayur";
			int classNo = 15;
			String subject = "Art";

			pstmt.setString(2, name);
			pstmt.setInt(1, classNo);
			pstmt.setString(3, subject);

			pstmt.execute();

			con.commit();
			//con.rollback();
			
			pstmt.close();
			con.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}