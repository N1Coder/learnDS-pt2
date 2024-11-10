package org.main;

import org.main.root.RedBlackTree;
import org.main.utils.ConsoleUtil;
import org.main.utils.SaltSeeder;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        RedBlackTree tree = new RedBlackTree();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Menu Red-Black Tree ---");
            System.out.println("1. Insert Node");
            System.out.println("2. Remove Node");
            System.out.println("3. Randomize Node");
            System.out.println("4. All Traversal");
            System.out.println("5. Display Tree");
            System.out.println("6. Clear Tree");
            System.out.println("7. Get Tree Height");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter value to insert: ");
                    int insertValue = scanner.nextInt();
                    tree.insert(insertValue);
                    break;

                case 2:
                    System.out.print("Enter value to remove: ");
                    int insertValueToDelete = scanner.nextInt();
                    tree.remove(insertValueToDelete);
                    break;

                case 3:
                    System.out.print("Enter range value to randomize: ");
                    int insertValueRange = scanner.nextInt();

                    SaltSeeder saltSeeder = new SaltSeeder();
                    Random randomSeeder = new Random();
                    ArrayList<Integer> test = saltSeeder.getSaltNumber(randomSeeder, insertValueRange, insertValueRange+1);

                    for (int i = 0; i < test.size(); i++) {
                        tree.insert(test.get(i));
                    }

                    break;

                case 4:
                    System.out.println("Pre-Order Traversal:");
                    tree.preOrderTraversal(tree.getRoot());
                    System.out.println();

                    System.out.println("In-Order Traversal:");
                    tree.inOrderTraversal(tree.getRoot());
                    System.out.println();

                    System.out.println("Post-Order Traversal:");
                    tree.postOrderTraversal(tree.getRoot());

                    break;

                case 5:
                    System.out.println("Red-Black Tree:");
                    tree.showTree();
                    break;

                case 6:
                    tree.setRoot(null);
                    System.out.println("Tree is cleared!");
                    break;

                case 7:
                    System.out.println("Getting height...");
                    System.out.println("Tree height is " + tree.getHeight());
                    break;

                case 0:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}