/** Binary search tree performance evaluation experiment
 *
 * @author Thai-Nam Hoang
 */
package binaryTrees;

public class BinaryTree {
    private TreeNode root;
    private int numberOfNodesChecked = 0;
    private int sizeOfTree = 0;

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

    public void insertToEmptyTree (TreeNode node) {
        root = node;
        sizeOfTree++;
        node.setLeftChild(null);
        node.setRightChild(null);
    }

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

    public int getNumberOfNodesChecked () {
        return numberOfNodesChecked;
    }

    public void clearNumberOfNodesChecked () {
        numberOfNodesChecked = 0;
    }

    public int getSizeOfTree () {
        return sizeOfTree;
    }
}
