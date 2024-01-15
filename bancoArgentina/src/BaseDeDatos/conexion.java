package BaseDeDatos;

import java.sql.*;
import javax.swing.JOptionPane;

public class conexion {
	Connection conn=null;
				//el connection se importa
	public static Connection metodo_conexion() {
		try {
			Class.forName("org.sqlite.JDBC");
			/*ubicacion de la base de datos a utilizar*/
			Connection conn=DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Martina\\git\\bancoArgentina\\bancoArgentina\\src\\BancoArgentina.db");
			return conn;
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		
		return null;
	}
}
