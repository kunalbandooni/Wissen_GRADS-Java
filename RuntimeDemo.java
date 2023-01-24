import java.lang.Thread;

public class RuntimeDemo
{
	public static void main(String[] args) {
		try
		{
			Runtime rt = Runtime.getRuntime();
			Process p1 = rt.exec("mspaint.exe");
			Process p2 = rt.exec("calc.exe");

			Thread.sleep(5000);

			//p2.destroy();
			p1.destroy();
		}
		catch(Exception e)
		{
			System.out.print(e);
		}
	}
}