package binaryTrees;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Test suite for CSCI 204, Fall 2020, program #3.
 * @author Jerry Ngo
 * @author Darrah Chavey
 */

public class BinaryTreeTest {

    private BinaryTree treeToTest;

    @Before
    public void setUp() throws Exception {
        // Always start with a fresh, empty tree
        treeToTest = new BinaryTree();
    }

    @Test
    public void testGetNumberOfNodesChecked() {
        assertEquals(0, treeToTest.getNumberOfNodesChecked());

        treeToTest.insert(new TreeNode(2));
        treeToTest.insert(new TreeNode(1));
        treeToTest.insert(new TreeNode(3));
        treeToTest.search(1);
        assertEquals(2, treeToTest.getNumberOfNodesChecked());
        treeToTest.search(3);
        assertEquals(4, treeToTest.getNumberOfNodesChecked());

        treeToTest.insert(new TreeNode(4));
        treeToTest.insert(new TreeNode(5));
        treeToTest.insert(new TreeNode(6));
        treeToTest.insert(new TreeNode(7));
        treeToTest.search(7);
        assertEquals(10, treeToTest.getNumberOfNodesChecked());

        treeToTest.insert(new TreeNode(-5));
        treeToTest.insert(new TreeNode(-6));
        treeToTest.insert(new TreeNode(-4));
        treeToTest.insert(new TreeNode(0));
        treeToTest.search(0);
        assertEquals(15, treeToTest.getNumberOfNodesChecked());

        treeToTest.search(-7);
        assertEquals(19, treeToTest.getNumberOfNodesChecked());
    }

    @Test
    public void testClearNumberOfNodesChecked() {
        treeToTest.insert(new TreeNode(2));
        treeToTest.insert(new TreeNode(1));
        treeToTest.search(3);
        treeToTest.insert(new TreeNode(3));
        treeToTest.clearNumberOfNodesChecked();
        treeToTest.search(3);
        assertEquals(2, treeToTest.getNumberOfNodesChecked());

        treeToTest.insert(new TreeNode(0));
        treeToTest.clearNumberOfNodesChecked();
        treeToTest.search(0);
        assertEquals(3, treeToTest.getNumberOfNodesChecked());

        treeToTest.insert(new TreeNode(-1));
        treeToTest.clearNumberOfNodesChecked();
        treeToTest.search(-1);
        assertEquals(4, treeToTest.getNumberOfNodesChecked());

        treeToTest.insert(new TreeNode(-2));
        treeToTest.clearNumberOfNodesChecked();
        treeToTest.search(-2);
        assertEquals(5, treeToTest.getNumberOfNodesChecked());
    }

    @Test
    public void testGetSizeOfTree() {
        assertEquals( 0, treeToTest.getSizeOfTree() );

        treeToTest.insert(new TreeNode(4));
        assertEquals( 1, treeToTest.getSizeOfTree() );

        treeToTest.insert(new TreeNode(8));
        treeToTest.insert(new TreeNode(2));
        assertEquals( 3, treeToTest.getSizeOfTree() );

        treeToTest.insert(new TreeNode(2));
        treeToTest.insert(new TreeNode(4));
        treeToTest.insert(new TreeNode(8));
        treeToTest.insert(new TreeNode(2));
        assertEquals( 3, treeToTest.getSizeOfTree() );
    }

    @Test
    public void testSearch() {
        TreeNode searchNode = treeToTest.search(0);
        assertTrue(searchNode == null);

        treeToTest.insert(new TreeNode(0));
        treeToTest.insert(new TreeNode(0));
        searchNode = treeToTest.search(0);
        assertTrue(searchNode.getContents() == 0 &&
                ((searchNode.getLeftChild() == null) && (searchNode.getRightChild() == null)));

        treeToTest.insert(new TreeNode(-2));
        treeToTest.insert(new TreeNode(-3));
        treeToTest.insert(new TreeNode(-1));
        searchNode = treeToTest.search(-1);
        assertTrue(searchNode.getContents() == -1);

        treeToTest.insert(new TreeNode(1));
        treeToTest.insert(new TreeNode(2));
        treeToTest.insert(new TreeNode(4));
        searchNode = treeToTest.search(4);
        assertTrue(searchNode.getContents() == 4);

        treeToTest.insert(new TreeNode(3));
        searchNode = treeToTest.search(3);
        assertTrue(searchNode.getContents() == 3);
    }

    @Test
    public void testInsert() {
        treeToTest.insert(new TreeNode(10));
        treeToTest.insert(new TreeNode(9));
        TreeNode searchNode = treeToTest.search(10);
        assertTrue(searchNode.getLeftChild().getContents() == 9);

        treeToTest.insert(new TreeNode(11));
        assertTrue(searchNode.getRightChild().getContents() == 11);
        treeToTest.insert(new TreeNode(11));
        assertTrue(searchNode.getRightChild().getContents() == 11 &&
                searchNode.getRightChild().getLeftChild() == null &&
                searchNode.getRightChild().getRightChild() == null);

        treeToTest.insert(new TreeNode(12));
        treeToTest.insert(new TreeNode(13));
        searchNode = treeToTest.search(11);
        assertTrue(searchNode.getRightChild().getContents() == 12 &&
                searchNode.getRightChild().getRightChild().getContents() == 13);
    }

    @Test
    public void testFullProgram() {
        BinaryTree fullTree;
        String experimentResults;

        Driver.setRandomNum( new MyRandom( 1 ) );
        fullTree = Driver.buildTree( 1000 );
        experimentResults = Driver.evaluateTree( fullTree );
        assert( experimentResults.equals("The number of nodes inspected, on average, was 11.48.") );

        Driver.setRandomNum( new MyRandom( 13 ) );
        fullTree = Driver.buildTree( 100 );
        experimentResults = Driver.evaluateTree( fullTree );
        assert( experimentResults.equals("The number of nodes inspected, on average, was 25.02.") );

        Driver.setRandomNum( new MyRandom( 49 ) );
        fullTree = Driver.buildTree( 10 );
        experimentResults = Driver.evaluateTree( fullTree );
        assert( experimentResults.equals("The number of nodes inspected, on average, was 121.90.") );
    }

}