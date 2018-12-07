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
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Hardware;
import model.TecnicoAcademico;
import model.databaseConection.ConnectionToDb;

/**
 *
 * @author texch
 */
public class TecnicoAcademicoDAO {

  public static TecnicoAcademico obtenerTecnico(TecnicoAcademico ta) 
    throws SQLException {
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
        academico = new TecnicoAcademico(numP, nom, pass, telefono, 
          extension, correo);
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

  public static List<TecnicoAcademico> obtenerAllTecnicos() throws SQLException {
    ObservableList<TecnicoAcademico> tecnicos = FXCollections.observableArrayList();
    TecnicoAcademico ta = null;
    Connection conexion = null;
    conexion = ConnectionToDb.conectar("root", "2580", "sacco", "localhost");
    Statement st = null;
    st = conexion.createStatement();
    ResultSet resultadoQuery = st.executeQuery("select * from tecnicoacademico");
    try {
      while (resultadoQuery.next()) {
        String numPersonal = resultadoQuery.getString("numPersonal");
        String nombre = (resultadoQuery.getString("nombre"));
        String password = (resultadoQuery.getString("password"));
        String telefono = (resultadoQuery.getString("telefono"));
        String extension = (resultadoQuery.getString("extension"));
        String correoInstitucional = (resultadoQuery.getString("correoInstitucional"));
        ta = new TecnicoAcademico(numPersonal, nombre, password, telefono, extension, correoInstitucional);
        tecnicos.add(ta);
      }
    } catch (SQLException ex) {
      Logger.getLogger(JefeCentroComputoDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      if (conexion != null) {
        conexion.close();
      }
    }
    return tecnicos;
  }

  public static List<TecnicoAcademico> obtenerTecnicoAcademicoNombre (String criterioNombre) throws SQLException {
    ObservableList<TecnicoAcademico> tecnicos = FXCollections.observableArrayList();
    TecnicoAcademico ta = null;
    Connection conexion = null;
    conexion = ConnectionToDb.conectar("root", "2580", "sacco", "localhost");
    PreparedStatement st = null;
    st = conexion.prepareStatement("select * from tecnicoacademico where nombre = ?");
    st.setString(1, criterioNombre);
    ResultSet resultadoQuery = st.executeQuery();
    try {
      while (resultadoQuery.next()) {
        String numPersonal = resultadoQuery.getString("numPersonal");
        String nombre = (resultadoQuery.getString("nombre"));
        String password = (resultadoQuery.getString("password"));
        String telefono = (resultadoQuery.getString("telefono"));
        String extension = (resultadoQuery.getString("extension"));
        String correoInstitusional = (resultadoQuery.getString("correoInstitusional"));
        ta = new TecnicoAcademico(numPersonal, nombre, password, telefono, extension, correoInstitusional);
        tecnicos.add(ta);
      }
    } catch (SQLException ex) {
      Logger.getLogger(JefeCentroComputoDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      conexion.close();
    }
    return tecnicos;  
  }

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

  public static void agregarTecnicoAcademico(TecnicoAcademico tecnicoAcademico) throws SQLException {
    Connection conexion = null;
    PreparedStatement stp = null;
    conexion = ConnectionToDb.conectar("root", "2580", "sacco", "localhost");
    stp = conexion.prepareStatement("insert into tecnicoacademico values(?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
    stp.setString(1, tecnicoAcademico.getNumPersonal());
    stp.setString(2, tecnicoAcademico.getNombre());
    stp.setString(3, tecnicoAcademico.getPassword());
    stp.setString(4, tecnicoAcademico.getTelefono());
    stp.setString(5, tecnicoAcademico.getExtension());
    stp.setString(6, tecnicoAcademico.getCorreoInstitucional());
    stp.executeUpdate();
  }

  public static void eliminarTecnicoAcademico(TecnicoAcademico seleccionado) throws SQLException {
    Connection conexion = null;
    conexion = ConnectionToDb.conectar("root", "2580", "sacco", "localhost");
    PreparedStatement st = null;
    st = conexion.prepareStatement("delete tecnicoAcademico from tecnicoAcademico where numPersonal = ?");
    st.setString(1, seleccionado.getNumPersonal());
    st.executeUpdate();
  }
}
