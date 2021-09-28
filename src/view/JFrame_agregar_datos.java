package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Compuesto;
import model.GestionArchivo;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class JFrame_agregar_datos extends JFrame {

	private JPanel contentPane;
	private JTextField txt_valor;
	private JTextField txt_clave;
	private JButton btn_agregar;
	private JTextField txt_cant;
	private int numero_random;
	ArrayList<Compuesto> AuxLstCompuesta;
	GestionArchivo objAuxArchivo;

	public JFrame_agregar_datos(ArrayList<Compuesto> lstAuxIn,GestionArchivo objArchivoIn) {
		setResizable(false);
		this.AuxLstCompuesta=lstAuxIn;
		this.objAuxArchivo=objArchivoIn;
		lanzar_componentes();
	}
	
	public void lanzar_componentes() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 502, 320);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Digite un valor a guardar:");
		lblNewLabel.setBounds(124, 97, 154, 14);
		contentPane.add(lblNewLabel);
		
		txt_valor = new JTextField();
		txt_valor.setBounds(270, 94, 108, 20);
		contentPane.add(txt_valor);
		txt_valor.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Clave aleatoria generada:");
		lblNewLabel_1.setBounds(124, 128, 154, 14);
		contentPane.add(lblNewLabel_1);
		
		txt_clave = new JTextField();
		txt_clave.setEditable(false);
		txt_clave.setColumns(10);
		txt_clave.setBounds(270, 125, 108, 20);
		contentPane.add(txt_clave);
		
		btn_agregar = new JButton("Agregar");
		btn_agregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregar_datos();
			}
		});
		btn_agregar.setBounds(270, 156, 108, 34);
		contentPane.add(btn_agregar);
		
		JButton btn_limpiar = new JButton("Limpiar");
		btn_limpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiar();
			}
		});
		btn_limpiar.setBounds(270, 201, 108, 34);
		contentPane.add(btn_limpiar);
		
		txt_cant = new JTextField();
		txt_cant.setColumns(10);
		txt_cant.setBounds(270, 63, 108, 20);
		contentPane.add(txt_cant);
		
		JLabel lblDigiteLaCantidad = new JLabel("Digite la cantidad de valores a generar:");
		lblDigiteLaCantidad.setBounds(33, 66, 227, 14);
		contentPane.add(lblDigiteLaCantidad);
		
		JButton btn_generar = new JButton("Generar");
		btn_generar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generar_x_valores();
			}
		});
		btn_generar.setBounds(392, 60, 84, 23);
		contentPane.add(btn_generar);
	}
	
	//Metodo para agregar un dato a la vez con sus comprobaciones
	public void agregar_datos() {
		//Verificacion tipica de campos vacios
		if(this.txt_valor.getText().equals("")) {
				
			JOptionPane.showMessageDialog(this, "Campos vacíos","WARNING",
					JOptionPane.WARNING_MESSAGE);
			
		}else {
				
			try {
				//Verificacion de numero en rango establecido
				if(Integer.parseInt(this.txt_valor.getText()) <=0 || Integer.parseInt(this.txt_valor.getText()) > 1000000 ) {
					JOptionPane.showMessageDialog(this, "Verifique que el dato ingresado sea mayor a cero y menor a un millon","ERROR",
							JOptionPane.ERROR_MESSAGE);
					this.txt_valor.setText("");
				}else {
					/*
					 * Se procede a crar una clave aleatoria para el valor digitado,
					 *  y verificar mediante un procedimiento si esta clave ya existe, para en tal caso generar otra
					 * */
					numero_random = (int)Math.floor(Math.random()*(1000000-1+1)+1);
					while(verificar_clave_existente(numero_random)) {
						numero_random = (int)Math.floor(Math.random()*(1000000-1+1)+1);
					}
					//En caso de generar una clave unica y el valor ser correcto se procede a crear un objeto compuesto y agregarlo a la lista
					Compuesto objAux = new Compuesto();
					objAux.setClave(numero_random);
					objAux.setValor(Integer.parseInt(this.txt_valor.getText()));
					this.AuxLstCompuesta.add(objAux);
					this.txt_clave.setText(String.valueOf(numero_random));
					
					//Aqui se llama al archivo para agregar datos y escribirlos instantaneamente en el archivo
					this.objAuxArchivo.escribir_dato_unico(objAux);
					this.btn_agregar.setEnabled(false);
					this.txt_valor.setEnabled(false);
					
				}
				
			}catch(NumberFormatException e) {
				JOptionPane.showMessageDialog(this, "Solo se aceptan valores numericos","WARNING",
						JOptionPane.WARNING_MESSAGE);
				this.txt_valor.setText("");
			}
			
			
		}
	}
	//Metodo simple para vaciar textbox
	public void limpiar() {
		this.txt_clave.setText("");
		this.txt_valor.setText("");
		this.txt_valor.setEnabled(true);
		this.btn_agregar.setEnabled(true);
	}
	
	//Metodo para genera n valores digitados por el usuario, es en esencia, muy parecido al de agregar unitariamente
	
	public void generar_x_valores() {
		try {
			if(this.txt_cant.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Campos vacíos","WARNING",
						JOptionPane.WARNING_MESSAGE);
			}else {
				if(Integer.parseInt(this.txt_cant.getText()) <=0 ||Integer.parseInt(this.txt_cant.getText()) > 1000000) {
					JOptionPane.showMessageDialog(this, "Verifique que el dato ingresado sea mayor a cero y menor a un millon","ERROR",
							JOptionPane.ERROR_MESSAGE);
					this.txt_cant.setText("");
				}else {
					
					/*
					 * Se recupera la cantidad de valores que se desean generar
					 *  y se hace un ciclo para generar estos n veces, controlando que las claves(tambien autogeneradas) de estos no esten repetidas
					 * */
					int cant = Integer.parseInt(this.txt_cant.getText());
					for(int i=0;i<cant;i++) {
						int valor = (int)Math.floor(Math.random()*(1000000-1+1)+1);
						int clave = (int)Math.floor(Math.random()*(1000000-1+1)+1);
						while(verificar_clave_existente(clave)) {
							clave = (int)Math.floor(Math.random()*(1000000-1+1)+1);
						}
						//Se crea el objeto para ser agregado
						Compuesto objAux2 = new Compuesto();
						objAux2.setValor(valor);
						objAux2.setClave(clave);
						/*
						 *Se comprueba que no se tengan mas de un millon de datos, para en tal caso cerrar la aplicacion
						 *(referenciarse a la clase "GestionArchivo" para una mejor explicacion del porque de este metodo)
						 * */
						if(this.objAuxArchivo.escribir_dato_unico(objAux2)) {
							JOptionPane.showMessageDialog(this, "El programa se cerrará para evitar errores","ERROR",
									JOptionPane.ERROR_MESSAGE);
							System.exit(0);
						}
						//Se agrega el objeto a la lista
						this.AuxLstCompuesta.add(objAux2);
					}
					JOptionPane.showMessageDialog(this, "Valores generados con éxito","INFORMATION",
							JOptionPane.INFORMATION_MESSAGE);
					this.txt_cant.setText("");
					
				}
			}
			
		}catch(Exception e) {
			JOptionPane.showMessageDialog(this, "Solo se aceptan valores numericos","WARNING",
					JOptionPane.WARNING_MESSAGE);
			this.txt_cant.setText("");
		}
	}
	/*
	 * Se verifica en la lista de todas las claves previamente cargadas y las que sean posteriormente añadadidas con los dos metodos
	 * si la clave autogenerada ya se encuentra siendo usada por otro valor, en tal caso, para a generar una nueva para asignarsela
	 * al valor, esto tantas veces se repita la clave
	 * */
	
	public boolean verificar_clave_existente(int claveIn) { 
		for(int i=0;i<this.AuxLstCompuesta.size();i++) {
			if(this.AuxLstCompuesta.get(i).getClave() == claveIn) {
				return true;
			}
		}
		return false;		
	}
}
