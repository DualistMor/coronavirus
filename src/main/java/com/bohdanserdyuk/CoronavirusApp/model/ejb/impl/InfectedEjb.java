package com.bohdanserdyuk.CoronavirusApp.model.ejb.impl;

import com.bohdanserdyuk.CoronavirusApp.model.entities.Infected;
import com.bohdanserdyuk.CoronavirusApp.model.entities.Vaccine;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class InfectedEjb {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Infected> getAllInfected() {
        return entityManager.createQuery("SELECT c from Infected c").getResultList();
    }
}
