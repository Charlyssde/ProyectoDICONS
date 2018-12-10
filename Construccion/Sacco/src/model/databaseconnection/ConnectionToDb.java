/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.databaseConection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author texch
 */
public class ConnectionToDb {
    
    
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
	      sqe.printStackTrace();
	    } catch (ClassNotFoundException e) {
	      e.printStackTrace();
	    }
	    return res;
	  }
	
    
}
