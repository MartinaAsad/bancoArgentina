package BaseDeDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class conexion {
	Connection conn=null;
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
				//el connection se importa
	public Connection metodo_conexion() {
		try {
			/*jdbc:mysql://host:puerto/base, "usuario", "contraseña"*/
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/bancoargentina","root","");
			//System.out.println("todo funciona");
		}catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		} 
		
		return conn;
	}
}
