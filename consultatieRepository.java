package org.example.repository;

import org.example.domain.consultatie;
import org.example.domain.validator.Validator;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class consultatieRepository implements Repository<Long, consultatie>{

    private Connection connection;
    private Validator<consultatie> validator;

    public consultatieRepository(Connection connection, Validator<consultatie> validator) {
        this.connection = connection;
        this.validator = validator;
    }


    @Override
    public consultatie findOne(Long id) {
        consultatie consultatie = null;
        try{
            PreparedStatement st = connection.prepareStatement("SELECT * from consultaties where id=?");
            st.setLong(1,id);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                consultatie = new consultatie();
                consultatie.setId(rs.getLong("id"));
                consultatie.setnumepacient(rs.getString("numepacient"));
                consultatie.setmedic(rs.getString("medic"));
                consultatie.setsectie(rs.getString("sectie"));
                consultatie.setdurata(rs.getDouble("durata"));
            }
        } catch (SQLException e) {
            e.getStackTrace();
        }
        return consultatie;
    }

    @Override
    public List<consultatie> findAll() {
        List<consultatie> consultaties = new ArrayList<>();
        try{
            Statement st = connection.createStatement();

            ResultSet rs = st.executeQuery("select * from consultaties");
            while(rs.next()){
                consultatie consultatie = new consultatie();
                consultatie.setId(rs.getLong("id"));
                consultatie.setnumepacient(rs.getString("numepacient"));
                consultatie.setmedic(rs.getString("medic"));
                consultatie.setsectie(rs.getString("sectie"));
                consultatie.setdurata(rs.getDouble("durata"));
                consultaties.add(consultatie);
            }

        } catch (Exception e) {
            e.getStackTrace();
        }
        return consultaties;
    }

    @Override
    public consultatie save(consultatie entity) {
        consultatie consultatie = entity;
        validator.validate(consultatie);
        try{
            String sql = "INSERT INTO consultaties(numepacient, medic, sectie, durata) VALUES(?,?,?,?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1,consultatie.getnumepacient());
            st.setString(2,consultatie.getmedic());
            st.setString(3,consultatie.getsectie());
            st.setDouble(4,consultatie.getdurata());
            st.executeUpdate();
            return entity;
        } catch (SQLException e) {
            e.getStackTrace();
        }
        return consultatie;
    }

    @Override
    public consultatie delete(Long id) {
        consultatie consultatie = findOne(id);
        if (consultatie!=null){
            try{
                String sql = "DELETE FROM consultaties where id=?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setLong(1,id);
                statement.executeUpdate();
            } catch (SQLException e) {
                e.getStackTrace();
            }
        }
        return consultatie;
    }

    @Override
    public consultatie update(consultatie entity) {
        consultatie consultatie = entity;
        validator.validate(consultatie);
        try{
            String sql =" UPDATE consultaties SET numepacient=?, medic = ?, sectie=?, durata=? where id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1,consultatie.getnumepacient());
            st.setString(2,consultatie.getmedic());
            st.setString(3,consultatie.getsectie());
            st.setDouble(4,consultatie.getdurata());
            st.executeUpdate();
            return entity;
        } catch (SQLException e) {
            e.getStackTrace();
        }
        return consultatie;
    }
}
