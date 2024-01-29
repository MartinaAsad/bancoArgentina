package Interfaz;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BaseDeDatos.conexion;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTabbedPane;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PaginaInicio extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField usuario_logueado;
	private JTextField nombre;
	private JTextField genero;
	private JTextField dni;
	private JTextField direccion;
	private JTextField telefono;
	private JTextField fechaNacimiento;
	private JTextField fecha_actual;
	/*Connection conn;
	ResultSet rs;
	PreparedStatement pst;*/

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PaginaInicio frame = new PaginaInicio();
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
	
	/*metodo para obtener la fecha actual*/
	//public void obtener_fecha_actual() {
		
	//}
	
	public PaginaInicio() {
		conexion c= new conexion();
		c.metodo_conexion();
		setTitle("Home Banking Banco Argentina");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 628, 477);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel texto_usuario = new JLabel("Usuario:");
		texto_usuario.setFont(new Font("Arial", Font.PLAIN, 16));
		texto_usuario.setBounds(388, 11, 77, 14);
		contentPane.add(texto_usuario);
		
		usuario_logueado = new JTextField();
		usuario_logueado.setFont(new Font("Arial", Font.PLAIN, 11));
		usuario_logueado.setBounds(502, 11, 86, 20);
		contentPane.add(usuario_logueado);
		usuario_logueado.setColumns(10);
		
		JTabbedPane opciones = new JTabbedPane(JTabbedPane.TOP);
		opciones.setBounds(10, 79, 592, 348);
		contentPane.add(opciones);
		
		JPanel perfil = new JPanel();
		opciones.addTab("Perfil", null, perfil, null);
		perfil.setLayout(null);
		
		JLabel texto_nombre = new JLabel("Nombre:");
		texto_nombre.setFont(new Font("Arial", Font.PLAIN, 16));
		texto_nombre.setBounds(10, 11, 73, 14);
		perfil.add(texto_nombre);
		
		JLabel texto_genero = new JLabel("Genero:");
		texto_genero.setFont(new Font("Arial", Font.PLAIN, 16));
		texto_genero.setBounds(10, 50, 73, 14);
		perfil.add(texto_genero);
		
		JLabel texto_dni = new JLabel("DNI:");
		texto_dni.setFont(new Font("Arial", Font.PLAIN, 16));
		texto_dni.setBounds(10, 93, 46, 14);
		perfil.add(texto_dni);
		
		JLabel texto_direccion = new JLabel("Direccion:");
		texto_direccion.setFont(new Font("Arial", Font.PLAIN, 16));
		texto_direccion.setBounds(10, 134, 73, 14);
		perfil.add(texto_direccion);
		
		JLabel texto_telefono = new JLabel("Telefono:");
		texto_telefono.setFont(new Font("Arial", Font.PLAIN, 16));
		texto_telefono.setBounds(10, 176, 73, 14);
		perfil.add(texto_telefono);
		
		JLabel texto_fecha_n = new JLabel("Fecha de nacimiento:");
		texto_fecha_n.setFont(new Font("Arial", Font.PLAIN, 16));
		texto_fecha_n.setBounds(10, 219, 166, 14);
		perfil.add(texto_fecha_n);
		
		nombre = new JTextField();
		nombre.setEditable(false);
		nombre.setBounds(219, 10, 159, 20);
		perfil.add(nombre);
		nombre.setColumns(10);
		
		genero = new JTextField();
		genero.setEditable(false);
		genero.setBounds(219, 49, 159, 20);
		perfil.add(genero);
		genero.setColumns(10);
		
		dni = new JTextField();
		dni.setEditable(false);
		dni.setBounds(219, 92, 159, 20);
		perfil.add(dni);
		dni.setColumns(10);
		
		direccion = new JTextField();
		direccion.setEditable(false);
		direccion.setBounds(219, 133, 159, 20);
		perfil.add(direccion);
		direccion.setColumns(10);
		
		telefono = new JTextField();
		telefono.setEditable(false);
		telefono.setBounds(219, 175, 159, 20);
		perfil.add(telefono);
		telefono.setColumns(10);
		
		fechaNacimiento = new JTextField();
		fechaNacimiento.setEditable(false);
		fechaNacimiento.setBounds(219, 218, 159, 20);
		perfil.add(fechaNacimiento);
		fechaNacimiento.setColumns(10);
		
		JButton boton_editar = new JButton("Editar campos");
		boton_editar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*habilitar los campos para editarlos*/
				nombre.setEditable(true);
				genero.setEditable(true);
				dni.setEditable(true);
				direccion.setEditable(true);
				telefono.setEditable(true);
				fechaNacimiento.setEditable(true);
				
			}
		});
		boton_editar.setBounds(255, 274, 123, 23);
		perfil.add(boton_editar);
		
		JButton boton_guardar = new JButton("Guardar cambios");
		boton_guardar.setBounds(438, 274, 132, 23);
		perfil.add(boton_guardar);
		
		JPanel depositos = new JPanel();
		opciones.addTab("Depositos", null, depositos, null);
		depositos.setLayout(null);
		
		JPanel transferencias = new JPanel();
		opciones.addTab("Tranferencias", null, transferencias, null);
		transferencias.setLayout(null);
		
		JPanel retiros = new JPanel();
		opciones.addTab("Retiros", null, retiros, null);
		retiros.setLayout(null);
		
		JPanel listaClientes = new JPanel();
		opciones.addTab("Lista de clientes", null, listaClientes, null);
		listaClientes.setLayout(null);
		
		JPanel historial = new JPanel();
		opciones.addTab("Historial", null, historial, null);
		historial.setLayout(null);
		
		JPanel balance = new JPanel();
		opciones.addTab("Balance", null, balance, null);
		balance.setLayout(null);
		
		JPanel cambiarPin = new JPanel();
		opciones.addTab("Cambiar pin", null, cambiarPin, null);
		cambiarPin.setLayout(null);
		
		JLabel texto_fecha = new JLabel("Fecha de hoy:");
		texto_fecha.setFont(new Font("Arial", Font.PLAIN, 16));
		texto_fecha.setBounds(388, 36, 110, 26);
		contentPane.add(texto_fecha);
		
		fecha_actual = new JTextField();
		fecha_actual.setBounds(502, 41, 86, 20);
		contentPane.add(fecha_actual);
		fecha_actual.setColumns(10);
		/*setear la fecha actua*/
		Calendar calendar=new GregorianCalendar();
		int mes=calendar.get(Calendar.MONTH);//se obtiene el mes
		int anio=calendar.get(Calendar.YEAR);//se obtiene el anio
		int dia=calendar.get(Calendar.DAY_OF_MONTH);//se obtiene el dia
		
		//setear lo anterior para que se pueda visualizar
		fecha_actual.setText(dia+"/"+(mes+1)+"/"+anio);
		
		
	}
}
