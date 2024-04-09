import controller.CoderController;
import controller.ContratacionController;
import controller.VacanteController;
import database.ConfigDB;
import model.ContratacionModel;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        int option = 0, option2= 0;

        try{
            do{
                option = Integer.parseInt(JOptionPane.showInputDialog("""
                    1. Administrar Coder
                    2. Administrar Vacante
                    3. Administrar Contratación
                    4. Salir
                    
                    Ingrese una opcion:
                    """));

                switch (option){
                    case 1:
                        do{
                            option2 = Integer.parseInt(JOptionPane.showInputDialog("""
                                1. Listar Coder
                                2. Crear Coder
                                3. Eliminar Coder
                                4. Actualizar Coder
                                5. Salir
                                
                                Ingrese una opcion:
                                """));

                            switch (option2){
                                case 1:
                                    //Listar
                                    CoderController.getAll();
                                    break;
                                case 2:
                                    //Crear
                                    CoderController.insert();
                                    break;
                                case 3:
                                    //Borrar
                                    CoderController.delete();
                                    break;
                                case 4:
                                    //Actualizar
                                    CoderController.update();
                                    break;
                                default:
                                    JOptionPane.showMessageDialog(null,"Campo no valido");
                            }

                        }while(option2 !=5);
                        break;

                    case 2:
                        //Vacante
                        do{
                            option2 = Integer.parseInt(JOptionPane.showInputDialog("""
                                1. Listar Vacantes
                                2. Crear Vacantes
                                3. Eliminar Vacantes
                                4. Actualizar Vacante
                                5. Salir
                                
                                Ingrese una opcion:
                                """));

                            switch (option2){
                                case 1:
                                    //Listar
                                    VacanteController.getAll();
                                    break;
                                case 2:
                                    //Crear
                                    VacanteController.insert();
                                    break;
                                case 3:
                                    //Borrar
                                    VacanteController.delete();
                                    break;
                                case 4:
                                    //Actualizar
                                    VacanteController.update();
                                    break;
                                default:
                                    JOptionPane.showMessageDialog(null,"Campo no valido");
                            }

                        }while(option2 !=5);
                        break;

                    case 3:
                        //Contratacion
                        do{
                            option2 = Integer.parseInt(JOptionPane.showInputDialog("""
                                1. Listar Contratacion
                                2. Crear Contratacion
                                3. Eliminar Contratacion
                                4. ActualizarContratacion
                                5. Salir
                                
                                Ingrese una opcion:
                                """));

                            switch (option2){
                                case 1:
                                    //Listar
                                    //ContratacionController.getAll();
                                    break;
                                case 2:
                                    //Crear
                                    ContratacionController.insert();
                                    break;
                                case 3:
                                    //Borrar
                                    //ContratacionController.delete();
                                    break;
                                case 4:
                                    //Actualizar
                                    //ContratacionController.update();
                                    break;
                                default:
                                    JOptionPane.showMessageDialog(null,"Campo no valido");
                            }

                        }while(option2 !=5);
                        break;
                    case 4:
                        JOptionPane.showMessageDialog(null,"Gracias por utilizar el programa");
                        break;
                    default:
                        JOptionPane.showMessageDialog(null,"Dato no valido");
                }


            }while(option != 4);
        }catch(Exception error){
            JOptionPane.showMessageDialog(null,"Fin del programa: " + error.getMessage());
        }

    }
}
