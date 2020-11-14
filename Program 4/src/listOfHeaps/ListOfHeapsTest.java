package listOfHeaps;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ListOfHeapsTest {

    @Before
    public void setUp() throws Exception {
    }

    //  Not being tested at present
//	@Test
    public void testPrepend() {
        fail("Not yet implemented");
    }

    @Test
    public void testPopNoHeapsDeleted() {
        // Test #0: This is verifying that the insert test has worked
        long[] toInsert = new long[]{56,100,32,7,84,200,150,50,73,42,12};
        ListOfHeaps heaps = new ListOfHeaps();
        for (int i=0; i<toInsert.length; i++) {
            heaps.push( toInsert[i] );
        }
        assertTrue( ("12 + 73,50,42 + 200,100,150,56,7,32,84".equals(heaps.toString())) ||
                ("12 + 73,50,42 + 200,100,150,7,56,32,84".equals(heaps.toString()))  );

        // Test #1: Pop one item from the last heap
        long justPopped = heaps.pop();
        assertEquals(200, justPopped);
        assertTrue( ("12 + 73,50,42 + 150,100,84,56,7,32".equals(heaps.toString())) ||
                ("12 + 73,50,42 + 150,100,84,7,56,32".equals(heaps.toString()))  );
        assertEquals(10, heaps.getSize());

        // Test #2: Pop several more items from the last heap
        assertEquals(150, heaps.pop());
        assertEquals(100, heaps.pop());
        assertEquals(84, heaps.pop());
        assertTrue( ("12 + 73,50,42 + 56,32,7".equals(heaps.toString())) ||
                ("12 + 73,50,42 + 56,7,32".equals(heaps.toString()))  );
        assertEquals(7, heaps.getSize());

        // Test #3: Pop an item from a heap in the middle
        assertEquals(73, heaps.pop());
        assertTrue( ("12 + 50,42 + 56,32,7".equals(heaps.toString())) ||
                ("12 + 50,42 + 56,7,32".equals(heaps.toString()))  );
        assertEquals(6, heaps.getSize());

        // Test #4:
        assertEquals(56, heaps.pop());
        assertEquals(50, heaps.pop());
        assertEquals("12 + 42 + 32,7", heaps.toString() );
        assertEquals(4, heaps.getSize());


    }

    @Test
    public void testPopHeapsDeleted() {
        // Test #0: This is verifying that the insert and initial pops are working correctly
        long[] toInsert = new long[]{56,100,32,7,84,200,150,50,73,42,12};
        ListOfHeaps heaps = new ListOfHeaps();
        for (int i=0; i<toInsert.length; i++) {
            heaps.push( toInsert[i] );
        }
        assertTrue( ("12 + 73,50,42 + 200,100,150,56,7,32,84".equals(heaps.toString())) ||
                ("12 + 73,50,42 + 200,100,150,7,56,32,84".equals(heaps.toString()))  );
        for (int pop = 0; pop < 7; pop++) {
            heaps.pop();
        }
        assertEquals("12 + 42 + 32,7", heaps.toString() );

        // Test #1: Pop items that result in empty heaps
        assertEquals(42, heaps.pop());
        assertEquals("12 + 32,7", heaps.toString() );
        assertEquals(3, heaps.getSize());

        // Test #2: Pop items that result in empty heaps
        assertEquals(32, heaps.pop());
        assertEquals("12 + 7", heaps.toString() );
        assertEquals(2, heaps.getSize());

        // Test #3: Pop items that result in empty heaps
        assertEquals(12, heaps.pop());
        assertEquals("7", heaps.toString() );
        assertEquals(1, heaps.getSize());

        // Test #4: Pop items that result in empty heaps
        assertEquals(7, heaps.pop());
        assertEquals("<Empty Heap>", heaps.toString() );
        assertEquals(0, heaps.getSize());
    }


    @Test
    public void testPush() {
        // Test #1:
        ListOfHeaps heaps = new ListOfHeaps();
        heaps.push(56);
        heaps.push(100);
        heaps.push(32);
        assertEquals("100,56,32",heaps.toString() );

        // Test #2:
        heaps.push(7);
        heaps.push(84);
        heaps.push(200);
        heaps.push(150);
        assertTrue( ("200,100,150,56,7,32,84".equals(heaps.toString())) ||
                ("200,100,150,7,56,32,84".equals(heaps.toString()))  );

        // Test #3:
        heaps.push(50);
        heaps.push(73);
        heaps.push(42);
        heaps.push(12);
        assertTrue( ("12 + 73,50,42 + 200,100,150,56,7,32,84".equals(heaps.toString())) ||
                ("12 + 73,50,42 + 200,100,150,7,56,32,84".equals(heaps.toString()))  );
    }

    @Test
    public void testGetSize() {
        // Test #1:
        ListOfHeaps heaps = new ListOfHeaps();
        heaps.push(56);
        assertEquals(1,heaps.getSize() );

        // Test #2:
        heaps.push(100);
        heaps.push(32);
        assertEquals(3,heaps.getSize() );

        // Test #3:
        heaps.push(7);
        heaps.push(84);
        heaps.push(200);
        heaps.push(150);
        assertEquals(7,heaps.getSize() );

        // Test #4:
        heaps.push(50);
        heaps.push(73);
        heaps.push(42);
        heaps.push(12);
        assertEquals(11,heaps.getSize() );
    }

    //  Not being independently tested at present
//	@Test
    public void testToString() {
        fail("Not yet implemented");
    }

}