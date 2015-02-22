package com.model;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "Request")
public class Request {

    public static enum STATUS {
        STOP, PREPARE, WAIT_FOR_NUMBER,
        WAIT_FOR_NUMBER_SUBMIT,
        WAIT_FOR_CODE,
        COMPLETED
    }


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "propose_id")
    private Propose propose;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private STATUS status;

    @Column(name = "number")
    private long number;

    @Column(name = "code")
    private String code;

    @Column(name = "started")
    private Date started;

    @Column(name = "finished")
    private Date finished;

    @Column(name = "expired")
    private boolean expired;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Propose getPropose() {
        return propose;
    }

    public void setPropose(Propose propose) {
        this.propose = propose;
    }

    public STATUS getStatus() {
        return status;
    }

    public void setStatus(STATUS status) {
        this.status = status;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getStarted() {
        return started;
    }

    public void setStarted(Date started) {
        this.started = started;
    }

    public Date getFinished() {
        return finished;
    }

    public void setFinished(Date finished) {
        this.finished = finished;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }
}
