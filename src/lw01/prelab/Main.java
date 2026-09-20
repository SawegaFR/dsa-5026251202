package lw01.prelab;

import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(Main.class.getResourceAsStream("jobs.txt"));
        ArrayList<PrintJob> jobs = new ArrayList<>();

        while (scan.hasNextLine()) {
            String line = scan.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }
            String[] parts = line.split(" ");
            String type = parts[0];
            String id = parts[1];
            int pages = Integer.parseInt(parts[2]);
            PrintJob job = null;

            if (type.equals("MONO")) {
                job = new MonoPrint(id, pages);
            } else if (type.equals("COLOUR")) {
                job = new ColourPrint(id, pages);
            }

            if (job != null) {
                jobs.add(job);
            }
        }
        scan.close();

        for (PrintJob job : jobs) {
            System.out.println(job.summary());
        }
    }
}
