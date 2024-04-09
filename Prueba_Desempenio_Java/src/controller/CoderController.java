package controller;

import entity.Coder;
import entity.Empresa;
import model.CoderModel;
import utils.Utils;


import javax.swing.*;
import java.util.List;

public class CoderController {

    public static void insert(){
        //Se piden datos
        try{
            String nombre = JOptionPane.showInputDialog("Ingrese el nombre del Coder\n");
            String apellidos = JOptionPane.showInputDialog("Ingrese el Apellido de\n" + nombre);
            String documento = JOptionPane.showInputDialog("Igrese el documento de identidad de\n" + nombre + " " + apellidos);
            int cohorte = Integer.parseInt(JOptionPane.showInputDialog("Ingrese cohorte del Coder Recuerde 1 o 2\n"));

            //ingresar cv que es la tecnologia

            //ingresar tecnologias
            String[] selectorTecnologias = {"Javascript, html, css","Java y bases de datos", "Java, bases de datos, Springboot"};

            //Se hace selector para tecnologias

            String cv = (String) JOptionPane.showInputDialog(null,
                    "Selecciona una tecnologia",
                    "tecnologias disponibles",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    selectorTecnologias,
                    selectorTecnologias[0]);


            //Se ingresa el coder
            instanceCoderModel().insert(new Coder(nombre,apellidos,documento,cohorte,cv));

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Dato no valido " + e.getMessage());
        }

    }

    public static CoderModel instanceCoderModel(){
        return new CoderModel();
    }

    public static void getAll(){
        String list = getAll(instanceCoderModel().findAll());

        JOptionPane.showMessageDialog(null, list);
    }

    public static String getAll(List<Object> list){
        String listString = "LISTA DE Coders: \n";

        for(Object temp: list){
            Coder objCoder = (Coder) temp;
            listString += objCoder.imprimirTodo() + "\n";
        }

        return listString;
    }

    public static void delete(){
        Object[] options = Utils.listToArray(instanceCoderModel().findAll());

        Coder objSelected = (Coder) JOptionPane.showInputDialog(null,
                "Selecciona un Coder",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);

        instanceCoderModel().delete(objSelected);
    }

    public static void update(){
        Object[] options = Utils.listToArray(instanceCoderModel().findAll());

        Coder objSelected = (Coder) JOptionPane.showInputDialog(null,
                "Selecciona un coder a actualizar CV",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);

        //Se lista las tecnologias y se selecciona la seleccion que tiene actualmente
        //ingresar tecnologias
        String[] selectorTecnologias = {"Javascript, html, css","Java y bases de datos", "Java, bases de datos, Springboot"};

        int index = 0;
        for(String iterador : selectorTecnologias){

            if(iterador.equals(objSelected.getCv())){
                break;
            }
            index++;
        }

        Object[] options2 = Utils.listToArray(EmpresaController.instanceEmpresaModel().findAll());

        String cv = (String) JOptionPane.showInputDialog(null,
                "Selecciona una tecnologia para el cv",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                selectorTecnologias,
                selectorTecnologias[index]);

        objSelected.setCv(cv);

        instanceCoderModel().update(objSelected);
    }

    public static List<Coder> findByTecnologia(String tecnologia){
        return instanceCoderModel().findByTecnologia(tecnologia);
    }


}
