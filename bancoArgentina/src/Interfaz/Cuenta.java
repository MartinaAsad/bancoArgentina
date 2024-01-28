package Interfaz;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import BaseDeDatos.conexion;

public class Cuenta extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField ingresar_nombre;
	private JTextField ingresar_telefono;
	private JTextField ingresar_dni;
	private JTextField ingresar_cantidad;
	private Connection conn;
	/*necesario para la conexion*/
	conexion con =  new conexion();
	Connection conexion = con.metodo_conexion();
	private JTextField ingresar_pin;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 713, 579);
		setTitle("Informacion de la cuenta");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel texto_tipo_cuenta = new JLabel("Tipo de cuenta:");
		texto_tipo_cuenta.setFont(new Font("Arial", Font.PLAIN, 16));
		texto_tipo_cuenta.setBounds(10, 33, 127, 19);
		contentPane.add(texto_tipo_cuenta);

		JLabel texto_genero = new JLabel("Genero:");
		texto_genero.setFont(new Font("Arial", Font.PLAIN, 16));
		texto_genero.setBounds(10, 75, 84, 14);
		contentPane.add(texto_genero);

		JLabel texto_direccion = new JLabel("Direccion:");
		texto_direccion.setFont(new Font("Arial", Font.PLAIN, 16));
		texto_direccion.setBounds(10, 124, 127, 14);
		contentPane.add(texto_direccion);

		JLabel texto_nombre = new JLabel("Nombre:");
		texto_nombre.setFont(new Font("Arial", Font.PLAIN, 16));
		texto_nombre.setBounds(403, 35, 84, 14);
		contentPane.add(texto_nombre);

		JLabel texto_fecha_n = new JLabel("Fecha de nacimiento:");
		texto_fecha_n.setFont(new Font("Arial", Font.PLAIN, 16));
		texto_fecha_n.setBounds(403, 75, 179, 14);
		contentPane.add(texto_fecha_n);
		
		JDateChooser fecha_nacimiento = new JDateChooser();
		fecha_nacimiento.setBounds(567, 73, 70, 20);
		contentPane.add(fecha_nacimiento);

		JLabel texto_telefono = new JLabel("Telefono: ");
		texto_telefono.setFont(new Font("Arial", Font.PLAIN, 16));
		texto_telefono.setBounds(403, 124, 127, 14);
		contentPane.add(texto_telefono);

		JLabel texto_dni = new JLabel("DNI:");
		texto_dni.setFont(new Font("Arial", Font.PLAIN, 16));
		texto_dni.setBounds(403, 212, 46, 14);
		contentPane.add(texto_dni);

		JComboBox<String> opciones_cuenta = new JComboBox();
		opciones_cuenta.setModel(new DefaultComboBoxModel(new String[] {"Cuenta sueldo", "Caja de ahorro", "Cuenta corriente"}));
		opciones_cuenta.setBounds(162, 33, 134, 22);
		contentPane.add(opciones_cuenta);

		JRadioButton opcion_femenino = new JRadioButton("Femenino");
		opcion_femenino.setBounds(162, 73, 84, 23);
		contentPane.add(opcion_femenino);

		JRadioButton opcion_masculino = new JRadioButton("Masculino");
		opcion_masculino.setBounds(267, 73, 97, 23);
		contentPane.add(opcion_masculino);
		
		// Agrupar los JRadioButtons en un ButtonGroup
        ButtonGroup opciones_genero = new ButtonGroup();
        opciones_genero.add(opcion_femenino);
        opciones_genero.add(opcion_masculino);

		JTextArea direccion = new JTextArea();
		direccion.setBounds(162, 121, 162, 44);
		contentPane.add(direccion);

		ingresar_nombre = new JTextField();
		ingresar_nombre.setBounds(477, 32, 144, 20);
		contentPane.add(ingresar_nombre);
		ingresar_nombre.setColumns(10);

		ingresar_telefono = new JTextField();
		ingresar_telefono.setBounds(498, 123, 84, 20);
		contentPane.add(ingresar_telefono);
		ingresar_telefono.setColumns(10);

		ingresar_dni = new JTextField();
		ingresar_dni.setBounds(477, 211, 86, 20);
		contentPane.add(ingresar_dni);
		ingresar_dni.setColumns(10);
		
		JButton boton_crearcuenta = new JButton("Crear cuenta");
		boton_crearcuenta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String query="INSERT INTO Cuenta(tipoCuenta, generoCliente,"
						+ " direccionCliente, dineroDisponible, fechaNacimiento, nombreCliente, telefonoCliente, dniCliente, pin)"
						+ "VALUES (?,?,?,?,?,?,?,?,?)";
				
				java.util.Date fechaCasteo=null;
				/*obtengo lo ingresado en el textfield y lo almaceno en variables, hago casteos de ser necesario*/
				String direccionTexto=direccion.getText();
				double cantidad=Double.parseDouble(ingresar_cantidad.getText());
				String nombreCliente=ingresar_nombre.getText();
				String fechaNacimiento=((JTextField)fecha_nacimiento.getDateEditor().getUiComponent()).getText();
				//Date fecha_casteo=Date.valueOf(fechaNacimiento);
				
				/*formatear la fecha segun date mysql*/
				// Crear un objeto SimpleDateFormat para el formato original
				SimpleDateFormat formatoOriginal = new SimpleDateFormat("d MMM. yyyy", new Locale("es", "ES"));

				// segun date mysql
				SimpleDateFormat formatoDeseado = new SimpleDateFormat("yyyy-MM-dd");

				try {
				    java.util.Date fechaParseada = formatoOriginal.parse(fechaNacimiento);

				    // Formatear la fecha según el formato deseado
				    String fechaFormateada = formatoDeseado.format(fechaParseada);

				    /*castear la fecha segun formatoDeseado*/
				     fechaCasteo = formatoDeseado.parse(fechaFormateada);
				} catch (ParseException e2) {
				    e2.printStackTrace();
				}
		        
				int telefono=Integer.parseInt(ingresar_telefono.getText());
				int dni=Integer.parseInt(ingresar_dni.getText());
				int pin=Integer.parseInt(ingresar_pin.getText());
				
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				try {
					stmt=conexion.prepareStatement(query);
					stmt.setString(1, (String)opciones_cuenta.getSelectedItem());//captura la opcion seleccionada en genero
					/*esto para poder obtener la opcion seleccionada como genero*/
					opcion_femenino.setActionCommand("Femenino");
					opcion_masculino.setActionCommand("Masculino");
					stmt.setString(2, opciones_genero.getSelection().getActionCommand());
					stmt.setString(3,direccionTexto);
					stmt.setDouble(4,cantidad);
					stmt.setDate(5,new java.sql.Date(fechaCasteo.getTime()));
					stmt.setString(6,nombreCliente);
					stmt.setInt(7,telefono);
					stmt.setInt(8,dni);
					stmt.setInt(9, pin);
					
					stmt.executeUpdate();
					stmt.close();
					conexion.close();
					JOptionPane.showMessageDialog(null, "cuenta creada exitosamente");
					insertarBalance();//se insertan en la bbdd nroCuenta, nombreCliente, totalDisponible
				}catch(Exception e2) {
					System.out.println(e2);
					e2.printStackTrace();
				}
			}
		});
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
				ingresar_nombre.setText("");
				ingresar_telefono.setText("");
				ingresar_dni.setText("");
				ingresar_cantidad.setText("");
				ingresar_pin.setText("");
				direccion.setText("");
				fecha_nacimiento.setDate(null);
			}
		});
		boton_limpiar.setForeground(new Color(255, 255, 255));
		boton_limpiar.setBackground(new Color(0, 0, 0));
		boton_limpiar.setBounds(551, 340, 146, 23);
		contentPane.add(boton_limpiar);

		/*JDateChooser fecha_nacimiento = new JDateChooser();
		fecha_nacimiento.setBounds(567, 73, 70, 20);
		contentPane.add(fecha_nacimiento);*/
		
		JLabel texto_cantidad = new JLabel("Cantidad de dinero:");
		texto_cantidad.setFont(new Font("Arial", Font.PLAIN, 16));
		texto_cantidad.setBounds(10, 212, 164, 14);
		contentPane.add(texto_cantidad);
		
		ingresar_cantidad = new JTextField();
		ingresar_cantidad.setColumns(10);
		ingresar_cantidad.setBounds(162, 211, 144, 20);
		contentPane.add(ingresar_cantidad);
		
		JLabel texto_pin = new JLabel("PIN:");
		texto_pin.setFont(new Font("Arial", Font.PLAIN, 16));
		texto_pin.setBounds(403, 265, 46, 14);
		contentPane.add(texto_pin);
		
		ingresar_pin = new JTextField();
		ingresar_pin.setBounds(477, 264, 86, 20);
		contentPane.add(ingresar_pin);
		ingresar_pin.setColumns(10);
	}
	public void insertarBalance() {
		String query="INSERT INTO Balances(nombreCliente, totalDisponible) VALUES (?,?)";//consulta que inserta datos
		PreparedStatement stmt = null;
        ResultSet resultSet = null;
		try {
			stmt=conexion.prepareStatement(query);
			//stmt.setInt(1, Integer.parseInt(nro_cuenta.getText()));
			stmt.setString(2, ingresar_nombre.getText());
			stmt.setDouble(3, Double.parseDouble(ingresar_cantidad.getText()));
			
			stmt.executeUpdate();
			stmt.close();
			conexion.close();
			
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
}