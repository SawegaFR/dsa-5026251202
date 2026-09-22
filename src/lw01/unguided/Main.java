package lw01.unguided;

import java.io.File;
import java.io.InputStream;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = null;
        InputStream is = Main.class.getResourceAsStream("washes.txt");
        if (is != null) {
            scan = new Scanner(is);
        } else {
            File file = new File("src/lw01/unguided/washes.txt");
            if (!file.exists()) {
                file = new File("washes.txt");
            }
            try {
                scan = new Scanner(file);
            } catch (Exception e) {
                System.err.println("File washes.txt not found.");
                return;
            }
        }

        WashService[] wash = new WashService[scan.nextInt()];

        for (int i = 0; i < wash.length; i++) {
            String type = scan.next();
            String id = scan.next();
            int days = scan.nextInt();
            int units = scan.nextInt();

            if (type.equalsIgnoreCase("CAR")) {
                wash[i] = new CarWash(id, days, units);
            } else if (type.equalsIgnoreCase("MOTORCYCLE")) {
                wash[i] = new MotorcycleWash(id, days, units);
            }
        }

        scan.close();

        for (WashService washService : wash) {
            System.out.println(washService.summary());
        }
    }
}
