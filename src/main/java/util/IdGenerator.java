package util;

/**
 * Generates unique sequential IDs for parcels and agents.
 * Two independent counters ensure parcel and agent sequences
 * never interfere with each other.
 */
public class IdGenerator {

    private static int parcelCounter = 1;
    private static int agentCounter = 1;

    private IdGenerator() {
    }

    /**
     * Generates the next parcel ID in sequence, e.g. PAR001, PAR002...
     * Uses String.format to pad the number to 3 digits.
     */
    public static String generateParcelId() {
        String id = String.format("PAR%03d", parcelCounter);
        parcelCounter++;
        return id;
    }

    /**
     * Generates the next agent ID in sequence, e.g. AGT01, AGT02...
     * Uses String.format to pad the number to 2 digits.
     */
    public static String generateAgentId() {
        String id = String.format("AGT%02d", agentCounter);
        agentCounter++;
        return id;
    }
}