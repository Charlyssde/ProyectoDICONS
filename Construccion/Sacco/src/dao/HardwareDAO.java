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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Area;
import model.Hardware;
import model.databaseconnection.ConnectionToDb;

/**
 *
 * @author texch
 */
public class HardwareDAO {
  private static final String USUARIO = "root";
  private static final String PASS = "2580";
  private static final String DB = "sacco";
  private static final String HOST = "localhost";
  
   private static ObservableList<Hardware> hardwares = null;
   private static Hardware hw;

  private HardwareDAO() {
    // Nothing
  }

  /**
   * Se obtienen todos los registros de hardware que estan dentro de la base de
   * datos.
   *
   * @return
   */
  public static List<Hardware> obtenerAllHardware() {
    hardwares = FXCollections.observableArrayList();
    try (Connection conexion = ConnectionToDb.conectar(USUARIO, PASS, DB, HOST);
        PreparedStatement st = conexion.prepareStatement("select * from hardware");
        ResultSet resultadoQuery = st.executeQuery();) {
      while (resultadoQuery.next()) {
        cargarDatosHardware(conexion, resultadoQuery);
        hardwares.add(hw);
      }
    } catch (SQLException ex) {
      Logger.getLogger(JefeCentroComputoDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return hardwares;
  }

  /**
   * Se agrega un nuevo objeto de tipo hardware en la base de datos.
   *
   * @param hardware
   */
  public static void agregarHardware(Hardware hardware) {
    try (Connection conexion = ConnectionToDb.conectar(USUARIO, PASS, DB, HOST);
        PreparedStatement stp
        = conexion.prepareStatement("insert into hardware values(default,?,?,?,?,?,?)");) {
      stp.setString(1, hardware.getMarca());
      stp.setString(2, hardware.getModelo());
      stp.setString(3, hardware.getNumSerie());
      stp.setString(4, hardware.getEstado());
      stp.setString(5, hardware.getTipo());
      stp.setInt(6, hardware.getUbicacion().getIdUbicacion());
      stp.executeUpdate();
    } catch (SQLException ex) {
      Logger.getLogger(HardwareDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  /**
   * Se obtienen todos los objetos de tipo hardware de acuerdo con la marca que
   * esta registrada.
   *
   * @param criterio
   * @return
   */
  public static List<Hardware> obtenerHardwareMarca(String criterio) {
    hardwares = FXCollections.observableArrayList();
    try (Connection conexion = ConnectionToDb.conectar(USUARIO, PASS, DB, HOST);
        PreparedStatement st
        = conexion.prepareStatement("select * from hardware where marca = ?");) {
      st.setString(1, criterio);
      ejecutarQuery(conexion, st);
    } catch (SQLException ex) {
      Logger.getLogger(HardwareDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return hardwares;
  }

  /**
   * Se obtienen todos los objetos de tipo hardware de acuerdo con el estado que
   * esta registrado con el.
   *
   * @param criterio
   * @return
   * @throws SQLException
   */
  public static List<Hardware> obtenerHardwareEstado(String criterio) {
    hardwares = FXCollections.observableArrayList();
    try (Connection conexion = ConnectionToDb.conectar(USUARIO, PASS, DB, HOST);
        PreparedStatement st
        = conexion.prepareStatement("select * from hardware where estado = ?");) {
      st.setString(1, criterio);
      ejecutarQuery(conexion, st);
    } catch (SQLException ex) {
      Logger.getLogger(HardwareDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return hardwares;
  }

  /**
   * Se edita un objeto de tipo hardware que esté registrado dentro de la base
   * de datos(no se puede editar el numInventario).
   *
   * @param nuevo
   */
  public static void editarHardware(Hardware nuevo) {
    try (Connection conexion = ConnectionToDb.conectar(USUARIO, PASS, DB, HOST);
        PreparedStatement st
        = conexion.prepareStatement("UPDATE hardware SET marca = ?, modelo = ?, "
            + "numSerie = ?, tipo = ?, idubicacion = ? where numInventario = ?");) {
      st.setString(1, nuevo.getMarca());
      st.setString(2, nuevo.getModelo());
      st.setString(3, nuevo.getNumSerie());
      st.setString(4, nuevo.getTipo());
      st.setInt(5, nuevo.getUbicacion().getIdUbicacion());
      st.setInt(6, nuevo.getNumInventario());
      st.executeUpdate();
    } catch (SQLException ex) {
      Logger.getLogger(HardwareDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  /**
   * Se edita las propiedades de un objeto de tipo hardware que se encuentre en
   * mantenimiento.
   *
   * @param nuevo
   */
  public static void editarHardwareFromManten(Hardware nuevo) {
    try (Connection conexion = ConnectionToDb.conectar(USUARIO, PASS, DB, HOST);
        PreparedStatement st
        = conexion.prepareStatement("UPDATE hardware SET estado = ? where numInventario = ?");) {
      st.setString(1, nuevo.getEstado());
      st.setInt(2, nuevo.getNumInventario());
      st.executeUpdate();
    } catch (SQLException ex) {
      Logger.getLogger(HardwareDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  /**
   * Se obtiene un objeto de tipo hardware de acuerdo con el atributo
   * numInventario.
   *
   * @param numero
   * @return
   * @throws SQLException
   */
  public static Hardware obtenerHardwareNumInv(Integer numero) throws SQLException {
    hardwares = FXCollections.observableArrayList();
    try (Connection conexion = ConnectionToDb.conectar(USUARIO, PASS, DB, HOST);
        PreparedStatement st
        = conexion.prepareStatement("select * from hardware where numInventario = ?");) {
      st.setInt(1, numero);
      try (ResultSet resultadoQuery = st.executeQuery();) { 
        while (resultadoQuery.next()) {
        cargarDatosHardware(conexion, resultadoQuery);
        }
        }
      } catch (SQLException ex) {
      Logger.getLogger(HardwareDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return hw;
  }

  /**
   * Se elimina un objeto de tipo hardware que se seleccionó en la GUI de la
   * base de datos.
   *
   * @param seleccionado
   */
  public static void eliminarHardware(Hardware seleccionado) {
    try (Connection conexion = ConnectionToDb.conectar(USUARIO, PASS, DB, HOST);
        PreparedStatement st
        = conexion.prepareStatement("delete hardware from hardware where numInventario = ?");) {
      st.setInt(1, seleccionado.getNumInventario());
      st.executeUpdate();
    } catch (SQLException ex) {
      Logger.getLogger(HardwareDAO.class.getName()).log(Level.SEVERE, null, ex);
    }

  }
  
  public static void cargarAreas(Connection conexion, Integer area){
              try (PreparedStatement st2
              = conexion.prepareStatement("select * from ubicacion where idubicacion = ?");) {
            st2.setInt(1, area);
            try (ResultSet resultadoQuery2 = st2.executeQuery();) {
              while (resultadoQuery2.next()) {
                int idubicacion = resultadoQuery2.getInt("idubicacion");
                String edificio = resultadoQuery2.getString("edificio");
                String uso = resultadoQuery2.getString("uso");
                Area ubicacion = new Area(idubicacion, edificio, uso);
                hw.setUbicacion(ubicacion);
              }
            }

          } catch (SQLException ex) {
      Logger.getLogger(HardwareDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  private static void cargarDatosHardware(Connection conexion, 
      ResultSet resultadoQuery) throws SQLException {
          Integer numInv = resultadoQuery.getInt("numInventario");
          String marca = (resultadoQuery.getString("marca"));
          String modelo = (resultadoQuery.getString("modelo"));
          String numSerie = (resultadoQuery.getString("numSerie"));
          String estado = (resultadoQuery.getString("estado"));
          String tipo = (resultadoQuery.getString("tipo"));
          Integer area = resultadoQuery.getInt("idubicacion");
          hw = new Hardware(numInv, marca, modelo, numSerie, estado, tipo);
          cargarAreas(conexion, area);
  }
  
  private static void ejecutarQuery(Connection conexion, PreparedStatement st){
    try (ResultSet resultadoQuery = st.executeQuery();) {
        while (resultadoQuery.next()) {
          cargarDatosHardware(conexion, resultadoQuery);
          hardwares.add(hw);
        }
      } catch (SQLException ex) {
        Logger.getLogger(JefeCentroComputoDAO.class.getName()).log(Level.SEVERE, null, ex);
      }
  }

}
