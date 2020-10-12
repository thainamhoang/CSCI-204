/**
 * Binary search tree performance evaluation experiment
 * @author Thai-Nam Hoang
 */
package binaryTrees;

public class BinaryTree {
    private TreeNode root;
    private int numberOfNodesChecked = 0;
    private int sizeOfTree = 0;

    /**
     * Private helper for insert method. Used to push node to the right
     * @param node
     * @param currNode
     * @return
     */
    private TreeNode goRight(TreeNode node, TreeNode currNode) {
        if (currNode.getRightChild() == null) {
            currNode.setRightChild(node);
            sizeOfTree++;
            currNode = null;
        } else {
            currNode = currNode.getRightChild();
        }
        return currNode;
    }

    /**
     * Private helper for insert method. Used to push node to the left
     * @param node
     * @param currNode
     * @return
     */
    private TreeNode goLeft(TreeNode node, TreeNode currNode) {
        if (currNode.getLeftChild() == null) {
            currNode.setLeftChild(node);
            sizeOfTree++;
            currNode = null;
        } else {
            currNode = currNode.getLeftChild();
        }
        return currNode;
    }

    /**
     * Private helper for insert method. Used to insert node to empty tree
     * @param node
     * @return
     */
    private void insertToEmptyTree (TreeNode node) {
        root = node;
        sizeOfTree++;
        node.setLeftChild(null);
        node.setRightChild(null);
    }

    /**
     * Method to insert a node into tree
     * @param node
     */
    public void insert (TreeNode node) {
        TreeNode currNode = root;
        if (root == null) {
            insertToEmptyTree(node);
        } else {
            while (currNode != null) {
                if (node.getContents() == currNode.getContents()) {
                    return;
                } else if (node.getContents() < currNode.getContents()) {
                    currNode = goLeft(node, currNode);
                } else {
                    currNode = goRight(node, currNode);
                }
            }
            node.setLeftChild(null);
            node.setRightChild(null);
        }
    }

    /**
     * Method to search for a given key
     * Return TreeNode if searched, else null
     * @param key
     * @return
     */
    public TreeNode search(long key) {
        TreeNode currNode = root;
        while (currNode != null) {
            numberOfNodesChecked++;
            if (key == currNode.getContents()) {
                return currNode;
            } else if (key < currNode.getContents()) {
                currNode = currNode.getLeftChild();
            } else {
                currNode = currNode.getRightChild();
            }
        }
        return null;
    }

    /**
     * Get total nodes that search method traverses through
     * @return
     */
    public int getNumberOfNodesChecked () {
        return numberOfNodesChecked;
    }

    /**
     * Set numberOfNodesChecked to be 0
     * @return
     */
    public void clearNumberOfNodesChecked () {
        numberOfNodesChecked = 0;
    }

    /**
     * Get total of nodes in the tree
     * @return
     */
    public int getSizeOfTree () {
        return sizeOfTree;
    }
}
