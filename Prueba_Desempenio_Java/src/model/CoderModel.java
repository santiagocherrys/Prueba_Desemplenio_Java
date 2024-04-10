package model;

import database.CRUD;
import database.ConfigDB;
import entity.Coder;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CoderModel implements CRUD {
    @Override
    public Object insert(Object obj) {
        //Castiamos el objeto
        Coder objCoder = (Coder) obj;

        //Abrimos la conexión
        Connection objConnection = ConfigDB.openConnection();

        //Sentencia sql
        try{
            String sql = "INSERT INTO coder(nombre, apellidos, documento, cohorte, cv) VALUES(?,?,?,?,?);";

            PreparedStatement objPrepare =objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1,objCoder.getNombre());
            objPrepare.setString(2,objCoder.getApellidos());
            objPrepare.setString(3,objCoder.getDocumento());
            objPrepare.setInt(4,objCoder.getCohorte());
            objPrepare.setString(5,objCoder.getCv());

            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            while(objResult.next()){
                objCoder.setId(objResult.getInt(1));
            }

            JOptionPane.showMessageDialog(null,"El coder fue agregado correctamente");

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Este es le error " + e.getMessage());
        }

        ConfigDB.closeConnection();

        return objCoder;
    }

    @Override
    public List<Object> findAll() {
        List<Object> listCoders = new ArrayList<>();

        Connection objConnection = ConfigDB.openConnection();

        try{
            String  sql = "SELECT * FROM coder;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            ResultSet objResult = objPrepare.executeQuery();

            while(objResult.next()){
                Coder objCoder = new Coder();

                objCoder.setId(objResult.getInt("id"));
                objCoder.setNombre(objResult.getString("nombre"));
                objCoder.setApellidos(objResult.getString("apellidos"));
                objCoder.setDocumento(objResult.getString("documento"));
                objCoder.setCohorte(objResult.getInt("cohorte"));
                objCoder.setCv(objResult.getString("cv"));

                listCoders.add(objCoder);
            }
        }catch (SQLException e){
            System.out.println("Error >" + e.getMessage());
        }
        ConfigDB.closeConnection();
        return listCoders;
    }

    public List<Coder> findByCohorte(int cohorte){
        List<Coder> listCoders = new ArrayList<>();

        Connection objConnection = ConfigDB.openConnection();

        try{
            String  sql = "SELECT * FROM coder WHERE cohorte = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1, cohorte);

            ResultSet objResult = objPrepare.executeQuery();

            while(objResult.next()){
                Coder objCoder = new Coder();

                objCoder.setId(objResult.getInt("id"));
                objCoder.setNombre(objResult.getString("nombre"));
                objCoder.setApellidos(objResult.getString("apellidos"));
                objCoder.setDocumento(objResult.getString("documento"));
                objCoder.setCohorte(objResult.getInt("cohorte"));
                objCoder.setCv(objResult.getString("cv"));

                listCoders.add(objCoder);
            }
        }catch (SQLException e){
            System.out.println("Error >" + e.getMessage());
        }
        ConfigDB.closeConnection();
        return listCoders;
    }

    public List<Coder> findByTecnologia(String tecnologia){
        List<Coder> listCoders = new ArrayList<>();

        Connection objConnection = ConfigDB.openConnection();

        try{
            String  sql = "SELECT * FROM coder WHERE cv = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setString(1, tecnologia);

            ResultSet objResult = objPrepare.executeQuery();

            while(objResult.next()){
                Coder objCoder = new Coder();

                objCoder.setId(objResult.getInt("id"));
                objCoder.setNombre(objResult.getString("nombre"));
                objCoder.setApellidos(objResult.getString("apellidos"));
                objCoder.setDocumento(objResult.getString("documento"));
                objCoder.setCohorte(objResult.getInt("cohorte"));
                objCoder.setCv(objResult.getString("cv"));

                listCoders.add(objCoder);
            }
        }catch (SQLException e){
            System.out.println("Error >" + e.getMessage());
        }
        ConfigDB.closeConnection();
        return listCoders;
    }


    @Override
    public boolean update(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Coder objCoder = (Coder) obj;

        boolean isUpdated = false;

        try{
            String sql = "UPDATE coder SET cv = ? WHERE id = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setString(1, objCoder.getCv());
            objPrepare.setInt(2,objCoder.getId());

            int totalRowAffected = objPrepare.executeUpdate();
            if(totalRowAffected > 0){
                isUpdated = true;
                JOptionPane.showMessageDialog(null, "El registro fue actualizado correctamente");
            }
        }catch (SQLException e){

        }

        ConfigDB.closeConnection();
        return isUpdated;
    }

    @Override
    public boolean delete(Object obj) {
        Connection objConnection = ConfigDB.openConnection();

        Coder objCoder = (Coder) obj;

        boolean isDeleted = false;

        try{
            String sql = "DELETE FROM coder WHERE id=?";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1,objCoder.getId());

            //devuelve un numero de filas afectadas

            int totalAffectedRows = objPrepare.executeUpdate();

            if(totalAffectedRows >0 ){
                isDeleted = true;

                JOptionPane.showMessageDialog(null,"Registro eliminado correctamente");
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "El Error es " + e.getMessage());
        }

        return isDeleted;
    }
}
