package controller;

import controller.messages.CancelarMessageController;
import controller.popups.EditarSoftwareController;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import dao.SoftwareDAO;
import model.JefeCentroComputo;
import model.Mensaje;
import model.Software;

/**
 * FXML Controller class
 *
 * @author texch
 */
public class FrameSoftwareController implements Initializable {

  @FXML
  private AnchorPane anchorPane;
  @FXML
  private TableView<Software> tblSoftware;
  @FXML
  private TableColumn<Software, String> colSoftware;
  @FXML
  private TableColumn<Software, Integer> colNumLicencias;
  @FXML
  private TableColumn<Software, String> colVersion;
  @FXML
  private TableColumn<Software, String> colObservaciones;
  @FXML
  private Button btnBack;
  @FXML
  private ChoiceBox<String> chbCriterio;
  @FXML
  private Button btnAgregar;
  @FXML
  private Button btnAdminLicencias;
  @FXML
  private Button btnEditar;
  @FXML
  private Button btnEliminar;
  @FXML
  private Button btnSearch;
  @FXML
  private Label lblJefeCc;
  @FXML
  private Button btnRefresh;

  private Software software;
  @FXML
  private TextField txtBuscar;

  private JefeCentroComputo jefe;

  /**
   * Initializes the controller class.
   * @param url url
   * @param rb rb
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    cargarBox();
    cargarColumnas();
    try {
      actualizarTabla();
    } catch (SQLException ex) {
      Logger.getLogger(FrameHardwareController.class.getName()).log(Level.SEVERE, null, ex);
    }
    selected();
  }

  @FXML
  private void agregarSoftware(MouseEvent event) {
    btnEditar.setDisable(true);
    btnEliminar.setDisable(true);
    try {
      Parent sc = FXMLLoader.load(getClass().getResource("/view/popups/agregarSoftware.fxml"));
      Scene nu = new Scene(sc);
      Stage stage = new Stage();
      stage.setTitle("Agregar Software");
      stage.initModality(Modality.APPLICATION_MODAL);
      stage.setResizable(false);
      stage.setScene(nu);
      stage.show();
    } catch (IOException e) {
      Logger.getLogger(LoginPrincipalController.class.getName()).log(Level.SEVERE, null, e);
    }
  }

  @FXML
  private void editarSoftware(MouseEvent event) {
    btnEditar.setDisable(true);
    btnEliminar.setDisable(true);
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/view/popups/editarSoftware.fxml"));
    try {
      Parent sc = loader.load();
      Scene nu = new Scene(sc);
      Stage stage = new Stage();
      EditarSoftwareController pantalla = loader.getController();
      pantalla.cargarSoftware(software);
      stage.setTitle("Editar Software");
      stage.initModality(Modality.APPLICATION_MODAL);
      stage.setResizable(false);
      stage.setScene(nu);
      stage.show();
    } catch (IOException e) {
      Logger.getLogger(LoginPrincipalController.class.getName()).log(Level.SEVERE, null, e);
    }
  }

  @FXML
  private void eliminarElemento(MouseEvent event) throws SQLException {
    btnEditar.setDisable(true);
    btnEliminar.setDisable(true);
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/view/messages/EliminarMessage.fxml"));
    try {
      Parent sc = loader.load();
      Scene nu = new Scene(sc);
      Stage stage = new Stage();
      CancelarMessageController pantalla = loader.getController();
      pantalla.vieneDe("Software");
      pantalla.cargarObjeto(software);
      stage.setTitle("Eliminar");
      stage.initModality(Modality.APPLICATION_MODAL);
      stage.setResizable(false);
      stage.setScene(nu);
      stage.show();
    } catch (IOException e) {
      Logger.getLogger(LoginPrincipalController.class.getName()).log(Level.SEVERE, null, e);
    }
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
    DashboardJefeCcController dashboard = loader.getController();
    dashboard.cargarUsuario(jefe);
    Scene newScene = new Scene(dashboardJefeCc);
    Stage curStage = (Stage) anchorPane.getScene().getWindow();
    curStage.setScene(newScene);
    curStage.setTitle("Dashboard Jefe de Centro de Cómputo");
    curStage.show();
  }

  @FXML
  private void actualizarDataTabla(MouseEvent event) throws SQLException {
    actualizarTabla();
  }

  @FXML
  private void buscarPorCriterio(MouseEvent event) throws SQLException {
    if (txtBuscar.getText().isEmpty() || chbCriterio.getValue() == null) {
      Mensaje.mostrarMensaje("Debes seleccionar un criterio y un filtro para realizar la busqueda");
    } else {
      tblSoftware.getItems().clear();
      ObservableList<Software> lista
          = (ObservableList<Software>) SoftwareDAO.obtenerSoftwareNombre(txtBuscar.getText());
      tblSoftware.getItems().addAll(lista);
    }
  }

  public void cargarUsuario(JefeCentroComputo jefe) {
    this.jefe = jefe;
    lblJefeCc.setText(jefe.getNombre());
  }

  private void actualizarTabla() throws SQLException {
    tblSoftware.getItems().clear();
    ObservableList<Software> visibleList = 
        (ObservableList<Software>) SoftwareDAO.obtenerAllSoftware();
    tblSoftware.getItems().addAll(visibleList);
    txtBuscar.clear();
    chbCriterio.setValue(null);
  }

  private void cargarBox() {
    chbCriterio.setTooltip(new Tooltip("Selecciona un criterio para búsqueda"));
    chbCriterio.getItems().addAll("Nombre", "Numero de licencias");

  }

  private void cargarColumnas() {

    colSoftware.setCellValueFactory(
        new PropertyValueFactory<Software, String>("nombre"));

    colNumLicencias.setCellValueFactory(
        new PropertyValueFactory<Software, Integer>("numLicencias"));

    colVersion.setCellValueFactory(
        new PropertyValueFactory<Software, String>("version"));

    colObservaciones.setCellValueFactory(
        new PropertyValueFactory<Software, String>("observaciones"));

  }

  private void selected() {
    tblSoftware.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
      @Override
      public void changed(ObservableValue observable, Object oldValue, Object newValue) {
        if (tblSoftware.getSelectionModel().getSelectedItem() != null) {
          TableViewSelectionModel selectionModel = tblSoftware.getSelectionModel();
          software = (Software) selectionModel.getSelectedItem();
          btnEditar.setDisable(false);
          btnEliminar.setDisable(false);
        } else {
          btnEditar.setDisable(true);
          btnEliminar.setDisable(true);
        }
      }

    });
  }
}
