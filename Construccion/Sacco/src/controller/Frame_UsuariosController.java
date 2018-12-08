/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.messages.CancelarMessageController;
import controller.popups.EditarTecnicoController;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.DAO.TecnicoAcademicoDAO;
import model.JefeCentroComputo;
import model.TecnicoAcademico;

/**
 * FXML Controller class
 *
 * @author texch
 */
public class Frame_UsuariosController implements Initializable {

  @FXML
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
  @FXML
  private TableView<TecnicoAcademico> tblTecnicos;
  
  private TecnicoAcademico tecnico;
  
  private JefeCentroComputo jefe;

  /**
   * Initializes the controller class.
   *
   * @param url
   * @param rb
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    cargarColumnas();
    try {
      actualizarTabla();
    } catch (SQLException ex) {
      Logger.getLogger(Frame_UsuariosController.class.getName()).log(Level.SEVERE, null, ex);
    }
    selected();
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
    dashboard.cargarUsuario(this.jefe);
    Scene newScene = new Scene(dashboardJefeCc);
    Stage curStage = (Stage) anchorPane.getScene().getWindow();
    curStage.setScene(newScene);
    curStage.setTitle("Dashboard Jefe de Centro de Cómputo");
    curStage.show();
  }

  @FXML
  private void agregarTecnico(MouseEvent event) {
     btnEditar.setDisable(true);
    benEliminar.setDisable(true);
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
     btnEditar.setDisable(true);
    benEliminar.setDisable(true);
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/view/popups/editarTecnico.fxml"));
    try {
      Parent sc = loader.load();
      EditarTecnicoController pantalla = loader.getController();
      pantalla.cargarTecnico(tecnico);
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
  private void eliminarTecnico(MouseEvent event) throws SQLException {
     btnEditar.setDisable(true);
    benEliminar.setDisable(true);
    try {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation((getClass().getResource("/view/messages/EliminarMessage.fxml")));
      Parent sc = loader.load();
      CancelarMessageController pantalla = loader.getController();
      pantalla.vieneDe("Tecnico");
      pantalla.cargarObjeto(tecnico);
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
     btnEditar.setDisable(true);
    benEliminar.setDisable(true);
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

  private void actualizarTabla() throws SQLException {
    tblTecnicos.getItems().clear();
    ObservableList<TecnicoAcademico> visibleList =
        (ObservableList<TecnicoAcademico>) TecnicoAcademicoDAO.obtenerAllTecnicos();
    tblTecnicos.getItems().addAll(visibleList);
  }

  void cargarUsuario(JefeCentroComputo jefe) {
    this.jefe = jefe;
    lblJefeCc.setText(jefe.getNombre());
  }
  
  private void selected() {
    tblTecnicos.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
      @Override
      public void changed(ObservableValue observable, Object oldValue, Object newValue) {
        if (tblTecnicos.getSelectionModel().getSelectedItem() != null) {
          TableViewSelectionModel selectionModel = tblTecnicos.getSelectionModel();
          tecnico = (TecnicoAcademico) selectionModel.getSelectedItem();
          System.out.println(tecnico.getNombre());
          btnEditar.setDisable(false);
          benEliminar.setDisable(false);
        } else {
          btnEditar.setDisable(true);
          benEliminar.setDisable(true);
        }
      }

    });
  }
}
