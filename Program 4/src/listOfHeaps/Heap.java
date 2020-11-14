/**
 * Heap class
 * @author Thai-Nam Hoang
 */
package listOfHeaps;

public class Heap {
    private long[] theHeap;
    private int size;
    private int length;

    /**
     * Constructor for Heap
     * @param rootValue
     */
    public Heap (long rootValue) {
        this.size = 0;
        theHeap = new long[1];
        theHeap[0] = rootValue;
        size++;
        length++;
    }

    /**
     * getSize: return size (numbers of filled value)
     * @return
     */
    public int getSize() {
        return size;
    }

    /**
     * growHeap: grow heap if it reaches the size of 2k+1 (from k = 1)
     */
    public void growHeap() {
        int oldSize = length;
        int newSize = 2 * oldSize + 1;
        long[] storeHeap = new long[newSize];
        if (oldSize >= 0) System.arraycopy(theHeap, 0, storeHeap, 0, oldSize);
        theHeap = new long[newSize];
        theHeap = storeHeap.clone();
        length = newSize;
    }

    /**
     * insert: insert a value to the heap
     * @param value
     */
    public void insert(long value) {
        if (getSize() == theHeap.length) {
            growHeap();
        }
        theHeap[getSize()] = value;
        maxHeapPercolateUp(getSize());
        size++;
    }

    /**
     * swap: private helper to swap to value in the heap
     * @param a
     * @param b
     */
    private void swap(int a, int b) {
        long temp = theHeap[a];
        theHeap[a] = theHeap[b];
        theHeap[b] = temp;
    }

    /**
     * maxHeapPercolateUp: percolate up (moving larger node from the end to the top), derived from zybooks
     * @param nodeIndex
     */
    public void maxHeapPercolateUp(int nodeIndex) {
        while (nodeIndex > 0) {
            int parentIndex = (nodeIndex - 1) / 2;
            if (theHeap[nodeIndex] <= theHeap[parentIndex]) {
                return;
            } else {
                swap(nodeIndex, parentIndex);
                nodeIndex = parentIndex;
            }
        }
    }

    /**
     * maxHeapPercolateDown: percolate down (moving smaller node from the end to the top), derived from zybooks
     * @param nodeIndex
     */
    public void maxHeapPercolateDown(int nodeIndex) {
        int childIndex = 2 * nodeIndex + 1;
        long value = theHeap[nodeIndex];

        while (childIndex < getSize()) {
            long maxValue = value;
            int maxIndex = -1;
            for (int i = 0; i < 2 && i + childIndex < getSize(); i++) {
                if (theHeap[i + childIndex] > maxValue) {
                    maxValue = theHeap[i + childIndex];
                    maxIndex = i + childIndex;
                }
            }

            if (maxValue == value) {
                return;
            } else {
                swap(nodeIndex, maxIndex);
                nodeIndex = maxIndex;
                childIndex = 2 * nodeIndex + 1;
            }
        }
    }

    /**
     * catchException: throw exception if smallerHeap length > the heap length
     * @param smallerHeap
     */
    public void catchException(Heap smallerHeap) {
        if (smallerHeap.theHeap.length > theHeap.length) {
            throw new IllegalArgumentException("public void addHeap( Heap smallerHeap ) throws IllegalArgumentException");
        }
    }

    /**
     * addHeap: add two heaps together
     * @param smallerHeap
     */
    public void addHeap(Heap smallerHeap){
        catchException(smallerHeap);
        if (theHeap.length >= smallerHeap.theHeap.length) {
            growHeap();
        }
        int i = 0;
        while (i < smallerHeap.theHeap.length) {
            theHeap[size] = smallerHeap.theHeap[i];
            maxHeapPercolateUp(size);
            i++;
            size++;
        }
    }

    /**
     * pop: return and remove the first element of the heap
     * @return
     */
    public long pop() {
        if (size == 0) {
            return 0;
        }
        long temp = theHeap[0];
        theHeap[0] = theHeap[size - 1];
        theHeap[size - 1] = 0;
        size--;
        maxHeapPercolateDown(0);
        return temp;
    }

    /**
     * peek: return the root node of the heap
     * @return
     */
    public long peek() {
        return theHeap[0];
    }

    /**
     * toString: String.toString() override
     * @return
     */
    public String toString() {
        StringBuilder result = new StringBuilder();
        if (getSize() == 0) {
            return "<Empty Heap>";
        }
        for (long node: theHeap) {
            if (node != 0) {
                result.append(node).append(",");
            }
        }
        return (result.substring(0, result.length() - 1));
    }
}
