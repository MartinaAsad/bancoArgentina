package Interfaz;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BaseDeDatos.conexion;

import javax.swing.JProgressBar;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;

public class BarraProgreso extends JFrame implements Runnable{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	/*necesario para la conexion*/
	conexion con =  new conexion();
	Connection conexion = con.metodo_conexion();
	int s=0;
	Thread th;
	JProgressBar progressBar = new JProgressBar();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BarraProgreso frame = new BarraProgreso();
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
	public BarraProgreso() {
		th=new Thread((Runnable)this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 321, 466);
		contentPane = new JPanel();
		setTitle("Por favor espere");
		contentPane.setBackground(new Color(255, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		progressBar.setStringPainted(true);
		progressBar.setForeground(new Color(64, 0, 0));
		progressBar.setBounds(66, 130, 146, 14);
		contentPane.add(progressBar);
		
		JLabel lblNewLabel = new JLabel("Banco Argentina");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel.setBounds(53, 58, 190, 29);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Cargando...");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 10));
		lblNewLabel_1.setBounds(97, 155, 79, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\Martina\\Downloads\\png-transparent-load-the-map-loa-removebg-preview.png"));
		lblNewLabel_2.setBounds(55, 248, 169, 149);
		contentPane.add(lblNewLabel_2);
	}
	
	public void inicializarBarra() {
		setVisible(false);
		th.start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			for(int i=0;i<=200;i++) {
				s=s+1;
				int m=progressBar.getMaximum();
				int v=progressBar.getValue();
				
				if(v<m) {
					progressBar.setValue(progressBar.getValue()+1);
				}else {
					i=201;
					setVisible(false);
					PaginaInicio pi=new PaginaInicio();
					pi.setVisible(true);
				}
				
				Thread.sleep(50);
			}
		}catch(Exception e) {
			
		}
		
	}
}
