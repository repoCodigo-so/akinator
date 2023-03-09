/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package akinator_interfaz;

/**
 *
 * @author connec
 */
public class AVLTree<T extends Comparable<T>> {

    private class Node {
        T value;
        Node left;
        Node right;
        int height;

        Node(T value) {
            this.value = value;
            this.height = 1;
        }
    }

    private Node root;

    public void insert(T value) {
        root = insert(root, value);
    }

    private Node insert(Node node, T value) {
        if (node == null) {
            return new Node(value);
        }

        int cmp = value.compareTo(node.value);

        if (cmp < 0) {
            node.left = insert(node.left, value);
        } else if (cmp > 0) {
            node.right = insert(node.right, value);
        } else {
            // Value already exists in tree
            return node;
        }

        node.height = 1 + Math.max(height(node.left), height(node.right));

        int balance = balance(node);

        if (balance > 1 && value.compareTo(node.left.value) < 0) {
            // Left Left Case
            return rightRotate(node);
        }

        if (balance < -1 && value.compareTo(node.right.value) > 0) {
            // Right Right Case
            return leftRotate(node);
        }

        if (balance > 1 && value.compareTo(node.left.value) > 0) {
            // Left Right Case
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        if (balance < -1 && value.compareTo(node.right.value) < 0) {
            // Right Left Case
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = 1 + Math.max(height(y.left), height(y.right));
        x.height = 1 + Math.max(height(x.left), height(x.right));

        return x;
    }

    private Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = 1 + Math.max(height(x.left), height(x.right));
        y.height = 1 + Math.max(height(y.left), height(y.right));

        return y;
    }

    private int height(Node node) {
        if (node == null) {
            return 0;
        }

        return node.height;
    }

    private int balance(Node node) {
        if (node == null) {
            return 0;
        }

        return height(node.left) - height(node.right);
    }
}