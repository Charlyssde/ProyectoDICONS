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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Area;
import model.databaseConection.ConnectionToDb;

/**
 *
 * @author texch
 */
public class AreaDAO {
/**
 * Se obtiene la ubicacion mediante un parametro dado por el usuario de tipo 
 * entero.
 * 
 * @param id
 * @return
 * @throws SQLException 
 */
  public static Area obtenerUbicacion(int id) throws SQLException {
    Area area = null;
    Connection conexion = null;
    conexion = ConnectionToDb.conectar("root", "2580", "sacco", "localhost");
    PreparedStatement st = null;
    st = conexion.prepareStatement("select * from ubicacion where idubicacion = ?");
    st.setInt(1, id);
    ResultSet resultadoQuery = st.executeQuery();
    while (resultadoQuery.next()) {
      int ubicacion = resultadoQuery.getInt("idubicacion");
      String edificio = resultadoQuery.getString("edificio");
      String uso = resultadoQuery.getString("uso");
      area = new Area(ubicacion, edificio, uso);
    }

    conexion.close();
    return area;

  }
/**
 * Se obtienen todas las areas registradas en la base de datos.
 * 
 * @return
 * @throws SQLException 
 */
  public static List<Area> obtenerAllAreas() throws SQLException {
    ObservableList<Area> listArea = FXCollections.observableArrayList();
    Area area = null;
    Connection conexion = null;
    conexion = ConnectionToDb.conectar("root", "2580", "sacco", "localhost");
    Statement st = null;
    st = conexion.createStatement();
    ResultSet resultadoQuery = st.executeQuery("select * from ubicacion");

    while (resultadoQuery.next()) {
      Integer idArea = resultadoQuery.getInt("idubicacion");
      String edificio = resultadoQuery.getString("edificio");
      String uso = resultadoQuery.getString("uso");
      area = new Area(idArea, edificio, uso);
      listArea.add(area);
    }
    conexion.close();
    return listArea;
  }

}
