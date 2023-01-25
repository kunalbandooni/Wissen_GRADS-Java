import java.io.*;
public class IODemo
{
	public static void main(String[] args) {
		try
		{
			//PrintWriter pw = new PrintWriter(System.out);
			PrintWriter pw = new PrintWriter(new FileOutputStream("abc.txt", true));
			pw.println("GOOD");
			pw.flush();
			pw.close();

			System.out.print("DONE");

			RandomAccessFile raf = new RandomAccessFile("abc.txt", "rw");

			raf.seek(raf.length());
			raf.writeBytes("VERY GOOD\n");
			raf.close();
		}
		catch(Exception e)
		{
			System.out.print("NO WORK HERE");
		}
	}
}