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
import model.Area;
import model.databaseconnection.ConnectionToDb;

/**
 *
 * @author texch
 */
public class AreaDAO {

  private AreaDAO() {
    // Nothing
  }

  
  /**
   * Se obtiene la ubicacion mediante un parametro dado por el usuario de tipo
   * entero.
   *
   * @param id
   * @return
   * @throws SQLException
   */
  public static Area obtenerUbicacion(int id) {
    Area area = null;
    try {

      Connection conexion;

      conexion = ConnectionToDb.conectar("root", "2580", "sacco", "localhost");
      try (PreparedStatement st = conexion.prepareStatement("select * from ubicacion where idubicacion = ?");) {
        st.setInt(1, id);
        try (ResultSet resultadoQuery = st.executeQuery()) {
          while (resultadoQuery.next()) {
            int ubicacion = resultadoQuery.getInt("idubicacion");
            String edificio = resultadoQuery.getString("edificio");
            String uso = resultadoQuery.getString("uso");
            area = new Area(ubicacion, edificio, uso);
          }
        }
      }
    } catch (SQLException ex) {
      Logger.getLogger(AreaDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return area;
  }

  /**
   * Se obtienen todas las areas registradas en la base de datos.
   *
   * @return
   */
  public static List<Area> obtenerAllAreas() {
    ObservableList<Area> listArea = FXCollections.observableArrayList();
    try {
      Area area;
      Connection conexion;
      conexion = ConnectionToDb.conectar("root", "2580", "sacco", "localhost");
      try (Statement st = conexion.createStatement();
          ResultSet resultadoQuery = st.executeQuery("select * from ubicacion")) {
        while (resultadoQuery.next()) {
          Integer idArea = resultadoQuery.getInt("idubicacion");
          String edificio = resultadoQuery.getString("edificio");
          String uso = resultadoQuery.getString("uso");
          area = new Area(idArea, edificio, uso);
          listArea.add(area);
        }
      }
    } catch (SQLException ex) {
      Logger.getLogger(AreaDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return listArea;
  }

}
