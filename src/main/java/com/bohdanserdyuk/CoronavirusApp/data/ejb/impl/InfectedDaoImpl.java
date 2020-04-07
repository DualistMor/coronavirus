package com.bohdanserdyuk.CoronavirusApp.data.ejb.impl;

import com.bohdanserdyuk.CoronavirusApp.data.ejb.InfectedDao;
import com.bohdanserdyuk.CoronavirusApp.data.models.Infected;

import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Stateless
public class InfectedDaoImpl implements InfectedDao {

    private Connection mConnection;

    public InfectedDaoImpl() {
        try {
            InitialContext context = new InitialContext();
            DataSource d = (DataSource) context.lookup("jdbc/MySqlDataResource");
            mConnection = d.getConnection();
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean saveInfected(Infected infected) {
        String query = "INSERT INTO INFECTEDS VALUES(null, ?, ?, ?);";
        try {
            PreparedStatement st = mConnection.prepareStatement(query);

            st.setString(1, infected.getPhoneNumber());
            st.setString(2, infected.getName());
            st.setString(3, infected.getAddress());

            st.execute();

            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Infected getInfectedByNumber(String phoneNumber) {
        try {
            String sql = "SELECT * FROM INFECTEDS WHERE phoneNumber = ?;";
            PreparedStatement st = mConnection.prepareStatement(sql);
            st.setString(1, phoneNumber);

            ResultSet result = st.executeQuery();
            Infected infected = null;

            if (result.next()) {

                String vendId = result.getString("phoneNumber");
                String name = result.getString("name");
                String address = result.getString("address");

                infected = new Infected(vendId, name, address);
            }

            result.close();
            st.close();

            return infected;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
