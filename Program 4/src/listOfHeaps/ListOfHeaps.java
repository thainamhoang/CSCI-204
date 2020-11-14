/**
 * List of Heaps as a Priority Queue
 * @author Thai-Nam Hoang
 */
package listOfHeaps;

public class ListOfHeaps {

    private Node head;
    private Node tail;

    /**
     * prepend: insert value to the beginning of the linked list
     * @param newNode
     */
    public void prepend(Node newNode) {
        newNode.setNext(head);
        if (head == null) {
            tail = newNode;
        }
        head = newNode;
    }

    /**
     * getMax: get the index of max value
     * @return
     */
    private Node getMax() {
        long currentMax = head.getData().peek();
        Node maxNode = head;

        for (Node currentNode = head.getNext(); currentNode != null; currentNode = currentNode.getNext()) {
            if (currentNode.getData().peek() > currentMax) {
                currentMax = currentNode.getData().peek();
                maxNode = currentNode;
            }
        }
        return maxNode;
    }

    /**
     * pop: return and remove the largest value
     * @return
     */
    public long pop() {
        return getMax().getData().pop();
    }

    /**
     * checkEqual: check if two heaps are equal
     * @return
     */
    private boolean checkEqual() {
        boolean result = false;
        if (head == null || head.getNext() == null) {
            return false;
        }
        else if (head.getData().getSize() == head.getNext().getData().getSize()) {
            result = true;
        }
        return result;
    }

    /**
     * isEmpty: check if the linked list is empty
     * @return
     */
    public boolean isEmpty() {
        boolean result = false;
        if (head == null) {
            result = true;
        }
        return result;
    }

    /**
     * push: insert value depends on priority
     * @param valueToAdd
     */
    public void push(long valueToAdd) {
        if (checkEqual()) {
            head.getNext().getData().addHeap(head.getData());
            head = head.getNext();
            head.getData().insert(valueToAdd);
        } else {
            Heap singleton = new Heap(valueToAdd);
            prepend(new Node(singleton));
        }
    }

    /**
     * getSize: get the total size of all heaps
     * @return
     */
    public int getSize() {
        if (head == null) {
            return 0;
        }
        int result = head.getData().getSize();
        for (Node node = head.getNext(); node != null; node = node.getNext()) {
            result += node.getData().getSize();
        }
        return result;
    }

    /**
     * insert: insert value to heap linked list
     * @param valueToAdd
     */
    public void insert(long valueToAdd) {
        if (checkEqual()) {
            head.getNext().getData().addHeap(head.getData());
            head  = head.getNext();
            head.getData().insert(valueToAdd);
        }
        else {
            Node newHeap = new Node(new Heap(valueToAdd));
            prepend(newHeap);
        }
    }

    /**
     * toString: String.toString() override
     * @return
     */
    public String toString() {
        if (head == null) return "<Empty Heap>";
        String result = head.getData().toString();
        for (Node node = head.getNext(); node != null; node = node.getNext()) {
            result += " + " + node.getData().toString();
        }
        result = result.replace("<Empty Heap> + ", "").replace(" + <Empty Heap>", "");
        return result;
    }
}
