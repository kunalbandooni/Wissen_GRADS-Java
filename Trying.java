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
		System.out.println("\tAnnual Salary \t : " + salary * 12);
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

// Menu Driven code lies here...
class MenuDriven
{
	// method for printing menu and inputting choice
	private void menuPrint()
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
	private void createMenuPrint()
	{
		System.out.println("----------------------------------");
		System.out.println("\t1. Clerk");
		System.out.println("\t2. Programmer");
		System.out.println("\t3. Manager");
		System.out.println("\t4. Exit");
		System.out.println("----------------------------------");
		System.out.print("\tEnter Choice : ");
	}

	// the main Menu function...
	public void mainMenu()
	{
		Scanner sc = new Scanner(System.in);
		int choice = 0;
		int clerkCount = 0, programmerCount = 0, managerCount = 0;

		Clerk[] c = new Clerk[100000];
		Programmer[] p = new Programmer[100000];
		Manager[] m = new Manager[100000];
		
		do{
			menuPrint();

			choice = sc.nextInt();

			System.out.println("----------------------------------");
			
			switch(choice)
			{
				case 1:
					int n;
					
					do{
						createMenuPrint();

						n = sc.nextInt();

						System.out.println("----------------------------------");
						switch(n)
						{	
							case 1:
								c[clerkCount] = new Clerk();
								clerkCount++;
								break;
							case 2:
								p[programmerCount] = new Programmer();
								programmerCount++;
								break;
							case 3:
								m[managerCount] = new Manager();
								managerCount++;
								break;
							case 4:
								System.out.println("\tCREATED ALL");
								break;
							default:
								System.out.println("\nWrong choice entered\n");
						}
					}while(n != 4);

					break;

				case 2:
					
					for(int i=0; i<clerkCount ;i++)
						c[i].display();
					
					for(int i=0; i<programmerCount ;i++)
						p[i].display();
					
					for(int i=0; i<managerCount ;i++)
						m[i].display();
					
					break;
				
				case 3:

					for(int i=0; i<clerkCount ;i++)
						c[i].raiseSalary();

					for(int i=0; i<programmerCount ;i++)
						p[i].raiseSalary();

					for(int i=0; i<managerCount ;i++)
						m[i].raiseSalary();

					break;
				
				case 4:
					System.out.println("\tThank you for using our services!! Have a nice day =D");
					break;
				
				default:
					System.out.println("\nWrong choice entered\n");
			}
		}while(choice != 4);
	}
}
public class Trying
{
	public static void main(String[] args) {
		MenuDriven m = new MenuDriven();
		m.mainMenu();
	}
}