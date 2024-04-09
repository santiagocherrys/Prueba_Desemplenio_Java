package controller;

import entity.Coder;
import entity.Contratacion;
import entity.Empresa;
import entity.Vacante;
import model.ContratacionModel;
import model.VacanteModel;
import utils.Utils;

import javax.swing.*;

public class ContratacionController {

    public static void insert() {
        //Se piden datos

        try{
            //Se lista las vacantes para que escoja cual quiere hacer
            //Se pregunta primero si hay vacante para crear contratacion
            if (VacanteController.instanceVacanteModel().findAll().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Cree vacante para poder crear contratacion");
            } else {
                //Se lista las vacantes para crear contratacion
                ;
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



                JOptionPane.showMessageDialog(null,instanceContratacionModel().insert(new Contratacion(objSelected.getId(), objSelectedCoder.getId(), estado,salario,objSelected,objSelectedCoder,objSelected.getEmpresa())));
            }


        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Dato no valido " + e.getMessage());
        }

    }

    public static ContratacionModel instanceContratacionModel(){
        return new ContratacionModel();
    }
}
