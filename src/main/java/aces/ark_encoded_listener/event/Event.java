package aces.ark_encoded_listener.event;

import lombok.Data;

@Data
public class Event {
    private String remoteId;
    private String data;
    private String createdAt;
}
