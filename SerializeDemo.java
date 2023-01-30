import java.io.*;
class Person implements java.io.Serializable{
	String name;
	int age;
}
public class SerializeDemo
{
	public static void main(String[] args) {
		try{
			Person p1 = new Person();
			p1.name = "KUNAL";
			p1.age = 25;

			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("person.ser"));
			oos.writeObject(p1);
			oos.close();

			System.out.println("SAVED");
		}catch(Exception e){
			System.out.print(e);
		}
	}
}