package Interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import BaseDeDatos.conexion;

import com.toedter.calendar.JCalendar;


import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class Cuenta extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nro_cuenta;
	private JTextField ingresar_nombre;
	private JTextField ingresar_telefono;
	private JTextField ingresar_dni;
	private JTextField ingresar_cantidad;
	Connection conn;
	ResultSet rs;
	PreparedStatement pst;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cuenta frame = new Cuenta();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Cuenta() {
		conn=conexion.metodo_conexion();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 713, 579);
		setTitle("Informacion de la cuenta");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel texto_nro_cuenta = new JLabel("Numero de cuenta:");
		texto_nro_cuenta.setFont(new Font("Arial", Font.PLAIN, 16));
		texto_nro_cuenta.setBounds(10, 33, 164, 14);
		contentPane.add(texto_nro_cuenta);

		JLabel texto_tipo_cuenta = new JLabel("Tipo de cuenta:");
		texto_tipo_cuenta.setFont(new Font("Arial", Font.PLAIN, 16));
		texto_tipo_cuenta.setBounds(10, 73, 127, 19);
		contentPane.add(texto_tipo_cuenta);

		JLabel texto_genero = new JLabel("Genero:");
		texto_genero.setFont(new Font("Arial", Font.PLAIN, 16));
		texto_genero.setBounds(10, 113, 84, 14);
		contentPane.add(texto_genero);

		JLabel texto_direccion = new JLabel("Direccion:");
		texto_direccion.setFont(new Font("Arial", Font.PLAIN, 16));
		texto_direccion.setBounds(10, 151, 127, 14);
		contentPane.add(texto_direccion);

		JLabel texto_nombre = new JLabel("Nombre:");
		texto_nombre.setFont(new Font("Arial", Font.PLAIN, 16));
		texto_nombre.setBounds(403, 35, 84, 14);
		contentPane.add(texto_nombre);

		JLabel texto_fecha_n = new JLabel("Fecha de nacimiento:");
		texto_fecha_n.setFont(new Font("Arial", Font.PLAIN, 16));
		texto_fecha_n.setBounds(403, 75, 179, 14);
		contentPane.add(texto_fecha_n);

		JLabel texto_telefono = new JLabel("Telefono: ");
		texto_telefono.setFont(new Font("Arial", Font.PLAIN, 16));
		texto_telefono.setBounds(403, 113, 127, 14);
		contentPane.add(texto_telefono);

		JLabel texto_dni = new JLabel("DNI:");
		texto_dni.setFont(new Font("Arial", Font.PLAIN, 16));
		texto_dni.setBounds(403, 153, 46, 14);
		contentPane.add(texto_dni);

		nro_cuenta = new JTextField();
		nro_cuenta.setBounds(162, 32, 144, 20);
		contentPane.add(nro_cuenta);
		nro_cuenta.setColumns(10);

		JComboBox<String> opciones_cuenta = new JComboBox();
		opciones_cuenta.setModel(new DefaultComboBoxModel(new String[] {"Cuenta sueldo", "Caja de ahorro", "Cuenta corriente"}));
		opciones_cuenta.setBounds(162, 73, 134, 22);
		contentPane.add(opciones_cuenta);

		JRadioButton opcion_femenino = new JRadioButton("Femenino");
		opcion_femenino.setBounds(162, 111, 84, 23);
		contentPane.add(opcion_femenino);

		JRadioButton opcion_masculino = new JRadioButton("Masculino");
		opcion_masculino.setBounds(263, 111, 97, 23);
		contentPane.add(opcion_masculino);

		JTextArea direccion = new JTextArea();
		direccion.setBounds(172, 148, 162, 44);
		contentPane.add(direccion);

		ingresar_nombre = new JTextField();
		ingresar_nombre.setBounds(477, 32, 144, 20);
		contentPane.add(ingresar_nombre);
		ingresar_nombre.setColumns(10);/*se agrega el calendario al panel*/

		ingresar_telefono = new JTextField();
		ingresar_telefono.setBounds(474, 112, 84, 20);
		contentPane.add(ingresar_telefono);
		ingresar_telefono.setColumns(10);

		ingresar_dni = new JTextField();
		ingresar_dni.setBounds(474, 150, 86, 20);
		contentPane.add(ingresar_dni);
		ingresar_dni.setColumns(10);

		JButton boton_crearcuenta = new JButton("Crear cuenta");
		boton_crearcuenta.setForeground(new Color(255, 255, 255));
		boton_crearcuenta.setBackground(new Color(0, 0, 0));
		boton_crearcuenta.setBounds(162, 340, 134, 23);
		contentPane.add(boton_crearcuenta);

		JButton boton_volver = new JButton("Volver");
		boton_volver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Login c = new Login();
				c.setVisible(true);
			}
		});
		boton_volver.setForeground(new Color(255, 255, 255));
		boton_volver.setBackground(new Color(0, 0, 0));
		boton_volver.setBounds(375, 340, 89, 23);
		contentPane.add(boton_volver);

		JButton boton_limpiar = new JButton("Limpiar campos");
		boton_limpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*	private JTextField nro_cuenta;
	private JTextField ingresar_nombre;
	private JTextField ingresar_telefono;
	private JTextField ingresar_dni;
	private JTextField ingresar_cantidad;*/
				nro_cuenta.setText("");
				ingresar_nombre.setText("");
				ingresar_telefono.setText("");
				ingresar_dni.setText("");
				ingresar_cantidad.setText("");
				
				
			}
		});
		boton_limpiar.setForeground(new Color(255, 255, 255));
		boton_limpiar.setBackground(new Color(0, 0, 0));
		boton_limpiar.setBounds(551, 340, 146, 23);
		contentPane.add(boton_limpiar);

		JDateChooser fecha_nacimiento = new JDateChooser();
		fecha_nacimiento.setBounds(567, 73, 70, 20);
		contentPane.add(fecha_nacimiento);
		
		JLabel texto_cantidad = new JLabel("Cantidad de dinero:");
		texto_cantidad.setFont(new Font("Arial", Font.PLAIN, 16));
		texto_cantidad.setBounds(10, 239, 164, 14);
		contentPane.add(texto_cantidad);
		
		ingresar_cantidad = new JTextField();
		ingresar_cantidad.setColumns(10);
		ingresar_cantidad.setBounds(172, 238, 144, 20);
		contentPane.add(ingresar_cantidad);
	}
}