package linkedList;

public class LinkedList {
    private Node head;
    private Node tail;

    public LinkedList (int[] newData) {
        head = null;
        tail = null;

        for (int element: newData) {
            Node newNode = new Node(element);
            listAppend(newNode);
        }
    }

    public void listAppend (Node node) {
        if (head == null) {
            head = node;
        } else {
            tail.setNext(node);
        }
        tail = node;
    }

    public void listPrepend (Node node) {
      if (head == null) {
          head = node;
          tail = node;
      } else {
          node.setNext(head);
          head = node;
      }
    }

    public Node search (int key) {
        Node currNode = head;
        while (currNode != null) {
            if (currNode.getData() == key) {
                return currNode;
            }
            currNode = currNode.getNext();
        }
        return null;
    }

    public void insertAfterNode (Node currNode, Node newNode) {
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else if (currNode == tail) {
            tail.setNext(newNode);
            tail = newNode;
        } else {
            newNode.setNext(currNode.getNext());
            currNode.setNext((newNode));
        }
    }

    public void insertListAfterNode (LinkedList list, Node currNode) {
        list.tail.setNext(currNode.getNext());
        currNode.setNext(list.head);
    }

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
            return "Empty list";
        }
        StringBuilder result = new StringBuilder(" " + head.getData());
        Node currNode = head;
        while (currNode != null) {
            result.append(", ").append(currNode.getData());
            currNode = currNode.getNext();
        }
        return result.toString();
    }
}
