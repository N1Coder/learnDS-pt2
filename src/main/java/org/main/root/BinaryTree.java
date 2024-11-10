package org.main.root;

public class BinaryTree {
    protected Node root;

    public BinaryTree() {
        root = null;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public Node getRoot() {
        return root;
    }

    public void insert(int value) {
        root = insertRoot(root, value);
    }

    protected Node insertRoot(Node root, int value) {
        if (root == null) {
            root = new Node(value);
            return root;
        }

        if (root.getData() > value) {
            root.setLeft(insertRoot(root.getLeft(), value));
        } else if (root.getData() < value) {
            root.setRight(insertRoot(root.getRight(), value));
        }

        return root;
    }

    public void preOrderTraversal(Node node) {
        if (node != null) {
            System.out.print(node.getData() + " ");
            preOrderTraversal(node.getLeft());
            preOrderTraversal(node.getRight());
        }
    }

    public void inOrderTraversal(Node node) {
        if (node != null) {
            inOrderTraversal(node.getLeft());
            System.out.print(node.getData() + " ");
            inOrderTraversal(node.getRight());
        }
    }

    public void postOrderTraversal(Node node) {
        if (node != null) {
            postOrderTraversal(node.getLeft());
            postOrderTraversal(node.getRight());
            System.out.print(node.getData() + " ");
        }
    }

    public void traverse(String orderType) {
        System.out.println(orderType.toUpperCase() + " Traversal:");
        switch (orderType.toLowerCase()) {
            case "preorder":
                preOrderTraversal(root);
                break;
            case "inorder":
                inOrderTraversal(root);
                break;
            case "postorder":
                postOrderTraversal(root);
                break;
            default:
                System.out.println("Invalid traversal type!");
                return;
        }
        System.out.println();
    }

    public void showTree() {
        formatTree(root, "", true);
    }

    protected void formatTree(Node node, String prefix, boolean isTail) {
        if (node == null) {
            return;
        }

        System.out.println(prefix + (isTail ? "└── " : "├── ") + node.getData());

        String childPrefix = prefix + (isTail ? "    " : "│   ");

        if (node.getLeft() != null || node.getRight() != null) {
            if (node.getRight() != null) {
                formatTree(node.getRight(), childPrefix, node.getLeft() == null);
            }
            if (node.getLeft() != null) {
                formatTree(node.getLeft(), childPrefix, true);
            }
        }
    }
}
