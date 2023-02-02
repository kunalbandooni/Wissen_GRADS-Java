// Package 
package project;

// Import
import java.util.*;
import java.util.regex.*;
import java.io.*;
import java.sql.*;

// This is the Employee Class
// abstract so that no one can make its object...
abstract class Employee
{
	private int employeeID;
	private String name;
	private int age;
	private double salary;
	private String designation;

	// Constructor - assign the values...
	protected Employee(double salary, String designation)
	{
		this.input(designation);

		this.salary = salary;
		this.designation = designation;
	}

	// Function to input the values from the user...
	protected void input(String designation)
	{
		Input input = new Input();

		System.out.println("\n\tEnter the following details for " + designation + " : ");
		
		do{
			System.out.print("\tEmployee ID \t : ");
			this.employeeID = input.inputInt();
		}while(!input.validate(this.employeeID));

		do{
			System.out.print("\tName \t\t : ");
			this.name = new Scanner(System.in).nextLine();
		}while(!input.validate(this.name));

		do{
			System.out.print("\tAge \t\t : ");
			this.age = input.ageInput();
		}while(!(this.age >= 21 && this.age <= 60));
	}

	// returns employeeID
	public int getEmployeeID()
	{	return this.employeeID;	}

	// returns name
	public String getName()
	{	return this.name;	}

	// returns age
	public int getAge()
	{	return this.age;	}

	// method to get salary
	public double getSalary()
	{	return this.salary;	}

	// returns designation
	public String getDesignation()
	{	return this.designation;	}
}

// class Clerk
final class Clerk extends Employee
{
	// Constructor
	public Clerk()
	{	super(10000, "CLERK");	}
}

// class Programmer
final class Programmer extends Employee
{
	// Constructor
	public Programmer()
	{	super(25000, "PROGRAMMER");	}
}

// class Manager
final class Manager extends Employee
{
	// Constructor
	public Manager()
	{	super(40000, "MANAGER");	}
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

	// Input age for employee
	protected int ageInput()
	{
		int age = 0;

		try
		{
			// input age using the same method: Input integer value using try-catch
			age = inputInt();

			// checking range of age...
			if(!(age >=21 && age <= 60))
				throw new UserException("AGE NOT IN RANGE\n");

			return age;
		}
		catch(UserException e)
		{
			System.out.println("--------------------------------------------------");
			System.out.println("\tAge must be between 21 to 60 (both inclusive)");
			System.out.println("\t" + e);
		}
		catch(Exception e)
		{
			System.out.println("\tUnknown Error Occured");
		}

		// put age as 0 so that value is again re-filled...
		return 0;
	}

	// method to validate employeeID
	protected boolean validate(int employeeID)
	{
		if(employeeID <= 0)
		{
			System.out.println("\tEmployee ID cannot be Negative or Zero!!\n");
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
			System.out.println("\tString length must be between 5 to 25 characters");
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

// Everything related to JDBC
class JDBC
{
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String username = "system";
	private String password = "tiger";

	// Adds data to EMPLOYEE table
	public void add(Employee empObject)
	{
		try
		{
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, username, password);
			
			PreparedStatement pstmt = con.prepareStatement("insert into EMPLOYEE values(?, ?, ?, ?, ?)");

			int employeeID = empObject.getEmployeeID();
			String name = empObject.getName();
			int age = empObject.getAge();
			double salary = empObject.getSalary();
			String designation = empObject.getDesignation();

			pstmt.setInt(1, employeeID);
			pstmt.setString(2, name);
			pstmt.setInt(3, age);
			pstmt.setDouble(4, salary);
			pstmt.setString(5, designation);

			pstmt.execute();
			pstmt.execute("commit");
			
			pstmt.close();
			con.close();

			System.out.println("\n\tADDED " + designation);
		}
		catch(Exception e)
		{
			System.out.println("\t" + e);
		}
	}

	// Displays EMPLOYEE table ORDER BY NAME
	public void displayByName()
	{	display(1);	}

	// Displays EMPLOYEE table ORDER BY DESIGNATION
	public void displayByDesignation()
	{	display(2);	}

	// Displays EMPLOYEE table ORDER BY AGE
	public void displayByAge()
	{	display(3);	}

	private void display(int ch)
	{
		// Default ch == 1 -> NAME
		String queryVariable = "NAME";

		// Otherwise...
		if(ch == 2)
			queryVariable = "DESIGNATION";
		else if(ch == 3)
			queryVariable = "AGE";

		try
		{
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, username, password);

			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("select * from EMPLOYEE order by " + queryVariable);
			while(rs.next())
			{
				System.out.println("\n--------------------------------------------------" + 
					"\n\tEmployee ID \t : " + rs.getInt(1) +
					"\n\tName \t\t : " + rs.getString(2) + 
					"\n\tAge \t\t : " + rs.getInt(3) + 
					"\n\tSalary \t\t : " + rs.getDouble(4) +
					"\n\tDesignation \t : " + rs.getString(5) );
			}
			rs.close();
			
			stmt.close();
			con.close();
		}
		catch(Exception e)
		{
			System.out.println("\t" + e);
		}
	}

	// Raise Salary for all
	public void raiseSalary()
	{
		try
		{
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, username, password);

			Statement stmt = con.createStatement();

			stmt.executeUpdate("update EMPLOYEE set SALARY = SALARY + 2000 where DESIGNATION = 'CLERK'");
			stmt.executeUpdate("update EMPLOYEE set SALARY = SALARY + 5000 where DESIGNATION = 'PROGRAMMER'");
			stmt.executeUpdate("update EMPLOYEE set SALARY = SALARY + 10000 where DESIGNATION = 'MANAGER'");
			stmt.executeUpdate("commit");
			
			stmt.close();
			con.close();

			System.out.println("\tRaised Salaries");
		}
		catch(Exception e)
		{
			System.out.println("\t" + e);
		}
	}

