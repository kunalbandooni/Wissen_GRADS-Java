class A extends Thread
{
	public void run()
	{
		for(int i=0;i<20;i++){
			System.out.println("A");
			try{
				if(i%3 == 0)
					Thread.sleep(1000);
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
	}
}
class B extends Thread
{
	public void run()
	{
		for (int j=0;j<20; j++) 
			System.out.println("B");
	}
}
class C extends Thread
{
	public void run()
	{
		for (int j=0;j<20; j++) 
			System.out.println("C");
	}
}

public class ThreadDemo
{
	public static void main(String[] args) {
		A a1 = new A();
		B b1 = new B();
		C c1 = new C();

		a1.start();
		b1.start();
		c1.start();

		System.out.println("MAIN EXIT");
	}
}