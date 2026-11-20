package service;

import exceptions.InvalidParcelException;
import exceptions.InvalidStatusTransitionException;
import model.Parcel;
import model.StatusUpdate;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Handles parcel status changes and maintains tracking history.
 */
public class TrackingService {

    private ParcelService parcelService;
    private AgentService agentService;
    private HashMap<String, ArrayList<StatusUpdate>> historyMap;

    private String[] statuses = {
            "Booked",
            "Picked Up",
            "In Transit",
            "Out for Delivery",
            "Delivered"
    };

    public TrackingService(ParcelService parcelService, AgentService agentService) {
        this.parcelService = parcelService;
        this.agentService = agentService;
        this.historyMap = new HashMap<>();
    }

    public void updateStatus(String parcelId, String newStatus)
            throws InvalidParcelException, InvalidStatusTransitionException {

        Parcel parcel = parcelService.getParcel(parcelId);

        if (parcel == null) {
            throw new InvalidParcelException("Parcel not found: " + parcelId);
        }
        if ("Picked Up".equals(newStatus)
                && parcel.getAssignedAgentId() == null) {

            throw new InvalidStatusTransitionException(
                    "An agent must be assigned before the parcel can be picked up."
            );
        }

        int currentIndex = -1;

        for (int i = 0; i < statuses.length; i++) {
            if (statuses[i].equals(parcel.getStatus())) {
                currentIndex = i;
                break;
            }
        }

        if (currentIndex == -1 || currentIndex == statuses.length - 1
                || !statuses[currentIndex + 1].equals(newStatus)) {
            throw new InvalidStatusTransitionException(
                    "Invalid status change from " + parcel.getStatus() + " to " + newStatus
            );
        }

        parcel.setStatus(newStatus);

        ArrayList<StatusUpdate> history = historyMap.get(parcelId);

        if (history == null) {
            history = new ArrayList<>();
            history.add(new StatusUpdate("Booked"));
            historyMap.put(parcelId, history);
        }

        history.add(new StatusUpdate(newStatus));

        if (newStatus.equals("Delivered")) {
            agentService.releaseAgent(parcel.getAssignedAgentId());
        }
    }

    public ArrayList<StatusUpdate> getHistory(String parcelId)
            throws InvalidParcelException {

        Parcel parcel = parcelService.getParcel(parcelId);

        if (parcel == null) {
            throw new InvalidParcelException("Parcel not found: " + parcelId);
        }

        ArrayList<StatusUpdate> history = historyMap.get(parcelId);

        if (history == null) {
            history = new ArrayList<>();
            history.add(new StatusUpdate("Booked"));
            historyMap.put(parcelId, history);
        }

        return history;
    }
}