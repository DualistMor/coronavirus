package com.bohdanserdyuk.CoronavirusApp.model.ejb.impl;

import com.bohdanserdyuk.CoronavirusApp.model.ejb.DoctorDao;
import com.bohdanserdyuk.CoronavirusApp.model.entities.Doctor;
import com.bohdanserdyuk.CoronavirusApp.model.entities.Infected;

import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Stateless
public class DoctorDaoImpl implements DoctorDao {

    private Connection mConnection;

    public DoctorDaoImpl() {
        try {
            InitialContext context = new InitialContext();
            DataSource d = (DataSource) context.lookup("jdbc/MySqlDataResource");
            mConnection = d.getConnection();
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean saveDoctor(Doctor doctor) {
        String query = "INSERT INTO DOCTORS VALUES(null, ?, ?, ?, ?);";
        try {
            PreparedStatement st = mConnection.prepareStatement(query);

            st.setString(1, doctor.getName());
            st.setString(2, doctor.getPassword());
            st.setInt(3, doctor.getCured());
            st.setInt(4, doctor.getDeaths());

            st.execute();

            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean updateDoctor(Doctor doctor) {
        String query = "UPDATE DOCTORS SET cured = ?, deaths= ? WHERE id = ?;";
        try {
            PreparedStatement st = mConnection.prepareStatement(query);
            st.setInt(1, doctor.getCured());
            st.setInt(2, doctor.getDeaths());
            st.setInt(3, doctor.getId());

            st.execute();

            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Doctor getDoctorById(int id) {
        String sql = "SELECT * FROM DOCTORS WHERE id = ?;";
        try {
            PreparedStatement st = mConnection.prepareStatement(sql);

            st.setInt(1, id);

            ResultSet result = st.executeQuery();
            Doctor doctor = getDoctor(result);
            result.close();
            st.close();

            return doctor;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Doctor getDoctorByName(String name) {
        String sql = "SELECT * FROM DOCTORS WHERE name = ?;";
        try {
            PreparedStatement st = mConnection.prepareStatement(sql);

            st.setString(1, name);

            ResultSet result = st.executeQuery();
            Doctor doctor = getDoctor(result);
            result.close();
            st.close();

            return doctor;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Doctor getDoctor(ResultSet result) throws SQLException {
        Doctor doctor = null;
        if (result.next()) {
            int id = result.getInt("id");
            String docName = result.getString("name");
            String password = result.getString("password");
            int cured = result.getInt("cured");
            int deaths = result.getInt("deaths");

            doctor = new Doctor(id, docName, password, cured, deaths);
        }
        return doctor;
    }

}
