package lw04.prelab;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(Main.class.getResourceAsStream("branches.txt"));
        int n = scan.nextInt();
        int[] branches = new int[n];

        for (int i = 0; i < n; i++) {
            branches[i] = scan.nextInt();
        }

        int m = scan.nextInt();
        int[] targets = new int[m];
        
        for (int i = 0; i < m; i++) {
            targets[i] = scan.nextInt();
        }

        for (int i = 1; i <= 3 && i < branches.length; i++) {
            int key = branches[i];
            int j = i - 1;
            while (j >= 0 && branches[j] < key) {
                branches[j + 1] = branches[j];
                j--;
            }
            branches[j + 1] = key;
        }

        for (int i = 0; i < branches.length; i++) {
            System.out.print(branches[i] + (i < branches.length - 1 ? " " : ""));
        }
        System.out.println();

        for (int i = 0; i < m; i++) {
            int target = targets[i];
            int foundIndex = -1;
            for (int j = 0; j < branches.length; j++) {
                if (branches[j] == target) {
                    foundIndex = j;
                    break;
                }
            }

            if (foundIndex != -1) {
                System.out.println(target + ": FOUND at index " + foundIndex);
            } else {
                System.out.println(target + ": NOT FOUND");
            }
        }
        scan.close();
    }
}

