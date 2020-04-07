package com.bohdanserdyuk.CoronavirusApp.model.ejb.impl;

import com.bohdanserdyuk.CoronavirusApp.model.entities.Treatment;
import com.bohdanserdyuk.CoronavirusApp.model.entities.Vaccine;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Stateless
public class TreatmentEjb {
    @PersistenceContext
    private EntityManager entityManager;

    public Treatment getTreatmentByDoctorAndInfectedId(int doctorId, int infectedId) {
        Treatment result;
        try {
            result = (Treatment) entityManager
                    .createQuery("select r from Treatment r where r.doctorId = :doctorId and r.infectedId = :infectedId")
                    .setParameter("doctorId", doctorId)
                    .setParameter("infectedId", infectedId)
                    .getSingleResult();
        } catch (NoResultException e) {
            result = null;
        }
        return result;
    }

    public Treatment getTreatmentById(int id) {
        return entityManager.find(Treatment.class, id);
    }

    public void createTreatment(int doctorId, int infectedId) {
        entityManager.persist(new Treatment(0, doctorId, infectedId, null));
    }

    public void removeTreatmentById(int id) {
        entityManager.remove(entityManager.find(Treatment.class, id));
    }

    public void updateTreatmentVaccine(int treatmentId, int vaccineId) {
        Vaccine vaccine = entityManager.find(Vaccine.class, vaccineId);
        entityManager.find(Treatment.class, treatmentId).setVaccine(vaccine);
    }
}
