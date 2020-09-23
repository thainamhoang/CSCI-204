package linkedList;

public class LinkedList {
    /**
     * Initialize Node head and tail for Linked List
     */
    private Node head;
    private Node tail;

    /**
     * Linked List constructor: taking array of int and converting it to Linked List
     *
     * @param list
     */
    public LinkedList (int[] list) {
        head = null;
        tail = null;

        for (int element: list) {
            Node newNode = new Node(element);
            append(newNode);
        }
    }

    /**
     * Append method: add a single Node to the end of Linked List
     *
     * @param node
     */
    public void append (Node node) {
        if (head == null) {
            head = node;
        } else {
            tail.setNext(node);
        }
        tail = node;
    }

    /**
     * Prepend method: add a single Node to the front of Linked List
     *
     * @param node
     */
    public void prepend (Node node) {
      if (head == null) {
          head = node;
          tail = node;
      } else {
          node.setNext(head);
          head = node;
      }
    }

    /**
     * Search method: linearly search for the given value
     *
     * @param value
     * @return
     */
    public Node search (int value) {
        Node currNode = head;
        while (currNode != null) {
            if (currNode.getData() == value) {
                return currNode;
            }
            currNode = currNode.getNext();
        }
        return null;
    }

    /**
     * insertListAfterNode method: insert a list after a given node
     *
     * @param list
     * @param currNode
     */
    public void insertListAfterNode (LinkedList list, Node currNode) {
        list.tail.setNext(currNode.getNext());
        currNode.setNext(list.head);
    }

    /**
     * insertListAfterKey method: insert a list after a given key (index)
     *
     * @param list
     * @param key
     */
    public void insertListAfterKey (LinkedList list, int key) {
        Node searchValue = search(key);
        if (searchValue == null) {
            throw new IllegalArgumentException("Non-existent key " + key);
        } else {
            insertListAfterNode(list, searchValue);
        }
    }

    // toString() method overriding
    public String toString() {
        if (head == null) {
            return "<empty list>";
        }
        String result = "" + head.getData();
        Node nextNode = head.getNext();
        while (nextNode != null) {
            result += ", " + nextNode.getData();
            nextNode = nextNode.getNext();
        }
        return result;
    }
}
