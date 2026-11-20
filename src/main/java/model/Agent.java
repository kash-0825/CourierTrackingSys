package model;

/**
 * Represents a delivery agent in the courier system.
 */
public class Agent extends Person {

    private boolean available;
    private int currentLoad;

    public Agent(String id, String name, String contact) {
        super(id, name, contact);
        this.available = true;
        this.currentLoad = 0;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public int getCurrentLoad() {
        return currentLoad;
    }

    public void setCurrentLoad(int currentLoad) {
        this.currentLoad = currentLoad;
    }

    @Override
    public void displayInfo() {
        System.out.println("Agent ID: " + getId());
        System.out.println("Name: " + getName());
        System.out.println("Contact: " + getContact());
        System.out.println("Available: " + available);
        System.out.println("Current Load: " + currentLoad);
    }
}