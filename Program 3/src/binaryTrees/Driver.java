package binaryTrees;

import com.sun.source.tree.Tree;

public class Driver {

    public final static int EXPERIMENT_SIZE = 1000;
    private static MyRandom randomNum;

    /** For JUnit testing purposes only. */
    public static void setRandomNum(MyRandom randomNum) {
        Driver.randomNum = randomNum;
    }

    public static String evaluateTree( BinaryTree searchTree ) {
        float sum = 0;
        for (int i = 1; i <= EXPERIMENT_SIZE; i++) {
            searchTree.search(i);
            sum += searchTree.getNumberOfNodesChecked();
            searchTree.clearNumberOfNodesChecked();
        }
        float average = sum / EXPERIMENT_SIZE;
        return String.format("The number of nodes inspected, on average, was %.2f", average);
    }

    /** This will insert half of the numbers in the given range of the provided tree. */
    private static void insertNodes( BinaryTree tree, int lowerBound, int upperBound ) {
        for (int i = 0; i < (upperBound - lowerBound + 1) / 2; i++) {
            TreeNode insertNode = new TreeNode(randomNum.randomBetween(lowerBound, upperBound));
            tree.insert(insertNode);
        }
    }

    /** Insert half of all the numbers within each "rangeSize" chunk from 1 to 1000. */
    public static BinaryTree buildTree( int rangeSize ) {
        BinaryTree tree = new BinaryTree();
        if (rangeSize == EXPERIMENT_SIZE) {
            insertNodes(tree, 1, rangeSize);
        } else if (rangeSize == 100) {
            insertNodesWithBatch(tree, rangeSize);
        } else if (rangeSize == 10) {
            insertNodesWithBatch(tree, rangeSize);
        }
        return tree;
    }

    private static void insertNodesWithBatch (BinaryTree tree, int rangeSize) {
        int lower = 1;
        int upper = rangeSize;

        for (int i = 0; i < EXPERIMENT_SIZE / rangeSize; i++) {
            for (int j = 0; j < rangeSize / 2; j++) {
                insertNodes(tree, lower, upper);
            }
            lower += rangeSize;
            upper += rangeSize;
        }
    }

    /** A testing method, intended to be used with the debugger to verify that your
     BinaryTree class is properly adding nodes, and adding them without the duplicate
     "5" at the end.
     */
    public static void watchTreeGrow( ) {
        BinaryTree treeToEvaluate = new BinaryTree();
        treeToEvaluate.insert( new TreeNode(4) );
        treeToEvaluate.insert( new TreeNode(2) );
        treeToEvaluate.insert( new TreeNode(6) );
        treeToEvaluate.insert( new TreeNode(1) );
        treeToEvaluate.insert( new TreeNode(3) );
        treeToEvaluate.insert( new TreeNode(5) );
        treeToEvaluate.insert( new TreeNode(7) );
        treeToEvaluate.insert( new TreeNode(5) );
    }


    /** When completed, this "main" should work appropriately with your program */
    public static void main(String[] args) {
        BinaryTree fullTree;
        String experimentResults;

        watchTreeGrow( );

        randomNum = new MyRandom( 1 );
        fullTree = buildTree( 1000 );
        experimentResults = evaluateTree( fullTree );
        System.out.println( experimentResults );

        randomNum = new MyRandom( 13 );
        fullTree = buildTree( 100 );
        experimentResults = evaluateTree( fullTree );
        System.out.println( experimentResults );

        randomNum = new MyRandom( 49 );
        fullTree = buildTree( 10 );
        experimentResults = evaluateTree( fullTree );
        System.out.println( experimentResults );
    }

}