	// Delete Record with EmployeeID
	public void removeEntry()
	{
		try
		{
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, username, password);
			
			Input input = new Input();

			System.out.print("\tEnter Employee ID : ");
			int employeeID = input.inputInt();

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from EMPLOYEE where EID = " + employeeID);

			if(!rs.next())
				System.out.println("\n\tNO RECORD FOUND WITH SUCH EMPLOYEE ID!!");
			else
			{
				PreparedStatement pstmt = con.prepareStatement("delete from EMPLOYEE where EID = ?");

				pstmt.setInt(1, employeeID);
				pstmt.execute();
				pstmt.execute("commit");
			
				System.out.println("\n\tDELETED RECORD WITH EMPLOYEE ID" + employeeID);

				pstmt.close();
			}
			rs.close();
			stmt.close();
			con.close();
		}
		catch(Exception e)
		{
			System.out.println("\t" + e);
		}
	}
}

// this is the main Class
public class MenuDriven
{
	// method for printing menu and inputting choice
	private void menuPrint()
	{
		System.out.println("--------------------------------------------------");
		System.out.println("\t1. Create");
		System.out.println("\t2. Display");
		System.out.println("\t3. Raise Salary");
		System.out.println("\t4. Remove");
		System.out.println("\t5. Exit");
		System.out.println("--------------------------------------------------");
		System.out.print("\tEnter Choice : ");
	}

	// method for printing create - menu and inputting choice
	private void createMenuPrint()
	{
		System.out.println("--------------------------------------------------");
		System.out.println("\t1. Clerk");
		System.out.println("\t2. Programmer");
		System.out.println("\t3. Manager");
		System.out.println("\t4. Exit");
		System.out.println("--------------------------------------------------");
		System.out.print("\tEnter Choice : ");
	}

	// method for printing display - menu and inputting choice
	private void displayMenuPrint()
	{
		System.out.println("\tDisplay according to which stream:\n");
		System.out.println("\t1. Name");
		System.out.println("\t2. Designation");
		System.out.println("\t3. Age");
		System.out.println("\t4. Exit");
		System.out.println("--------------------------------------------------");
		System.out.print("\tEnter Choice : ");
	}

	// the main function...
	public void startApp()
	{
		int choice = 0, n = 0;

		Input input = new Input();
		JDBC empObject = new JDBC();

		do{
			menuPrint();

			choice = input.inputInt();

			System.out.println("--------------------------------------------------");
			
			switch(choice)
			{
				// Create
				case 1:

					do{
						createMenuPrint();

						n = input.inputInt();

						System.out.println("--------------------------------------------------");
						switch(n)
						{
							// Clerk creation...
							case 1:
								empObject.add(new Clerk());
								break;

							// Programmer creation...
							case 2:
								empObject.add(new Programmer());
								break;

							// Manager creation...
							case 3:
								empObject.add(new Manager());
								break;

							case 4:
								System.out.println("\tCREATED ALL");
								break;

							default:
								System.out.println("\tINVALID VALUE ENTERED");
						}
					}while(n != 4);
					
					break;

				// Display
				case 2:

					displayMenuPrint();
					n = input.inputInt();

					switch(n)
					{	
						// Display according to Name
						case 1:
							empObject.displayByName();
							break;

						// Display according to Designation
						case 2:
							empObject.displayByDesignation();
							break;

						// Display according to Age
						case 3:
							empObject.displayByAge();
							break;

						case 4:
							System.out.println("\tDISPLAYED");
							break;

						default:
							System.out.println("\tINVALID VALUE ENTERED");
					}

					break;
				
				// Raise Salary
				case 3:
					
					empObject.raiseSalary();
					break;

				// Remove
				case 4:

					empObject.removeEntry();
					break;

				// Bii Bii... =D
				case 5:

					System.out.println("\n\tThank you for using our services!! Have a nice day =D");
					System.out.println("\n\n\n\n\tClosing in 5 seconds...");

					try{	Thread.sleep(5000);	}
					catch(Exception e){	System.out.println("\t" + e);	}

					break;

				default:
					System.out.println("\tINVALID VALUE ENTERED");
			}
		}while(choice != 5);
	}
}