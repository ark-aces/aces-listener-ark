package aces.ark_encoded_listener.unsubscribe;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.ZonedDateTime;

@Data
@Entity
public class UnsubscribeEntity {

    @Id
    @GeneratedValue
    private Long id;

    private ZonedDateTime createdAt;
}
