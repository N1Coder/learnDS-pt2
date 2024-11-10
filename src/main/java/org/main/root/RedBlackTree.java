package org.main.root;

public class RedBlackTree extends BinaryTree {
    public RedBlackTree() {
        root = null;
    }

    public int getHeight() {
        return getHeight(root);
    }

    private int getHeight(Node node) {
        if (node == null) {
            return 0;
        }

        int leftHeight = getHeight(node.getLeft());
        int rightHeight = getHeight(node.getRight());

        return 1 + Math.max(leftHeight, rightHeight);
    }

    @Override
    public void insert(int value) {
        Node newNode = new Node(value);
        root = insertRoot(root, newNode);
        balanceTree(newNode);
    }

    protected Node insertRoot(Node root, Node node) {
        if (root == null) {
            return node;
        }

        if (root.getData() == node.getData()) {
            System.out.println("Duplicate node, no insertion performed.");

            return node;
        }

        if (root.getData() > node.getData()) {
            root.setLeft(insertRoot(root.getLeft(), node));
            root.getLeft().setParent(root);
        } else {
            root.setRight(insertRoot(root.getRight(), node));
            root.getRight().setParent(root);
        }

        return root;
    }

    public void remove(int value) {
        Node node = findNode(root, value);
        if (node == null) {
            return;
        }

        removeNode(node);
    }

    private Node findNode(Node node, int value) {
        if (node == null) {
            return null;
        }

        if (value < node.getData()) {
            return findNode(node.getLeft(), value);
        } else if (value > node.getData()) {
            return findNode(node.getRight(), value);
        } else {
            return node;
        }
    }

    private void removeNode(Node node) {
        if (node.getLeft() == null && node.getRight() == null) {
            if (node == root) {
                root = null;
            } else {
                if (node.getParent().getLeft() == node) {
                    node.getParent().setLeft(null);
                } else {
                    node.getParent().setRight(null);
                }
                balanceTree(node.getParent());
            }
        } else if (node.getLeft() == null) {
            Node child = node.getRight();
            if (node == root) {
                root = child;
            } else {
                if (node.getParent().getLeft() == node) {
                    node.getParent().setLeft(child);
                } else {
                    node.getParent().setRight(child);
                }
                child.setParent(node.getParent());
                balanceTree(child);
            }
        } else if (node.getRight() == null) {
            Node child = node.getLeft();
            if (node == root) {
                root = child;
            } else {
                if (node.getParent().getLeft() == node) {
                    node.getParent().setLeft(child);
                } else {
                    node.getParent().setRight(child);
                }
                child.setParent(node.getParent());
                balanceTree(child);
            }
        } else {
            Node successor = findMin(node.getRight());
            node.setData(successor.getData());
            removeNode(successor);
        }
    }

    private Node findMin(Node node) {
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
    }

    private void rotateLeft(Node node) {
        Node rightNode = node.getRight();
        node.setRight(rightNode.getLeft());

        if (node.getRight() != null) {
            node.getRight().setParent(node);
        }

        rightNode.setParent(node.getParent());

        if (node.getParent() == null) {
            root = rightNode;
        } else if (node == node.getParent().getLeft()) {
            node.getParent().setLeft(rightNode);
        } else {
            node.getParent().setRight(rightNode);
        }

        rightNode.setLeft(node);
        node.setParent(rightNode);
    }

    private void rotateRight(Node node) {
        Node leftNode = node.getLeft();
        node.setLeft(leftNode.getRight());

        if (node.getLeft() != null) {
            node.getLeft().setParent(node);
        }

        leftNode.setParent(node.getParent());

        if (node.getParent() == null) {
            root = leftNode;
        } else if (node == node.getParent().getLeft()) {
            node.getParent().setLeft(leftNode);
        } else {
            node.getParent().setRight(leftNode);
        }

        leftNode.setRight(node);
        node.setParent(leftNode);
    }

    private void balanceTree(Node node) {
        while (node != root && node.getParent().getColor() == Color.RED) {
            if (node.getParent() == node.getParent().getParent().getLeft()) {
                Node uncle = node.getParent().getParent().getRight();

                if (uncle != null && uncle.getColor() == Color.RED) {
                    node.getParent().setColor(Color.BLACK);
                    uncle.setColor(Color.BLACK);
                    node.getParent().getParent().setColor(Color.RED);
                    node = node.getParent().getParent();
                } else {
                    if (node == node.getParent().getRight()) {
                        node = node.getParent();
                        rotateLeft(node);
                    }

                    node.getParent().setColor(Color.BLACK);
                    node.getParent().getParent().setColor(Color.RED);
                    rotateRight(node.getParent().getParent());
                }
            } else {
                Node uncle = node.getParent().getParent().getLeft();

                if (uncle != null && uncle.getColor() == Color.RED) {
                    node.getParent().setColor(Color.BLACK);
                    uncle.setColor(Color.BLACK);
                    node.getParent().getParent().setColor(Color.RED);
                    node = node.getParent().getParent();
                } else {
                    if (node == node.getParent().getLeft()) {
                        node = node.getParent();
                        rotateRight(node);
                    }

                    node.getParent().setColor(Color.BLACK);
                    node.getParent().getParent().setColor(Color.RED);
                    rotateLeft(node.getParent().getParent());
                }
            }
        }

        root.setColor(Color.BLACK);
    }

    @Override
    protected void formatTree(Node node, String prefix, boolean isTail) {
        if (node == null) return;

        System.out.println(prefix + (isTail ? "└── " : "├── ") + (node.getColor() == Color.RED ? "R" : "B") + node.getData());

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
