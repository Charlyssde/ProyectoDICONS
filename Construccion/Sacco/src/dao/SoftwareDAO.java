/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

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
import model.databaseconnection.ConnectionToDb;

/**
 *
 * @author texch
 */
public class SoftwareDAO {
  
  private static final String USUARIO = "root";
  private static final String PASS = "2580";
  private static final String DB = "sacco";
  private static final String HOST = "localhost";
  
  private SoftwareDAO(){
    // Nothing
  }
  /**
   * Se obtienen todos los objetos de tipo Software registrados en la base de 
   * datos.
   * 
   * @return softwares la lista de los software. 
   */
  public static List<Software> obtenerAllSoftware() {
    ObservableList<Software> softwares = FXCollections.observableArrayList();
    Software soft;
    try ( Connection conexion = ConnectionToDb.conectar(USUARIO, PASS, DB, HOST);
      Statement st = conexion.createStatement();
      ResultSet resultadoQuery = st.executeQuery("select * from software");){

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
    }catch (SQLException ex) {
        Logger.getLogger(JefeCentroComputoDAO.class.getName()).log(Level.SEVERE, null, ex);
      }
    return softwares;
  }
  /**
   * Se obtienen todos los objetos de tipo Software que cumplan con el criterio
   * ingresado por el usuario.
   * 
   * @param criterio el nombre que se desea encontrar el software.
   * @return lista la lista de los software que coinciden con el criterio. 
   */
  public static List<Software> obtenerSoftwareNombre(String criterio) {
    ObservableList<Software> lista = FXCollections.observableArrayList();
    Software software;
    try (Connection conexion = ConnectionToDb.conectar(USUARIO, PASS, DB, HOST);
      PreparedStatement st = conexion.prepareStatement("select * from software where nombre = ?");) {  
      st.setString(1, criterio);
      try (ResultSet resultadoQuery = st.executeQuery();){
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
      }
    } catch (SQLException ex) {
      Logger.getLogger(SoftwareDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return lista;
  }
  
  /**
   * Se edita un objeto de tipo software que esté registrado en la base de 
   * datos.
   * 
   * @param nuevo el objeto con los nuevos datos del software. 
   */
  public static void editarSoftware(Software nuevo) {
    try (
      Connection conexion = ConnectionToDb.conectar(USUARIO, PASS, DB, HOST);
      PreparedStatement stp = 
          conexion.prepareStatement("UPDATE software SET nombre = ?, "
              + "observaciones = ?, version = ?" + " WHERE numInventario = ? ");) {
      stp.setString(1, nuevo.getNombre());
      stp.setString(2, nuevo.getObservaciones());
      stp.setString(3, nuevo.getVersion());
      stp.setInt(4, nuevo.getNumInventario());
      
      stp.executeUpdate();
    } catch (SQLException ex) {
      Logger.getLogger(SoftwareDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  /**
   * Se agrega un nuevo objeto de tipo Software a la base de datos.
   * @param nuevo el objeto con los datos para agregar. 
   */
  public static void agregarSoftware(Software nuevo) {

    try (Connection conexion = ConnectionToDb.conectar(USUARIO, PASS, DB, HOST);
        PreparedStatement stp = 
            conexion.prepareStatement("insert into software values(default,?,?,?,?,?)");) {
      
      stp.setString(1, nuevo.getNombre());
      stp.setInt(2, nuevo.getNumLicencias());
      stp.setDate(3, nuevo.getFechaAdquisicion());
      stp.setString(4, nuevo.getObservaciones());
      stp.setString(5, nuevo.getVersion());
      
      stp.executeUpdate();
    } catch (SQLException ex) {
      Logger.getLogger(SoftwareDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  /**
   * Se elimina un objeto de tipo software de la base de datos.
   * @param seleccionado el elemento que se desea eliminar. 
   */
  public static void eliminarSoftware(Software seleccionado) {
    try (Connection conexion = ConnectionToDb.conectar(USUARIO, PASS, DB, HOST);
      PreparedStatement stp = 
          conexion.prepareStatement("delete software from software where numInventario = ?");) {
      stp.setInt(1, seleccionado.getNumInventario());
      stp.executeUpdate();
    } catch (SQLException ex) {
      Logger.getLogger(SoftwareDAO.class.getName()).log(Level.SEVERE, null, ex);
    }

  }

}
