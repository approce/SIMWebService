package com.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


@Entity
@Table(name = "requests")
public class Request {

    public static enum STATUS {
        STOP, PREPARE, WAIT_NUMBER,
        NUMBER_SUBMIT,
        WAIT_CODE,
        COMPLETED,
        NUMBER_REJECT
    }


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "offer_id")
    private Offer offer;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private STATUS status;

    @Column(name = "number")
    private long number;

    @Column(name = "code")
    private String code; //TODO max 50

    @Column(name = "started")
    private Date started;

    @Column(name = "finished")
    private Date finished;

    @Column(name = "expired")
    private boolean expired;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "request")
    private List<Transaction> transaction;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
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

    public List<Transaction> getTransaction() {
        return transaction;
    }

    public float getTransactionSum() {
        float sum = 0;
        for (Transaction t : this.getTransaction()) {
            sum -= t.getChange_value();
        }
        return sum;
    }

    public void setTransaction(List<Transaction> transaction) {
        this.transaction = transaction;
    }

    public void addTransaction(Transaction transaction1) {
        if (this.transaction == null) {
            transaction = new LinkedList<>();
        }
        this.transaction.add(transaction1);
    }
}
