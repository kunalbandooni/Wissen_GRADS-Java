import java.util.Scanner;
public class KeyInput
{
	public static void main(String Args[])
	{
		Scanner sc = new Scanner(System.in);
		
		String name = sc.next();
		int age = sc.nextInt();
		float salary = sc.nextFloat();
		char ch = sc.next().charAt(0);

		System.out.println(name + " " + age + " " + salary + " " + ch);

		// similarily all can be used...

	}
}