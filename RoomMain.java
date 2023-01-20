class Room
{
	int length, breadth;
	Room()
	{
		length = 10;
		breadth = 20;
	}
	Room(int x, int y)
	{
		length = x;
		breadth = y;
	}
	void area(){
		System.out.println(length * breadth);
	}
}
public class RoomMain
{
	public static void main(String Args[])
	{
		Room r1 = new Room();
		Room r2 = new Room(30,40);

		r1.area();
		r2.area();

		System.out.println("========");

		r1.length = 90;
		r2.breadth = 80;

		r1.area();
		r2.area();
	}
}