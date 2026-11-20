
package service;

import exceptions.InvalidParcelException;
import model.Parcel;
import util.IdGenerator;

public class BookingService {

    private final ParcelService parcelService;
    private final AgentService agentService;

    public BookingService(ParcelService parcelService, AgentService agentService) {
        this.parcelService = parcelService;
        this.agentService = agentService;
    }

    public Parcel bookParcel(String sender, String receiver, double weight) {

        if (sender == null || sender.trim().isEmpty()) {
            throw new IllegalArgumentException("Sender name cannot be empty.");
        }

        if (receiver == null || receiver.trim().isEmpty()) {
            throw new IllegalArgumentException("Receiver name cannot be empty.");
        }

        if (!Double.isFinite(weight) || weight <= 0) {
            throw new IllegalArgumentException(
                    "Parcel weight must be a positive finite number."
            );
        }

        String newId = IdGenerator.generateParcelId();

        Parcel parcel = new Parcel(newId, sender.trim(), receiver.trim(), weight);

        parcelService.addParcel(parcel);

        return parcel;
    }

    public void cancelParcel(String parcelId) throws InvalidParcelException {

        Parcel parcel = parcelService.getParcel(parcelId);

        if (parcel == null) {
            throw new InvalidParcelException(
                    "No parcel found with ID: " + parcelId
            );
        }

        if (!"Booked".equals(parcel.getStatus())) {
            throw new IllegalStateException(
                    "Only parcels with Booked status can be cancelled."
            );
        }

        String agentId = parcel.getAssignedAgentId();

        if (agentId != null) {
            agentService.releaseAgent(agentId);
        }

        parcelService.removeParcel(parcelId);
    }
}
