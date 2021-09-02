package co.edu.unbosque.view;

import java.util.Scanner;

public class View {

    Scanner in = new Scanner(System.in);

    public String recibirDatos(){
        String result = in.nextLine();
        return result;
    }


    public void mostrarDatos(String dato){
         System.out.println(dato);
    }


}
