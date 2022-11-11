package sevidor;

import java.util.LinkedList;;
public class teste {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		LinkedList<Integer> l= new LinkedList<Integer>();
		
		l.add(1);
		l.add(2);
		l.add(3);
		l.remove(1);
		System.out.println(l.get(0)+"    "+l.get(1));

	}

}
