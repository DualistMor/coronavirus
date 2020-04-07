package com.bohdanserdyuk.CoronavirusApp.data.ejb.impl;

import com.bohdanserdyuk.CoronavirusApp.data.models.Treatment;
import com.bohdanserdyuk.CoronavirusApp.data.models.Vaccine;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Stateless
public class TreatmentEjb {
    @PersistenceContext
    private EntityManager entityManager;

    public Treatment getInfectedTreatmentByVaccine(String infectedPhoneNumber, String vaccineCode) {
        Treatment result;
        try {
            result = (Treatment) entityManager
                    .createQuery("select r from Treatment r where r.infectedPhone = :infectedPhoneNumber and r.vaccine.code >= :vaccineCode")
                    .setParameter("infectedPhoneNumber", infectedPhoneNumber)
                    .setParameter("vaccineCode", vaccineCode)
                    .getSingleResult();
        } catch (NoResultException e) {
            result = null;
        }
        return result;
    }

    public Treatment getInfectedTreatmentById(int id) {
        return entityManager.find(Treatment.class, id);
    }

    public void createTreatment(String vaccineCode, String infectedPhone) {
        Vaccine vaccine = entityManager.find(Vaccine.class, vaccineCode);
        Treatment treatment = new Treatment();
        treatment.setInfectedPhone(infectedPhone);
        treatment.setVaccine(vaccine);
        entityManager.persist(treatment);
    }

    public void removeTreatmentById(int id) {
        entityManager.remove(entityManager.find(Treatment.class, id));
    }

    public void updateTreatmentVaccine(String vaccineCode, int treatmentId) {
        Vaccine vaccine = entityManager.find(Vaccine.class, vaccineCode);
        entityManager.find(Treatment.class, treatmentId).setVaccine(vaccine);
    }
}
