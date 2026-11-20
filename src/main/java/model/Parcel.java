package model;

/**
 * Represents a single parcel booked into the courier system.
 * Holds sender/receiver details as plain Strings (no separate
 * Customer class), current delivery status, and which agent
 * (if any) it has been assigned to.
 */
public class Parcel {

    private String id;
    private String sender;
    private String receiver;
    private double weight;
    private String status;
    private String assignedAgentId;

    public Parcel(String id, String sender, String receiver, double weight) {
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        setWeight(weight);           // routed through the setter for validation
        this.status = "Booked";      // every parcel starts in the Booked state
        this.assignedAgentId = null; // not assigned to any agent yet
    }

    public String getId() {
        return id;
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public double getWeight() {
        return weight;
    }

    /**
     * Validated setter — rejects zero or negative weights, since a
     * parcel with no real weight makes no physical sense.
     */
    public void setWeight(double weight) {
        if (weight <= 0) {
            throw new IllegalArgumentException("Parcel weight must be positive.");
        }
        this.weight = weight;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAssignedAgentId() {
        return assignedAgentId;
    }

    public void setAssignedAgentId(String assignedAgentId) {
        this.assignedAgentId = assignedAgentId;
    }

    /**
     * Readable summary of this parcel, used by menu options like
     * "view all parcels".
     */
    @Override
    public String toString() {
        return "Parcel{" +
                "id='" + id + '\'' +
                ", sender='" + sender + '\'' +
                ", receiver='" + receiver + '\'' +
                ", weight=" + weight +
                ", status='" + status + '\'' +
                ", assignedAgentId='" + assignedAgentId + '\'' +
                '}';
    }
}