package model;

import database.CRUD;
import database.ConfigDB;
import entity.Coder;
import entity.Contratacion;
import entity.Empresa;
import entity.Vacante;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
        List<Object> listContratacion = new ArrayList<>();

        Connection objConnection = ConfigDB.openConnection();

        try{
            String  sql = "SELECT * FROM contratacion INNER JOIN vacante ON contratacion.vacante_id = vacante.id " +
                    "INNER JOIN coder ON contratacion.coder_id = coder.id INNER JOIN empresa ON empresa.id = vacante.empresa_id;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            ResultSet objResult = objPrepare.executeQuery();

            while(objResult.next()){


                int id = objResult.getInt("contratacion.id");
                int vacante_id = objResult.getInt("contratacion.vacante_id");
                int coder_id = objResult.getInt("contratacion.coder_id");
                String fecha_aplicacion = objResult.getString("contratacion.fecha_aplicacion");
                String estado = objResult.getString("contratacion.estado");
                Double salario = objResult.getDouble("contratacion.salario");


                Vacante objVacante = new Vacante();
                objVacante.setId(objResult.getInt("vacante.id"));
                objVacante.setEmpresa_id(objResult.getInt("vacante.empresa_id"));
                objVacante.setTitulo(objResult.getString("vacante.titulo"));
                objVacante.setDescripcion(objResult.getString("vacante.descripcion"));
                objVacante.setDuracion(objResult.getString("vacante.duracion"));
                objVacante.setEstado(objResult.getString("vacante.estado"));
                objVacante.setTecnologia(objResult.getString("vacante.tecnologia"));
                objVacante.setClan(objResult.getString("vacante.clan"));

                Empresa objEmpresa = new Empresa();
                objEmpresa.setId(objResult.getInt("Empresa.id"));
                objEmpresa.setNombre(objResult.getString("Empresa.nombre"));
                objEmpresa.setSector(objResult.getString("Empresa.sector"));
                objEmpresa.setUbicacion(objResult.getString("Empresa.ubicacion"));
                objEmpresa.setContacto(objResult.getString("Empresa.contacto"));

                objVacante.setEmpresa(objEmpresa);

                Coder objCoder = new Coder();
                objCoder.setId(objResult.getInt("coder.id"));
                objCoder.setNombre(objResult.getString("coder.nombre"));
                objCoder.setApellidos(objResult.getString("coder.apellidos"));
                objCoder.setDocumento(objResult.getString("coder.documento"));
                objCoder.setCohorte(objResult.getInt("coder.cohorte"));
                objCoder.setCv(objResult.getString("coder.cv"));



                Contratacion objContratacion = new Contratacion(vacante_id,coder_id,estado,salario,objVacante,objCoder,objEmpresa);
                objContratacion.setId(id);
                objContratacion.setFecha_aplicacion(fecha_aplicacion);


                listContratacion.add(objContratacion);
            }
        }catch (SQLException e){
            System.out.println("Error >" + e.getMessage());
        }
        ConfigDB.closeConnection();
        return listContratacion;
    }

    @Override
    public boolean update(Object obj) {

        Connection objConnection = ConfigDB.openConnection();
        Contratacion objContratacion = (Contratacion) obj;

        boolean isUpdated = false;

        try{
            String sql = "UPDATE contratacion SET vacante_id = ? , coder_id = ?, estado = ? , salario = ? WHERE id = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1, objContratacion.getVacante_id());
            objPrepare.setInt(2,objContratacion.getCoder_id());
            objPrepare.setString(3,objContratacion.getEstado());
            objPrepare.setDouble(4,objContratacion.getSalario());
            objPrepare.setInt(5,objContratacion.getId());


            int totalRowAffected = objPrepare.executeUpdate();
            if(totalRowAffected > 0){
                isUpdated = true;
                JOptionPane.showMessageDialog(null, "El registro fue actualizado correctamente");
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"El error es " + e.getMessage());
        }

        ConfigDB.closeConnection();
        return isUpdated;
    }

    @Override
    public boolean delete(Object obj) {
        Connection objConnection = ConfigDB.openConnection();

        Contratacion objContratacion = (Contratacion) obj;

        boolean isDeleted = false;

        try{
            String sql = "DELETE FROM contratacion WHERE id=?";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1,objContratacion.getId());

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
