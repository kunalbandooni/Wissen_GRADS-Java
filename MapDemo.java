import java.util.*;
public class MapDemo
{
	public static void main(String[] args) {
		Hashtable ob1 = new Hashtable();
		HashMap ob2 = new HashMap();
		TreeMap ob3 = new TreeMap();
		LinkedHashMap ob6 = new LinkedHashMap();

		ob1.put("abx","bha");

		Set s = ob1.entrySet();
		Iterator i1 = s.iterator();
		
		while(i1.hasNext())
		{
			Map.Entry me = (Map.Entry)i1.next();
			System.out.print(me.getKey());
			System.out.print(me.getValue());
		}
	}
}