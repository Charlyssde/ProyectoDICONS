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
import model.Area;
import model.Hardware;
import model.databaseConection.ConnectionToDb;

/**
 *
 * @author texch
 */
public class HardwareDAO {

  public static List<Hardware> obtenerAllHardware() throws SQLException {
    ObservableList<Hardware> hardwares = FXCollections.observableArrayList();
    Hardware hw = null;
    Connection conexion = null;
    conexion = ConnectionToDb.conectar("root", "2580", "sacco", "localhost");
    Statement st = null;
    st = conexion.createStatement();
    ResultSet resultadoQuery = st.executeQuery("select * from hardware");
    try {
      while (resultadoQuery.next()) {
        Integer numInv = resultadoQuery.getInt("numInventario");
        String marca = (resultadoQuery.getString("marca"));
        String modelo = (resultadoQuery.getString("modelo"));
        String numSerie = (resultadoQuery.getString("numSerie"));
        String estado = (resultadoQuery.getString("estado"));
        String tipo = (resultadoQuery.getString("tipo"));
        int area = resultadoQuery.getInt("idubicacion");
        hw = new Hardware(numInv, marca, modelo, numSerie, estado, tipo);
        PreparedStatement st2 = null;
        st2 = conexion.prepareStatement("select * from ubicacion where idubicacion = ?");
        st2.setInt(1, area);
        ResultSet resultadoQuery2 = st2.executeQuery();
        while (resultadoQuery2.next()) {
          int idubicacion = resultadoQuery2.getInt("idubicacion");
          String edificio = resultadoQuery2.getString("edificio");
          String uso = resultadoQuery2.getString("uso");
          Area ubicacion = new Area(idubicacion, edificio, uso);
          hw.setUbicacion(ubicacion);
        }

        hardwares.add(hw);
      }
    } catch (SQLException ex) {
      Logger.getLogger(JefeCentroComputoDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      if (conexion != null) {
        conexion.close();
      }
    }
    return hardwares;
  }

  public static void agregarHardware(Hardware hardware) throws SQLException {
    Connection conexion = null;
    PreparedStatement stp = null;
    conexion = ConnectionToDb.conectar("root", "2580", "sacco", "localhost");
    stp = conexion.prepareStatement("insert into hardware values(default,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
    //stp.setString(1, hardware.getNumInventario());
    stp.setString(1, hardware.getMarca());
    stp.setString(2, hardware.getModelo());
    stp.setString(3, hardware.getNumSerie());
    stp.setString(4, hardware.getEstado());
    stp.setString(5, hardware.getTipo());
    stp.setInt(6, hardware.getUbicacion().getIdUbicacion());
    stp.executeUpdate();
  }

  public static List<Hardware> obtenerHardwareMarca(String criterio) throws SQLException {
    ObservableList<Hardware> hardwares = FXCollections.observableArrayList();
    Hardware hw = null;
    Connection conexion = null;
    conexion = ConnectionToDb.conectar("root", "2580", "sacco", "localhost");
    PreparedStatement st = null;
    st = conexion.prepareStatement("select * from hardware where marca = ?");
    st.setString(1, criterio);
    ResultSet resultadoQuery = st.executeQuery();
    try {
      while (resultadoQuery.next()) {
        int numInv = resultadoQuery.getInt("numInventario");
        String marca = (resultadoQuery.getString("marca"));
        String modelo = (resultadoQuery.getString("modelo"));
        String numSerie = (resultadoQuery.getString("numSerie"));
        String estado = (resultadoQuery.getString("estado"));
        String tipo = (resultadoQuery.getString("tipo"));
        Integer area = resultadoQuery.getInt("idubicacion");
        hw = new Hardware(numInv, marca, modelo, numSerie, estado, tipo);
        hardwares.add(hw);
        PreparedStatement st2 = null;
        st2 = conexion.prepareStatement("select * from ubicacion where idubicacion = ?");
        st2.setInt(1, area);
        ResultSet resultadoQuery2 = st2.executeQuery();
        while (resultadoQuery2.next()) {
          int idubicacion = resultadoQuery2.getInt("idubicacion");
          String edificio = resultadoQuery2.getString("edificio");
          String uso = resultadoQuery2.getString("uso");
          Area ubicacion = new Area(idubicacion, edificio, uso);
          hw.setUbicacion(ubicacion);
        }
      }
    } catch (SQLException ex) {
      Logger.getLogger(JefeCentroComputoDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      conexion.close();
    }
    return hardwares;
  }

  public static List<Hardware> obtenerHardwareEstado(String criterio) throws SQLException {
    ObservableList<Hardware> hardwares = FXCollections.observableArrayList();
    Hardware hw = null;
    Connection conexion = null;
    conexion = ConnectionToDb.conectar("root", "2580", "sacco", "localhost");
    PreparedStatement st = null;
    st = conexion.prepareStatement("select * from hardware where estado = ?");
    st.setString(1, criterio);
    ResultSet resultadoQuery = st.executeQuery();
    while (resultadoQuery.next()) {
      Integer numInv = resultadoQuery.getInt("numInventario");
      String marca = (resultadoQuery.getString("marca"));
      String modelo = (resultadoQuery.getString("modelo"));
      String numSerie = (resultadoQuery.getString("numSerie"));
      String estado = (resultadoQuery.getString("estado"));
      String tipo = (resultadoQuery.getString("tipo"));
      Integer area = resultadoQuery.getInt("idubicacion");
      hw = new Hardware(numInv, marca, modelo, numSerie, estado, tipo);
      hardwares.add(hw);

      PreparedStatement st2 = null;
      st2 = conexion.prepareStatement("select * from ubicacion where idubicacion = ?");
      st2.setInt(1, area);
      ResultSet resultadoQuery2 = st2.executeQuery();
      while (resultadoQuery2.next()) {
        int idubicacion = resultadoQuery2.getInt("idubicacion");
        String edificio = resultadoQuery2.getString("edificio");
        String uso = resultadoQuery2.getString("uso");
        Area ubicacion = new Area(idubicacion, edificio, uso);
        hw.setUbicacion(ubicacion);
      }
    }
    conexion.close();
    return hardwares;
  }

  public static void editarHardware(Hardware nuevo) throws SQLException {
    Connection conexion;
    conexion = ConnectionToDb.conectar("root", "2580", "sacco", "localhost");
    PreparedStatement st;
    st = conexion.prepareStatement("UPDATE hardware SET marca = ?, modelo = ?, "
        + "numSerie = ?, tipo = ?, idubicacion = ? where numInventario = ?");
    st.setString(1, nuevo.getMarca());
    st.setString(2, nuevo.getModelo());
    st.setString(3, nuevo.getNumSerie());
    st.setString(4, nuevo.getTipo());
    st.setInt(5, nuevo.getUbicacion().getIdUbicacion());
    st.setInt(6, nuevo.getNumInventario());
    st.executeUpdate();
  }

  public static void editarHardwareFromManten(Hardware nuevo) throws SQLException {
    Connection conexion;
    conexion = ConnectionToDb.conectar("root", "2580", "sacco", "localhost");
    PreparedStatement st;
    st = conexion.prepareStatement("UPDATE hardware SET estado = ? where numInventario = ?");
    st.setString(1, nuevo.getEstado());
    st.setInt(2, nuevo.getNumInventario());
    st.executeUpdate();
  }

  public static Hardware obtenerHardwareNumInv(Integer numero) throws SQLException {
    Hardware hw = null;
    Connection conexion = null;
    conexion = ConnectionToDb.conectar("root", "2580", "sacco", "localhost");
    PreparedStatement st = null;
    st = conexion.prepareStatement("select * from hardware where numInventario = ?");
    st.setInt(1, numero);
    ResultSet resultadoQuery = st.executeQuery();
    while (resultadoQuery.next()) {
      Integer numInv = resultadoQuery.getInt("numInventario");
      String marca = (resultadoQuery.getString("marca"));
      String modelo = (resultadoQuery.getString("modelo"));
      String numSerie = (resultadoQuery.getString("numSerie"));
      String estado = (resultadoQuery.getString("estado"));
      String tipo = (resultadoQuery.getString("tipo"));
      Integer area = resultadoQuery.getInt("idubicacion");
      hw = new Hardware(numInv, marca, modelo, numSerie, estado, tipo);

      PreparedStatement st2 = null;
      st2 = conexion.prepareStatement("select * from ubicacion where idubicacion = ?");
      st2.setInt(1, area);
      ResultSet resultadoQuery2 = st2.executeQuery();
      while (resultadoQuery2.next()) {
        int idubicacion = resultadoQuery2.getInt("idubicacion");
        String edificio = resultadoQuery2.getString("edificio");
        String uso = resultadoQuery2.getString("uso");
        Area ubicacion = new Area(idubicacion, edificio, uso);
        hw.setUbicacion(ubicacion);
      }
    }
    conexion.close();
    return hw;
  }

  public static void eliminarHardware(Hardware seleccionado) throws SQLException {
    Connection conexion = null;
    conexion = ConnectionToDb.conectar("root", "2580", "sacco", "localhost");
    PreparedStatement st = null;
    st = conexion.prepareStatement("delete hardware from hardware where numInventario = ?");
    st.setInt(1, seleccionado.getNumInventario());
    st.executeUpdate();

  }

}
