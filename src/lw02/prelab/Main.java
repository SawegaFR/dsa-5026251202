package lw02.prelab;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        LinkedList<String[]> transactions = new LinkedList<>();
        Scanner scan = new Scanner(Main.class.getResourceAsStream("transactions.txt"));

        while (scan.hasNextLine()) {
            String line = scan.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }
            String[] parts = line.split("\\s+");
            transactions.add(parts);
        }
        scan.close();

        LinkedList<String[]> customers = new LinkedList<>();
        for (String[] tx : transactions) {
            String name = tx[0];
            boolean exists = false;
            for (String[] cust : customers) {
                if (cust[0].equals(name)) {
                    exists = true;
                    break;
                }
            }
            if (!exists) {
                customers.add(new String[] { name, "0" });
            }
        }

        Queue<String[]> queue = new LinkedList<>();
        while (!transactions.isEmpty()) {
            queue.offer(transactions.removeFirst());
        }

        Stack<String[]> failedStack = new Stack<>();

        while (!queue.isEmpty()) {
            String[] tx = queue.poll();
            String name = tx[0];
            String type = tx[1];
            int amount = Integer.parseInt(tx[2]);

            String[] targetCustomer = null;
            for (String[] cust : customers) {
                if (cust[0].equals(name)) {
                    targetCustomer = cust;
                    break;
                }
            }

            if (targetCustomer != null) {
                int currentBalance = Integer.parseInt(targetCustomer[1]);
                if (type.equalsIgnoreCase("DEPOSIT")) {
                    currentBalance += amount;
                    targetCustomer[1] = String.valueOf(currentBalance);
                } else if (type.equalsIgnoreCase("WITHDRAW")) {
                    if (amount > currentBalance) {
                        failedStack.push(tx);
                    } else {
                        currentBalance -= amount;
                        targetCustomer[1] = String.valueOf(currentBalance);
                    }
                }
            }
        }

        System.out.println("=== Final Balances ===");
        for (String[] cust : customers) {
            System.out.println(cust[0] + " : " + cust[1]);
        }

        System.out.println();
        System.out.println("=== Failed Transactions ===");
        while (!failedStack.isEmpty()) {
            String[] failedTransactions = failedStack.pop();
            System.out.println(failedTransactions[0] + " " + failedTransactions[1] + " " + failedTransactions[2]);
        }
    }
}
