/**
 * Heap class
 * @author Thai-Nam Hoang
 */
package listOfHeaps;

public class Heap {
    private long[] theHeap;
    private int size;
    private int length;

    public Heap (long rootValue) {
        this.size = 0;
        theHeap = new long[1];
        theHeap[0] = rootValue;
        size++;
        length = 1;
    }

    public int getSize() {
        return size;
    }

    public void growHeap() {
        int oldSize = length;
        int newSize = 2 * oldSize + 1;
        long[] storeHeap = new long[newSize];
        if (oldSize >= 0) System.arraycopy(theHeap, 0, storeHeap, 0, oldSize);
        theHeap = new long[newSize];
        theHeap = storeHeap.clone();
        length = newSize;
    }

    public void insert(long value) {
        if (getSize() == theHeap.length) {
            growHeap();
        }
        theHeap[getSize()] = value;
        maxHeapPercolateUp(getSize());
        size++;
    }

    private void swap(int a, int b) {
        long temp = theHeap[a];
        theHeap[a] = theHeap[b];
        theHeap[b] = temp;
    }

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

    public String toString() {
        StringBuilder result = new StringBuilder();
        for (long node: theHeap) {
            if (node != 0) {
                result.append(node).append(",");
            }
        }
        return (result.substring(0, result.length() - 1));
    }
}
