package ru.popov.microService1.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "session_id")
    private Long sessionId;

    @Column(name = "MC1_timestamp")
    private Date MC1Time;

    @Column(name = "MC2_timestamp")
    private Date MC2Time;

    @Column(name = "MC3_timestamp")
    private Date MC3Time;

    @Column(name = "end_timestamp")
    private Date endTime;

    public Message() {
    }

    public Message(Long id, Long sessionId, Date MC1Time, Date MC2Time, Date MC3Time, Date endTime) {
        this.id = id;
        this.sessionId = sessionId;
        this.MC1Time = MC1Time;
        this.MC2Time = MC2Time;
        this.MC3Time = MC3Time;
        this.endTime = endTime;
    }
}
