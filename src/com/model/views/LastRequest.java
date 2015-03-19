package com.model.views;

import org.hibernate.annotations.Subselect;
import org.hibernate.annotations.Synchronize;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Calendar;

/**
 * Created by Роман on 28.02.2015.
 */
@Entity
@Subselect("select * from lastRequests")
@Synchronize({"requests"})
public class LastRequest {
    @Id
    private int id;

    @Column(name = "finished")
    private Calendar calendar;

    @Column(name = "done")
    private int done;

    @Column(name = "fail")
    private int fail;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public int getDone() {
        return done;
    }

    public void setDone(int done) {
        this.done = done;
    }

    public int getFail() {
        return fail;
    }

    public void setFail(int fail) {
        this.fail = fail;
    }
}
