package lw03.prelab;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        solveProblem1();
        solveProblem2();
        solveProblem3();
    }

    private static void solveProblem1() {
        Scanner scan = new Scanner(Main.class.getResourceAsStream("playlist.txt"));
        List<String> playlist = new ArrayList<>();

        while (scan.hasNext()) {
            String op = scan.next();
            if (op.equals("ADD")) {
                playlist.add(scan.nextLine().trim());
            } else if (op.equals("INSERT")) {
                int index = scan.nextInt();
                playlist.add(index, scan.nextLine().trim());
            } else if (op.equals("REMOVE")) {
                playlist.remove(scan.nextLine().trim());
            }
        }
        scan.close();

        System.out.println("===== Problem 1 =====");
        System.out.println("Total songs: " + playlist.size());
        for (int i = 0; i < playlist.size(); i++) {
            System.out.println((i + 1) + ": " + playlist.get(i));
        }
        System.out.println();
    }

    private static void solveProblem2() {
        Scanner scan = new Scanner(Main.class.getResourceAsStream("participants.txt"));
        Set<String> participants = new LinkedHashSet<>();
        int duplicates = 0;

        while (scan.hasNextLine()) {
            String name = scan.nextLine().trim();
            if (!name.isEmpty() && !participants.add(name)) {
                duplicates++;
            }
        }
        scan.close();

        System.out.println("===== Problem 2 =====");
        System.out.println("Unique participants: " + participants.size());
        int rank = 1;
        for (String p : participants) {
            System.out.println(rank++ + ". " + p);
        }
        System.out.println("Duplicate registrations: " + duplicates);
        System.out.println();
    }

    private static void solveProblem3() {
        Scanner scan = new Scanner(Main.class.getResourceAsStream("inventory.txt"));
        Map<String, Integer> inventory = new LinkedHashMap<>();
        int failedSales = 0;

        while (scan.hasNext()) {
            String type = scan.next();
            String product = scan.next();
            int qty = scan.nextInt();

            if (type.equals("ADD")) {
                inventory.put(product, inventory.getOrDefault(product, 0) + qty);
            } else if (type.equals("SELL")) {
                int stock = inventory.getOrDefault(product, 0);
                if (stock >= qty) {
                    inventory.put(product, stock - qty);
                } else {
                    failedSales++;
                }
            }
        }
        scan.close();

        System.out.println("===== Problem 3 =====");
        for (String product : inventory.keySet()) {
            System.out.println(product + ": " + inventory.get(product));
        }
        System.out.println("Failed sales: " + failedSales);
        System.out.println();
    }
}