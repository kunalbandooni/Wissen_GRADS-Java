// do odd and even numbers in multi-threading...
class B extends Thread
{
	public void run()
	{
		for (int j=0;j<50; j+=2) 
			System.out.println("B" + j);
	}
}
class C extends Thread
{
	public void run()
	{
		for (int j=1;j<51; j+=2) 
			System.out.println("C" + j);
	}
}

public class ThreadDemo
{
	public static void main(String[] args) {
		//A a1 = new A();
		B b1 = new B();
		C c1 = new C();

		//a1.start();
		b1.start();
		c1.start();

		System.out.println("MAIN EXIT");
	}
}