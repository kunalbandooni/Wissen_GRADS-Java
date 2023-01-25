import java.io.*;
public class ReadFileDemo
{
	public static void main(String[] args) {
		try
		{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			System.out.print("FILE NAME:");
			String filename = br.readLine();

			File f = new File(filename);
			if(f.exists())
			{
				BufferedReader fr = new BufferedReader(new FileReader(filename));

				String line = null;
				while( (line = fr.readLine()) != null)
					System.out.print(line);

				fr.close();
			}
			else
				System.out.print("NO SUCH FILE");

			br.close();
		}
		catch(Exception e)
		{
			System.out.print(e);
		}
	}
}