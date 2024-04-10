package controller;

import entity.Coder;
import entity.Contratacion;
import entity.Empresa;
import entity.Vacante;
import model.ContratacionModel;
import model.VacanteModel;
import utils.Utils;

import javax.swing.*;
import java.util.List;

public class ContratacionController {

    public static void insert() {
        //Se piden datos

        try{
            //Se lista las vacantes para que escoja cual quiere hacer
            //Se pregunta primero si hay vacante para crear contratacion
            if (VacanteController.instanceVacanteModel().findAllActivo().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Cree vacante para poder crear contratacion o revise si las vacante que le interesa estan activas");
            } else {
                //Se lista las vacantes para crear contratacion

                Object[] options = Utils.listToArray(VacanteController.instanceVacanteModel().findAllActivo());

                Vacante objSelected = (Vacante) JOptionPane.showInputDialog(null,
                        "Selecciona la vacante que necesita",
                        "Vacantes disponibles",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]);

                //Se obtiene la tecnologia para filtrar por tecnologia los coders
                String tecnologia = objSelected.getTecnologia();

                //Se filtra por tecnologia los coders
                //Validacion vacantes ofertadas solo salen perfil tecnologico requerido

                Object[] options2 = Utils.listToArray(CoderController.instanceCoderModel().findByTecnologia(tecnologia));
                Coder objSelectedCoder = (Coder) JOptionPane.showInputDialog(null,
                        "Selecciona el coder",
                        "Coder disponibles",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options2,
                        options2[0]);

                //Se hace selector para estado
                String[] selectorEstado = {"ACTIVO","INACTIVO"};
                String estado = (String) JOptionPane.showInputDialog(null,
                        "Selecciona el estado de la contratacion",
                        "estados disponibles",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        selectorEstado,
                        selectorEstado[0]);

                double salario = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el salario de la contratacion"));


                //Se cambia el estado de la vacante
                VacanteController.updateVacanteEstado(objSelected.getId());


                Contratacion objContratacion = (Contratacion) instanceContratacionModel().insert(new Contratacion(objSelected.getId(), objSelectedCoder.getId(), estado,salario,objSelected,objSelectedCoder,objSelected.getEmpresa()));
                JOptionPane.showMessageDialog(null, objContratacion.imprimirAlCrear());
            }


        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Dato no valido " + e.getMessage());
        }

    }

    public static ContratacionModel instanceContratacionModel(){
        return new ContratacionModel();
    }

    public static void getAll(){
        String list = getAll(instanceContratacionModel().findAll());

        JOptionPane.showMessageDialog(null, list);
    }

    public static String getAll(List<Object> list){
        String listString = "LISTA DE Contrataciones: \n";

        for(Object temp: list){
            Contratacion objContratacion = (Contratacion) temp;
            listString += objContratacion.imprimirTodo() + "\n";
        }

        return listString;
    }

    public static void delete(){
        Object[] options = Utils.listToArray(instanceContratacionModel().findAll());

        if(options.length == 0 ){
            JOptionPane.showMessageDialog(null,"No hay ninguna contratacion a eliminar");
        }else{
            Contratacion objSelected = (Contratacion) JOptionPane.showInputDialog(null,
                    "Selecciona una contratacion",
                    "",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);

            instanceContratacionModel().delete(objSelected);
        }

    }

    public static void update(){

        if(Utils.listToArray(instanceContratacionModel().findAll()).length == 0){
            JOptionPane.showMessageDialog(null,"No hay contrataciones para actualizar");
        }else{
            Object[] options = Utils.listToArray(instanceContratacionModel().findAll());

            Contratacion objSelected = (Contratacion) JOptionPane.showInputDialog(null,
                    "Selecciona una contratacion a actualizar",
                    "",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);


            //Se hace selector para estado
            String[] selectorEstado = {"ACTIVO","INACTIVO"};
            int index = 0;
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

            objSelected.setSalario(Double.parseDouble(JOptionPane.showInputDialog(null, "Ingresa el nuevo salario", Double.toString(objSelected.getSalario()))));

            instanceContratacionModel().update(objSelected);
        }

    }
}
