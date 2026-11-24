package lw06.unguided;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner read = new Scanner(Main.class.getResourceAsStream("treenumbers.txt"));

        BinarySearchTree bst = new BinarySearchTree();
        while(read.hasNext()){
            bst.insert(read.nextInt());
        }
        
        System.out.print("Preorder : ");
        bst.preOrder();
        System.out.print("Inorder : ");
        bst.inOrder();
        System.out.print("Postorder : ");
        bst.postOrder();
        System.out.println("Search 65 : " + bst.search(65));  
        System.out.println("Nodes: " + bst.countNodes());
        System.out.println("Leaves: " + bst.countLeaves());
        read.close();
    }
}
