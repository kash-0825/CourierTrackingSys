package service;

import model.Parcel;
import util.IdGenerator;

/**
 * BookingService handles the creation of new parcel bookings.
 * It depends on ParcelService to actually store the parcel once created,
 * and on IdGenerator to assign each new parcel a unique ID.
 */
public class BookingService {

    private ParcelService parcelService;

    public BookingService(ParcelService parcelService) {
        this.parcelService = parcelService;
    }
}