package co.edu.unbosque.model;

import com.healthmarketscience.jackcess.Cursor;
import com.healthmarketscience.jackcess.CursorBuilder;
import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.DatabaseBuilder;
import com.healthmarketscience.jackcess.Row;
import com.healthmarketscience.jackcess.Table;

import java.io.File;
import java.io.IOException;

public class Acces {
    private String rutaBD;
    private Database BDAcces;

    public Acces(String rutaBD) {
        this.rutaBD = rutaBD;
    }

    public boolean conectarBD() {
        try {
            BDAcces = DatabaseBuilder.open(new File(rutaBD));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public String buscarRegistroValor(String nombreTabla, String campo, Object filtro, String campoDevuelto) {
        String valor = "";
        try {
            Table tabla = BDAcces.getTable(nombreTabla);
            Cursor cursor = CursorBuilder.createCursor(tabla);
            for (Row registro : cursor.newIterable().addMatchPattern(campo, filtro)) {
                valor = registro.get(campoDevuelto).toString();
            }
            return valor;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public void mostrarRegistrosTabla1(String nombreTabla, String campo1, String campo2, String campo3, String campo4,
                                       String campo5) {
        try {
            Table tabla = BDAcces.getTable(nombreTabla);
            for (Row registro : tabla) {
                System.out.println("-------------------------------------");
                if (!campo1.equalsIgnoreCase(""))
                    System.out.println(campo1 + ": " + registro.get(campo1));
                if (!campo2.equalsIgnoreCase(""))
                    System.out.println(campo2 + ": " + registro.get(campo2));
                if (!campo3.equalsIgnoreCase(""))
                    System.out.println(campo3 + ": " + registro.get(campo3));
                if (!campo4.equalsIgnoreCase(""))
                    System.out.println(campo4 + ": " + registro.get(campo4));
                if (!campo5.equalsIgnoreCase(""))
                    System.out.println(campo5 + ": " + registro.get(campo5));

            }
        } catch (Exception e) {
            System.out.println("Error al mostrar tabla [" + nombreTabla + "]: " + e.getMessage());
        }
    }

    public void mostrarRegistrosTabla2(String nombreTabla, String nombreTablaSecundaria, String campoFiltrarTabla1,
                                       String campoFiltrarTabla2, String campoMostrarTabla2, String campo1, String campo2, String campo3,
                                       String campo4, String campo5, String campo6) {
        try {
            Table tabla = BDAcces.getTable(nombreTabla);
            for (Row registro : tabla) {
                System.out.println("-------------------------------------");
                if (!campo1.equalsIgnoreCase(""))
                    System.out.println(campo1 + ": " + registro.get(campo1));
                if (!campo2.equalsIgnoreCase(""))
                    System.out.println(campo2 + ": " + registro.get(campo2));
                if (!campo3.equalsIgnoreCase(""))
                    System.out.println(campo3 + ": " + registro.get(campo3));
                if (!campo4.equalsIgnoreCase(""))
                    System.out.println(campo4 + ": " + registro.get(campo4));
                if (!campo5.equalsIgnoreCase(""))
                    System.out.println(campo5 + ": " + registro.get(campo5));
                if (!campo6.equalsIgnoreCase(""))
                    System.out.println(campo6 + ": " + registro.get(campo6));
                if (!campoFiltrarTabla1.equalsIgnoreCase("") & (!campoFiltrarTabla2.equalsIgnoreCase("")))
                    System.out.println(campoMostrarTabla2 + ": " + buscarRegistroValor(nombreTablaSecundaria,
                            campoFiltrarTabla2, registro.get(campoFiltrarTabla2), campoMostrarTabla2));
            }
        } catch (Exception e) {
            System.out.println("Error al mostrar tabla [" + nombreTabla + "]: " + e.getMessage());
        }
    }

    // Insertar registro en tabla Cliente
    public void registrarEstudiante(String nombreTabla, String apellido, String nombre, String sxo, int edad, String curso) {
        try {
            Table table = BDAcces.getTable(nombreTabla);
            table.addRow(apellido, nombre, sxo, edad, curso);
        } catch (Exception e) {
            System.out.println("Error al insertar registro en tabla [" + nombreTabla + "]: " + e.getMessage());
        }
    }

    // Eliminar registro de tabla con condición de filtro
    // es importante establecer una condición única para eliminar
    // un único registro dado que si no eliminará todos los que cumplan la condición
    public void eliminarRegistroTabla(String nombreTabla, String campo, int valorFiltro) {
        try {
            Table tabla = BDAcces.getTable(nombreTabla);
            Cursor cursor = CursorBuilder.createCursor(tabla);
            for (Row registro : cursor.newIterable().addMatchPattern(campo, valorFiltro)) {
                tabla.deleteRow(registro);
            }
        } catch (Exception e) {
            System.out.println("Error al eliminar registro en tabla [" + nombreTabla + "]: " + e.getMessage());
        }
    }

    public void buscarEstudiante(String nombreTabla, String campo, String valorFiltro,String campo1, String campo2, String campo3, String campo4,
                                 String campo5) {
        try {
            Table tabla = BDAcces.getTable(nombreTabla);
            Cursor cursor = CursorBuilder.createCursor(tabla);
            for (Row registro : cursor.newIterable().addMatchPattern(campo, valorFiltro)) {
                System.out.println("-------------------------------------");
                if (!campo1.equalsIgnoreCase(""))
                    System.out.println(campo1 + ": " + registro.get(campo1));
                if (!campo2.equalsIgnoreCase(""))
                    System.out.println(campo2 + ": " + registro.get(campo2));
                if (!campo3.equalsIgnoreCase(""))
                    System.out.println(campo3 + ": " + registro.get(campo3));
                if (!campo4.equalsIgnoreCase(""))
                    System.out.println(campo4 + ": " + registro.get(campo4));
                if (!campo5.equalsIgnoreCase(""))
                    System.out.println(campo5 + ": " + registro.get(campo5));
            }
        } catch (Exception e) {
            System.out.println("Error al buscar en tabla [" + nombreTabla + "]: " + e.getMessage());
        }
    }


    // Método para actualizar el valor de los campos
    // de una tabla que cumplan un determinado filtro
    public void actualizarCampoTabla(String nombreTabla, String campo, Object filtro, String nuevoValor) {
        try {
            Table tabla = BDAcces.getTable(nombreTabla);
            Cursor cursor = CursorBuilder.createCursor(tabla);
            for (Row registro : cursor.newIterable().addMatchPattern(campo, filtro)) {
                registro.put(campo, nuevoValor);
                tabla.updateRow(registro);
            }
        } catch (Exception e) {
            System.out.println("Error al actualizar tabla [" + nombreTabla + "]: " + e.getMessage());
        }
    }

    // Devuelve el número de registros de una tabla
    public int numRegistros(String nombreTabla) {
        try {
            Table tabla = BDAcces.getTable(nombreTabla);
            return tabla.getRowCount();
        } catch (Exception e) {
            System.out.println("Error al contar registros de [" + nombreTabla + "]: " + e.getMessage());
            return 0;

        }
    }

    // Cerrar la conexión a la base de datos
    public void desconectarBD() {
        try {
            BDAcces.close();
        } catch (Exception e) {
            System.out.println("Error al cerrar BD [" + rutaBD + "]: " + e.getMessage());
        }
    }
}

