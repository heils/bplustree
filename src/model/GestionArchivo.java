package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class GestionArchivo {
	/*
	 * Clase para la gestion del archivo y los metodos para la lectura y escritura
	 * 
	 * */
	
	DataInputStream origenDatos= null;
	FileInputStream archivo= null;
	DataOutputStream destinoDatos = null;
	FileOutputStream archivoSalida = null;
	String linea;
	String [] elementos;
	ArrayList<Compuesto> AuxLstClaves;
	
	
	
	public GestionArchivo(ArrayList<Compuesto> lstClavesIn) {
		this.AuxLstClaves=lstClavesIn;
	}
	
	
	public void leer_archivo(){
		/*
		 * Se hace uso del try catch, puesto que el ciclo while lanza una excepcion cuando ya ha acabado de leer todo el archivo
		 *  ademas de que es necesario siempre que se trate con archivos
		 * */
		try {
			BufferedReader bf = new BufferedReader(new FileReader("src\\data\\datos.txt"));
			String bfRead;
			while((bfRead  = bf.readLine()) !=null) {
				//Se captura cada linea
				linea =bfRead;
				//Se divide la linea por un espacio, para separa en un vector de String clave  y valor
				elementos = linea.split(" ");
				//Se crea el objeto de tipo compuesto para almacenar datos
				Compuesto objAux = new Compuesto();
				objAux.setClave(Integer.parseInt(elementos[0]));
				objAux.setValor(Integer.parseInt(elementos[1]));
				//Se agrega a la lista de claves
				this.AuxLstClaves.add(objAux);
				
			}
			JOptionPane.showMessageDialog(null, "Archivo cargado con éxito", "Gestion de Archivo", JOptionPane.INFORMATION_MESSAGE);
			
			
			
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Archivo no encontrado", "Gestion de Archivo", JOptionPane.ERROR_MESSAGE);
		}
			
	}
	
	public boolean escribir_dato_unico(Compuesto objAuxIn) {
		
		try {
			
			FileReader fr = new FileReader("src\\data\\datos.txt");
			BufferedReader bf = new BufferedReader(fr);
			/*
			 * Se guarda en una variable la cantidad de lineas del archivo
			 *  para asi verificar que no se puedan agregar mas de un millon de datos
			 * */
			long cant_lineas = bf.lines().count();
			System.out.println("El fichero tiene " + bf.lines().count() + " lineas");
			
			System.out.println("long "+ cant_lineas);
			/*
			 * Se verifica que no sean mas de un millon y en tal caso, envia una bandera al metodo que la invoca para que cierre el programa
			 *(Este fue el unico metodo que se me ocurrió para controlar que no saliese un mensaje de error n veces, machetazo pero funciona
			 *porque al abrir la aplicacion de nuevo se cargan todos los datos de nuevo)
			 * */
			if(cant_lineas>=1000000) {
				JOptionPane.showMessageDialog(null, "Se ha llegado a la maxima capacidad de datos(1000000) porfavor borre datos del archivo", "Gestion de Archivo", JOptionPane.ERROR_MESSAGE);
				return true;
				 
			}else {
				//Se recie un objeto y se agrega haciendo uso de la notacion al archivo
				PrintWriter out = new PrintWriter(new FileWriter("src\\data\\datos.txt", true));
				out.write(objAuxIn.getClave()+" "+objAuxIn.getValor()+"\n");
				out.close();
			}
			return false;
	
			
			
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Problema al escribir en el archivo", "Gestion de Archivo", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}
	
	//Busqueda normal de un dato en un txt que si no lo encuentra envia false para control de mensajes
	public boolean busqueda_no_indexada(int valorIn) {
		try {
			BufferedReader bf = new BufferedReader(new FileReader("src\\data\\datos.txt"));
			String bfRead;
			while((bfRead  = bf.readLine()) !=null) {
				linea =bfRead;
				elementos = linea.split(" ");
				if(Integer.parseInt(elementos[1]) == valorIn) {
					return true;
				}		
				
			}
			return false;
			
		}catch(Exception e) {
			return false;
		}
	}


}
	



