package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Arbol;
import model.Compuesto;
import model.GestionArchivo;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

public class JFrame_main extends JFrame {

	private JPanel contentPane;
	private JPanel panel_central;
	ArrayList<Compuesto> AuxLstClaves;
	GestionArchivo objAuxArchivo;
	private JTextField txtValorBuscar;
	private JTextField txt_tiem_index;
	private JTextField txt_tiem_no_index;
	private JLabel lblNewLabel_2;
	

	
	public JFrame_main(ArrayList<Compuesto> AuxLstClavesIn,GestionArchivo objArchivoIn) {
		setResizable(false);
		this.AuxLstClaves=AuxLstClavesIn;
		this.objAuxArchivo=objArchivoIn;
		lanzar_componentes();
	}
	
	public void lanzar_componentes() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 437, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		panel_central = new JPanel();
		contentPane.add(panel_central, BorderLayout.CENTER);
		panel_central.setLayout(null);
		
		JButton btn_agregar_datos = new JButton("Agregar Datos");
		btn_agregar_datos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregar_datos();
			}
		});
		btn_agregar_datos.setBounds(123, 22, 166, 43);
		panel_central.add(btn_agregar_datos);
		
		txtValorBuscar = new JTextField();
		txtValorBuscar.setBounds(123, 185, 166, 20);
		panel_central.add(txtValorBuscar);
		txtValorBuscar.setColumns(10);
		
		JButton btn_buscar = new JButton("Buscar");
		btn_buscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscar_valor();
			}
		});
		btn_buscar.setBounds(149, 217, 123, 43);
		panel_central.add(btn_buscar);
		
		txt_tiem_index = new JTextField();
		txt_tiem_index.setEditable(false);
		txt_tiem_index.setBounds(227, 371, 136, 20);
		panel_central.add(txt_tiem_index);
		txt_tiem_index.setColumns(10);
		
		txt_tiem_no_index = new JTextField();
		txt_tiem_no_index.setEditable(false);
		txt_tiem_no_index.setColumns(10);
		txt_tiem_no_index.setBounds(227, 308, 136, 20);
		panel_central.add(txt_tiem_no_index);
		
		JLabel lblNewLabel = new JLabel("BUSQUEDA");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(163, 118, 92, 20);
		panel_central.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Tiempo de busqueda NO indexada:");
		lblNewLabel_1.setBounds(21, 311, 201, 14);
		panel_central.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Tiempo de busqueda  INDEXADA:");
		lblNewLabel_1_1.setBounds(15, 374, 207, 14);
		panel_central.add(lblNewLabel_1_1);
		
		lblNewLabel_2 = new JLabel("NANOSEGUNDOS");
		lblNewLabel_2.setBounds(247, 282, 101, 14);
		panel_central.add(lblNewLabel_2);
	}
	//Invocar a un nuevo JFrame para agregar datos
	public void agregar_datos() {
		JFrame_agregar_datos obj_frame_datos = new JFrame_agregar_datos(this.AuxLstClaves,this.objAuxArchivo);
		obj_frame_datos.setVisible(true);
	
	
	}
	
	//Las busquedas de los valores por los dos metodos, INDEXADA y NO indexada
	public void buscar_valor() {
		try {
			
			//Verificaciones generales
			if(this.txtValorBuscar.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Campos vacíos","WARNING",
						JOptionPane.WARNING_MESSAGE);
			}else {
				if(Integer.parseInt(this.txtValorBuscar.getText()) <=0 ||Integer.parseInt(this.txtValorBuscar.getText()) > 1000000) {
					JOptionPane.showMessageDialog(this, "Verifique que el dato ingresado sea mayor a cero y menor a un millon","ERROR",
							JOptionPane.ERROR_MESSAGE);
					this.txtValorBuscar.setText("");
				}else {
					
					//BUSQUEDA NO INDEXADA
					//Se inicializa una variable start en nano segundos antes de iniciar la busqueda
					long startTime = System.nanoTime();
					long endTime;

					//Se realiza la busqueda y la comprobacion en caso de que no encuentre el valor
					if(this.objAuxArchivo.busqueda_no_indexada(Integer.parseInt(this.txtValorBuscar.getText()))) {
						//Se detiene el contador de tiempo
						endTime = System.nanoTime();
						JOptionPane.showMessageDialog(this, "Dato encontrado","INFORMATION",
								JOptionPane.INFORMATION_MESSAGE);
						
					}else {
						//Se detiene el contador de tiempo
						endTime = System.nanoTime();
						JOptionPane.showMessageDialog(this, "No se encontró el dato","ERROR",
								JOptionPane.ERROR_MESSAGE);
						
					}
 
				    long time = endTime-startTime;
				    //Se resta el tiempo final con el inicial para determinar el tiempo que tomó la busqueda y se agrega este al textbox para la visualizacion
				    
				    this.txt_tiem_no_index.setText(String.valueOf(time));
					
					
					
					
					
					//BUSQUEDA INDEXADA
					
					
					
					
					
					
					}
					
				}
			}catch(Exception e) {
			JOptionPane.showMessageDialog(this, "Solo se aceptan valores numericos","WARNING",
					JOptionPane.WARNING_MESSAGE);
			this.txtValorBuscar.setText("");
		}
			
		
	}
}
