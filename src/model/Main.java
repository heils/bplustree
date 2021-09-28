package model;

import java.util.ArrayList;

import view.JFrame_main;

public class Main {

	public static void main(String[] args) {
		/*
		 * Se crea una lista global que es enviada a todas las clases que la necesiten,
		 *  de este modo se recibe como auxiliar, no se inicializa una lista nueva
		 *   y se pueden manejar los mismos datos entre clases de igual manera
		 *   con el archivo y muy seguramente el arbol
		 *   
		 * */
	    ArrayList<Compuesto> lstClaves;
	    lstClaves = new ArrayList<Compuesto>();
	    GestionArchivo objArchivo = new GestionArchivo(lstClaves);
	    objArchivo.leer_archivo();
		JFrame_main obj_vista_main = new JFrame_main(lstClaves,objArchivo);
		obj_vista_main.setVisible(true);
		
		
		
		
		//COMO SE USA EL ARBOL
		/*
		Arbol arbol = new Arbol(4);
        arbol.insertar(2, null);
        arbol.insertar(3, null);
        arbol.insertar(5, null);
        arbol.insertar(7, null);
        System.out.println(arbol.raiz);
        */

	}

}
