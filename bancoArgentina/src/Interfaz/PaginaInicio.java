package Interfaz;

import java.awt.EventQueue;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BaseDeDatos.conexion;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTabbedPane;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

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
    private Connection conn;
    /*necesario para la conexion*/
    conexion con =  new conexion();
    Connection conexion = con.metodo_conexion();
    private JTextField depositos_nrocuenta;
    private JTextField depositos_cantdepositar;
    private JTextField depositos_usuario;
    private JTextField depositos_total;
    private JTextField transferencia_usuario;
    private JTextField transferencia_nroCuenta;
    private JTextField transferencia_cantidad;
    private JTextField retiros_colocar_nro;
    private JTextField retiros_colocar_monto;
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
        texto_usuario.setBounds(329, 11, 77, 14);
        texto_usuario.setFont(new Font("Arial", Font.PLAIN, 16));
        contentPane.add(texto_usuario);

        usuario_logueado = new JTextField();
        usuario_logueado.setBounds(445, 11, 86, 20);
        usuario_logueado.setFont(new Font("Arial", Font.PLAIN, 11));
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
                direccion.setEditable(true);
                telefono.setEditable(true);

            }
        });
        boton_editar.setBounds(255, 274, 123, 23);
        perfil.add(boton_editar);

        JButton boton_guardar = new JButton("Guardar cambios");
        boton_guardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    /*obtengo desde los campos de texto los valores editados*/
                    String direccionNueva=direccion.getText();
                    String telefonoNuevo=telefono.getText();
                    String nombreUsuario=usuario_logueado.getText();

                    PreparedStatement stmt = null;
                    ResultSet resultSet = null;
                    String sql="UPDATE Cuenta SET direccionCliente='"+direccionNueva+"',telefonoCliente='"+telefonoNuevo+"'where nombreCliente='"+nombreUsuario+"'";
                    stmt=conexion.prepareStatement(sql);
                    stmt.execute();
                    JOptionPane.showMessageDialog(null, "cambios correctamente realizados");
                }catch(Exception e2) {
                    JOptionPane.showMessageDialog(null, e2);
                }
            }
        });
        boton_guardar.setBounds(438, 274, 132, 23);
        perfil.add(boton_guardar);

        JPanel depositos = new JPanel();
        opciones.addTab("Depositos", null, depositos, null);
        depositos.setLayout(null);

        JLabel texto_depositos_nrocuenta = new JLabel("Numero de cuenta:");
        texto_depositos_nrocuenta.setFont(new Font("Arial", Font.PLAIN, 16));
        texto_depositos_nrocuenta.setBounds(41, 101, 153, 14);
        depositos.add(texto_depositos_nrocuenta);

        JLabel texto_depositos_cantidad = new JLabel("Cantidad a depositar:");
        texto_depositos_cantidad.setFont(new Font("Arial", Font.PLAIN, 16));
        texto_depositos_cantidad.setBounds(41, 169, 228, 27);
        depositos.add(texto_depositos_cantidad);

        depositos_nrocuenta = new JTextField();
        depositos_nrocuenta.setEditable(false);
        depositos_nrocuenta.setBounds(314, 95, 122, 20);
        depositos.add(depositos_nrocuenta);
        depositos_nrocuenta.setColumns(10);

        depositos_cantdepositar = new JTextField();
        depositos_cantdepositar.setBounds(314, 174, 122, 20);
        depositos.add(depositos_cantdepositar);
        depositos_cantdepositar.setColumns(10);

        JButton depositos_boton_depositar = new JButton("Depositar");
        depositos_boton_depositar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    double totalDisponible=Double.parseDouble(depositos_total.getText());
                    double valorADepo=Double.parseDouble(depositos_cantdepositar.getText());
                    double balanceActualizado=totalDisponible+valorADepo;
                    String balance=Double.toString(balanceActualizado);
                    String usuario=depositos_usuario.getText();

                    PreparedStatement stmt = null;
                    ResultSet resultSet = null;
                    String sql="UPDATE balances SET totalDisponible='"+balanceActualizado+"'where nombreCliente='"+usuario+"'";
                    stmt=conexion.prepareStatement(sql);
                    stmt.execute();
                    JOptionPane.showMessageDialog(null, "deposito realizado");
                    depositos_total.setText(balance);
                    camposVaciosDeposito();

                }catch(Exception e2) {
                    JOptionPane.showMessageDialog(null, e2);
                }
            }
        });
        depositos_boton_depositar.setBounds(447, 233, 89, 23);
        depositos.add(depositos_boton_depositar);

        JLabel texto_depositos_usuario = new JLabel("Usuario:");
        texto_depositos_usuario.setFont(new Font("Arial", Font.PLAIN, 16));
        texto_depositos_usuario.setBounds(41, 63, 105, 14);
        depositos.add(texto_depositos_usuario);

        depositos_usuario = new JTextField();
        depositos_usuario.setBounds(314, 57, 122, 20);
        depositos.add(depositos_usuario);
        depositos_usuario.setColumns(10);

        JLabel texto_depositos_total = new JLabel("Total disponible:");
        texto_depositos_total.setFont(new Font("Arial", Font.PLAIN, 16));
        texto_depositos_total.setBounds(41, 139, 133, 19);
        depositos.add(texto_depositos_total);

        depositos_total = new JTextField();
        depositos_total.setEditable(false);
        depositos_total.setBounds(314, 133, 122, 20);
        depositos.add(depositos_total);
        depositos_total.setColumns(10);

        JButton depositos_buscarUsuario = new JButton("");
        depositos_buscarUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PreparedStatement stmt = null;
                ResultSet resultSet = null;
                String sql="SELECT * FROM balances WHERE nombreCliente = ?";
                try {
                    stmt=conexion.prepareStatement(sql);
                    stmt.setString(1, depositos_usuario.getText());
                    resultSet=stmt.executeQuery();
                    if(resultSet.next()) {
                        /*obtengo los valores y los seteo*/
                        String nroCuenta=Integer.toString(resultSet.getInt(2));
                        String totalDisponible=Double.toString(resultSet.getDouble(4));
                        depositos_nrocuenta.setEnabled(true);
                        depositos_nrocuenta.setText(nroCuenta);
                        depositos_total.setEnabled(true);
                        depositos_total.setText(totalDisponible);
                        resultSet.close();
                        stmt.close();
                    }else {
                        JOptionPane.showMessageDialog(null, "usuario no encontrado");
                    }
                }catch(Exception e2) {
                    JOptionPane.showMessageDialog(null, e2);
                }
            }
        });
        depositos_buscarUsuario.setIcon(new ImageIcon("C:\\Users\\Martina\\Downloads\\3721746 (2).png"));
        depositos_buscarUsuario.setBounds(464, 33, 41, 44);
        depositos.add(depositos_buscarUsuario);

        JPanel transferencias = new JPanel();
        opciones.addTab("Tranferencias", null, transferencias, null);
        transferencias.setLayout(null);

        JLabel texto_transferencia_usuario = new JLabel("Usuario a transferir:");
        texto_transferencia_usuario.setFont(new Font("Arial", Font.PLAIN, 16));
        texto_transferencia_usuario.setBounds(43, 45, 199, 14);
        transferencias.add(texto_transferencia_usuario);

        JLabel texto_transferencia_nrocuenta = new JLabel("Numero de cuenta a transferir:");
        texto_transferencia_nrocuenta.setFont(new Font("Arial", Font.PLAIN, 16));
        texto_transferencia_nrocuenta.setBounds(43, 90, 227, 14);
        transferencias.add(texto_transferencia_nrocuenta);

        JLabel texto_transferencia_cantidad = new JLabel("Cantidad a transferir:");
        texto_transferencia_cantidad.setFont(new Font("Arial", Font.PLAIN, 16));
        texto_transferencia_cantidad.setBounds(42, 139, 228, 27);
        transferencias.add(texto_transferencia_cantidad);

        JButton transferencia_buscarUsuario = new JButton("");
        transferencia_buscarUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PreparedStatement stmt = null;
                ResultSet resultSet = null;
                String sql="SELECT * FROM balances WHERE nombreCliente = ?";
                try {
                    stmt=conexion.prepareStatement(sql);
                    stmt.setString(1, transferencia_usuario.getText());
                    resultSet=stmt.executeQuery();
                    if(resultSet.next()) {
                        /*obtengo los valores y los seteo*/
                        String nroCuenta=Integer.toString(resultSet.getInt(2));
                        /*String totalDisponible=Double.toString(resultSet.getDouble(4));*/
                        transferencia_nroCuenta.setEnabled(true);
                        transferencia_nroCuenta.setText(nroCuenta);
						/*transferencia_total.setEnabled(true);
						transferencia_total.setText(totalDisponible);*/
                        resultSet.close();
                        stmt.close();
                    }else {
                        JOptionPane.showMessageDialog(null, "usuario no encontrado");
                    }
                }catch(Exception e2) {
                    JOptionPane.showMessageDialog(null, e2);
                }
                try {


                }catch(Exception e2) {
                    JOptionPane.showMessageDialog(null, e2);
                }

            }
        });
        transferencia_buscarUsuario.setIcon(new ImageIcon("C:\\Users\\Martina\\Downloads\\3721746 (2).png"));
        transferencia_buscarUsuario.setBounds(473, 26, 41, 44);
        transferencias.add(transferencia_buscarUsuario);

        JButton transferencia_transferir = new JButton("Tranferir");
        transferencia_transferir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                transferir();
                //verificarSaldo();

            }

        });
        transferencia_transferir.setBounds(460, 215, 89, 23);
        transferencias.add(transferencia_transferir);

        transferencia_usuario = new JTextField();
        transferencia_usuario.setBounds(301, 44, 133, 20);
        transferencias.add(transferencia_usuario);
        transferencia_usuario.setColumns(10);

        transferencia_nroCuenta = new JTextField();
        transferencia_nroCuenta.setEditable(false);
        transferencia_nroCuenta.setColumns(10);
        transferencia_nroCuenta.setBounds(301, 89, 133, 20);
        transferencias.add(transferencia_nroCuenta);

        transferencia_cantidad = new JTextField();
        transferencia_cantidad.setBounds(301, 144, 133, 20);
        transferencias.add(transferencia_cantidad);
        transferencia_cantidad.setColumns(10);

        JPanel retiros = new JPanel();
        opciones.addTab("Retiros", null, retiros, null);
        retiros.setLayout(null);

        JLabel retiros_nrocuenta = new JLabel("Numero de cuenta:");
        retiros_nrocuenta.setFont(new Font("Arial", Font.PLAIN, 16));
        retiros_nrocuenta.setBounds(91, 37, 185, 14);
        retiros.add(retiros_nrocuenta);

        JLabel retiros_monto = new JLabel("Monto:");
        retiros_monto.setFont(new Font("Arial", Font.PLAIN, 16));
        retiros_monto.setBounds(91, 120, 112, 14);
        retiros.add(retiros_monto);

        retiros_colocar_nro = new JTextField();
        retiros_colocar_nro.setBounds(284, 36, 86, 20);
        retiros.add(retiros_colocar_nro);
        retiros_colocar_nro.setColumns(10);

        retiros_colocar_monto = new JTextField();
        retiros_colocar_monto.setColumns(10);
        retiros_colocar_monto.setBounds(284, 114, 86, 20);
        retiros.add(retiros_colocar_monto);

        JButton retiros_retirar_dinero = new JButton("Retirar dinero");
        retiros_retirar_dinero.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String usuario=usuario_logueado.getText();
                String nroCuenta=retiros_colocar_nro.getText();
                String monto=retiros_colocar_monto.getText();

                int nroCuentaCasteado=Integer.valueOf(nroCuenta);
                int montoCasteado=Integer.valueOf(monto);
                if(verificarCuenta(usuario, nroCuentaCasteado)) {
                    verificarMonto(nroCuentaCasteado, montoCasteado);

                }
            }
        });
        retiros_retirar_dinero.setBounds(455, 259, 127, 23);
        retiros.add(retiros_retirar_dinero);

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
        texto_fecha.setBounds(329, 42, 110, 26);
        texto_fecha.setFont(new Font("Arial", Font.PLAIN, 16));
        contentPane.add(texto_fecha);

        fecha_actual = new JTextField();
        fecha_actual.setBounds(445, 48, 86, 20);
        contentPane.add(fecha_actual);
        fecha_actual.setColumns(10);
        /*setear la fecha actua*/
        Calendar calendar=new GregorianCalendar();
        int mes=calendar.get(Calendar.MONTH);//se obtiene el mes
        int anio=calendar.get(Calendar.YEAR);//se obtiene el anio
        int dia=calendar.get(Calendar.DAY_OF_MONTH);//se obtiene el dia

        //setear lo anterior para que se pueda visualizar
        fecha_actual.setText(dia+"/"+(mes+1)+"/"+anio);

        JButton boton_ver_nombre_usuario = new JButton("ver usuario");
        boton_ver_nombre_usuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PreparedStatement stmt = null;
                ResultSet resultSet = null;
                String sql="SELECT * FROM cuenta WHERE nombreCliente=?";
                try {
                    stmt=conexion.prepareStatement(sql);
                    stmt.setString(1, usuario_logueado.getText());
                    resultSet=stmt.executeQuery();
					/*nombre.setEditable(true);
				genero.setEditable(true);
				dni.setEditable(true);
				direccion.setEditable(true);
				telefono.setEditable(true);
				fechaNacimiento.setEditable(true);*/
                    if(resultSet.next()) {
                        String nombreObtenido=resultSet.getString("nombreCliente");//aca obtengo el nombre del cliente logueado
                        //seteo el nombre en el campo para que se visualice
                        nombre.setText(nombreObtenido);

                        String generoObtenido=resultSet.getString("generoCliente");//aca obtengo el genero del cliente logueado
                        //seteo el genero en el campo para que se visualice
                        genero.setText(generoObtenido);

                        String dniObtenido=resultSet.getString("dniCliente");//aca obtengo el dni del cliente logueado
                        //seteo el dni en el campo para que se visualice
                        dni.setText(dniObtenido);

                        String direccionObtenida=resultSet.getString("direccionCliente");//aca obtengo la direccion del cliente logueado
                        //seteo la direccion en el campo para que se visualice
                        direccion.setText(direccionObtenida);

                        String telefonoObtenido=resultSet.getString("telefonoCliente");//aca obtengo el telefono del cliente logueado
                        //seteo el telefono en el campo para que se visualice
                        telefono.setText(telefonoObtenido);

						/*Date fechaObtenida=resultSet.getDate("fechaNacimiento");
						//pasar de date (formato original desde la bbdd) para que sea string y pueda visualizarse
						Date date=new Date();
						SimpleDateFormat formato=new SimpleDateFormat("yyyy/dd/mm");
						String fechaConvertida=formato.format(date);
						fechaNacimiento.setText(fechaConvertida);*/
                        resultSet.close();
                        stmt.close();
                    }else {
                        JOptionPane.showMessageDialog(null, "el nombre ingresado no es correcto");
                    }
                }catch(Exception e3) {
                    JOptionPane.showMessageDialog(null, e3);
                }finally {
                    try {
                        resultSet.close();
                        stmt.close();
                    } catch (SQLException e1) {
                        // TODO Auto-generated catch block
                        JOptionPane.showMessageDialog(null, e1);
                    }
                }

            }
        });
        boton_ver_nombre_usuario.setIcon(new ImageIcon("C:\\Users\\Martina\\Downloads\\eye_icon-icons.com_64930 (1).png"));
        boton_ver_nombre_usuario.setBounds(548, 11, 54, 23);
        contentPane.add(boton_ver_nombre_usuario);



    }
    public void camposVaciosDeposito() {
        depositos_cantdepositar.setText("");
        depositos_nrocuenta.setText("");
        depositos_total.setText("");
        depositos_usuario.setText("");

    }

    public void transferir() {
        //logueado es el usuario que realiza la transferencia y de donde se descuentan los fonods
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        int cantFilas=0;

        String cantTransferir=transferencia_cantidad.getText();
        double casteoTransferir=Double.parseDouble(cantTransferir);
        double cantidadDisponibleActualTranferir=0;
        String nombreClienteAtransferir=transferencia_usuario.getText();
        double cantidadDisponiblePrevioUsuarioTranferir=0;

        double cantidadDisponibleActualLogueado=0;
        String clienteLogueado=usuario_logueado.getText();
        double cantidadDisponiblePrevioUsuarioLogueado=0;


        String verificarDisponibilidadLogueado="SELECT totalDisponible FROM balances WHERE nombreCliente = ?";
        String nuevoSaldoLogueado="UPDATE balances SET TotalDisponible = ? WHERE nombreCliente = ?";

        String verificarDisponibilidadTranferir="SELECT totalDisponible FROM balances WHERE nombreCliente = ?";
        String nuevoSaldoTransferir="UPDATE balances SET TotalDisponible = ? WHERE nombreCliente = ?";

        try {
            stmt=conexion.prepareStatement(verificarDisponibilidadLogueado);
            stmt.setString(1, clienteLogueado);
            resultSet=stmt.executeQuery();

            if(resultSet.next()) {
                cantidadDisponiblePrevioUsuarioLogueado=resultSet.getDouble(1);
                stmt.close();
                if(cantidadDisponiblePrevioUsuarioLogueado<=casteoTransferir) {
                    System.out.println("no se puede transferir el monto");
                }else {
                    System.out.println("se puede transferir el monto");
                    stmt=conexion.prepareStatement(verificarDisponibilidadTranferir);
                    stmt.setString(1, nombreClienteAtransferir);
                    resultSet=stmt.executeQuery();
                    if(resultSet.next()) {
                        /*aca se pone monto posterior de la cuenta a transferir + cantidad que se le transfiere*/
                        cantidadDisponibleActualTranferir=resultSet.getDouble(1)+casteoTransferir;
                        stmt=conexion.prepareStatement(nuevoSaldoTransferir);
                        stmt.setDouble(1, cantidadDisponibleActualTranferir);
                        stmt.setString(2, nombreClienteAtransferir);
                        cantFilas=stmt.executeUpdate();
                        if(cantFilas>0) {
                            JOptionPane.showMessageDialog(null, "transferencia exitosa");
                            /*si la transferencia es exitosa, descontar el monto de la cueta de origen*/
                            cantidadDisponibleActualLogueado=resultSet.getDouble(1)-casteoTransferir;
                            stmt=conexion.prepareStatement(nuevoSaldoLogueado);
                            stmt.setDouble(1, cantidadDisponibleActualLogueado);
                            stmt.setString(2, clienteLogueado);
                            cantFilas=stmt.executeUpdate();
                        }else {
                            JOptionPane.showMessageDialog(null, "no pudo realizarse la transferencia. Contactar al banco");
                        }
                    }
                }
            }


        }catch(Exception e) {
            System.out.println(e);
        }


    }

    public boolean verificarCuenta(String nombreClienteLogueado, int nroCuenta) {
        //aca se chequea si el numero de cuenta ingresado corresponde al cliente logueado
        boolean existe=false;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;

        String query="SELECT nroCuenta FROM balances WHERE nroCuenta = ? AND nombreCliente = ?";

        try {
            stmt=conexion.prepareStatement(query);
            stmt.setInt(1, nroCuenta);
            stmt.setString(2, nombreClienteLogueado);
            resultSet=stmt.executeQuery();

            if(resultSet.next()) {
                existe=true;
            }
            return existe;

        }catch(Exception e) {
            System.out.println(e);
        }

        return existe;

    }

    public boolean verificarMonto(int nroCuenta, int monto) {
        //aca se chequea si el numero de cuenta ingresado corresponde al cliente logueado
        boolean esMenorOIgual=false;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        double montoPrevio=0;

        String query="SELECT totalDisponible FROM balances WHERE nroCuenta = ?";

        try {
            stmt=conexion.prepareStatement(query);
            stmt.setInt(1, nroCuenta);
            resultSet=stmt.executeQuery();

            if(resultSet.next()) {
                montoPrevio=resultSet.getDouble(1);
                if(montoPrevio>=monto) {
                    esMenorOIgual=true;
                }
            }

        }catch(Exception e) {
            System.out.println(e);
        }

        return esMenorOIgual;

    }
}
