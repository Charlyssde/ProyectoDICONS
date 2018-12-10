/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.JefeCentroComputo;
import model.databaseconnection.ConnectionToDb;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author texch
 */
public class JefeCentroComputoDAO {
  private static final String USUARIO = "root";
  private static final String PASS = "2580";
  private static final String DB = "sacco";
  private static final String HOST = "localhost";

  private JefeCentroComputoDAO() {
    // Nothing
  }

  /**
   * Se obtiene un objeto de tipo JefeCentroComputo de la base de datos de
   * acuerdo con los criterios ingresados por el usuario. Despues inicia sesion
   * en el sistema si sus criterios estan registrados en la base de datos.
   *
   * @param datos
   * @return jcc el objeto con los datos del jefe del centro de computo.
   */
  public static JefeCentroComputo obtenerJefeCc(JefeCentroComputo datos) {
    JefeCentroComputo jcc = null;
    try (Connection conexion = ConnectionToDb.conectar(USUARIO, PASS, DB, HOST);
        PreparedStatement st = conexion.prepareStatement("select * from jefecentrocomputo where "
            + "numPersonal = ? and password = ?");) {
      st.setString(1, datos.getNumPersonal());
      st.setString(2, datos.getPassword());
      try (ResultSet resultadoQuery = st.executeQuery();) {
        if (resultadoQuery.next()) {
          String numP = resultadoQuery.getString("numPersonal");
          String nom = (resultadoQuery.getString("nombre"));
          String pass = (resultadoQuery.getString("password"));
          jcc = new JefeCentroComputo(numP, nom, pass);
        }
      }
    } catch (SQLException ex) {
      Logger.getLogger(JefeCentroComputoDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return jcc;

  }

}
