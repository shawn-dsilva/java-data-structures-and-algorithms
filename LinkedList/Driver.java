package LinkedList;


public class Driver<T> {


	public static void main(String args[]) {

		DoublyLinkedList list = new DoublyLinkedList();


		list.add(5);
		list.add(4);
		list.add(3);
		list.add(2);
		list.add(1);


		System.out.println(list.toString());



	}
}