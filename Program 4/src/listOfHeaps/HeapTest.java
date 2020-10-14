package listOfHeaps;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class HeapTest {

    // Some perfect heaps (arrays filled) for use in testing.
    private Heap perfectHeapOf3;
    private Heap perfectHeapOf7;

    @Before
    public void setUp() throws Exception {
        // Create two perfect (filled array) heaps to use for testing
        // The order of inserts here don't need "percolate" to get into proper positions
        perfectHeapOf3 = new Heap(29);
        perfectHeapOf3.insert(7);
        perfectHeapOf3.insert(17);

        perfectHeapOf7 = new Heap(32);
        perfectHeapOf7.insert(8);
        perfectHeapOf7.insert(16);
        perfectHeapOf7.insert(4);
        perfectHeapOf7.insert(6);
        perfectHeapOf7.insert(10);
        perfectHeapOf7.insert(12);

    }

    @Test
    public void testHeap() {
        Heap testingHeap = new Heap( 56 );
        assertEquals(1, testingHeap.getSize());
        assertEquals( "56", testingHeap.toString());
    }

    @Test
    public void testGrowHeap() throws IllegalAccessException, NoSuchFieldException {
        Heap testingHeap = new Heap(56);

        java.lang.reflect.Field privateField = Heap.class.getDeclaredField("theHeap");
        privateField.setAccessible(true);
        long[] heapArray = (long[]) privateField.get(testingHeap);
        assertEquals (1, heapArray.length );

        testingHeap.growHeap();
        heapArray = (long[]) privateField.get(testingHeap);
        assertEquals (3, heapArray.length );

        testingHeap.growHeap();
        heapArray = (long[]) privateField.get(testingHeap);
        assertEquals (7, heapArray.length );

        testingHeap.growHeap();
        heapArray = (long[]) privateField.get(testingHeap);
        assertEquals (15, heapArray.length );

        testingHeap.growHeap();
        heapArray = (long[]) privateField.get(testingHeap);
        assertEquals (31, heapArray.length );
    }

    @Test
    public void testInsert() {
        Heap testingHeap;
        String testingHeapToString;

        // Test #1:
        testingHeapToString = perfectHeapOf3.toString();
        assertEquals( "29,7,17",  testingHeapToString);

        // Test #2:
        testingHeapToString = perfectHeapOf7.toString();
        assertEquals( "32,8,16,4,6,10,12",  testingHeapToString);

        // Test #3: A tree that's not perfect, and that requires percolate to be working
        testingHeap = new Heap( 56 );
        testingHeap.insert(100);
        testingHeap.insert(32);
        testingHeap.insert(7);
        testingHeap.insert(84);
        testingHeap.insert(200);
        testingHeap.insert(150);
        testingHeap.insert(50);
        testingHeapToString = testingHeap.toString();
        assertEquals( "200,84,150,50,56,32,100,7",  testingHeapToString);
    }

    @Test
    public void testMaxHeapPercolateUp() throws IllegalAccessException, NoSuchFieldException {
        java.lang.reflect.Field privateField;
        long[] heapToTest;

        // Test #1:
        heapToTest = new long[ ] { 10, 7, 20 };
        privateField = Heap.class.getDeclaredField("theHeap");
        privateField.setAccessible(true);
        privateField.set(perfectHeapOf3, heapToTest );
        perfectHeapOf3.maxHeapPercolateUp(2);
        assertEquals( "20,7,10",  perfectHeapOf3.toString());

        // Test #2:
        heapToTest = new long[ ] { 1,2,3,4,5,6,7 };
        privateField.set(perfectHeapOf7, heapToTest );
        perfectHeapOf7.maxHeapPercolateUp(3);
        assertEquals( "4,1,3,2,5,6,7",  perfectHeapOf7.toString());

        // Test #3:
        perfectHeapOf7.maxHeapPercolateUp(5);
        assertEquals( "6,1,4,2,5,3,7",  perfectHeapOf7.toString());

    }

    @Test
    public void testMaxHeapPercolateDown() throws IllegalAccessException, NoSuchFieldException {
        java.lang.reflect.Field privateField;
        long[] heapToTest;

        // Test #1:
        heapToTest = new long[ ] { 10, 7, 20 };
        privateField = Heap.class.getDeclaredField("theHeap");
        privateField.setAccessible(true);
        privateField.set(perfectHeapOf3, heapToTest );
        perfectHeapOf3.maxHeapPercolateDown(0);
        assertEquals( "20,7,10", perfectHeapOf3.toString());

        // Test #2:
        heapToTest = new long[ ] { 1,2,3,4,5,6,7 };
        privateField.set(perfectHeapOf7, heapToTest );
        perfectHeapOf7.maxHeapPercolateDown(1);
        assertEquals( "1,5,3,4,2,6,7", perfectHeapOf7.toString());

        // Test #3:
        perfectHeapOf7.maxHeapPercolateDown(2);
        assertEquals( "1,5,7,4,2,6,3", perfectHeapOf7.toString());

        // Test #4:
        perfectHeapOf7.maxHeapPercolateDown(0);
        assertEquals( "7,5,6,4,2,1,3", perfectHeapOf7.toString());

        // Test #5:
        // Add a test that ensures the code works when the bottom
        // row isn't filled.
    }

}