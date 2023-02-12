package Main;

public class Tree {
    public interface Position {
        public String getData();
    }

    public static class Node implements Position {
        private String data;
        private Node parent;
        private Node leftChild;
        private Node rightChild;

        Node (String data) {
            this.data = data;
            this.parent = null;
            this.leftChild = null;
            this.rightChild = null;
        }

        public Node(String data, Node parent, Node leftChild, Node rightChild) {
            this.data = data;
            this.parent = parent;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
        }

        @Override
        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        public Node getParent() {
            return parent;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }

        public Node getLeftChild() {
            return leftChild;
        }

        public void setLeftChild(Node leftChild) {
            this.leftChild = leftChild;
        }

        public Node getRightChild() {
            return rightChild;
        }

        public void setRightChild(Node rightChild) {
            this.rightChild = rightChild;
        }

        public boolean hasLeftChild() {
            return leftChild != null;
        }

        public boolean hasRightChild() {
            return rightChild != null;
        }
    }

    private Node root;
    private int size;

    public Tree() {
        this.root = null;
        this.size = 0;
    }

    public Tree(Node root) {
        this.root = root;
        size = predictTreeSize(this.root);
    }

    private int predictTreeSize(Node root) {
        if (root == null) return 0;
        int count = 1;
        if (root.hasLeftChild()) count += predictTreeSize(root.getLeftChild());
        if (root.hasRightChild()) count += predictTreeSize(root.getRightChild());
        return count;
    }

    private Node validate(Position p) {
        if (!(p instanceof Node)) throw new IllegalArgumentException("Not valid position type.");
        Node node = (Node) p;
        if (node.getParent() == node) throw new IllegalArgumentException("P is no longer in the tree.");
        return node;
    }

    public int getSize() {
        return size;
    }

    public Position getRoot() {
        return root;
    }

    public Position getParent(Position p) {
        Node node = validate(p);
        return node.getParent();
    }

    public Position getLeftChild(Position p) {
        Node node = validate(p);
        return node.getLeftChild();
    }

    public Position getRightChild(Position p) {
        Node node = validate(p);
        return node.getRightChild();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isRoot(Position p) {
        return p == getRoot();
    }

    public boolean isInternal(Position p) {
        Node parent = validate(p);
        return parent.hasLeftChild() || parent.hasRightChild();
    }

    public boolean isExternal(Position p) {
        Node parent = validate(p);
        return !parent.hasLeftChild() && !parent.hasRightChild();
    }

    public Position addRoot(String data) {
        if (!isEmpty()) throw new IllegalStateException("TreeForm is not empty.");
        root = new Node(data);
        size = 1;
        return root;
    }

    public Position addLeftChild(Position parent, String data) {
        Node parentNode = validate(parent);
        if (parentNode.hasLeftChild()) throw new IllegalArgumentException("parent already has a left child.");
        Node leftChild = new Node(data, parentNode, null, null);
        parentNode.setLeftChild(leftChild);
        size++;
        return leftChild;
    }

    public Position addRightChild(Position parent, String data) {
        Node parentNode = validate(parent);
        if (parentNode.hasRightChild()) throw new IllegalArgumentException("parent already has a right child.");
        Node rightChild = new Node(data, parentNode, null, null);
        parentNode.setRightChild(rightChild);
        size++;
        return rightChild;
    }

    public Position addChild(Position parent, String data) {
        Node parentNode = validate(parent);
        if (!parentNode.hasLeftChild()) return addLeftChild(parent, data);
        else if (!parentNode.hasRightChild()) return addRightChild(parent, data);
        throw new IllegalArgumentException("Parent already has 2 children.");
    }

    public String replaceData(Position p, String data) {
        Node node = validate(p);
        String temp = node.getData();
        node.setData(data);
        return temp;
    }

    public void attach(Position parent, Tree leftSubTree, Tree rightSubTree) {
        Node parentNode = validate(parent);
        if (isInternal(parent)) throw new IllegalArgumentException("Parent must be a leaf");
        size += leftSubTree.getSize() + rightSubTree.getSize();
        if (!leftSubTree.isEmpty()) {
            leftSubTree.root.setParent(parentNode);
            parentNode.setLeftChild(leftSubTree.root);
            leftSubTree.root = null;
            leftSubTree.size = 0;
        }
        if (!rightSubTree.isEmpty()) {
            rightSubTree.root.setParent(parentNode);
            parentNode.setRightChild(rightSubTree.root);
            rightSubTree.root = null;
            rightSubTree.size = 0;
        }
    }

    public String removeNode(Position p) {
        Node node = validate(p);
        if (node.hasLeftChild() && node.hasRightChild()) throw new IllegalArgumentException("P has 2 children.");
        Node child = node.getLeftChild() != null ? node.getLeftChild() : node.getRightChild();
        if (child != null) child.setParent(node.getParent());
        if (node == root) root = child;
        else {
            Node parent = node.getParent();
            if (node == parent.getLeftChild()) parent.setLeftChild(child);
            else parent.setRightChild(child);
        }
        size--;
        String temp = node.getData();
        node.setData(null);
        node.setLeftChild(null);
        node.setRightChild(null);
        node.setParent(node);
        return temp;
    }
}
