package com.bohdanserdyuk.CoronavirusApp.data.ejb;

import com.bohdanserdyuk.CoronavirusApp.data.models.Infected;

import javax.ejb.Local;

@Local
public interface InfectedDao {
    boolean saveInfected(Infected infected);
    Infected getInfectedByNumber(String phoneNumber);
}
