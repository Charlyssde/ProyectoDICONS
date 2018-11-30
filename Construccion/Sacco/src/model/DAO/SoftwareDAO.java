/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Software;
import model.databaseConection.ConnectionToDb;

/**
 *
 * @author texch
 */
public class SoftwareDAO {
  
  public static List<Software> obtenerAllSoftware() throws SQLException{
    ObservableList<Software> softwares = FXCollections.observableArrayList();
    Software soft = null;
    Connection conexion = null;
    conexion = ConnectionToDb.conectar("root","2580","sacco","localhost");
          Statement st = null;
          st = conexion.createStatement();
          ResultSet resultadoQuery = st.executeQuery("select * from software");
        try{
          while(resultadoQuery.next()){
              String numInv = resultadoQuery.getString("numInventario");
	      String nombre = (resultadoQuery.getString("nombre"));
              Integer numLicencias = (resultadoQuery.getInt("numLicencias"));
              Date fechaAdquisicion = (resultadoQuery.getDate("fechaAdquisicion"));
              String observaciones = (resultadoQuery.getString("observaciones"));
              soft = new Software(numInv, nombre, numLicencias, observaciones, fechaAdquisicion);
              softwares.add(soft);
          }       
      } catch (SQLException ex) {
          Logger.getLogger(JefeCentroComputoDAO.class.getName()).log(Level.SEVERE, null, ex);
      } finally{
          if(conexion != null){
              conexion.close();
          }
      }
    return softwares;
  }
 
public static List<Software> obtenerSoftwareNombre(String criterio){
    return null;
  
}
public static List<Software> obtenerSoftwareNumLicencias(int criterio){
    return null;
  
}
public static void editarSoftware(Software nuevo){
  
} 
public static void agregarSoftware(Software nuevo){
  
}
public static void eliminarSoftware(Software seleccionado){
  
}
    
}
