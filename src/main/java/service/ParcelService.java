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
}