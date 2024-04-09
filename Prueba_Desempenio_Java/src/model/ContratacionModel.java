package model;

import database.CRUD;
import database.ConfigDB;
import entity.Contratacion;
import entity.Vacante;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class ContratacionModel implements CRUD {

    @Override
    public Object insert(Object obj) {
        //Castiamos el objeto
        Contratacion objContratacion = (Contratacion) obj;

        //Abrimos la conexión
        Connection objConnection = ConfigDB.openConnection();

        //Sentencia sql
        try{
            String sql = "INSERT INTO contratacion(vacante_id, coder_id, estado, salario) VALUES(?,?,?,?);";

            PreparedStatement objPrepare =objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);


            objPrepare.setInt(1,objContratacion.getVacante_id());
            objPrepare.setInt(2,objContratacion.getCoder_id());
            objPrepare.setString(3,objContratacion.getEstado());
            objPrepare.setDouble(4,objContratacion.getSalario());


            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            while(objResult.next()){
                objContratacion.setId(objResult.getInt(1));
            }

            JOptionPane.showMessageDialog(null,"La contratacion fue agregada correctamente");

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Este es le error " + e.getMessage());
        }

        ConfigDB.closeConnection();

        return objContratacion;
    }

    @Override
    public List<Object> findAll() {
        return null;
    }

    @Override
    public boolean update(Object obj) {
        return false;
    }

    @Override
    public boolean delete(Object obj) {
        return false;
    }
}
