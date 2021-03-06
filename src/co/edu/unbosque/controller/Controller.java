package co.edu.unbosque.controller;

import co.edu.unbosque.model.BaseDatos;
import co.edu.unbosque.view.View;

public class Controller {

    private View vista;
    private BaseDatos accesoMDB;
    final String ficheroAccess = "Academia.accdb";

    public Controller() {
        vista = new View();
        accesoMDB = new BaseDatos(ficheroAccess);
        inicializar();
    }

    private void inicializar() {

        int CONST = 1;
        vista.mostrarDatos("Bienvenido a la base de datos de la Academia" + "\n");
        while (CONST == 1) {
            String nombreTabla = "Estudiante";
            if (accesoMDB.conectarBD()) {
                vista.mostrarDatos("Número registros tabla Contactos: " + accesoMDB.numRegistros("Estudiante"));
                vista.mostrarDatos("Registrar -> [1]" + "\n" + "Consultar por curso -> [2]" + "\n" + "Terminar -> [3]");
                int menu = Integer.parseInt(vista.recibirDatos());
                if (menu == 1) {
                    vista.mostrarDatos("Ingrese Apellido");
                    String apellido = vista.recibirDatos();
                    vista.mostrarDatos("Ingrese Nombre");
                    String nombre = vista.recibirDatos();
                    vista.mostrarDatos("Ingrese genero");
                    String sexo = vista.recibirDatos();
                    String sexoM = sexo.toUpperCase();
                    vista.mostrarDatos("Ingrese edad");
                    int edad = Integer.parseInt(vista.recibirDatos());
                    vista.mostrarDatos("Ingrese curso");
                    String curso = vista.recibirDatos();

                    try {

                        if (sexo.length() == 1) {
                            accesoMDB.registrarEstudiante(nombreTabla, apellido, nombre, sexoM, edad, curso);
                        } else {
                            vista.mostrarDatos("Escribir en genero solo" + "\n" + "Masuclino = M" + "\n" + "Femenino = F" + "\n");
                        }
                    }catch (NumberFormatException e){
                        vista.mostrarDatos("Inrgrese numeros en lso campos debidos");
                    }
                }
                if (menu == 2) {
                    vista.mostrarDatos("Escribe el curso que deseas buscar");
                    String curso = vista.recibirDatos();
                    if (!curso.equals("")) {
                        vista.mostrarDatos(accesoMDB.buscarRegistroValor(nombreTabla, "Curso", curso, "Apellidos", "Nombres", "Sexo", "Edad", "Curso"));
                    } else {
                        accesoMDB.mostrarRegistrosTabla1("Estudiante", "Apellidos", "Nombres", "Sexo", "Edad", "Curso");
                    }
                }
                if (menu == 3) {
                    accesoMDB.desconectarBD();
                    CONST = 0;
                }
            }
        }
    }
}
