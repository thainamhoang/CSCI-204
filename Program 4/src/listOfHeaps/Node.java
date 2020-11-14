package listOfHeaps;

/* ASSUME: Your program using this inserts things for a while, the stops.
 *         Then it pops thing one at a time, and never go back to adding.
 */

public class Node {
    private Heap data;
    private Node next;

    public Heap getData() {
        return data;
    }
    public void setData(Heap data) {
        this.data = data;
    }

    public Node getNext() {
        return next;
    }
    public void setNext(Node next) {
        this.next = next;
    }

    public Node ( Heap newData ) {
        data = newData;
        next = null;
    }

    public Node ( Heap newData, Node nextNode ) {
        data = newData;
        next = nextNode;
    }

}