/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Dictamen;
import model.databaseconnection.ConnectionToDb;

/**
 *
 * @author texch
 */
public class DictamenDAO {
  
  private static final String USUARIO = "root";
  private static final String PASS = "2580";
  private static final String DB = "sacco";
  private static final String HOST = "localhost";

  private DictamenDAO() {
    // Nothing
  }

  /**
   * Se guarda un nuevo objeto de tipo Dictamen en la base de datos.
   *
   * @param dictamen el dictamen a guardar
   */
  public static void guardarDictamen(Dictamen dictamen) {
    try {
      Connection conexion;
      conexion = ConnectionToDb.conectar(USUARIO, PASS, DB, HOST);
      try (PreparedStatement stp = conexion.prepareStatement("insert into dictamen values(default,?,?,?,?,?,?)");) {
        stp.setString(1, dictamen.getTipo());
        stp.setString(2, dictamen.getObservaciones());
        stp.setString(3, dictamen.getDescripcion());
        stp.setString(4, dictamen.getTecnico().getNumPersonal());
        stp.setString(5, dictamen.getSolicitante().getNumPersonal());
        stp.setInt(6, dictamen.getEquipo().getNumInventario());
        stp.executeUpdate();
      }
    } catch (SQLException ex) {
      Logger.getLogger(DictamenDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

}
