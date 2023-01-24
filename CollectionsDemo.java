import java.util.*;
public class CollectionsDemo
{
	public static void main(String[] args) {
		HashSet	ob1 = new HashSet();
		TreeSet ob2 = new TreeSet();
		LinkedHashSet ob3 = new LinkedHashSet();

		Vector ob4 = new Vector();
		ArrayList ob5 = new ArrayList();
		LinkedList ob6 = new LinkedList();

		PriorityQueue ob7 = new PriorityQueue();

		ob1.add("abx");

		Iterator i1 = ob1.iterator();
		while(i1.hasNext())
			System.out.print(i1.next());
	}
}