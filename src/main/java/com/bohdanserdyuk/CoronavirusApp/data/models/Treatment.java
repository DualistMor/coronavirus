package com.bohdanserdyuk.CoronavirusApp.data.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Treatment {
    @Id
    @GeneratedValue
    private int id;
    private String infectedPhone;
    @ManyToOne
    private Vaccine vaccine;

    public Treatment() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInfectedPhone() {
        return infectedPhone;
    }

    public void setInfectedPhone(String infectedPhone) {
        this.infectedPhone = infectedPhone;
    }

    public Vaccine getVaccine() {
        return vaccine;
    }

    public void setVaccine(Vaccine vaccine) {
        this.vaccine = vaccine;
    }
}
