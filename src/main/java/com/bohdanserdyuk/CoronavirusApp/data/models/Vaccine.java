package com.bohdanserdyuk.CoronavirusApp.data.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Vaccine {
    @Id
    private String code;
    private String name;
    private float recoveryChance;

    public Vaccine() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getRecoveryChance() {
        return recoveryChance;
    }

    public void setRecoveryChance(float recoveryChance) {
        this.recoveryChance = recoveryChance;
    }
}
