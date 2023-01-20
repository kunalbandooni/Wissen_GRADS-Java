import java.lang.*;

class A implements Runnable
{
	public void run()
	{
		System.out.println(Thread.currentThread().getName());
		synchronized(this)
		{
			for(int i=0;i<10;i++)
			{
				System.out.println(Thread.currentThread().getName() + " " + i);
			}
		}

		// These will run parallel to our tasks...
		for(int i=0;i<10;i++){
			System.out.println(Thread.currentThread().getName() + i);
		}
	}
}
public class RunnableDemo
{
	public static void main(String[] args) {
		A a1 = new A();

		Thread t1 = new Thread(a1);
		Thread t2 = new Thread(a1);

		t1.start();
		t2.start();
	}
}