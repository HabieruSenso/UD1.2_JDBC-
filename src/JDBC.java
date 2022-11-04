import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class JDBC extends JFrame {

  private JPanel contentPane;
  private JTextField tf1;
  private JTextField tf2;
  private JLabel labelResultado;
  private JButton btnConsultaPorCdigo;
  private JLabel lblIngreseCdigoDe;
  private JTextField tf3;
  private JButton btnGuardar;
  private JButton btnNewButton_1;

  
  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
            JDBC frame = new JDBC();
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
  public JDBC() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 606, 405);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);
    
    JLabel lblNota = new JLabel("Nota:");
    lblNota.setBounds(25, 65, 193, 14);
    contentPane.add(lblNota);
    
    tf1 = new JTextField();
    tf1.setBounds(221, 62, 259, 187);
    contentPane.add(tf1);
    tf1.setColumns(10);
    
    JLabel lblFecha = new JLabel("Fecha:");
    lblFecha.setBounds(25, 38, 95, 14);
    contentPane.add(lblFecha);
    
    tf2 = new JTextField();
    tf2.setBounds(373, 35, 107, 20);
    contentPane.add(tf2);
    tf2.setColumns(10);
    
    btnGuardar = new JButton("Guardar");
    btnGuardar.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
            labelResultado.setText("");  
            String fecha = tf1.getText();
            String nota = tf2.getText();
            System.out.println(fecha + " - " + nota);
            try {
              Connection conexion=DriverManager.getConnection("jdbc:mysql://localhost/agenda_carlos","root" ,"");
              PreparedStatement comando=conexion.prepareStatement("INSERT INTO `ag_carlos` (`fecha`, `nota`, `codigo_nota`) VALUES (?,?, NULL)");
              comando.setString(1, nota);
              comando.setString(2, fecha);
              comando.executeUpdate();
              comando.close();
              conexion.close();
              labelResultado.setText("se registraron los datos");
              tf1.setText("");
              tf2.setText("");
            } catch(SQLException ex){
              setTitle(ex.toString());
            }
          
    	}
    });
    btnGuardar.setBounds(433, 287, 89, 23);
    contentPane.add(btnGuardar);
    
    labelResultado = new JLabel("resultado");
    labelResultado.setBounds(25, 356, 229, 14);
    contentPane.add(labelResultado);
    
    btnConsultaPorCdigo = new JButton("Consulta por código");
    btnConsultaPorCdigo.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        labelResultado.setText("");
        tf1.setText("");
        tf2.setText("");        
        try {
          Connection conexion=DriverManager.getConnection("jdbc:mysql://localhost/agenda_carlos","root" ,"");
          Statement comando=conexion.createStatement();
          ResultSet registro = comando.executeQuery("select nota,fecha from ag_carlos where codigo_nota="+tf3.getText());
          if (registro.next()==true) {
            tf1.setText(registro.getString("nota"));
            tf2.setText(registro.getString("fecha"));
          } else {
            labelResultado.setText("No existe un artículo con dicho código");
          }
          conexion.close();
        } catch(SQLException ex){
          setTitle(ex.toString());
        }
      }
    });
    btnConsultaPorCdigo.setBounds(390, 321, 177, 23);
    contentPane.add(btnConsultaPorCdigo);
    
    lblIngreseCdigoDe = new JLabel("Ingrese código de la nota a consultar:");
    lblIngreseCdigoDe.setBounds(25, 325, 243, 14);
    contentPane.add(lblIngreseCdigoDe);
    
    tf3 = new JTextField();
    tf3.setBounds(266, 322, 86, 20);
    contentPane.add(tf3);
    tf3.setColumns(10);
    cargarDriver();
    
    JButton btnNewButton = new JButton("Borrar");
    btnNewButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        labelResultado.setText("");
        try {
          Connection conexion=DriverManager.getConnection("jdbc:mysql://localhost/agenda_carlos","root" ,"");
          Statement comando=conexion.createStatement();
          int cantidad = comando.executeUpdate("delete from ag_carlos where codigo_nota="+tf3.getText());
          if (cantidad==1) {
            tf1.setText("");
            tf2.setText("");        
            labelResultado.setText("Se borro el artículo con dicho código");
          } else {
            labelResultado.setText("No existe un artículo con dicho código");
          }
          conexion.close();
        } catch(SQLException ex){
          setTitle(ex.toString());
        }        
      }
    });
    btnNewButton.setBounds(41, 287, 177, 23);
    contentPane.add(btnNewButton);
    
    btnNewButton_1 = new JButton("Modificar");
    btnNewButton_1.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
            labelResultado.setText("");
            String fecha = tf1.getText();
            String nota = tf2.getText();
            String codigo = tf3.getText();
            try {
                Connection conexion=DriverManager.getConnection("jdbc:mysql://localhost/agenda_carlos","root" ,"");
                PreparedStatement comando=conexion.prepareStatement("UPDATE `ag_carlos` SET `fecha` = ?, `nota` = ? WHERE `ag_carlos`.`codigo_nota` = ?");
                comando.setString(1, nota);
                comando.setString(2, fecha);
                comando.setString(3, codigo);
                comando.executeUpdate();
                comando.close();
                conexion.close();
                labelResultado.setText("se modificaron los datos");
                tf1.setText("");
                tf2.setText("");
                tf3.setText("");
              } catch(SQLException ex){
                setTitle(ex.toString());
              }      
    	}
    });
  
    btnNewButton_1.setBounds(244, 287, 179, 23);
    contentPane.add(btnNewButton_1);
    cargarDriver();
  }
  
  private void cargarDriver() {
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
    }catch(Exception ex) {
      setTitle(ex.toString());
    }
  }
}