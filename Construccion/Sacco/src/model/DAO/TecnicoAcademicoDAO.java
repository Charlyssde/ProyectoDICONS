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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.TecnicoAcademico;
import model.databaseConection.ConnectionToDb;

/**
 *
 * @author texch
 */
public class TecnicoAcademicoDAO {
  
  /**
   * Se obtiene un objeto de tipo TecnicoAcademico mediante los criterios 
   * ingresados por el usuario.
   * 
   * @param ta
   * @return
   * @throws SQLException 
   */  
  public static TecnicoAcademico obtenerTecnico(TecnicoAcademico ta) throws SQLException {
    TecnicoAcademico academico = null;
    Connection conexion = null;

    try {
      conexion = ConnectionToDb.conectar("root", "2580", "sacco", "localhost");
      PreparedStatement st = null;
      st = conexion.prepareStatement("select * from tecnicoacademico "
          + "WHERE numPersonal = ? AND password = ?");
      st.setString(1, ta.getNumPersonal());
      st.setString(2, ta.getPassword());
      ResultSet resultadoQuery = st.executeQuery();

      while (resultadoQuery.next()) {
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
    } finally {
      if (conexion != null) {
        conexion.close();
      }
    }
    return academico;
  }

  /**
   * Se guarda un objeto de tipo TecnicoAcademico dentro de la base de datos.
   * 
   * @param tecnico
   * @throws SQLException 
   */
  public static void guardarTecnico(TecnicoAcademico tecnico) throws SQLException {
    Connection conexion = null;
    try {
      conexion = ConnectionToDb.conectar("root", "2580", "sacco", "localhost");
      PreparedStatement st = null;
      st = conexion.prepareStatement("insert into tecnicoacademico values(?,?,?,?,?,?)");
      st.setString(1, tecnico.getNumPersonal());
      st.setString(2, tecnico.getNombre());
      st.setString(3, tecnico.getPassword());
      st.setString(4, tecnico.getTelefono());
      st.setString(5, tecnico.getExtension());
      st.setString(6, tecnico.getCorreoInstitucional());
      st.executeUpdate();

    } catch (SQLException ex) {
      Logger.getLogger(JefeCentroComputoDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      conexion.close();
    }
  }

  /**
   * Se obtienen todos los objetos de tipo TecnicoAcademico de la base de datos.
   * 
   * @return
   * @throws SQLException 
   */
  public static List<TecnicoAcademico> obtenerAllTecnicos() throws SQLException {
    ObservableList<TecnicoAcademico> academicos = FXCollections.observableArrayList();
    TecnicoAcademico academico;
    Connection conexion = null;

    try {
      conexion = ConnectionToDb.conectar("root", "2580", "sacco", "localhost");
      PreparedStatement st = null;
      st = conexion.prepareStatement("select * from tecnicoacademico");
      ResultSet resultadoQuery = st.executeQuery();

      while (resultadoQuery.next()) {
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
    } finally {
      if (conexion != null) {
        conexion.close();
      }
    }
    return academicos;
  }

  /**
   * Se obtienen objetos de tipo TecnicoAcademico de la base de datos
   * mediante el criterio ingresado por el usuario (nombre).
   * 
   * @param criterio
   * @return 
   */
  public static List<TecnicoAcademico> obtenerTecnicoAcademicoNombre
        (String criterio) throws SQLException {
    ObservableList<TecnicoAcademico> tecnicos = 
            FXCollections.observableArrayList();
    TecnicoAcademico ta = null;
    Connection conexion = null;
    conexion = ConnectionToDb.conectar("root", "2580", "sacco", "localhost");
    PreparedStatement st = null;
    st = conexion.prepareStatement("select * from tecnicoacademico"
      + " where nombre = ?");
    st.setString(1, criterio);
    ResultSet resultadoQuery = st.executeQuery();
    try {
      while (resultadoQuery.next()) {
        String numPersonal = (resultadoQuery.getString("numPersonal"));
        String nombre = (resultadoQuery.getString("nombre"));
        String password = (resultadoQuery.getString("password"));
        String telefono = (resultadoQuery.getString("telefono"));
        String extension = (resultadoQuery.getString("extension"));
        String correo = (resultadoQuery.getString("correo"));
        
        ta = new TecnicoAcademico(numPersonal, nombre, password, telefono,
                extension, correo);
        tecnicos.add(ta);  
      }
    } catch (SQLException ex) {
      Logger.getLogger(JefeCentroComputoDAO.class.getName()).log(Level.SEVERE,
        null, ex);
    } finally {
      conexion.close();
    }
    return tecnicos;

  }

  /**
   * Se edita un objeto de tipo TecnicoAcademico que esté en la base de datos.
   * 
   * @param nuevo
   * @throws SQLException 
   */
  public static void editarTecnicoAcademico(TecnicoAcademico nuevo) throws SQLException {
     Connection conexion;
    conexion = ConnectionToDb.conectar("root", "2580", "sacco", "localhost");
    PreparedStatement st;
    st = conexion.prepareStatement("UPDATE tecnicoacademico SET nombre = ?, "
            + "password = ?, telefono = ?, extension = ?, correoInstitucional = ? "
            + " where numPersonal = ?");
    st.setString(1, nuevo.getNombre());
    st.setString(2, nuevo.getPassword());
    st.setString(3, nuevo.getTelefono());
    st.setString(4, nuevo.getExtension());
    st.setString(5, nuevo.getCorreoInstitucional());
    st.setString(6, nuevo.getNumPersonal());
    st.executeUpdate();
  }

  /**
   * Se elimina un objeto de tipo TecnicoAcademico de la base datos.
   * @param seleccionado
   * @throws SQLException 
   */
  public static void eliminarTecnicoAcademico(TecnicoAcademico seleccionado) throws SQLException {
        Connection conexion = null;
    conexion = ConnectionToDb.conectar("root", "2580", "sacco", "localhost");
    PreparedStatement st = null;
    st = conexion.prepareStatement("delete tecnicoAcademico from tecnicoAcademico where numPersonal = ?");
    st.setString(1, seleccionado.getNumPersonal());
    st.executeUpdate();
  }
}
