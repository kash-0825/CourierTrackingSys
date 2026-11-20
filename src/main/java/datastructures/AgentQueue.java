package datastructures;

import exceptions.NoAgentAvailableException;
import model.Agent;

/**
 * A hand-written FIFO queue for delivery agents.
 */
public class AgentQueue {

    private class Node {
        Agent agent;
        Node next;

        Node(Agent agent) {
            this.agent = agent;
            this.next = null;
        }
    }

    private Node front;
    private Node rear;

    public AgentQueue() {
        front = null;
        rear = null;
    }

    public boolean isEmpty() {
        return front == null;
    }

    public void enqueue(Agent agent) {
        Node newNode = new Node(agent);

        if (isEmpty()) {
            front = newNode;
            rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
    }

    public Agent dequeue() throws NoAgentAvailableException {
        if (isEmpty()) {
            throw new NoAgentAvailableException("No agents available in the queue.");
        }

        Agent agent = front.agent;
        front = front.next;

        if (front == null) {
            rear = null;
        }

        return agent;
    }

    public Agent peek() throws NoAgentAvailableException {
        if (isEmpty()) {
            throw new NoAgentAvailableException("No agents available in the queue.");
        }

        return front.agent;
    }
}