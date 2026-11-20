package service;

import model.Parcel;
import util.IdGenerator;
import exceptions.InvalidParcelException;

/**
 * BookingService handles the creation and cancellation of parcel bookings.
 * It depends on ParcelService to actually store/retrieve/remove the parcel,
 * and on IdGenerator to assign each new parcel a unique ID.
 */
public class BookingService {

    private ParcelService parcelService;

    public BookingService(ParcelService parcelService) {
        this.parcelService = parcelService;
    }

    /**
     * Books a new parcel: generates a unique ID, creates the Parcel object,
     * and stores it via ParcelService.
     */
    public Parcel bookParcel(String sender, String receiver, double weight) {
        String newId = IdGenerator.generateParcelId();
        Parcel parcel = new Parcel(newId, sender, receiver, weight);
        parcelService.addParcel(parcel);
        return parcel;
    }

    /**
     * Cancels a booking by parcel ID. If the parcel doesn't exist,
     * ParcelService.removeParcel() returns null — we check for that
     * and throw InvalidParcelException instead of letting the caller
     * silently think the cancellation worked.
     */
    public void cancelParcel(String parcelId) throws InvalidParcelException {
        Parcel removed = parcelService.removeParcel(parcelId);
        if (removed == null) {
            throw new InvalidParcelException("No parcel found with ID: " + parcelId);
        }
    }
}