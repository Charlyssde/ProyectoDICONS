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
import java.util.logging.Level;
import java.util.logging.Logger;
import model.TecnicoAcademico;
import model.databaseConection.ConnectionToDb;

/**
 *
 * @author texch
 */
public class TecnicoAcademicoDAO {

    public static TecnicoAcademico obtenerTecnico(TecnicoAcademico ta) throws SQLException {
      TecnicoAcademico academico = null;
      Connection conexion = null;
      
      try {
          conexion = ConnectionToDb.conectar("root","2580","sacco","localhost");
          PreparedStatement st = null;
          st = conexion.prepareStatement("select * from tecnicoacademico "
              + "WHERE numPersonal = ? AND password = ?");
          st.setString(1, ta.getNumPersonal());
          st.setString(2, ta.getPassword());
          ResultSet resultadoQuery = st.executeQuery();
          
          while(resultadoQuery.next()){
            String numP = resultadoQuery.getString("numPersonal");
	   String nom = (resultadoQuery.getString("nombre"));
            String pass = (resultadoQuery.getString("password"));
            String telefono = resultadoQuery.getString("telefono");
            String extension = resultadoQuery.getString("extension");
            String correo = resultadoQuery.getString("correoInstitucional");
            academico = new TecnicoAcademico(numP, nom, pass, telefono, extension, correo);
            System.out.println(academico.getNumPersonal());
            System.out.println(academico.getPassword());
          }        
      } catch (SQLException ex) {
          Logger.getLogger(JefeCentroComputoDAO.class.getName()).log(Level.SEVERE, null, ex);
      } finally{
          if(conexion != null){
              conexion.close();
          }
      }
      return academico;
    }

  public static void guardarTecnico(TecnicoAcademico tecnico) throws SQLException {
    Connection conexion =  null;
    try {
          conexion = ConnectionToDb.conectar("root","2580","sacco","localhost");
          PreparedStatement st = null;
          st = conexion.prepareStatement("insert into tecnicoacademico values(?,?,?,?,?,?)");
          st.setString(1, tecnico.getNumPersonal());
          st.setString(2, tecnico.getNombre());
          st.setString(3,tecnico.getPassword());
          st.setString(4, tecnico.getTelefono());
          st.setString(5, tecnico.getExtension());
          st.setString(6, tecnico.getCorreoInstitucional());
          st.executeUpdate();
                
      } catch (SQLException ex) {
          Logger.getLogger(JefeCentroComputoDAO.class.getName()).log(Level.SEVERE, null, ex);
      } finally{
              conexion.close();
      }
  }
  
   public static List<TecnicoAcademico> obtenerAllTecnicos() throws SQLException {
      List<TecnicoAcademico> academicos = null;
      TecnicoAcademico academico;
      Connection conexion = null;
      
      try {
          conexion = ConnectionToDb.conectar("root","2580","sacco","localhost");
          PreparedStatement st = null;
          st = conexion.prepareStatement("select * from tecnicoacademico");
          ResultSet resultadoQuery = st.executeQuery();
          
          while(resultadoQuery.next()){
            String numP = resultadoQuery.getString("numPersonal");
	   String nom = (resultadoQuery.getString("nombre"));
            String pass = (resultadoQuery.getString("password"));
            String telefono = resultadoQuery.getString("telefono");
            String extension = resultadoQuery.getString("extension");
            String correo = resultadoQuery.getString("correoInstitucional");
            academico = new TecnicoAcademico(numP, nom, pass, telefono, extension, correo);
            academicos.add(academico);
          }        
      } catch (SQLException ex) {
          Logger.getLogger(JefeCentroComputoDAO.class.getName()).log(Level.SEVERE, null, ex);
      } finally{
          if(conexion != null){
              conexion.close();
          }
      }
      return academicos;
    }
    
public static List<TecnicoAcademico> obtenerTecnicoAcademicoNombre(String criterio){
      return null;
  
}
public static void editarTecnicoAcademico(TecnicoAcademico nuevo){
  
}

public static void agregarTecnicoAcademico(TecnicoAcademico nuevo){
  
}
public static void eliminarTecnicoAcademico(TecnicoAcademico seleccionado){
  
}
}
