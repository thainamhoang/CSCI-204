package binaryTrees;

public class TreeNode {

    private long contents;
    private TreeNode leftChild, rightChild;

    /** Constructor */
    public TreeNode( long contents) {
        this.contents = contents;
    }

    public void setLeftChild(TreeNode leftChild) {
        this.leftChild = leftChild;
    }
    public TreeNode getLeftChild() {
        return leftChild;
    }

    public void setRightChild(TreeNode rightChild) {
        this.rightChild = rightChild;
    }
    public TreeNode getRightChild() {
        return rightChild;
    }

    public void setContents(long contents) {
        this.contents = contents;
    }
    public long getContents() {
        return contents;
    }


}