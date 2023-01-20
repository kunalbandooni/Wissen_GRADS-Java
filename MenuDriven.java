// Import
import java.util.Scanner;

// This is the Employee Class
class Employee
{
	private String name;
	private int age;
	private double salary;
	private String designation;

	// Constructor - assign the values...
	public Employee(double salary, String designation)
	{
		this.input(designation);

		this.salary = salary;
		this.designation = designation;
	}

	// Function to input the values from the user...
	private void input(String designation)
	{
		Scanner sc = new Scanner(System.in);

		System.out.println("\n\tEnter the following details for " + designation + " : ");
		
		System.out.print("\tName \t\t : ");
		this.name = sc.nextLine();

		System.out.print("\tAge \t\t : ");
		this.age = sc.nextInt();
	}
	
	// method to display all the details
	public void display()
	{
		System.out.println("----------------------------------");
		System.out.println("\n\tEmployee Details for " + designation + ": \n");
		System.out.println("\tName \t\t : " + name);
		System.out.println("\tAge \t\t : " + age);
		System.out.println("\tSalary \t : " + salary);
		System.out.println("\tDesignation \t : " + designation);
	}

	// method to raise Salary for 'Clerk'
	public void raiseSalary()
	{
		this.salary += 2000;
		
		//System.out.println("\nRaised " + amount + " for " + name);
		display();
	}

	// method to get salary
	protected double getSalary()
	{
		return salary;
	}

	// method to set salary value
	protected void setSalary(double salary)
	{
		if(salary >= 0)
		this.salary = salary;
	}

}

// class Clerk
class Clerk extends Employee
{
	// Constructor
	public Clerk()
	{
		super(10000, "CLERK");
	}

	// raise salary for Clerk done in Employee...
}

// class Programmer
class Programmer extends Employee
{
	// Constructor
	public Programmer()
	{
		super(25000, "PROGRAMMER");
	}

	// raise salary for Programmer
	public void raiseSalary()
	{
		setSalary(getSalary() + 5000);
		display();
	}
}

// class Manager
class Manager extends Employee
{
	// Constructor
	public Manager()
	{
		super(80000, "MANAGER");
	}

	// raise salary for Manager
	public void raiseSalary()
	{
		setSalary(getSalary() + 10000);
		display();
	}
}

// this is the main Class
public class MenuDriven
{
	// method for printing menu and inputting choice
	private static void menuPrint()
	{
		System.out.println("----------------------------------");
		System.out.println("\t1. Create");
		System.out.println("\t2. Display");
		System.out.println("\t3. Raise Salary");
		System.out.println("\t4. Exit");
		System.out.println("----------------------------------");
		System.out.print("\tEnter Choice : ");
	}

	// method for printing create - menu and inputting choice
	private static void createMenuPrint()
	{
		System.out.println("----------------------------------");
		System.out.println("\t1. Clerk");
		System.out.println("\t2. Programmer");
		System.out.println("\t3. Manager");
		System.out.println("\t4. Exit");
		System.out.println("----------------------------------");
		System.out.print("\tEnter Choice : ");
	}

	// the main function...
	public static void main(String Args[])
	{
		Scanner sc = new Scanner(System.in);
		int choice = 0;
		//int clerkCount = 0, programmerCount = 0, managerCount = 0;
		int empCount = 0;

		Employee[] empObject = new Employee[100000];

		/* 
		Clerk[] c = new Clerk[100000];
		Programmer[] p = new Programmer[100000];
		Manager[] m = new Manager[100000];


			This gives a complexity, 
			if a new designation is added, 
			it makes it problematic.

			So, to make if flexible enough
			use the Emp[]
		
		*/


		do{
			menuPrint();

			choice = sc.nextInt();

			System.out.println("----------------------------------");
			
			switch(choice)
			{
				// Create
				case 1:
					int n;
					
					do{
						createMenuPrint();

						n = sc.nextInt();

						System.out.println("----------------------------------");
						switch(n)
						{	
							// Clerk creation...
							case 1:
								empObject[empCount] = new Clerk();
								empCount++;
								break;

							// Programmer creation...
							case 2:
								empObject[empCount] = new Programmer();
								empCount++;
								break;

							// Manager creation...
							case 3:
								empObject[empCount] = new Manager();
								empCount++;
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
					
					for(int i=0; i<empCount ;i++)
						empObject[i].display();
					
					break;
				
				// Raise Salary
				case 3:

					for(int i=0; i<empCount ;i++)
						empObject[i].raiseSalary();
					break;

				case 4:
					System.out.println("\tThank you for using our services!! Have a nice day =D");
					break;

				default:
					System.out.println("\tINVALID VALUE ENTERED");
			}
		}while(choice != 4);
	}
}