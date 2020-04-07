package com.bohdanserdyuk.CoronavirusApp.data.ejb.impl;

import com.bohdanserdyuk.CoronavirusApp.data.models.Vaccine;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class VaccineEjb {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Vaccine> getAllVaccines() {
        return entityManager.createQuery("SELECT c from Vaccine c").getResultList();
    }
}
