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
import java.util.Scanner;

class Guess<T> {
    String question;
    String answerYes;
    String answerNo;
    Node<T> nextYes;
    Node<T> nextNo;

    public Guess(String question, String answerYes, String answerNo) {
        this.question = question;
        this.answerYes = answerYes;
        this.answerNo = answerNo;
        nextYes = null;
        nextNo = null;
    }

    public Guess(String question, String answerYes, String answerNo, Node<T> nextYes, Node<T> nextNo) {
        this.question = question;
        this.answerYes = answerYes;
        this.answerNo = answerNo;
        this.nextYes = nextYes;
        this.nextNo = nextNo;
    }
}

class Node<T> {
    T data;
    Guess<T> guess;
    Node<T> left;
    Node<T> right;

    public Node(T data, Guess<T> guess) {
        this.data = data;
        this.guess = guess;
        left = null;
        right = null;
    }
}

class BinaryTree<T> {
    Node<T> root;

    public void insert(Node<T> newNode) {
        if (root == null) {
            root = newNode;
            return;
        }

        Queue<Node<T>> q = new LinkedList<Node<T>>();
        q.add(root);

        while (!q.isEmpty()) {
            Node<T> current = q.peek();
            q.remove();

            if (current.guess != null) {
                if (current.guess.nextYes == null) {
                    current.guess.nextYes = newNode;
                    break;
                } else if (current.guess.nextNo == null) {
                    current.guess.nextNo = newNode;
                    break;
                } else {
                    q.add(current.guess.nextYes);
                    q.add(current.guess.nextNo);
                }
            } else {
                if (current.left == null) {
                    current.left = newNode;
                    break;
                } else {
                    q.add(current.left);
                }

                if (current.right == null) {
                    current.right = newNode;
                    break;
                } else {
                    q.add(current.right);
                }
            }
        }
    }

    private void inOrderTraversal(Node<T> node) {
        if (node != null) {
            inOrderTraversal(node.left);
            if (node.guess != null) {
                System.out.println(node.guess.question);
                Scanner input = new Scanner(System.in);
                String answer = input.nextLine().toLowerCase();
                if (answer.equals("yes")) {
                    inOrderTraversal(node.guess.nextYes);
                } else if (answer.equals("no")) {
                    inOrderTraversal(node.guess.nextNo);
                } else {
                    System.out.println("Invalid input. Please answer \"yes\" or \"no\".");
                    inOrderTraversal(node);
                }
            } else {
                System.out.println(node.data);
                inOrderTraversal(node.left);
                inOrderTraversal(node.right);
            }
        }
    }

    public void play() {
        System.out.println("Think of an object and I'll try to guess what it is.");
        inOrderTraversal(root);
    }
}
public class Main {
    public static void main(String[] args) {
        BinaryTree<String> tree = new BinaryTree<String>();
        tree.insert(new Node<String>("an elephant", null));
        tree.insert(new Node<String>("a mouse", null));
        tree.insert(new Node<String>(null, new Guess<String>("Is it bigger than a breadbox?", "an elephant", "a mouse")));
        tree.insert(new Node<String>(null, new Guess<String>("Does it live in the water?", "a shark", "a lion")));
        tree.insert(new Node<String>(null, new Guess<String>("Is it a mammal?", null, null)));
        tree.play();
    }
}