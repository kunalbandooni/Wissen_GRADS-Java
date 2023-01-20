// Import
import java.util.Scanner;

// This is the Employee Class
class Employee
{
	private String name;
	private int age;
	private double salary;
	private String designation;

	// Parameterized constructor 
	public Employee(String name, int age, double salary, String designation)
	{
		this.name = name;
		
		if(age > 0 && age <= 120)
			this.age = age;
		else 
			this.age = 0;
		
		this.salary = salary;
		this.designation = designation;

		// Display the values...
		this.display();
	}

	// Default Constructor
	public Employee()
	{
		this.input();
		this.display();
	}

	// Function to input the values from the user...
	private void input()
	{
		Scanner sc = new Scanner(System.in);

		System.out.println("\nEnter the following details : ");
		
		System.out.print("Name \t\t : ");
		this.name = sc.nextLine();

		System.out.print("Age \t\t : ");
		this.age = sc.nextInt();

		System.out.print("Salary \t\t : ");
		this.salary = sc.nextDouble();

		sc.nextLine();	// to skip the newline character...
		
		System.out.print("Designation \t : ");
		this.designation = sc.nextLine();
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

	// method to raise Salary by 'amount' passed in it
	public void raiseSalary()
	{
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\nEnter amount to be raised for " + this.name + " : ");

		double amount = sc.nextDouble();
		this.salary += amount;
		
		//System.out.println("\nRaised " + amount + " for " + name);

		this.display();
	}
}

// this is the main Class
public class ProjectMain
{
	// the main function...
	public static void main(String Args[])
	{
		// Initializations...
		Employee e0 = new Employee();
		
		/*
		Employee e1 = new Employee("Panaj", 21, 43000, "SDE");
		Employee e2 = new Employee("Ayush", 21, 40000, "Analyst");
		Employee e3 = new Employee("Riya", 22, 45000, "Manager");
		*/
		
		// Displaying in constructor...

		System.out.println("\n==================");

		// Change in values...
		e0.raiseSalary();

		/*
		e1.raiseSalary(2000);
		e2.raiseSalary(-1000);
		e3.raiseSalary(3000);
		*/

		// Displaying in raiseSalary()
	}
}