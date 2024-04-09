package model;

import database.CRUD;
import database.ConfigDB;
import entity.Coder;
import entity.Empresa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpresaModel implements CRUD{

    @Override
    public Object insert(Object obj) {
        return null;
    }

    @Override
    public List<Object> findAll() {
        List<Object> listEmpresas = new ArrayList<>();

        Connection objConnection = ConfigDB.openConnection();

        try{
            String  sql = "SELECT * FROM empresa;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            ResultSet objResult = objPrepare.executeQuery();

            while(objResult.next()){
                Empresa objEmpresa = new Empresa();

                objEmpresa.setId(objResult.getInt("id"));
                objEmpresa.setNombre(objResult.getString("nombre"));
                objEmpresa.setSector(objResult.getString("sector"));
                objEmpresa.setUbicacion(objResult.getString("ubicacion"));
                objEmpresa.setContacto(objResult.getString("contacto"));


                listEmpresas.add(objEmpresa);
            }
        }catch (SQLException e){
            System.out.println("Error >" + e.getMessage());
        }
        ConfigDB.closeConnection();
        return listEmpresas;
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
