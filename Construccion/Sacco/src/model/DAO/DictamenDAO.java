/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import model.Dictamen;
import model.databaseConection.ConnectionToDb;

/**
 *
 * @author texch
 */
public class DictamenDAO {
/**
 * Se guarda un nuevo objeto de tipo Dictamen en la base de datos.
 * 
 * @param dictamen
 * @throws SQLException 
 */
  public static void guardarDictamen(Dictamen dictamen) throws SQLException {
    Connection conexion = null;
    PreparedStatement stp = null;
    conexion = ConnectionToDb.conectar("root", "2580", "sacco", "localhost");
    stp = conexion.prepareStatement("insert into dictamen values(default,?,?,?,?,?,?)");
    stp.setString(1, dictamen.getTipo());
    stp.setString(2, dictamen.getObservaciones());
    stp.setString(3, dictamen.getDescripcion());
    stp.setString(4, dictamen.getTecnico().getNumPersonal());
    stp.setString(5, dictamen.getSolicitante().getNumPersonal());
    stp.setInt(6, dictamen.getEquipo().getNumInventario());

    stp.executeUpdate();
  }

}
