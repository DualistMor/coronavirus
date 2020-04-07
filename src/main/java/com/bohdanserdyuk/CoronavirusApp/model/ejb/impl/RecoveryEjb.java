package com.bohdanserdyuk.CoronavirusApp.model.ejb.impl;

import javax.ejb.Stateless;

@Stateless
public class RecoveryEjb {

    public LifeState getInfectedTreatmentResult() {
        return LifeState.DEAD;
    }

    public enum LifeState {
        CURED, DEAD
    }
}
