package com.bohdanserdyuk.CoronavirusApp.model.ejb;

import com.bohdanserdyuk.CoronavirusApp.model.entities.Doctor;
import com.bohdanserdyuk.CoronavirusApp.model.entities.Infected;

import javax.ejb.Local;

@Local
public interface DoctorDao {
    boolean saveDoctor(Doctor doctor);
    boolean updateDoctor(Doctor doctor);
    Doctor getDoctorByName(String name);
    Doctor getDoctorById(int id);
}
