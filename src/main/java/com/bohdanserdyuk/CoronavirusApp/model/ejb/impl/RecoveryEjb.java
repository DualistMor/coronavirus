package com.bohdanserdyuk.CoronavirusApp.model.ejb.impl;

import com.bohdanserdyuk.CoronavirusApp.model.entities.Infected;
import com.bohdanserdyuk.CoronavirusApp.model.entities.Vaccine;

import javax.ejb.Stateless;

@Stateless
public class RecoveryEjb {

    public LifeState getInfectedTreatmentResult(Infected infected, Vaccine vaccine) {
        if( Math.random() <= infected.getAge() / 100f - vaccine.getRecoveryChance() ) {
            return LifeState.DEAD;
        }
        return LifeState.CURED;
    }

    public enum LifeState {
        CURED, DEAD
    }
}
