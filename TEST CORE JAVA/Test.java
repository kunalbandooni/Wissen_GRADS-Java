// Import
import java.util.*;
import java.util.regex.*;
import java.io.*;
import java.sql.*;

/*// Enum standard
Standard standard
{
	"NURSERY", "KG", "I", "II", "III", "IV"
}*/

// This is the Student Class
class Student
{
	private int regNo;
	private String name;
	private int age;
	private int standard;
	private static String school = "DAV Public School";

	private static Connection con;
	private static Statement stmt;

	static
	{
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "tiger");
			stmt = con.createStatement();
			stmt.executeUpdate("create table STUDENT( REGISTERATION_NO number(6) primary key, NAME varchar(25), AGE number(2), STANDARD varchar(7), SCHOOL varchar(25) )");
		}
		catch(Exception e){
			System.out.println("\t" + e);
		}
	}

	// Constructor - assign the values...
	Student()
	{
	}

	// Function to input the values from the user...
	protected void input()
	{
		Input input = new Input();

		System.out.println("\n\tEnter the following details for student : ");
		
		do{
			System.out.print("\tRegisteration Number : ");
			this.regNo = input.inputInt();
		}while(!input.validate(this.regNo));

		do{
			System.out.print("\tName \t\t : ");
			this.name = new Scanner(System.in).nextLine();
		}while(!input.validate(this.name));

		do{
			System.out.print("\tAge \t\t : ");
			this.age = input.ageInput();
		}while(!(this.age >= 1 && this.age <= 99));

		do{
			System.out.print("\tStandard \t\t : ");
			this.standard = input.standardInput();
		}while(!(this.standard >= 0 && this.standard <= 12));
	}

	// Adds data to STUDENT table
	public void add()
	{
		this.input();
		try
		{	
			PreparedStatement pstmt = con.prepareStatement("insert into STUDENT values(?, ?, ?, ?, ?)");

			pstmt.setInt(1, this.regNo);
			pstmt.setString(2, this.name);
			pstmt.setInt(3, this.age);
			pstmt.setInt(4, this.standard);
			pstmt.setString(5, Student.school);

			pstmt.execute();

			pstmt.close();

			System.out.println("\n\tADDED STUDENT");
		}
		catch(Exception e)
		{
			System.out.println("\t" + e);
		}
	}

	public void printAll()
	{
		try
		{
			ResultSet rs = stmt.executeQuery("select * from STUDENT order by REGISTERATION_NO");
			if(rs.next()){
				while(rs.next())
				{
					System.out.println("\n--------------------------------------------------" + 
						"\n\tRegistration No \t : " + rs.getInt(1) +
						"\n\tName \t\t : " + rs.getString(2) + 
						"\n\tAge \t\t : " + rs.getInt(3) + 
						"\n\tStandard \t\t : " + rs.getInt(4) +
						"\n\tSchool \t : " + rs.getString(5) );
				}
			}
			else
				System.out.println("\n\tNO RECORD FOUND!!");
			rs.close();
		}
		catch(Exception e)
		{
			System.out.println("\t" + e);
		}
	}

	public void searchByRegisterationNo()
	{
		try
		{
			Input input = new Input();

			System.out.print("\tEnter Registration No : ");
			int registerationNo = input.inputInt();

			ResultSet rs = stmt.executeQuery("select * from STUDENT where REGISTERATION_NO = " + registerationNo);

			if(rs.next())
			{
				System.out.println("\n--------------------------------------------------" + 
						"\n\tRegistration No \t : " + rs.getInt(1) +
						"\n\tName \t\t : " + rs.getString(2) + 
						"\n\tAge \t\t : " + rs.getInt(3) + 
						"\n\tStandard \t\t : " + rs.getInt(4) +
						"\n\tSchool \t : " + rs.getString(5) );

				System.out.println("\n\tDo you want to delete this record ?");
				System.out.print("\tEnter choice (Y/N) : ");
				char confirm = input.inputChar();

				if(confirm == 'Y'){
					stmt.executeUpdate("delete from STUDENT where REGISTERATION_NO = " + registerationNo);
					System.out.println("\n\tDELETED RECORD WITH REGISTERATION NO " + registerationNo);
				}
				else
					System.out.println("\n\tRECORD NOT DELETED");
			}
			else
				System.out.println("\n\tNO RECORD FOUND WITH SUCH REGISTERATION NO!!");
			
			rs.close();
		}
		catch(Exception e)
		{
			System.out.println("\t" + e);
		}
	}
}

// User Exception
class UserException extends Exception
{
	// Constructor
	public UserException()
	{	super();	}

	// Message passed in Constructor
	public UserException(String message)
	{	super(message);	}
}

// Class for checking RunTime Exceptions while taking input
class Input
{
	// method to input integer value and throw exception
	private int inputIntCheck() throws InputMismatchException
	{
		int intValue = 0;
		intValue = new Scanner(System.in).nextInt();
		return intValue;
	}

