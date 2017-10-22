package aces.ark_encoded_listener.event;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
public class Event {

    @Id
    @GeneratedValue
    private Long id;



}

