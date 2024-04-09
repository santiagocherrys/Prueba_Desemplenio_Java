package controller;

import entity.Coder;
import entity.Empresa;
import model.CoderModel;
import model.EmpresaModel;

import javax.swing.*;
import java.util.List;

public class EmpresaController {
    public static void getAll(){
        String list = getAll(instanceEmpresaModel().findAll());

        JOptionPane.showMessageDialog(null, list);
    }

    public static String getAll(List<Object> list){
        String listString = "LISTA DE Empresas: \n";

        for(Object temp: list){
            Empresa objEmpresa = (Empresa) temp;
            listString += objEmpresa.imprimirTodo() + "\n";
        }

        return listString;
    }

    public static EmpresaModel instanceEmpresaModel(){
        return new EmpresaModel();
    }
}
