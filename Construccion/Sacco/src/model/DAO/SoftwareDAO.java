/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Software;
import model.databaseConection.ConnectionToDb;

/**
 *
 * @author texch
 */
public class SoftwareDAO {

  public static List<Software> obtenerAllSoftware() throws SQLException {
    ObservableList<Software> softwares = FXCollections.observableArrayList();
    Software soft = null;
    Connection conexion = null;
    conexion = ConnectionToDb.conectar("root", "2580", "sacco", "localhost");
    Statement st = null;
    st = conexion.createStatement();
    ResultSet resultadoQuery = st.executeQuery("select * from software");
    try {
      while (resultadoQuery.next()) {
        int numInv = resultadoQuery.getInt("numInventario");
        String nombre = (resultadoQuery.getString("nombre"));
        Integer numLicencias = (resultadoQuery.getInt("numLicencias"));
        Date fechaAdquisicion = (resultadoQuery.getDate("fechaAdq"));
        String observaciones = (resultadoQuery.getString("observaciones"));
        String version = resultadoQuery.getString("version");
        soft = new Software(numInv, nombre, numLicencias, fechaAdquisicion, observaciones, version);
        softwares.add(soft);
      }
    } catch (SQLException ex) {
      Logger.getLogger(JefeCentroComputoDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      if (conexion != null) {
        conexion.close();
      }
    }
    return softwares;
  }

  public static List<Software> obtenerSoftwareNombre(String criterio) throws SQLException {
    ObservableList<Software> lista = FXCollections.observableArrayList();
    Connection conexion;
    Software software;
    conexion = ConnectionToDb.conectar("root", "2580", "sacco", "localhost");
    PreparedStatement st;
    st = conexion.prepareStatement("select * from software where nombre = ?");
    st.setString(1, criterio);
    ResultSet resultadoQuery = st.executeQuery();

    while (resultadoQuery.next()) {
      Integer numInventario = resultadoQuery.getInt("numInventario");
      String nombre = resultadoQuery.getString("nombre");
      Integer numLicencias = resultadoQuery.getInt("numLicencias");
      Date fechaAdq = resultadoQuery.getDate("fechaAdq");
      String version = resultadoQuery.getString("version");
      String observaciones = resultadoQuery.getString("observaciones");
      software = new Software(numInventario, nombre, numLicencias, fechaAdq, version, observaciones);
      lista.add(software);
    }

    return lista;

  }

  public static void editarSoftware(Software nuevo) throws SQLException {
    Connection conexion;
    PreparedStatement stp;
    conexion = ConnectionToDb.conectar("root", "2580", "sacco", "localhost");
    stp = conexion.prepareStatement("UPDATE software SET nombre = ?, observaciones = ?, version = ?"
        + " WHERE numInventario = ? ");
    stp.setString(1, nuevo.getNombre());
    stp.setString(2, nuevo.getObservaciones());
    stp.setString(3, nuevo.getVersion());
    stp.setInt(4, nuevo.getNumInventario());

    stp.executeUpdate();
  }

  public static void agregarSoftware(Software nuevo) throws SQLException {

    Connection conexion = null;
    PreparedStatement stp = null;
    conexion = ConnectionToDb.conectar("root", "2580", "sacco", "localhost");
    stp = conexion.prepareStatement("insert into software values(default,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
    stp.setString(1, nuevo.getNombre());
    stp.setInt(2, nuevo.getNumLicencias());
    stp.setDate(3, nuevo.getFechaAdquisicion());
    stp.setString(4, nuevo.getObservaciones());
    stp.setString(5, nuevo.getVersion());

    stp.executeUpdate();
  }

  public static void eliminarSoftware(Software seleccionado) throws SQLException {
    Connection conexion;
    PreparedStatement stp;
    conexion = ConnectionToDb.conectar("root", "2580", "sacco", "localhost");
    stp = conexion.prepareStatement("delete software from software where numInventario = ?");
    stp.setInt(1, seleccionado.getNumInventario());
    stp.executeUpdate();

  }

}
