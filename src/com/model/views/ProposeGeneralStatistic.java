package com.model.views;

import org.hibernate.annotations.Subselect;
import org.hibernate.annotations.Synchronize;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Роман on 01.03.2015.
 */
@Entity
@Subselect("select * from proposeGeneralStatistic")
@Synchronize({"propose"})
public class ProposeGeneralStatistic {
    @Id
    private int id;
    @Column(name = "fullName")
    private String fullName;

    @Column(name = "count")
    private long count;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
