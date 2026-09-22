package lw01.unguided;

public abstract class WashService implements Billable {

    private String id;
    private int days;
    private int units = 1;

    protected WashService(String id, int days) {
        if (days <= 0) {
            throw new IllegalArgumentException("Days must be greater than zero.");
        }
        this.id = id;
        this.days = days;
        this.units = 1;
    }

    protected WashService(String id, int days, int units) {
        if (days <= 0) {
            throw new IllegalArgumentException("Days must be greater than zero.");
        }
        if (units <= 0) {
            throw new IllegalArgumentException("Units must be greater than zero.");
        }
        this.id = id;
        this.days = days;
        this.units = units;
    }

    public String getId() {
        return this.id;
    }

    public int getDays() {
        return this.days;
    }

    public int getUnits() {
        return this.units;
    }

    @Override
    public abstract int calculateCharge();

    public int calculateCharge(int units) {
        if (units <= 0) {
            throw new IllegalArgumentException("Units must be greater than zero.");
        }
        return units * calculateCharge();
    }

    public String label() {
        return "Service";
    }

    public String summary() {
        return id + " | " + label() + " | " + calculateCharge(units);
    }

    public String summary(int units) {
        return id + " | " + label() + " | " + calculateCharge(units);
    }
}