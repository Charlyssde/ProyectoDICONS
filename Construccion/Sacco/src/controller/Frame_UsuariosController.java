/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.DAO.TecnicoAcademicoDAO;
import model.TecnicoAcademico;

/**
 * FXML Controller class
 *
 * @author texch
 */
public class Frame_UsuariosController implements Initializable {

  private AnchorPane anchorPane;
  @FXML
  private Button btnBack;
  @FXML
  private Button btnAgregar;
  @FXML
  private Button btnEditar;
  @FXML
  private Button benEliminar;
  @FXML
  private Label lblJefeCc;
  @FXML
  private Button btnRefresh;
  @FXML
  private TableColumn<TecnicoAcademico, String> numPersonal;
  @FXML
  private TableColumn<TecnicoAcademico, String> nombre;
  @FXML
  private TableColumn<TecnicoAcademico, String> telefono;
  @FXML
  private TableColumn<TecnicoAcademico, String> correo;

  private TecnicoAcademico tecnico;

  /**
   * Initializes the controller class.
   *
   * @param url
   * @param rb
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    cargarColumnas();
    actualizarTabla();
  }

  @FXML
  private void regresarPantalla(MouseEvent event) {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/view/DashboardJefeCC.fxml"));
    try {
      loader.load();
    } catch (IOException ex) {
      Logger.getLogger(LoginPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
    }
    //LoginJefeCCController display = loader.getController();//Utilizar a menos que se haga una accion dentro de la clase que viene
    AnchorPane dashboardJefeCc = loader.getRoot();
    DashboardJefeCCController dashboard = loader.getController();
    dashboard.cargarUsuario(lblJefeCc.getText());
    Scene newScene = new Scene(dashboardJefeCc);
    Stage curStage = (Stage) anchorPane.getScene().getWindow();
    curStage.setScene(newScene);
    curStage.setTitle("Dashboard Jefe de Centro de Cómputo");
    curStage.show();
  }

  @FXML
  private void agregarTecnico(MouseEvent event) {
    try {
      Parent sc = FXMLLoader.load(getClass().getResource("/view/popups/agregarTecnico.fxml"));
      Scene nu = new Scene(sc);
      Stage stage = new Stage();
      stage.setTitle("Agregar Técnico");
      stage.initModality(Modality.APPLICATION_MODAL);
      stage.setResizable(false);
      stage.setScene(nu);
      stage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @FXML
  private void editarTecnico(MouseEvent event) {
    try {
      Parent sc = FXMLLoader.load(getClass().getResource("/view/popups/editarTecnico.fxml"));
      Scene nu = new Scene(sc);
      Stage stage = new Stage();
      stage.setTitle("Editar Técnico");
      stage.initModality(Modality.APPLICATION_MODAL);
      stage.setResizable(false);
      stage.setScene(nu);
      stage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @FXML
  private void eliminarTecnico(MouseEvent event) {
    try {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation((getClass().getResource("/view/messages/EliminarMessage.fxml")));
      Parent sc = loader.load();
      Scene nu = new Scene(sc);
      Stage stage = new Stage();
      stage.setTitle("Eliminar");
      stage.initModality(Modality.APPLICATION_MODAL);
      stage.setResizable(false);
      stage.setScene(nu);
      stage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @FXML
  private void actualizarDataTabla(MouseEvent event) throws SQLException {
    actualizarTabla();
  }

  public void cargarColumnas() {

    numPersonal.setCellValueFactory(
        new PropertyValueFactory<TecnicoAcademico, String>("numPersonal"));

    telefono.setCellValueFactory(
        new PropertyValueFactory<TecnicoAcademico, String>("telefono"));

    nombre.setCellValueFactory(
        new PropertyValueFactory<TecnicoAcademico, String>("nombre"));

    correo.setCellValueFactory(
        new PropertyValueFactory<TecnicoAcademico, String>("correoInstitucional"));

  }

  private void actualizarTabla() {
    try {
      TecnicoAcademicoDAO.obtenerAllTecnicos();
    } catch (SQLException ex) {
      Logger.getLogger(Frame_UsuariosController.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  void cargarUsuario(String nombre) {
    lblJefeCc.setText(nombre);
  }
}
