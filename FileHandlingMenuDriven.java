// Package
package Project;

// Import
import java.util.*;
import java.io.*;
import java.util.regex.*;

// This is the Employee Class
// abstract so that no one can make its object...
abstract class Employee
{
	private String name;
	private int age;
	private double salary;
	private String designation;

	// counts number of Employees total made
	static int empCount = 0;

	// Constructor - assign the values...
	protected Employee(double salary, String designation)
	{
		this.salary = salary;
		this.designation = designation;

		this.input(designation);
	}

	// Function to input the values from the user...
	protected void input(String designation)
	{
		Input input = new Input();

		System.out.println("\n\tEnter the following details for " + designation + " : ");
		
		// input name
		do{
			System.out.print("\tName \t\t : ");
			this.name = new Scanner(System.in).nextLine();
		}while(!input.validateName(this.name));

		// input age
		do{
			System.out.print("\tAge \t\t : ");
			this.age = input.ageInput();
		}while(!(this.age >= 21 && this.age <= 60));

		// file handling here...
		try
		{
			File dir = new File("FILE HANDLING DEMO");
			dir.mkdir();

			File f = new File(dir, "DETAILS.txt");
			f.createNewFile();

			RandomAccessFile raf = new RandomAccessFile(f, "rw");
			raf.seek(raf.length());
			raf.writeBytes(this.name + "|" + this.age + "|" + this.salary + "|" + this.designation + "\n");
			raf.close();

			System.out.println("\n\tDETAILS WRITTEN");
		}
		catch(Exception e)
		{
			System.out.println("\nFILE ERROR" + e);
		}

		// Counter++
		empCount++;
	}
	
	// method to display all the details
	final public String toString()
	{
		String message = "--------------------------------------------------" + 
				"\n\tEmployee Details for " + designation + ": \n" +
				"\n\tName \t\t : " + name + 
				"\n\tAge \t\t : " + age + 
				"\n\tSalary \t\t : " + salary +
				"\n\tDesignation \t : " + designation;
		return message;
	}

	// abstract so each time this method has to be made when inherited
	protected abstract void raiseSalary();

	// method to get salary
	protected double getSalary()
	{
		return this.salary;
	}

	// method to set salary value
	protected void setSalary(double salary)
	{
		if(salary >= 0)
			this.salary = salary;
	}

}

// class Clerk
final class Clerk extends Employee
{
	// Constructor
	public Clerk()
	{
		super(10000, "CLERK");
	}

	// method to raise Salary for 'Clerk'
	protected void raiseSalary()
	{
		setSalary(getSalary() + 2000);
	}
}

// class Programmer
final class Programmer extends Employee
{
	// Constructor
	public Programmer()
	{
		super(25000, "PROGRAMMER");
	}

	// raise salary for Programmer
	protected void raiseSalary()
	{
		setSalary(getSalary() + 5000);
	}
}

// class Manager
final class Manager extends Employee
{
	// Constructor
	public Manager()
	{
		super(80000, "MANAGER");
	}

	// raise salary for Manager
	protected void raiseSalary()
	{
		setSalary(getSalary() + 10000);
	}
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

	// method to validate name [a-zA-Z.-]
	protected boolean validateName(String name)
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

class UserException extends Exception
{
	// Constructor
	public UserException()
	{
		super();
	}

	// Message passed in Constructor
	public UserException(String message)
	{
		super(message);
	}
}

// this is the main Class
public class FileHandlingMenuDriven
{

	// method for printing menu and inputting choice
	private void menuPrint()
	{
		System.out.println("--------------------------------------------------");
		System.out.println("\t1. Create");
		System.out.println("\t2. Display");
		System.out.println("\t3. Exit");
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

	// the main function...
	//public static void main(String Args[])
	public void run()
	{
		int choice = 0, n = 0;

		Input input = new Input();
		ArrayList<Employee> empObject = new ArrayList<Employee>(3);

		do{
			menuPrint();

			choice = input.inputInt();
			//choice = new Scanner(System.in).nextInt();

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

					try
					{
						File f = new File("FILE HANDLING DEMO\\DETAILS.txt");
						if(f.exists())
						{
							BufferedReader fr = new BufferedReader(new FileReader("FILE HANDLING DEMO\\DETAILS.txt"));
							String line = null;
							while( (line = fr.readLine()) != null)
							{
								//System.out.println(line);
								StringTokenizer st = new StringTokenizer(line, "|");
								System.out.println("--------------------------------------------------");
								System.out.println("\tName \t\t : " + st.nextToken());
								System.out.println("\tAge \t\t : " + st.nextToken());
								System.out.println("\tSalary \t\t : " + st.nextToken());
								System.out.println("\tDesignation \t : " + st.nextToken());
							}

							fr.close();
						}
						else
							System.out.println("\tNO SUCH FILE");
					}
					catch(Exception e)
					{
						System.out.println("\tFILE ERROR: " + e);
					}

					break;

				// Bii Bii... =D
				case 3:
					System.out.println("\tTotal number of employees joined " + Employee.empCount);
					System.out.println("--------------------------------------------------");
					System.out.println("\n\tThank you for using our services!! Have a nice day =D");
					break;

				default:
					System.out.println("\tINVALID VALUE ENTERED");
			}
		}while(choice != 3);
	}
}