package model;

import controller.EmpresaController;
import database.CRUD;
import database.ConfigDB;
import entity.Coder;
import entity.Empresa;
import entity.Vacante;
import utils.Utils;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static controller.EmpresaController.instanceEmpresaModel;

public class VacanteModel implements CRUD {
    @Override
    public Object insert(Object obj) {
        //Castiamos el objeto
        Vacante objVacante = (Vacante) obj;

        //Abrimos la conexión
        Connection objConnection = ConfigDB.openConnection();

        //Sentencia sql
        try{
            String sql = "INSERT INTO vacante(empresa_id, titulo, descripcion, duracion, estado, tecnologia, clan) VALUES(?,?,?,?,?,?,?);";

            PreparedStatement objPrepare =objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);


            objPrepare.setInt(1,objVacante.getEmpresa_id());
            objPrepare.setString(2,objVacante.getTitulo());
            objPrepare.setString(3,objVacante.getDescripcion());
            objPrepare.setString(4,objVacante.getDuracion());
            objPrepare.setString(5,objVacante.getEstado());
            objPrepare.setString(6,objVacante.getTecnologia());
            objPrepare.setString(7,objVacante.getClan());

            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            while(objResult.next()){
                objVacante.setId(objResult.getInt(1));
            }

            JOptionPane.showMessageDialog(null,"LA vacante fue agregado correctamente");

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Este es le error " + e.getMessage());
        }

        ConfigDB.closeConnection();

        return objVacante;

    }

    @Override
    public List<Object> findAll() {
        List<Object> listVacantes = new ArrayList<>();

        Connection objConnection = ConfigDB.openConnection();

        try{
            String  sql = "SELECT * FROM vacante INNER JOIN empresa ON vacante.empresa_id = empresa.id;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            ResultSet objResult = objPrepare.executeQuery();

            while(objResult.next()){
                Vacante objVacante = new Vacante();

                objVacante.setId(objResult.getInt("vacante.id"));
                objVacante.setEmpresa_id(objResult.getInt("empresa_id"));
                objVacante.setTitulo(objResult.getString("titulo"));
                objVacante.setDescripcion(objResult.getString("descripcion"));
                objVacante.setDuracion(objResult.getString("duracion"));
                objVacante.setEstado(objResult.getString("estado"));
                objVacante.setTecnologia(objResult.getString("tecnologia"));
                objVacante.setClan(objResult.getString("clan"));

                Empresa objEmpresa = new Empresa();
                objEmpresa.setId(objResult.getInt("empresa.id"));
                objEmpresa.setNombre(objResult.getString("nombre"));
                objEmpresa.setSector(objResult.getString("sector"));
                objEmpresa.setUbicacion(objResult.getString("ubicacion"));
                objEmpresa.setContacto(objResult.getString("contacto"));

                objVacante.setEmpresa(objEmpresa);

                listVacantes.add(objVacante);
            }
        }catch (SQLException e){
            System.out.println("Error >" + e.getMessage());
        }
        ConfigDB.closeConnection();
        return listVacantes;
    }

    public List<Vacante> findAllActivo(){
        List<Vacante> listVacantes = new ArrayList<>();

        Connection objConnection = ConfigDB.openConnection();

        try{
            String  sql = "SELECT * FROM vacante INNER JOIN empresa ON vacante.empresa_id = empresa.id WHERE estado= ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setString(1,"ACTIVO" );


            ResultSet objResult = objPrepare.executeQuery();

            while(objResult.next()){
                Vacante objVacante = new Vacante();

                objVacante.setId(objResult.getInt("vacante.id"));
                objVacante.setEmpresa_id(objResult.getInt("empresa_id"));
                objVacante.setTitulo(objResult.getString("titulo"));
                objVacante.setDescripcion(objResult.getString("descripcion"));
                objVacante.setDuracion(objResult.getString("duracion"));
                objVacante.setEstado(objResult.getString("estado"));
                objVacante.setTecnologia(objResult.getString("tecnologia"));
                objVacante.setClan(objResult.getString("clan"));

                Empresa objEmpresa = new Empresa();
                objEmpresa.setId(objResult.getInt("empresa.id"));
                objEmpresa.setNombre(objResult.getString("nombre"));
                objEmpresa.setSector(objResult.getString("sector"));
                objEmpresa.setUbicacion(objResult.getString("ubicacion"));
                objEmpresa.setContacto(objResult.getString("contacto"));

                objVacante.setEmpresa(objEmpresa);

                listVacantes.add(objVacante);
            }
        }catch (SQLException e){
            System.out.println("Error >" + e.getMessage());
        }
        ConfigDB.closeConnection();
        return listVacantes;
    }

    @Override
    public boolean update(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Vacante objVacante = (Vacante) obj;

        boolean isUpdated = false;

        try{
            String sql = "UPDATE vacante SET empresa_id = ? , titulo = ?, descripcion = ?, duracion = ? , estado = ?, tecnologia = ?, clan = ?  WHERE id = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1, objVacante.getEmpresa_id());
            objPrepare.setString(2,objVacante.getTitulo());
            objPrepare.setString(3,objVacante.getDescripcion());
            objPrepare.setString(4,objVacante.getDuracion());
            objPrepare.setString(5,objVacante.getEstado());
            objPrepare.setString(6,objVacante.getTecnologia());
            objPrepare.setString(7,objVacante.getClan());
            objPrepare.setInt(8,objVacante.getId());

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

    public boolean updateVacanteEstado(int id){
        Connection objConnection = ConfigDB.openConnection();

        boolean isUpdated = false;

        try{
            String sql = "UPDATE vacante SET  estado = ? WHERE id = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setString(1,"INACTIVO");
            objPrepare.setInt(2, id);



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

        Vacante objVacante = (Vacante) obj;

        boolean isDeleted = false;

        try{
            String sql = "DELETE FROM vacante WHERE id=?";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1,objVacante.getId());

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
