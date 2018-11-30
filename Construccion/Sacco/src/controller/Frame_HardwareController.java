/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.popups.EditarHardwareController;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.DAO.HardwareDAO;
import model.Hardware;
import model.Area;

/**
 * FXML Controller class
 *
 * @author texch
 */
public class Frame_HardwareController implements Initializable {

  @FXML
  private AnchorPane anchorPane;
  @FXML
  private Button btnBack;
  @FXML
  private Button btnAdministrarLicencias;
  @FXML
  private Button btnAgregar;
  @FXML
  private Button btnEditar;
  @FXML
  private Button btnEliminar;
  @FXML
  private ChoiceBox<String> chbCriterio;
  @FXML
  private Button btnBuscar;
  @FXML
  private Label lblJefeCc;
  @FXML
  private TextField txtBuscar;
  @FXML
  private TableView<Hardware> tblHardware = new TableView<>();
  @FXML
  private TableColumn<Area, String> colUbicacion;
  @FXML
  private TableColumn<Hardware, String> colNumInv;
  @FXML
  private TableColumn<Hardware, String> colMarca;
  @FXML
  private TableColumn<Hardware, String> colModelo;
  @FXML
  private TableColumn<Hardware, String> colNoSerie;
  @FXML
  private TableColumn<Hardware, String> colEstado;
  @FXML
  private Button btnRefreshTable;
  
  private Hardware hardware;
  

  /**
   * Initializes the controller class.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    cargarBox();
    cargarColumnas();
    try {
      actualizarTabla();
    } catch (SQLException ex) {
      Logger.getLogger(Frame_HardwareController.class.getName()).log(Level.SEVERE, null, ex);
    }
    selected();

  }

  @FXML
  private void actualizarTablaData(MouseEvent event) throws SQLException {
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

    AnchorPane dashboardJefeCc = loader.getRoot();
    DashboardJefeCCController dashboard = loader.getController();
    dashboard.cargarUsuario(lblJefeCc.getText());
    Scene newScene = new Scene(dashboardJefeCc);
    Stage curStage = (Stage) anchorPane.getScene().getWindow();
    curStage.setScene(newScene);
    curStage.setTitle("Dashboard Técnico Académico");
    curStage.show();
  }

  @FXML
  private void agregarHardware(MouseEvent event) throws SQLException {

    try {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("/view/popups/agregarHardware.fxml"));
      Parent sc = loader.load();
      Scene nu = new Scene(sc);
      Stage stage = new Stage();
      stage.setTitle("Agregar Hardware");
      stage.initModality(Modality.APPLICATION_MODAL);
      stage.setResizable(false);
      stage.setScene(nu);
      stage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @FXML
  private void editarHardware(MouseEvent event) {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/view/popups/editarHardware.fxml"));
    Hardware hardware = new Hardware();
    try {
      Parent sc = loader.load();
      Scene nu = new Scene(sc);
      Stage stage = new Stage();
      stage.setTitle("Editar Hardware");
      stage.initModality(Modality.APPLICATION_MODAL);
      stage.setResizable(false);
      EditarHardwareController pantalla = loader.getController();
      pantalla.cargarHardware(hardware);
      stage.setScene(nu);
      stage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @FXML
  private void eliminarHardware(MouseEvent event) {
    try {
      Parent sc = FXMLLoader.load(getClass().getResource("/view/messages/EliminarMessage.fxml"));
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
  private void buscarPorCriterio(MouseEvent event) throws SQLException {
    if (chbCriterio.getValue() == null || txtBuscar.getText().equals("")) {
      mensaje("Debe seleccionar un filtro y un criterio de busqueda");
    } else if (chbCriterio.getValue().equals("marca")) {
      tblHardware.getItems().clear();
      ObservableList<Hardware> visibleList
          = (ObservableList<Hardware>) HardwareDAO.obtenerHardwareMarca(txtBuscar.getText());
      tblHardware.getItems().addAll(visibleList);
    } else {
      tblHardware.getItems().clear();
      ObservableList<Hardware> visibleList
          = (ObservableList<Hardware>) HardwareDAO.obtenerHardwareEstado(txtBuscar.getText());
      tblHardware.getItems().addAll(visibleList);
    }

  }

  public void cargarUsuario(String nombre) {
    lblJefeCc.setText(nombre);
  }

  private void actualizarTabla() throws SQLException {
    tblHardware.getItems().clear();
    ObservableList<Hardware> visibleList = (ObservableList<Hardware>) HardwareDAO.obtenerAllHardware();
    tblHardware.getItems().addAll(visibleList);
  }

  private void mensaje(String mensaje) {
    Alert dialogo = new Alert(Alert.AlertType.INFORMATION);
    dialogo.setTitle("Aviso");
    dialogo.setHeaderText(null);
    dialogo.setContentText(mensaje);
    dialogo.initStyle(StageStyle.UTILITY);
    dialogo.showAndWait();
  }

  private void cargarColumnas() {
    colNumInv.setCellValueFactory(
        new PropertyValueFactory<Hardware, String>("numInventario"));
    colMarca.setCellValueFactory(
        new PropertyValueFactory<Hardware, String>("marca"));
    colModelo.setCellValueFactory(
        new PropertyValueFactory<Hardware, String>("modelo"));
    colEstado.setCellValueFactory(
        new PropertyValueFactory<Hardware, String>("estado"));
    colNoSerie.setCellValueFactory(
        new PropertyValueFactory<Hardware, String>("numSerie"));
  }

  private void cargarBox() {
    chbCriterio.setTooltip(new Tooltip("Selecciona un criterio para búsqueda"));
    chbCriterio.getItems().addAll("marca", "estado");
  }
  
  private void selected(){
    tblHardware.getSelectionModel().selectedItemProperty().addListener(new ChangeListener(){
      @Override
      public void changed(ObservableValue observable, Object oldValue, Object newValue) {
        if(tblHardware.getSelectionModel().getSelectedItem() != null) {
          TableViewSelectionModel selectionModel = tblHardware.getSelectionModel(); 
          hardware = (Hardware)selectionModel.getSelectedItem();
          System.out.println(hardware.getNumInventario());
          
          btnEditar.setDisable(false);
          btnEliminar.setDisable(false);
        }else{
          btnEditar.setDisable(true);
          btnEliminar.setDisable(true);
        } 
      }
      
    });
  }
}
