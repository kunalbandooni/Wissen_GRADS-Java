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

		display();
	}

	// Function to input the values from the user...
	private void input(String designation)
	{
		Scanner sc = new Scanner(System.in);

		System.out.println("\nEnter the following details for " + designation + " : ");
		
		System.out.print("Name \t\t : ");
		this.name = sc.nextLine();

		System.out.print("Age \t\t : ");
		this.age = sc.nextInt();
	}
	
	// method to display all the details
	public void display()
	{
		System.out.println("\nEmployee Details: \n");
		System.out.println("Name \t\t : " + name);
		System.out.println("Age \t\t : " + age);
		System.out.println("Annual Salary \t : " + salary * 12);
		System.out.println("Designation \t : " + designation);
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
public class InheritanceAssignment
{
	// the main function...
	public static void main(String Args[])
	{
		// Initializations...
		Clerk c1 = new Clerk();
		Programmer p1 = new Programmer();
		Manager m1 = new Manager();
		
		// Displaying in constructor...

		System.out.println("\n==================");
		System.out.println("Raised Salary");
		System.out.println("==================");

		// Change in values...
		c1.raiseSalary();
		p1.raiseSalary();
		m1.raiseSalary();

		// Displaying in raiseSalary()
		c1.display();		// can be called from main()
	}
}