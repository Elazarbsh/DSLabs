
public class BSTreeNode {
	private int key;
	private BSTreeNode left, right;
	
	public BSTreeNode(int key, BSTreeNode leftPtr, BSTreeNode rightPtr) {
		this.key = key;
		this.right = rightPtr;
		this.left = leftPtr;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public BSTreeNode getLeft() {
		return left;
	}

	public void setLeft(BSTreeNode left) {
		this.left = left;
	}

	public BSTreeNode getRight() {
		return right;
	}

	public void setRight(BSTreeNode right) {
		this.right = right;
	}
	
	

}
