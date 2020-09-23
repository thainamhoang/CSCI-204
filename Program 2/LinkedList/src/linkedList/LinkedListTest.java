package linkedList;

/**
 * Test suite for CSCI 204, Fall 2020, program #2.
 * @author Jerry Ngo
 * @author Darrah Chavey
 */

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LinkedListTest {

    @Test
    public void testAppend() {
        //First Test
        LinkedList test = new LinkedList(new int[] {});
        test.append(new Node(-42));
        assertTrue(test.toString().equals("-42"));
    }

    @Test
    public void testPrepend() {
        //First Test
        LinkedList test = new LinkedList(new int[] {});
        test.prepend(new Node(-42));
        assertTrue(test.toString().equals("-42"));

        //Second and Third Test
        test = new LinkedList(new int[] {0});
        test.prepend(new Node(1));
        assertTrue(test.toString().equals("1, 0"));
        test.prepend(new Node(0));
        assertTrue(test.toString().equals("0, 1, 0"));

        //Fourth Test
        test = new LinkedList(new int[] { 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37});
        test.prepend(new Node(2, new Node(1)));
        assertTrue(test.toString().equals("2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37"));
    }

    /**
     * Test method for {@link linkedList.LinkedList#search(int)}.
     */
    @Test
    public void testSearch() {
        //First and Second Test
        LinkedList test = new LinkedList(new int[] {5, 5});
        Node query = test.search(5);
        assertTrue((query.getData() == 5) && (query.getNext() != null));
        query = test.search(-7);
        assertTrue(query == null);

        //Third and Fourth Test
        test = new LinkedList(new int[] {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37});
        query = test.search(2);
        assertTrue((query.getData() == 2));
        query = test.search(37);
        assertTrue((query.getData() == 37));

        //Fifth Test
        test = new LinkedList(new int[] {});
        query = test.search(0);
        assertTrue(query == null);
    }


    @Test
    public void testInsertListAfterKey() {
        //First Test
        LinkedList test = new LinkedList(new int[] {3});
        try{
            test.insertListAfterKey(new LinkedList(new int[] {0}), 2);
        } catch(Exception e) {};
        assertTrue(test.toString().equals("3"));

        //Second Test
        test = new LinkedList(new int[] {2, 2});
        test.insertListAfterKey(new LinkedList(new int[] {8, 1, 8}), 2);
        assertTrue(test.toString().equals("2, 8, 1, 8, 2"));

        //Third Test
        test = new LinkedList(new int[] {2, 3, 5, 7, 11, 13});
        test.insertListAfterKey(new LinkedList(new int[] {17, 19, 23, 29, 31, 37}), 13);
        assertTrue(test.toString().equals("2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37"));

        // Exception Test
        test = new LinkedList(new int[] {2, 3, 5, 7, 11, 13, 17, 19, 31, 37});
        try {
            test.insertListAfterKey(new LinkedList(new int[] {23, 29}), 21);
            assert("You found a non-existant number!".equals("Program fail"));  // Force a failure
        } catch (Exception e) {   // You were *supposed* to throw an exception! Is it the right one?
            assert( e.getClass() == new IllegalArgumentException().getClass() );
            assert( e.getMessage().equals("Non-existent key 21") );
        }
    }

    @Test
    public void testInsertListAfterNode() {
        //First Test
        LinkedList test = new LinkedList(new int[] {0, 0, 0, 0});
        Node query = test.search(0).getNext();
        test.insertListAfterNode(new LinkedList(new int[] {1, 9, 8}), query);
        assertTrue(test.toString().equals("0, 0, 1, 9, 8, 0, 0"));

        //Second Test
        test = new LinkedList(new int[] {2, 3, 13});
        query = test.search(3);
        test.insertListAfterNode(new LinkedList(new int[] {5, 7}), query);
        assertTrue(test.toString().equals("2, 3, 5, 7, 13"));

        //Third and Fourth Test
        test = new LinkedList(new int[] {6});
        query = test.search(6);
        test.insertListAfterNode(new LinkedList(new int[] {9}), query);
        assertTrue(test.toString().equals("6, 9"));
        try{
            test.insertListAfterNode(new LinkedList(new int[] {}), query);
        }catch(Exception e) {};
        assertTrue(test.toString().equals("6, 9"));
    }

    @Test
    public void testToString() {
        //First Test
        LinkedList test = new LinkedList(new int[] {0});
        assertTrue(test.toString().equals("0"));

        //Second Test
        test = new LinkedList(new int[] {});
        assertTrue(test.toString().equals("<empty list>"));

        //Third Test
        test = new LinkedList(new int[] {2, 3, 5, 7, 11, 13});
        assertTrue(test.toString().equals("2, 3, 5, 7, 11, 13"));

        //Fourth Test
        test = new LinkedList(new int[] {-1, -1, -1, -1, -1, -1, -1, -1, 0, 10});
        assertTrue(test.toString().equals("-1, -1, -1, -1, -1, -1, -1, -1, 0, 10"));
    }
}