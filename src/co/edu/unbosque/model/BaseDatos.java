package co.edu.unbosque.model;

import com.healthmarketscience.jackcess.Cursor;
import com.healthmarketscience.jackcess.CursorBuilder;
import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.DatabaseBuilder;
import com.healthmarketscience.jackcess.Row;
import com.healthmarketscience.jackcess.Table;

import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class BaseDatos {
    private String rutaBD;
    private Database BDAcces;

    public BaseDatos(String rutaBD) {
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

    public void registrarEstudiante(String nombreTabla, String apellido, String nombre, String sxo, int edad, String curso) {
        try {
            Table table = BDAcces.getTable(nombreTabla);
            table.addRow(apellido, nombre, sxo, edad, curso);
        } catch (Exception e) {
            System.out.println("Error al insertar registro en tabla [" + nombreTabla + "]: " + e.getMessage());
        }
    }

    public String buscarRegistroValor(String nombreTabla, String campo,
                                      String filtro, String campo1, String campo2, String campo3, String campo4,
                                      String campo5) {
        List nombres = new LinkedList<>();
        String valor = "";
        String result = "";
        try {
            Table tabla = BDAcces.getTable(nombreTabla);
            Cursor cursor = CursorBuilder.createCursor(tabla);
            for (Row registro : cursor.newIterable().addMatchPattern(campo, filtro)) {
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
            return valor;
        } catch (Exception e) {
            System.out.println("Error obtener valor de tabla [" + nombreTabla + "]: " +
                    e.getMessage());
            return "";
        }
    }

    public int numRegistros(String nombreTabla) {
        try {
            Table tabla = BDAcces.getTable(nombreTabla);
            return tabla.getRowCount();
        } catch (Exception e) {
            System.out.println("Error al contar registros de [" + nombreTabla + "]: " + e.getMessage());
            return 0;

        }
    }

    public void desconectarBD() {
        try {
            BDAcces.close();
        } catch (Exception e) {
            System.out.println("Error al cerrar BD [" + rutaBD + "]: " + e.getMessage());
        }
    }
}

