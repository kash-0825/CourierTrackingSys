package service;

import datastructures.AgentQueue;
import exceptions.InvalidParcelException;
import exceptions.NoAgentAvailableException;
import model.Agent;
import model.Parcel;
import util.IdGenerator;
import java.util.ArrayList;

import java.util.HashMap;

/**
 * Handles agent registration and parcel assignment.
 */
public class AgentService {

    private ParcelService parcelService;
    private AgentQueue agentQueue;
    private HashMap<String, Agent> agentMap;

    public AgentService(ParcelService parcelService) {
        this.parcelService = parcelService;
        this.agentQueue = new AgentQueue();
        this.agentMap = new HashMap<>();
    }

    public Agent registerAgent(String name, String contact) {
        String agentId = IdGenerator.generateAgentId();

        Agent agent = new Agent(agentId, name, contact);

        agentMap.put(agentId, agent);
        agentQueue.enqueue(agent);

        return agent;
    }

    public Agent assignParcel(String parcelId)
            throws InvalidParcelException, NoAgentAvailableException {

        Parcel parcel = parcelService.getParcel(parcelId);

        if (parcel == null) {
            throw new InvalidParcelException(
                    "Parcel not found: " + parcelId
            );
        }

        if (parcel.getAssignedAgentId() != null) {
            throw new IllegalStateException(
                    "An agent is already assigned to this parcel."
            );
        }

        Agent agent = agentQueue.dequeue();

        agent.setAvailable(false);
        agent.setCurrentLoad(agent.getCurrentLoad() + 1);

        parcel.setAssignedAgentId(agent.getId());

        return agent;
    }

    public void releaseAgent(String agentId) {
        Agent agent = agentMap.get(agentId);

        if (agent != null && !agent.isAvailable()) {
            agent.setAvailable(true);
            agent.setCurrentLoad(agent.getCurrentLoad() - 1);
            agentQueue.enqueue(agent);
        }
    }
    public ArrayList<Agent> getAllAgents() {
        return new ArrayList<>(agentMap.values());
    }

    public Agent getAgent(String agentId) {
        return agentMap.get(agentId);
    }
}