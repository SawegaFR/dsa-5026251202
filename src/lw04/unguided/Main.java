package lw04.unguided;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(Main.class.getResourceAsStream("parcels.txt"));

        int n = scan.nextInt();
        int[] shelf = new int[n];
        for (int i = 0; i < n; i++) {
            shelf[i] = scan.nextInt();
        }

        int m = scan.nextInt();
        int[] targets = new int[m];
        for (int i = 0; i < m; i++) {
            targets[i] = scan.nextInt();
        }
        scan.close();

        int[] afterTwoPasses = new int[n];

        bubbleSort(shelf, afterTwoPasses);

        System.out.println("==== After 2 Passes ====");
        printArray(afterTwoPasses);
        System.out.println();

        System.out.println("==== Fully Sorted ====");
        printArray(shelf);
        System.out.println();

        System.out.println("==== Search Results ====");
        for (int i = 0; i < m; i++) {
            binarySearch(shelf, targets[i]);
        }
    }

    public static void bubbleSort(int[] shelf, int[] afterTwoPasses) {
        int n = shelf.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (shelf[j] < shelf[j + 1]) {
                    int temp = shelf[j];
                    shelf[j] = shelf[j + 1];
                    shelf[j + 1] = temp;
                }
            }
            if (i == 1) {
                for (int k = 0; k < n; k++) {
                    afterTwoPasses[k] = shelf[k];
                }
            }
        }
    }

    public static void binarySearch(int[] shelf, int target) {
        int low = 0;
        int high = shelf.length - 1;
        int steps = 0;
        int foundIndex = -1;

        while (low <= high) {
            steps++;
            int mid = (low + high) / 2;
            if (shelf[mid] == target) {
                foundIndex = mid;
                break;
            } else if (shelf[mid] < target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        if (foundIndex != -1) {
            System.out.println(target + ": FOUND at index " + foundIndex + " (steps: " + steps + ")");
        } else {
            System.out.println(target + ": NOT FOUND (steps: " + steps + ")");
        }
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}

