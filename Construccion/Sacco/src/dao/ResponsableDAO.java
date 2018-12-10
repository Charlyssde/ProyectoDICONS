/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

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
import model.databaseconnection.ConnectionToDb;

/**
 *
 * @author texch
 */
public class ResponsableDAO {
  private static final String USUARIO = "root";
  private static final String PASS = "2580";
  private static final String DB = "sacco";
  private static final String HOST = "localhost";

  private ResponsableDAO() {
    // Nothing
  }

  /**
   * Se obtienen todos los objetos de tipo Responsable que estan registrados en
   * la base de datos.
   *
   * @return
   */
  public static List<Responsable> obtenerAllResponsable() {
    ObservableList<Responsable> responsables
        = FXCollections.observableArrayList();
    Responsable re = null;
    try (Connection conexion = ConnectionToDb.conectar(USUARIO, PASS, DB, HOST);
        Statement st = conexion.createStatement();
        ResultSet resultadoQuery = st.executeQuery("select * from responsable");) {
      while (resultadoQuery.next()) {
        re = cargarDatosResponsable(resultadoQuery, re);
        responsables.add(re);
      }
    } catch (SQLException ex) {
      Logger.getLogger(JefeCentroComputoDAO.class.getName()).log(Level.SEVERE,
          null, ex);
    }
    return responsables;
  }

  /**
   * Se obtienen objetos de tipo Responsable mediante el nombre, que estan
   * registrados en la base de datos.
   *
   * @param name
   * @return
   * @throws SQLException
   */
  public static List<Responsable> obtenerResponsableNombre(String name) {
    ObservableList<Responsable> responsables
        = FXCollections.observableArrayList();
    Responsable re = null;
    try (Connection conexion = ConnectionToDb.conectar(USUARIO, PASS, DB, HOST);
        PreparedStatement st = conexion.prepareStatement("select * from responsable"
            + " where nombre = ?");) {
      st.setString(1, name);

      try (ResultSet resultadoQuery = st.executeQuery();) {
        while (resultadoQuery.next()) {
          re = cargarDatosResponsable(resultadoQuery, re);
          responsables.add(re);
        }
      }
    } catch (SQLException ex) {
      Logger.getLogger(ResponsableDAO.class.getName()).log(Level.SEVERE,
          null, ex);
    }
    return responsables;
  }

  /**
   * Obtiene un objeto de tipo Responsable registrado en la base de datos
   * mediante su numPersonal.
   *
   * @param numPersonal
   * @return
   */
  public static Responsable obtenerResponsableNumPersonal(String numPersonal) {
    Responsable re = null;
    try (Connection conexion = ConnectionToDb.conectar(USUARIO, PASS, DB, HOST);
        PreparedStatement st = conexion.prepareStatement("select * from responsable where "
            + "numPersonal = ?");) {

      st.setString(1, numPersonal);
      try (ResultSet resultadoQuery = st.executeQuery();) {

        while (resultadoQuery.next()) {
          re = cargarDatosResponsable(resultadoQuery, re);
        }
      }

    } catch (SQLException ex) {
      Logger.getLogger(ResponsableDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return re;
  }

  /**
   * Se agrega un nuevo objeto de tipo Responsable a la base de datos.
   *
   * @param responsable
   * @throws SQLException
   */
  public static void agregarResponsable(Responsable responsable) {
    try (Connection conexion = ConnectionToDb.conectar(USUARIO, PASS, DB, HOST);
        PreparedStatement stp = conexion.prepareStatement("insert into responsable "
            + "values(?,?,?,?,?)");) {
      stp.setString(1, responsable.getNumPersonal());
      stp.setString(2, responsable.getNombre());
      stp.setString(3, responsable.getTelefono());
      stp.setString(4, responsable.getExtension());
      stp.setString(5, responsable.getCorreo());
      stp.executeUpdate();
    } catch (SQLException ex) {
      Logger.getLogger(ResponsableDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  /**
   * Se edita un objeto de tipo Responsable que esté registrado en la base de
   * datos.
   *
   * @param responsable
   * @throws SQLException
   */
  public static void editarResponsable(Responsable responsable) {
    try (Connection conexion = ConnectionToDb.conectar(USUARIO, PASS, DB, HOST);
        PreparedStatement st = conexion.prepareStatement("UPDATE Responsable SET nombre = ?, "
            + "telefono = ?, extension = ?, correo = ? where numPersonal = ?");) {

      st.setString(1, responsable.getNombre());
      st.setString(2, responsable.getTelefono());
      st.setString(3, responsable.getExtension());
      st.setString(4, responsable.getCorreo());
      st.setString(5, responsable.getNumPersonal());
      st.executeUpdate();
    } catch (SQLException ex) {
      Logger.getLogger(ResponsableDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  /**
   * Se elimina un objeto de tipo Responsable de la base de datos.
   *
   * @param responsable
   * @throws SQLException
   */
  public static void eliminarResponsable(Responsable responsable) {
    try (Connection conexion = ConnectionToDb.conectar(USUARIO, PASS, DB, HOST);
        PreparedStatement st = conexion.prepareStatement("delete responsable from responsable "
            + "where numPersonal = ?");) {
      st.setString(1, responsable.getNumPersonal());
      st.executeUpdate();
    } catch (SQLException ex) {
      Logger.getLogger(ResponsableDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
  private static Responsable cargarDatosResponsable(ResultSet resultadoQuery, Responsable re) throws SQLException {
    String numPersonal = resultadoQuery.getString("numPersonal");
        String nombre = (resultadoQuery.getString("nombre"));
        String telefono = (resultadoQuery.getString("telefono"));
        String extension = (resultadoQuery.getString("extension"));
        String correo = (resultadoQuery.getString("correo"));
        re = new Responsable(numPersonal, nombre, telefono, extension, correo);
        return re;
  }
}