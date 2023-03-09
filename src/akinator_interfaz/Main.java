/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package akinator_interfaz;

/**
 *
 * @author connec
 */
import java.util.LinkedList;
import java.util.Queue;

class Node<T> {
    T data;
    Node<T> left;
    Node<T> right;
    
    public Node(T data) {
        this.data = data;
        left = null;
        right = null;
    }
}

class BinaryTree<T> {
    Node<T> root;

    public void insert(T data) {
        Node<T> node = new Node<T>(data);

        if (root == null) {
            root = node;
            return;
        }

        Queue<Node<T>> q = new LinkedList<Node<T>>();
        q.add(root);

        while (!q.isEmpty()) {
            Node<T> current = q.peek();
            q.remove();

            if (current.left == null) {
                current.left = node;
                break;
            } else {
                q.add(current.left);
            }

            if (current.right == null) {
                current.right = node;
                break;
            } else {
                q.add(current.right);
            }
        }
    }

    private void inOrderTraversal(Node<T> node) {
        if (node == null) {
            return;
        }

        inOrderTraversal(node.left);
        System.out.print(node.data + " ");
        inOrderTraversal(node.right);
    }

    public void inOrderTraversal() {
        inOrderTraversal(root);
    }

    private int getHeight(Node<T> node) {
        if (node == null) {
            return 0;
        }

        int leftHeight = getHeight(node.left);
        int rightHeight = getHeight(node.right);

        if (leftHeight > rightHeight) {
            return leftHeight + 1;
        } else {
            return rightHeight + 1;
        }
    }

    public boolean isBalanced() {
        return isBalanced(root);
    }

    private boolean isBalanced(Node<T> node) {
        if (node == null) {
            return true;
        }

        int leftHeight = getHeight(node.left);
        int rightHeight = getHeight(node.right);

        if (Math.abs(leftHeight - rightHeight) <= 1 && isBalanced(node.left) && isBalanced(node.right)) {
            return true;
        }

        return false;
    }
}

class ThreeString {
    String first;
    String second;
    String third;

    public ThreeString(String first, String second, String third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    @Override
    public String toString() {
        return "(" + first + ", " + second + ", " + third + ")";
    }
}

public class Main {
    public static void main(String[] args) {
        BinaryTree<ThreeString> tree = new BinaryTree<ThreeString>();
        tree.insert(new ThreeString("Hello", "world", "Java"));
        tree.insert(new ThreeString("This", "is", "a"));
        tree.insert(new ThreeString("Binary", "Tree", "Example"));

        tree.inOrderTraversal();
        System.out.println();

        if (tree.isBalanced()) {
            System.out.println("The tree is balanced");
        } else {
            System.out.println("The tree is not balanced");
        }
    }
}