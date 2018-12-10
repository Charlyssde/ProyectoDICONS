/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.databaseconnection;

import controller.LoginPrincipalController;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author texch
 */
public class ConnectionToDb {

  private ConnectionToDb() {

  }

  static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
  static final String DB_URL = "jdbc:mysql://";

  public static Connection conectar(String usuario, String pass, String bd, String host) {
    Connection res = null;
    try {
      // Registrar JDBC driver
      Class.forName(JDBC_DRIVER);
      String url = DB_URL + host + '/' + bd;
      res = DriverManager.getConnection(url, usuario, pass);
    } catch (SQLException sqe) {
      //
    } catch (ClassNotFoundException e) {
      Logger.getLogger(LoginPrincipalController.class.getName()).log(Level.SEVERE, null, e);
    }
    return res;
  }

}
