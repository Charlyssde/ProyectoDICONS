/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

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
import model.databaseconnection.ConnectionToDb;

/**
 *
 * @author texch
 */
public class TecnicoAcademicoDAO {

  private static final String USUARIO = "root";
  private static final String PASS = "2580";
  private static final String DB = "sacco";
  private static final String HOST = "localhost";

  private static final String NUMPERSONAL = "numPersonal";
  private static final String NOMBRE = "nombre";
  private static final String TELEFONO = "telefono";
  private static final String PASSW = "password";
  private static final String EXTENSION = "extension";
  private static final String CORREO = "correoInstitucional";

  private TecnicoAcademicoDAO() {
    // Nothing
  }

  
  /**
   * Se obtiene un objeto de tipo TecnicoAcademico mediante los criterios
   * ingresados por el usuario.
   *
   * @param ta los datos con lo que se desea ingresar al sistema.
   * @return academico el objeto con los datos de ta.
   */
  public static TecnicoAcademico obtenerTecnico(TecnicoAcademico ta) {
    TecnicoAcademico academico = null;
    Connection conexion;

    try {
      conexion = ConnectionToDb.conectar(USUARIO, PASS, DB, HOST);
      try (PreparedStatement st = conexion.prepareStatement("select * from tecnicoacademico "
          + "WHERE numPersonal = ? AND password = ?");) {
        st.setString(1, ta.getNumPersonal());
        st.setString(2, ta.getPassword());
        try (ResultSet resultadoQuery = st.executeQuery();) {
          while (resultadoQuery.next()) {
            String numP = resultadoQuery.getString(NUMPERSONAL);
            String nom = (resultadoQuery.getString(NOMBRE));
            String pass = (resultadoQuery.getString(PASSW));
            String telefono = resultadoQuery.getString(TELEFONO);
            String extension = resultadoQuery.getString(EXTENSION);
            String correo = resultadoQuery.getString(CORREO);
            academico = new TecnicoAcademico(numP, nom, pass, telefono, extension, correo);
          }
        }
      }

    } catch (SQLException ex) {
      Logger.getLogger(JefeCentroComputoDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return academico;
  }

  /**
   * Se guarda un objeto de tipo TecnicoAcademico dentro de la base de datos.
   *
   * @param tecnico el tecnico a guardar.
   */
  public static void guardarTecnico(TecnicoAcademico tecnico) {
    try (Connection conexion = ConnectionToDb.conectar(USUARIO, PASS, DB, HOST);
        PreparedStatement st
        = conexion.prepareStatement("insert into tecnicoacademico values(?,?,?,?,?,?)");) {
      st.setString(1, tecnico.getNumPersonal());
      st.setString(2, tecnico.getNombre());
      st.setString(3, tecnico.getPassword());
      st.setString(4, tecnico.getTelefono());
      st.setString(5, tecnico.getExtension());
      st.setString(6, tecnico.getCorreoInstitucional());
      st.executeUpdate();

    } catch (SQLException ex) {
      Logger.getLogger(JefeCentroComputoDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  /**
   * Se obtienen todos los objetos de tipo TecnicoAcademico de la base de datos.
   *
   * @return academicos la lista con los academicos.
   */
  public static List<TecnicoAcademico> obtenerAllTecnicos() {
    ObservableList<TecnicoAcademico> academicos = FXCollections.observableArrayList();
    TecnicoAcademico academico;
    try (Connection conexion = ConnectionToDb.conectar(USUARIO, PASS, DB, HOST);
        PreparedStatement st = conexion.prepareStatement("select * from tecnicoacademico");
        ResultSet resultadoQuery = st.executeQuery();) {
      while (resultadoQuery.next()) {
        String numP = resultadoQuery.getString(NUMPERSONAL);
        String nom = (resultadoQuery.getString(NOMBRE));
        String pass = (resultadoQuery.getString(PASSW));
        String telefono = resultadoQuery.getString(TELEFONO);
        String extension = resultadoQuery.getString(EXTENSION);
        String correo = resultadoQuery.getString(CORREO);
        academico = new TecnicoAcademico(numP, nom, pass, telefono, extension, correo);
        academicos.add(academico);
      }
    } catch (SQLException ex) {
      Logger.getLogger(JefeCentroComputoDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return academicos;
  }

  /**
   * Se obtienen objetos de tipo TecnicoAcademico de la base de datos mediante
   * el criterio ingresado por el usuario (nombre).
   *
   * @param criterio
   * @return tecnicos la lista con los tecnicos que cumplen el criterio.
   */
  public static List<TecnicoAcademico> obtenerTecnicoAcademicoNombre(String criterio) {
    ObservableList<TecnicoAcademico> tecnicos
        = FXCollections.observableArrayList();
    try (Connection conexion = ConnectionToDb.conectar(USUARIO, PASS, DB, HOST);
        PreparedStatement st = conexion.prepareStatement("select * from tecnicoacademico"
            + " where nombre = ?");) {
      st.setString(1, criterio);
      cargarQuery(st, tecnicos);
    } catch (SQLException ex) {
      Logger.getLogger(TecnicoAcademicoDAO.class.getName()).log(Level.SEVERE,
          null, ex);
    }
    return tecnicos;

  }

  /**
   * Se edita un objeto de tipo TecnicoAcademico que esté en la base de datos.
   *
   * @param nuevo el onjeto con los nuevos datos a guardar.
   */
  public static void editarTecnicoAcademico(TecnicoAcademico nuevo) {
    try {
      Connection conexion;
      conexion = ConnectionToDb.conectar(USUARIO, PASS, DB, HOST);
      try (PreparedStatement st = conexion.prepareStatement("UPDATE tecnicoacademico SET nombre = ?, "
          + "password = ?, telefono = ?, extension = ?, correoInstitucional = ? "
          + " where numPersonal = ?");) {
        st.setString(1, nuevo.getNombre());
        st.setString(2, nuevo.getPassword());
        st.setString(3, nuevo.getTelefono());
        st.setString(4, nuevo.getExtension());
        st.setString(5, nuevo.getCorreoInstitucional());
        st.setString(6, nuevo.getNumPersonal());
        st.executeUpdate();
      }
    } catch (SQLException ex) {
      Logger.getLogger(TecnicoAcademicoDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  /**
   * Se elimina un objeto de tipo TecnicoAcademico de la base datos.
   *
   * @param seleccionado el elemento seleccionado de la tabla para eliminar.
   */
  public static void eliminarTecnicoAcademico(TecnicoAcademico seleccionado) {
    Connection conexion;
    conexion = ConnectionToDb.conectar(USUARIO, PASS, DB, HOST);
    try {
      try (PreparedStatement st
          = conexion.prepareStatement(
              "delete tecnicoAcademico from tecnicoAcademico where numPersonal = ?");) {
        st.setString(1, seleccionado.getNumPersonal());
        st.executeUpdate();
      }
    } catch (SQLException ex) {
      Logger.getLogger(TecnicoAcademicoDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
  private static void cargarQuery(PreparedStatement st,ObservableList tecnicos){
    try (ResultSet resultadoQuery = st.executeQuery();) {
      while (resultadoQuery.next()) {
        String numP = resultadoQuery.getString(NUMPERSONAL);
        String nom = (resultadoQuery.getString(NOMBRE));
        String pass = (resultadoQuery.getString(PASSW));
        String telefono = resultadoQuery.getString(TELEFONO);
        String extension = resultadoQuery.getString(EXTENSION);
        String correo = resultadoQuery.getString(CORREO);
        TecnicoAcademico ta = new TecnicoAcademico(numP, nom, pass, telefono,extension, correo);
        tecnicos.add(ta);
      }
    } catch (SQLException ex) {
      Logger.getLogger(JefeCentroComputoDAO.class.getName()).log(Level.SEVERE,
          null, ex);
    }
  }
}
