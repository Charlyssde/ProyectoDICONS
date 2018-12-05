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
import java.util.List;
import model.Responsable;
import model.databaseConection.ConnectionToDb;

/**
 *
 * @author texch
 */
public class ResponsableDAO {

  public static List<Responsable> obtenerAllResponsable() {
    return null;
  }

  public static List<Responsable> obtenerResponsableNombre(String criterio) {
    return null;
  }

  public static Responsable obtenerResponsableNumPersonal(String numPersonal) throws SQLException {
    Responsable responsable = null;
    Connection conexion = null;
    conexion = ConnectionToDb.conectar("root", "2580", "sacco", "localhost");
    PreparedStatement st = null;
    st = conexion.prepareStatement("select * from responsable where numPersonal = ?");
    st.setString(1, numPersonal);
    ResultSet resultadoQuery = st.executeQuery();

    while (resultadoQuery.next()) {
      String numPers = resultadoQuery.getString("numPersonal");
      String nombre = resultadoQuery.getString("nombre");
      String correo = resultadoQuery.getString("correo");
      String telefono = resultadoQuery.getString("telefono");
      String extension = resultadoQuery.getString("extension");

      responsable = new Responsable(numPers, nombre, telefono, extension, correo);
      System.out.println(responsable.getNombre());
    }

    return responsable;
  }

}
