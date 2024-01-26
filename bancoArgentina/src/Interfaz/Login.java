package Interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import BaseDeDatos.conexion;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField ingresar_nroCuenta;
	private JTextField ingresar_pin;
	/*necesario para la conexion*/
	conexion c= new conexion();
	Connection conn=null;
	Statement stmt=null;
	final ResultSet rs=null;/*aca se asigna el resultado de la consulta*/

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		/*codigo para el jtattoo*/
		   try {
	           for(javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
	        	   UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");	   
	           }
	        } catch (ClassNotFoundException ex) {
	            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	        } catch (InstantiationException ex) {
	            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	        } catch (IllegalAccessException ex) {
	            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
	            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	        }
		   
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		final ResultSet rs = null;
		PreparedStatement pst;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 486, 374);
		setTitle("Home Banking Banco Argentina");
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel titulo = new JLabel("Banco Argentina");
		titulo.setFont(new Font("Arial", Font.BOLD, 20));
		titulo.setBounds(20, 22, 209, 24);
		contentPane.add(titulo);
		
		JLabel text_nroCuenta = new JLabel("Numero de cuenta:");
		text_nroCuenta.setFont(new Font("Arial", Font.PLAIN, 16));
		text_nroCuenta.setBounds(83, 97, 143, 14);
		contentPane.add(text_nroCuenta);
		
		JLabel texto_pin = new JLabel("PIN:");
		texto_pin.setFont(new Font("Arial", Font.PLAIN, 16));
		texto_pin.setBounds(83, 151, 46, 14);
		contentPane.add(texto_pin);
		
		ingresar_nroCuenta = new JTextField();
		ingresar_nroCuenta.setBounds(255, 96, 86, 20);
		contentPane.add(ingresar_nroCuenta);
		ingresar_nroCuenta.setColumns(10);
		
		ingresar_pin = new JTextField();
		ingresar_pin.setBounds(255, 150, 86, 20);
		contentPane.add(ingresar_pin);
		ingresar_pin.setColumns(10);
		
		JButton boton_ingresar = new JButton("Ingresar");
		boton_ingresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*consulta de bbdd que me permite traer los datos*/
				//String consulta="SELECT * FROM Cuenta WHERE nroCuenta = ? AND clave = ?";
				String consulta="SELECT * FROM Cuenta";
				try {
					conn=c.metodo_conexion();
					stmt=conn.createStatement();
					final ResultSet rsFinal = rs; // Declara rs como final
					rs=stmt.executeQuery(consulta);
					/*el while es de prueba*/
					
					while(rsFinal.next()) {
						int nroCuenta=rsFinal.getInt(1);//solo trae la rimera columna
						String tipoCuenta=rsFinal.getString(2);
						
						System.out.println(nroCuenta+" "+tipoCuenta);
					}
				}catch(SQLException e2) {
					e2.printStackTrace();
				}
				}
		});
		boton_ingresar.setForeground(new Color(255, 255, 255));
		boton_ingresar.setBackground(new Color(0, 0, 0));
		boton_ingresar.setBounds(128, 235, 89, 23);
		contentPane.add(boton_ingresar);
		
		JButton boton_crearcuenta = new JButton("Crear cuenta");
		boton_crearcuenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Cuenta c = new Cuenta();
				c.setVisible(true);//se abre esta ventana para completar los datos
			}
		});
		boton_crearcuenta.setForeground(new Color(255, 255, 255));
		boton_crearcuenta.setBackground(new Color(0, 0, 0));
		boton_crearcuenta.setBounds(291, 235, 122, 23);
		contentPane.add(boton_crearcuenta);
	}
}