	// Input integer value using try-catch
	protected int inputInt()
	{
		int intValue = 0;
		
		try
		{
			intValue = inputIntCheck();
			return intValue;
		}
		catch(InputMismatchException e)
		{
			System.out.println("--------------------------------------------------");
			System.out.println("\tInput Mismatch Exception Occured\n\tMake sure you are entering a number");
		}
		catch(Exception e)
		{
			System.out.println("\tUnknown Error Occured");
		}

		// If error occurs, 0 is passed...
		return 0;
	}

	// Input a single character
	protected char inputChar()
	{
		char ch = 'N';

		try
		{
			ch = new Scanner(System.in).next().charAt(0);
		}
		catch(InputMismatchException e)
		{
			System.out.println("--------------------------------------------------");
			System.out.println("\tInput Mismatch Exception Occured\n\tMake sure you are entering a number");
		}
		catch(Exception e){
			System.out.println("\n\tUnknown Error Occured");
		}

		return ch;
	}

	// Input age for students
	protected int ageInput()
	{
		int age = 0;

		try
		{
			// input age using the same method: Input integer value using try-catch
			age = inputInt();

			// checking range of age...
			if(!(age >= 1 && age <= 99))
				throw new UserException("AGE NOT IN RANGE\n");

			return age;
		}
		catch(UserException e)
		{
			System.out.println("--------------------------------------------------");
			System.out.println("\tAge must be between 1 to 99 (both inclusive)");
			System.out.println("\t" + e);
		}
		catch(Exception e)
		{
			System.out.println("\tUnknown Error Occured");
		}

		// put age as 0 so that value is again re-filled...
		return 0;
	}

	// Input standard for students
	protected int standardInput()
	{
		int standard = 0;

		try
		{
			// input standard using the same method: Input integer value using try-catch
			standard = inputInt();

			// checking range of standard...
			if(!(standard >= 0 && standard <= 12))
				throw new UserException("STANDARD NOT IN RANGE\n");

			return standard;
		}
		catch(UserException e)
		{
			System.out.println("--------------------------------------------------");
			System.out.println("\tStandard must be between 0 to 12 (both inclusive)");
			System.out.println("\t" + e);
		}
		catch(Exception e)
		{
			System.out.println("\tUnknown Error Occured");
		}

		// put standard as -1 so that value is again re-filled...
		return -1;
	}

	// method to validate regiteration No
	protected boolean validate(int registerationNo)
	{
		if(registerationNo <= 0)
		{
			System.out.println("\tRegistration Number cannot be Negative or Zero!!\n");
			return false;
		}
		else
			return true;
	}

	// method to validate name [a-zA-Z.-]
	protected boolean validate(String name)
	{
		try
		{
			if(name.length() < 5 || name.length() > 25)
				throw new UserException("Inadequate length of name");

			String regex = "[a-zA-Z .-]+";

			if(!(Pattern.matches(regex, name)))
				throw new InputMismatchException("Inadequate Characters Used");

			return true;
		}
		catch(UserException e)
		{
			System.out.println("--------------------------------------------------");
			System.out.println("\tName length must be between 5 to 25 characters");
			System.out.println("\t" + e);
		}
		catch(InputMismatchException e)
		{
			System.out.println("--------------------------------------------------");
			System.out.println("\t" + e);
			System.out.println("\tOnly use following characters: a-z A-Z '.' '-' 'space'");
		}
		catch(Exception e)
		{
			System.out.println("\tUnknown Error Occured");
		}

		return false;
	}
}

// this is the main Class
public class Test
{
	// method for printing menu and inputting choice
	private static void menuPrint()
	{
		System.out.println("--------------------------------------------------");
		System.out.println("\t1. Add");
		System.out.println("\t2. Search");
		System.out.println("\t3. Result");
		System.out.println("\t4. Exit");
		System.out.println("--------------------------------------------------");
		System.out.print("\tEnter Choice : ");
	}

	// method for printing search-menu and inputting choice
	private static void searchMenuPrint()
	{
		System.out.println("\tSearch according to :\n");
		System.out.println("\t1. By Registration No.");
		System.out.println("\t2. All Students");
		System.out.println("--------------------------------------------------");
		System.out.print("\tEnter Choice : ");
	}

	// the main function...
	public static void main(String args[])
	{
		int choice = 0;

		Input input = new Input();
		Student stObject = new Student();

		do{
			menuPrint();

			choice = input.inputInt();

			System.out.println("--------------------------------------------------");
			
			switch(choice)
			{
				// Add
				case 1:

					stObject.add();
					
					break;

				// Search
				case 2:

					do
					{
						searchMenuPrint();
						choice = input.inputInt();
						switch(choice)
						{
							case 1:
								stObject.searchByRegisterationNo();
								break;
							case 2:
								stObject.printAll();
								break;
							default:
								System.out.println("\tINVALID VALUE ENTERED");
						}
					}while(choice != 1 || choice != 2);

					break;
				
				// Result
				case 3:
					
					//Student.result();

					break;

				// Bii Bii... =D
				case 4:

					System.out.println("\n\tThank you for using our services!! Have a nice day =D");
					System.out.println("\n\n\n\n\tClosing in 5 seconds...");

					try{	Thread.sleep(5000);	}
					catch(Exception e){	System.out.println("\t" + e);	}

					break;

				default:
					System.out.println("\tINVALID VALUE ENTERED");
			}
		}while(choice != 4);
	}
}