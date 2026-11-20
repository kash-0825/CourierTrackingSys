package service;

import model.Parcel;
import java.util.HashMap;

/**
 * ParcelService is responsible for storing and managing all Parcel objects
 * created in the system. It acts as the central in-memory "database" for
 * parcels, using a HashMap so that any parcel can be looked up instantly
 * by its unique ID instead of searching through a list one by one.
 */
public class ParcelService {

    // Stores parcels with their unique ID as the key for O(1) lookup.
    private HashMap<String, Parcel> parcelMap;

    public ParcelService() {
        this.parcelMap = new HashMap<>();
    }

    /**
     * Adds a new parcel to the system using its ID as the key.
     * If a parcel with the same ID already exists, it will be silently overwritten; this should never happen
     * in practice since IDs are generated uniquely by IdGenerator.
     */
    public void addParcel(Parcel parcel) {
        parcelMap.put(parcel.getId(), parcel);
    }

    /**
     * Retrieves a parcel by its ID.
     * Returns null if no parcel with that ID exists — callers (like
     * BookingService or TrackingService) are responsible for checking
     * this and throwing InvalidParcelException if needed.
     */
    public Parcel getParcel(String parcelId) {
        return parcelMap.get(parcelId);
    }
}