import java.io.IOException;

public class AVLTree {
	
	AVLTreeNode root;
	
	public AVLTree() {
		root = null;
	}
	
	
	public String Preorder() {
		return PreorderRec(root);
	}

	public String Inorder() {
		return InorderRec(root);
	}

	public String Postorder() {
		return PostorderRec(root);
	}
	
	private void updateHeight(AVLTreeNode node) {
		node.setHeight(1 + Math.max(height(node.getLeft()), height(node.getRight())));
	}
	
	private int height(AVLTreeNode node) {
		
		if(node == null) {
			return -1;
		}else {
			return node.getHeight();
		}
        //return n == null ? -1 : n.height;
    }
	
    private int getBalance(AVLTreeNode node) {
    	if(node == null) {
    		return 0;
    	}else {
    		return height(node.getLeft()) - height(node.getRight());
    	}
       // return (n == null) ? 0 : height(n.right) - height(n.left);
    }
	
	public void insert(int newKey) {
		if (retrieve(newKey) != null) {
			return;
		}
		root = insertRec(newKey, root);
	}
	
	
	public AVLTreeNode retrieve(int searchKey) {
		return searchRec(searchKey, root);
	}
	
	
	public void clear() {
		root = null;
	}

	public boolean isEmpty() {
		if (root == null) {
			return true;
		}
		return false;
	}

	public boolean isFull() {
		return false;
	}
	
	public String toString() {
		return Inorder();
	}
	
	////////////AVL functions/////////////////
	
	private AVLTreeNode rotateLeft(AVLTreeNode root) {
		AVLTreeNode right = root.getRight();
		AVLTreeNode rightleft = right.getLeft();
		right.setLeft(root);
		root.setRight(rightleft);
		updateHeight(root);
		updateHeight(right);
		return right;
	}
	
	private AVLTreeNode rotateRight(AVLTreeNode root) {
		AVLTreeNode left = root.getLeft();
		AVLTreeNode leftright = left.getRight();
		left.setRight(root);
		root.setLeft(leftright);
		updateHeight(root);
		updateHeight(left);
		return left;
	}
	
	
	
	private AVLTreeNode rebalance(AVLTreeNode node) {
		updateHeight(node);
		int balance = getBalance(node);
		if(balance > 1) {
			if(height(node.getLeft().getLeft()) > height(node.getLeft().getRight())) {
				node = rotateRight(node);
			}else {
				node.setLeft(rotateLeft(node.getLeft()));
				node = rotateRight(node);
			}
		}
		
		else if(balance < -1) {
			if(height(node.getRight().getRight()) > height(node.getRight().getLeft())) {
				node = rotateLeft(node);
			}else {
				node.setRight(rotateRight(node.getRight()));
				node = rotateLeft(node);
			}
		}
		return node;
	}
	
	
	////////////Recursive functions//////////////
	
	
	private AVLTreeNode insertRec(int key, AVLTreeNode root) {

		if (root == null) {
			root = new AVLTreeNode(key, 0,  null, null);
			return root;
		}
		if (key < root.getKey()) {
			root.setLeft(insertRec(key, root.getLeft()));
		}

		if (key > root.getKey()) {
			root.setRight(insertRec(key, root.getRight()));
		}
		return rebalance(root);
	}
	
	
	
	
	private AVLTreeNode searchRec(int searchKey, AVLTreeNode root) {
		if (root == null) {
			return null;
		}
		if (searchKey == root.getKey()) {
			return root;
		}

		if (searchKey < root.getKey()) {
			return searchRec(searchKey, root.getLeft());
		} else {
			return searchRec(searchKey, root.getRight());
		}
	}
	
	
	///////////////traversal functions/////////////////
	private String PreorderRec(AVLTreeNode root) {
		if (root == null) {
			return "";
		}
		return (root.getKey() + " " + PreorderRec(root.getLeft()) + " " + PreorderRec(root.getRight())).trim()
				.replaceAll(" +", " ");
	}

	private String InorderRec(AVLTreeNode root) {
		if (root == null) {
			return "";
		}
		return (InorderRec(root.getLeft()) + " " + root.getKey() + " " + InorderRec(root.getRight())).trim()
				.replaceAll(" +", " ");
	}

	private String PostorderRec(AVLTreeNode root) {
		if (root == null) {
			return "";
		}
		return (PostorderRec(root.getLeft()) + " " + PostorderRec(root.getRight()) + " " + root.getKey()).trim()
				.replaceAll(" +", " ");
	}
	

}
