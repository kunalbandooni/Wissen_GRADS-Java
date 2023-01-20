class A
{
	public void abc()
	{
		System.out.println("HI");
	}
}
class B extends A
{
	public void abc()
	{
		System.out.println("HEY!!");
	}
}
class C extends B
{
}
public class Overriding
{
	public static void main(String Args[])
	{
		A a = new A();
		a.abc();

		System.out.println("==========");

		B b = new B();
		b.abc();

		System.out.println("==========");

		C c = new C();
		c.abc();
	}
}