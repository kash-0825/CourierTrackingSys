package model;

import java.time.LocalDateTime;

/**
 * Stores one status change and the time it occurred.
 */
public class StatusUpdate {

    private String status;
    private LocalDateTime timestamp;

    public StatusUpdate(String status) {
        this.status = status;
        this.timestamp = LocalDateTime.now();
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return status + " - " + timestamp;
    }
}