/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DAO;

import model.JefeCentroComputo;
import model.databaseConection.ConnectionToDb;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author texch
 */
public class JefeCentroComputoDAO {

/**
 * Se obtiene un objeto de tipo JefeCentroComputo de la base de datos de acuerdo
 * con los criterios ingresados por el usuario. Despues inicia sesion en el 
 * sistema si sus criterios estan registrados en la base de datos.
 * 
 * @param datos
 * @return
 * @throws SQLException 
 */
  public static JefeCentroComputo obtenerJefeCc(JefeCentroComputo datos) throws SQLException {
    Connection conexion = null;
    JefeCentroComputo jcc = null;
    try {
      conexion = ConnectionToDb.conectar("root", "2580", "sacco", "localhost");
      PreparedStatement st;
      st = conexion.prepareStatement("select * from jefecentrocomputo where numPersonal = ? and password = ?");
      st.setString(1, datos.getNumPersonal());
      st.setString(2, datos.getPassword());
      ResultSet resultadoQuery = st.executeQuery();

      if (resultadoQuery.next()) {
        String numP = resultadoQuery.getString("numPersonal");
        String nom = (resultadoQuery.getString("nombre"));
        String pass = (resultadoQuery.getString("password"));
        jcc = new JefeCentroComputo(numP, nom, pass);
      }
    } catch (SQLException ex) {
      Logger.getLogger(JefeCentroComputoDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      if (conexion != null) {
        conexion.close();
      }
    }
    return jcc;

  }

}
