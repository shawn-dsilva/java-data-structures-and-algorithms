public class DoublyLinkedList<T> implements Iterable <T> {

  private int size = 0;
  private Node<T> head = null;
  private Node<T> tail = null;

  private class Node<T> {

    T data;
    Node<T> prev, next;

    public Node(T data, Node<T> prev, Node<T> next) {
      this.data = data;
      this.prev = prev;
      this.next = next;
    }

    @Override
    public String toString() {
      return data.toString();
    }
  }

  public void clear() {
    Node<T> trav = head; // set's starting node to head
    while (trav != null) {
      Node<T> next = trav.next;
      trav.prev = trav.next = null;
      trav.data = null;
      trav = next;
    }
    head = tail = trav = null;
    size = 0;
  }

  public int size() {
    return size;
  }

  public boolean isEmpty() {
    return size() == 0;
  }

  public void add(T elem) {
    addLast(elem);
  }

  public void addFirst(T elem) {

    if (isEmpty()) {
      head = tail = new Node<T>(elem, null, null);
    } else {

      //Current Head has it's prev set to a new Node
      //which has it's next reference set to current head
      //current head is then set to the value in head.prev which is the new node
      //which makes the new node the current head
      head.prev = new Node<T>(elem, null, head);
      head = head.prev;
    }

    size++;

  }

  public void addLast(T elem) {

    if (isEmpty()) {
      head = tail = new Node<T>(elem, null, null);
    } else {

      //Current Tail has it's next set to a new Node
      //which has it's prev reference set to current tail
      //current tail is then set to the value in tail.next which is the new node.
      // which makes the new node the current head
      tail.next = new Node<T>(elem, tail, null);
      tail = tail.next;
    }

    size++;

  }

  public T peekFirst() {
    if (isEmpty())
      throw new RuntimeException("Empty List");
    return head.data;
  }

  public T peekLast() {
    if (isEmpty())
      throw new RuntimeException("Empty List");
    return tail.data;
  }

  public T removeFirst() {

    if (isEmpty())
      throw new RuntimeException("Empty List");

    // store data in a data variable
    T data = head.data;

    // set current head to the next node
    head = head.next;
    --size;

    if (isEmpty()) {
      tail = null;
    } else {
      head.prev = null; // set previous to null since it is the new head
    }

    return data;

  }

  public T removeLast() {

    if (isEmpty())
      throw new RuntimeException("Empty List");

    // store data in a data variable
    T data = tail.data;

    // set current tail to the previous node
    tail = tail.prev;
    --size;

    if (isEmpty()) {
      head = null;
    } else {
      tail.next = null; // set next of current tail to null since it is the new tail
    }

    return data;

  }

  private T remove(Node<T> node) {

    // Do the [ n e e d f u l ] if the given node is either head or tail
    if (node.prev == null)
      return removeFirst();
    if (node.next == null)
      return removeLast();

    //Else if the given node is a non-head or tail node

    // set the next node's prev reference to the current node's previous
    // instead of it pointing to current node
    // i.e remove the current node's previous relation to both it's preceding and succeeding nodes
    node.next.prev = node.prev;

    // set the previous node's next to point to the current node's next,
    // that is the previous node's next will point to the node succeeding the current node
    // since the current node will be deleted
    node.prev.next = node.next;

    // store data in temp variable to return
    T data = node.data;

    // set data to null
    node.data = null;
    // set prev and next references of current node to null
    node = node.prev = node.next = null;

    //This node will now be garbage collected? i guess.

    //decrease the size variable
    --size;

    return data;
  }

  public T removeAt(int index) {

    if (index < 0 || index >= size)
      throw new IllegalArgumentException();

    int i;
    Node<T> trav;

    if (index < size / 2) {
      for (i = 0, trav = head; i != index; i++) {
        trav = trav.next;
      }
    } else {
      for (i = size - 1, trav = tail; i != index; i--) {
        trav = trav.prev;
      }
    }

    return remove(trav);

  }

  public boolean remove(Object obj) {

    Node<T> trav = head;

    if (obj == null) {
      for (trav = head; trav != null; trav = trav.next) {
        if (trav.data == null) {
          remove(trav);
          return true;
        }
      }
    } else {
      for (trav = head; trav != null; trav = trav.next) {
        if (obj.equals(trav.data)) {
          remove(trav);
          return true;
        }
      }
    }
    return false;
  }

  public int indexOf(Object obj) {

    int index = 0;
    Node<T> trav = head;

    if (obj == null) {
      for (trav = head; trav != null; trav = trav.next, index++) {
        if(trav.data == null)
          return index;
      }
    } else {
      for (trav = head; trav != null; trav = trav.next, index++) {
        if(obj.equals(trav.data))
          return index;
      }
    }
    return -1;
  }

  @Override
  public java.util.Iterator<T> iterator() {
    return new java.util.Iterator<T>() {
      private Node<T> trav = head;

      @Override
      public boolean hasNext() {
        return trav != null;
      }

      @Override
      public T next() {
        T data = trav.data;
        trav = trav.next;
        return data;
      }
    };
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("[");
    Node<T> trav = head;
    while (trav != null) {
      sb.append(trav.data + ", ");
      trav = trav.next;
    }
    sb.append(" ]");
    return sb.toString();
  }
}