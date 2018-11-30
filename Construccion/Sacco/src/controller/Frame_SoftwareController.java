
package controller;

import controller.messages.CancelarMessageController;
import controller.popups.EditarSoftwareController;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.DAO.SoftwareDAO;
import model.Software;

/**
 * FXML Controller class
 *
 * @author texch
 */
public class Frame_SoftwareController implements Initializable {

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
  }

    @FXML
  private void agregarSoftware(MouseEvent event) {
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
      e.printStackTrace();
    }
  }


   @FXML
  private void editarSoftware(MouseEvent event) {
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
      e.printStackTrace();
    }
  }

  @FXML
  private void eliminarElemento(MouseEvent event) {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/view/messages/CancelarMessage.fxml"));
    try {
      Parent sc = loader.load();
      Scene nu = new Scene(sc);
      Stage stage = new Stage();
      CancelarMessageController pantalla = loader.getController();
      pantalla.cargarSoftware(software);
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
  private void actualizarDataTabla(MouseEvent event) throws SQLException {
    actualizarTabla();
  }

  @FXML
  private void buscarPorCriterio(MouseEvent event) {
  }

  public void cargarUsuario(String nombre) {
    lblJefeCc.setText(nombre);
  }

  private void actualizarTabla() throws SQLException {
    tblSoftware.getItems().clear();
    ObservableList<Software> visibleList = (ObservableList<Software>) SoftwareDAO.obtenerAllSoftware();
    tblSoftware.getItems().addAll(visibleList);
  }

  private void mensaje(String mensaje) {
    Alert dialogo = new Alert(Alert.AlertType.INFORMATION);
    dialogo.setTitle("Aviso");
    dialogo.setHeaderText(null);
    dialogo.setContentText(mensaje);
    dialogo.initStyle(StageStyle.UTILITY);
    dialogo.showAndWait();
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
}
