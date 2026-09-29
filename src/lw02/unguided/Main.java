package lw02.unguided;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        LinkedList<String[]> orders = new LinkedList<>();
        LinkedList<String[]> foods = new LinkedList<>();
        LinkedList<String[]> drinks = new LinkedList<>();
        LinkedList<String[]> processed = new LinkedList<>();

        Queue<String[]> processOrders = new LinkedList<>();
        Stack<String[]> failed = new Stack<>();

        foods.add(new String[]{"Bakso", "2"});
        foods.add(new String[]{"Sate", "1"});
        foods.add(new String[]{"Soto", "2"});

        drinks.add(new String[]{"EsTeh", "4"});
        drinks.add(new String[]{"EsJeruk", "2"});

        Scanner scan = new Scanner (Main.class.getResourceAsStream("orders.txt"));
        
        while (scan.hasNext()) {
            String[] order = new String[4];
            order[0] = scan.next();
            order[1] = scan.next();
            order[2] = scan.next();
            order[3] = scan.next();
            orders.add(order);
        }
        scan.close();

        processOrders.addAll(orders);

        while (!processOrders.isEmpty()) {
            String[] order = processOrders.poll();
            String foodName = order[1];
            String drinkName = order[2];

            boolean foodAvailable = true;
            String[] targetFood = null;
            if (!foodName.equals("-")) {
                foodAvailable = false;
                for (String[] f : foods) {
                    if (f[0].equals(foodName)) {
                        targetFood = f;
                        if (Integer.parseInt(f[1]) > 0) {
                            foodAvailable = true;
                        }
                        break;
                    }
                }
            }

            boolean drinkAvailable = true;
            String[] targetDrink = null;
            if (!drinkName.equals("-")) {
                drinkAvailable = false;
                for (String[] d : drinks) {
                    if (d[0].equals(drinkName)) {
                        targetDrink = d;
                        if (Integer.parseInt(d[1]) > 0) {
                            drinkAvailable = true;
                        }
                        break;
                    }
                }
            }

            if (foodAvailable && drinkAvailable) {
                if (targetFood != null) {
                    int currentStock = Integer.parseInt(targetFood[1]);
                    targetFood[1] = String.valueOf(currentStock - 1);
                }
                if (targetDrink != null) {
                    int currentStock = Integer.parseInt(targetDrink[1]);
                    targetDrink[1] = String.valueOf(currentStock - 1);
                }
                processed.add(order);
            } else {
                failed.push(order);
            }
        }

        System.out.println("=== Successfully Processed Orders ===");
        for (String[] order : processed) {
            System.out.println(order[0] + " " + order[1] + " " + order[2] + " " + order[3]);
        }
        System.out.println();

        System.out.println("=== Remaining Food Stock ===");
        for (String[] food : foods) {
            System.out.println(food[0] + " : " + food[1]);
        }
        System.out.println();

        System.out.println("=== Remaining Drink Stock ===");
        for (String[] drink : drinks) {
            System.out.println(drink[0] + " : " + drink[1]);
        }
        System.out.println();

        System.out.println("=== Failed Orders ===");
        while (!failed.isEmpty()) {
            String[] order = failed.pop();
            System.out.println(order[0] + " " + order[1] + " " + order[2] + " " + order[3]);
        }
    }
}

