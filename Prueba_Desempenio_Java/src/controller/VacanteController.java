package controller;

import database.ConfigDB;
import entity.Coder;
import entity.Empresa;
import entity.Vacante;
import model.CoderModel;
import model.VacanteModel;
import utils.Utils;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VacanteController {
    public static void insert(){
        //Se piden datos
        try{

            //Se lista la empresa
            ;
            Object[] options = Utils.listToArray(EmpresaController.instanceEmpresaModel().findAll());

            Empresa objSelected = (Empresa) JOptionPane.showInputDialog(null,
                    "Selecciona una empresa",
                    "",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);

            String titulo = JOptionPane.showInputDialog("Ingrese titulo de la vacante");
            String descripcion = JOptionPane.showInputDialog("Ingrese descripcion de la vacante");
            String duracion = JOptionPane.showInputDialog("Ingrese duracion de la vacante");

            //Se hace selector para estado
            String[] selectorEstado = {"ACTIVO","INACTIVO"};
            String estado = (String) JOptionPane.showInputDialog(null,
                    "Selecciona un estado",
                    "estados disponibles",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    selectorEstado,
                    selectorEstado[0]);

            //ingresar tecnologias
            String[] selectorTecnologias = {"Javascript, html, css","Java y bases de datos", "Java, bases de datos, Springboot"};

            //Se hace selector para tecnologias

            String tecnologia = (String) JOptionPane.showInputDialog(null,
                    "Selecciona una tecnologia",
                    "tecnologias disponibles",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    selectorTecnologias,
                    selectorTecnologias[0]);

            //Ingresar clanes
                String[] selectorClanes = {"Meta","Lovelace","Van Russon", "Linus" ,"Steve Jobs"};
            String clan = (String) JOptionPane.showInputDialog(null,
                    "Selecciona un clan",
                    "clanes disponibles",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    selectorClanes,
                    selectorClanes[0]);


            JOptionPane.showMessageDialog(null,instanceVacanteModel().insert(new Vacante(objSelected.getId(),titulo,descripcion,duracion,estado,tecnologia,clan,objSelected)));

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Dato no valido " + e.getMessage());
        }

    }

    public static void getAll(){
        String list = getAll(instanceVacanteModel().findAll());

        JOptionPane.showMessageDialog(null, list);
    }

    public static List<Vacante> getAllActivo(){
        return instanceVacanteModel().findAllActivo();
    }

    public static String getAll(List<Object> list){
        String listString = "LISTA DE Vacantes: \n";

        for(Object temp: list){
            Vacante objVacante = (Vacante) temp;
            listString += objVacante.imprimirTodo() + "\n";
        }

        return listString;
    }

    public static void delete(){
        Object[] options = Utils.listToArray(instanceVacanteModel().findAll());

        Vacante objSelected = (Vacante) JOptionPane.showInputDialog(null,
                "Selecciona una vacante",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);

        instanceVacanteModel().delete(objSelected);
    }



    public static VacanteModel instanceVacanteModel(){
        return new VacanteModel();
    }

    public static void update(){

        if(Utils.listToArray(instanceVacanteModel().findAll()).length == 0){
            JOptionPane.showMessageDialog(null,"No hay vacantes para actualizar");
        }else{
            Object[] options = Utils.listToArray(instanceVacanteModel().findAll());

            Vacante objSelected = (Vacante) JOptionPane.showInputDialog(null,
                    "Selecciona una vacante a actualizar",
                    "",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);

            //Se lista la empresa y se selecciona la seleccion que tiene actualmente
            List<Object> listEmpresa =  EmpresaController.instanceEmpresaModel().findAll();
            int index = 0;
            for(Object iterador : listEmpresa){
                Empresa iterEmpresa = (Empresa) iterador;

                if(iterEmpresa.getId() == objSelected.getEmpresa_id()){
                    break;
                }
                index++;
            }

            Object[] options2 = Utils.listToArray(EmpresaController.instanceEmpresaModel().findAll());

            Empresa objSelectedEmpresa = (Empresa) JOptionPane.showInputDialog(null,
                    "Selecciona una empresa",
                    "",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options2,
                    options2[index]);

            objSelected.setEmpresa(objSelectedEmpresa);
            objSelected.setEmpresa_id(objSelectedEmpresa.getId());
            objSelected.setTitulo(JOptionPane.showInputDialog(null, "Ingresa el nuevo titulo", objSelected.getTitulo()));
            objSelected.setDescripcion(JOptionPane.showInputDialog(null, "Ingresa la nueva descripcion", objSelected.getDescripcion()));
            objSelected.setDuracion(JOptionPane.showInputDialog(null, "Ingresa la nueva duracion", objSelected.getDuracion()));

            //Se hace selector para estado
            String[] selectorEstado = {"ACTIVO","INACTIVO"};
            index = 0;
            for(String iterador: selectorEstado){
                if(iterador.equals(objSelected.getEstado())){
                    break;
                }
                index++;
            }

            String estado = (String) JOptionPane.showInputDialog(null,
                    "Selecciona un estado",
                    "estados disponibles",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    selectorEstado,
                    selectorEstado[index]);

            objSelected.setEstado(estado);

            //ingresar tecnologias
            String[] selectorTecnologias = {"Javascript, html, css","Java y bases de datos", "Java, bases de datos, Springboot"};

            //Se hace selector para tecnologias
            index = 0;
            for(String iterador: selectorTecnologias){
                if(iterador.equals(objSelected.getTecnologia())){
                    break;
                }
                index++;
            }
            String tecnologia = (String) JOptionPane.showInputDialog(null,
                    "Selecciona una tecnologia",
                    "tecnologias disponibles",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    selectorTecnologias,
                    selectorTecnologias[index]);

            objSelected.setTecnologia(tecnologia);

            //Ingresar clanes
            String[] selectorClanes = {"Meta","Lovelace","Van Russon", "Linus" ,"Steve Jobs"};
            index = 0;
            for(String iterador: selectorClanes){
                if(iterador.equals(objSelected.getClan())){
                    break;
                }
                index++;
            }
            String clan = (String) JOptionPane.showInputDialog(null,
                    "Selecciona un clan",
                    "clanes disponibles",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    selectorClanes,
                    selectorClanes[index]);

            objSelected.setClan(clan);


            instanceVacanteModel().update(objSelected);
        }

    }

    public static void findByClan(){

        //Ingresar clanes
        String[] selectorClanes = {"Meta","Lovelace","Van Russon", "Linus" ,"Steve Jobs"};

        String clan = (String) JOptionPane.showInputDialog(null,
                "Selecciona un clan",
                "clanes disponibles",
                JOptionPane.QUESTION_MESSAGE,
                null,
                selectorClanes,
                selectorClanes[0]);

        List<Vacante> listaVacantes = new ArrayList<>();
        listaVacantes = instanceVacanteModel().findByClan(clan);

        if(listaVacantes.isEmpty()){
            JOptionPane.showMessageDialog(null, "No hay registros para el clan " + clan);
        }else{
            String imprimir = "Esta es la lista de vacantes por clan " + clan + " \n";
            for(Vacante iterador : listaVacantes){
                imprimir += iterador.toString() + "\n";
            }
            JOptionPane.showMessageDialog(null,imprimir);
        }

    }

    public static void updateVacanteEstado(int id){
        instanceVacanteModel().updateVacanteEstado(id);
    }

    public static void findByTitulo(){
        List<Vacante> listaVacantes = new ArrayList<>();

        String titulo = JOptionPane.showInputDialog("Ingrese el titulo a buscar");
        listaVacantes = instanceVacanteModel().findByTitulo(titulo);

        if(listaVacantes.isEmpty()){
            JOptionPane.showMessageDialog(null, "No hay registros para el titulo " + titulo);
        }else{
            String imprimir = "Esta es la lista de vacantes por titulo " + titulo + " \n";
            for(Vacante iterador : listaVacantes){
                imprimir += iterador.toString() + "\n";
            }
            JOptionPane.showMessageDialog(null,imprimir);
        }
    }

    public static void findByTecnologia(){
        List<Vacante> listaVacantes = new ArrayList<>();

        //ingresar tecnologias
        String[] selectorTecnologias = {"Javascript, html, css","Java y bases de datos", "Java, bases de datos, Springboot"};

        //Se hace selector para tecnologias

        String tecnologia = (String) JOptionPane.showInputDialog(null,
                "Selecciona una tecnologia",
                "tecnologias disponibles",
                JOptionPane.QUESTION_MESSAGE,
                null,
                selectorTecnologias,
                selectorTecnologias[0]);


        listaVacantes = instanceVacanteModel().findByTecnologia(tecnologia);

        if(listaVacantes.isEmpty()){
            JOptionPane.showMessageDialog(null, "No hay registros para el tecnologia " + tecnologia);
        }else{
            String imprimir = "Esta es la lista de vacantes por tecnologia " + tecnologia + " \n";
            for(Vacante iterador : listaVacantes){
                imprimir += iterador.toString() + "\n";
            }
            JOptionPane.showMessageDialog(null,imprimir);
        }
    }
}
