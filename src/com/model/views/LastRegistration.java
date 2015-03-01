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
//using view from db:
@Subselect("select * from lastregistered")
@Synchronize({"user"})
public class LastRegistration {

    @Id
    private int id;

    @Column(name = "registered")
    private Calendar calendar;

    @Column(name = "count")
    private int count;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }
}
