import java.io.IOException;

public class BSTree {
	BSTreeNode root;

	public BSTree() {
		root = null;
	}



	public void insert(int newKey) {
		if (retrieve(newKey) != null) {
			return;
		}
		root = insertRec(newKey, root);
	}

	public BSTreeNode retrieve(int searchKey) {
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

	public String Preorder() {
		return PreorderRec(root);
	}

	public String Inorder() {
		return InorderRec(root);
	}

	public String Postorder() {
		return PostorderRec(root);
	}

	public int height() {
		return heightRec(root);
	}

	public BSTreeNode findParent(int key) {
		return findParentRec(root, key);
	}

	public BSTreeNode findMin() {
		return findMinRec(root);
	}

	public BSTreeNode findMax() {
		return findMaxRec(root);
	}

	public void delete(int key) {
		deleteRec(root, key);
	}

	/////////////////// Recursive functions//////////////////////

	private BSTreeNode deleteRec(BSTreeNode root, int key) {
		if (root == null) {
			return root;
		}

		if (key < root.getKey()) {
			root.setLeft(deleteRec(root.getLeft(), key));
		}

		else if (key > root.getKey()) {
			root.setRight(deleteRec(root.getRight(), key));
		}

		else {
			if (root.getLeft() == null && root.getRight() == null) {
				return null;
			} 
			else if (root.getRight() != null && root.getLeft() == null) {
				return root.getRight();
			} 
			else if (root.getLeft() != null && root.getRight() == null) {
				return root.getLeft();
			}else {
				BSTreeNode min = findMinRec(root.getRight());
				root.setKey(min.getKey());
				root.setRight(deleteRec(root.getRight(), min.getKey()));
			}
		}
		return root;
	}

	private BSTreeNode findMaxRec(BSTreeNode root) {
		if (root.getRight() == null) {
			return root;
		}
		return findMaxRec(root.getRight());
	}

	private BSTreeNode findMinRec(BSTreeNode root) {
		if (root.getLeft() == null) {
			return root;
		}
		return findMinRec(root.getLeft());
	}

	private BSTreeNode findParentRec(BSTreeNode root, int key) {
		if (root.getLeft() == null && root.getRight() == null) {
			return null;
		}
		if (root.getLeft() != null && root.getLeft().getKey() == key) {
			return root;
		}
		if (root.getRight() != null && root.getRight().getKey() == key) {
			return root;
		}
		if (key < root.getKey()) {
			return findParentRec(root.getLeft(), key);

		}
		return findParentRec(root.getRight(), key);

	}

	private BSTreeNode insertRec(int key, BSTreeNode root) {

		if (root == null) {
			root = new BSTreeNode(key, null, null);
			return root;
		}
		if (key < root.getKey()) {
			root.setLeft(insertRec(key, root.getLeft()));
		}

		if (key > root.getKey()) {
			root.setRight(insertRec(key, root.getRight()));
		}
		return root;
	}

	private BSTreeNode searchRec(int searchKey, BSTreeNode root) {
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

	private int heightRec(BSTreeNode root) {
		if (root == null) {
			return -1;
		}
		int leftHeight = heightRec(root.getLeft());
		int rightHeight = heightRec(root.getRight());

		if (leftHeight > rightHeight) {
			return leftHeight + 1;
		}
		return rightHeight + 1;
	}

	private String PreorderRec(BSTreeNode root) {
		if (root == null) {
			return "";
		}
		return (root.getKey() + " " + PreorderRec(root.getLeft()) + " " + PreorderRec(root.getRight())).trim()
				.replaceAll(" +", " ");
	}

	private String InorderRec(BSTreeNode root) {
		if (root == null) {
			return "";
		}
		return (InorderRec(root.getLeft()) + " " + root.getKey() + " " + InorderRec(root.getRight())).trim()
				.replaceAll(" +", " ");
	}

	private String PostorderRec(BSTreeNode root) {
		if (root == null) {
			return "";
		}
		return (PostorderRec(root.getLeft()) + " " + PostorderRec(root.getRight()) + " " + root.getKey()).trim()
				.replaceAll(" +", " ");
	}

}
