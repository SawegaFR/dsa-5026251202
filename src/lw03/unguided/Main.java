package lw03.unguided;

import java.util.*;
public class Main {
    public static void main(String[] args) {
        Set<String> registered = new HashSet<>();
        Set<String> checkedIn = new HashSet<>();
        
        Scanner reg = new Scanner(Main.class.getResourceAsStream("registrations.txt"));
        while (reg.hasNextLine()) {
            String id = reg.nextLine().trim();
            if (!id.isEmpty()) {
                registered.add(id); 
            }
        }
        reg.close();

        System.out.println("===== Event Check-In Results");

        Scanner checkin = new Scanner(Main.class.getResourceAsStream("checkins.txt"));
        int rejected = 0;
        while (checkin.hasNextLine()) {
            String id = checkin.nextLine().trim();
            if (id.isEmpty()) continue;

            if (registered.contains(id)) {
                if (checkedIn.contains(id)) {
                    System.out.println(id + ": Rejected (already checked in)");
                    rejected++;
                } else {
                    checkedIn.add(id);
                    System.out.println(id + ": Checked in");
                }
            } else {
                System.out.println(id + ": Rejected (not registered)");
                rejected++;
            }
        }
        checkin.close();
        int absent = registered.size() - checkedIn.size();

        System.out.println("\n===== Final Event Summary =====");
        System.out.println("Registered students: " + registered.size());
        System.out.println("Successful check-ins: " + checkedIn.size());
        System.out.println("Absent students: " + absent);
        System.out.println("Rejected attempts: " + rejected);
    }
}
