/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Responsable;
import model.databaseConection.ConnectionToDb;

/**
 *
 * @author texch
 */
public class ResponsableDAO {

 public static List<Responsable> obtenerAllResponsable() throws SQLException {
    ObservableList<Responsable> responsables = 
            FXCollections.observableArrayList();
    Responsable re = null;
    Connection conexion = null;
    conexion = ConnectionToDb.conectar("root", "2580", "sacco", "localhost");
    Statement st = null;
    st = conexion.createStatement();
    ResultSet resultadoQuery = st.executeQuery("select * from responsable");
    try {
      while (resultadoQuery.next()) {
        String numPersonal = resultadoQuery.getString("numPersonal");
        String nombre = (resultadoQuery.getString("nombre"));
        String telefono = (resultadoQuery.getString("telefono"));
        String extension = (resultadoQuery.getString("extension"));
        String correo = (resultadoQuery.getString("correo"));
        re = new Responsable(numPersonal, nombre, telefono, extension, correo);
        responsables.add(re);
      }
    } catch (SQLException ex) {
      Logger.getLogger(JefeCentroComputoDAO.class.getName()).log(Level.SEVERE
              , null, ex);
    } finally {
      if (conexion != null) {
        conexion.close();
      }
    }
    return responsables;  
  }

  public static List<Responsable> obtenerResponsableNombre(String name) 
    throws SQLException{
    ObservableList<Responsable> responsables = 
            FXCollections.observableArrayList();
    Responsable re = null;
    Connection conexion = null;
    conexion = ConnectionToDb.conectar("root", "2580", "sacco", "localhost");
    PreparedStatement st = null;
    st = conexion.prepareStatement("select * from responsable"
      + " where nombre = ?");
    st.setString(1, name);
    ResultSet resultadoQuery = st.executeQuery();
    try {
      while (resultadoQuery.next()) {
        String numPersonal = (resultadoQuery.getString("numPersonal"));
        String nombre = (resultadoQuery.getString("nombre"));
        String telefono = (resultadoQuery.getString("telefono"));
        String extension = (resultadoQuery.getString("extension"));
        String correo = (resultadoQuery.getString("correo"));
        
        re = new Responsable(numPersonal, nombre, telefono,extension, correo);
        responsables.add(re);  
      }
    } catch (SQLException ex) {
      Logger.getLogger(JefeCentroComputoDAO.class.getName()).log(Level.SEVERE,
        null, ex);
    } finally {
      conexion.close();
    }
    return responsables;
  }

  public static Responsable obtenerResponsableNumPersonal(String numPersonal) 
          throws SQLException {
    Responsable responsable = null;
    Connection conexion = null;
    conexion = ConnectionToDb.conectar("root", "2580", "sacco", "localhost");
    PreparedStatement st = null;
    st = conexion.prepareStatement("select * from responsable where "
            + "numPersonal = ?");
    st.setString(1, numPersonal);
    ResultSet resultadoQuery = st.executeQuery();

    while (resultadoQuery.next()) {
      String numPers = resultadoQuery.getString("numPersonal");
      String nombre = resultadoQuery.getString("nombre");
      String correo = resultadoQuery.getString("correo");
      String telefono = resultadoQuery.getString("telefono");
      String extension = resultadoQuery.getString("extension");

      responsable = new Responsable(numPers, nombre, telefono, extension,
              correo);
      System.out.println(responsable.getNombre());
    }

    return responsable;
  }
  
  public static void agregarResponsable(Responsable responsable) throws
          SQLException {
    Connection conexion = null;
    PreparedStatement stp = null;
    conexion = ConnectionToDb.conectar("root", "2580", "sacco", "localhost");
    stp = conexion.prepareStatement("insert into responsable "
            + "values(?,?,?,?,?)");
    stp.setString(1, responsable.getNumPersonal());
    stp.setString(2, responsable.getNombre());
    stp.setString(3, responsable.getTelefono());
    stp.setString(4, responsable.getExtension());
    stp.setString(5, responsable.getCorreo());
    stp.executeUpdate();      
  }
  
   public static void editarResponsable(Responsable responsable) throws 
           SQLException {
    Connection conexion;
    conexion = ConnectionToDb.conectar("root", "2580", "sacco", "localhost");
    PreparedStatement st;
    st = conexion.prepareStatement("UPDATE Responsable SET nombre = ?, "
            + "telefono = ?, extension = ?, correo = ? where numPersonal = ?");
    st.setString(1, responsable.getNombre());
    st.setString(2, responsable.getTelefono());
    st.setString(3, responsable.getExtension());
    st.setString(4, responsable.getCorreo());
    st.setString(5, responsable.getNumPersonal());
    st.executeUpdate();
  }
   
  public static void eliminarResponsable(Responsable responsable) 
    throws SQLException {
    Connection conexion = null;
    conexion = ConnectionToDb.conectar("root", "2580", "sacco", "localhost");
    PreparedStatement st = null;
    st = conexion.prepareStatement("delete responsable from responsable "
            + "where numPersonal = ?");
    st.setString(1, responsable.getNumPersonal());
    st.executeUpdate();
  }
  
  
  
}
