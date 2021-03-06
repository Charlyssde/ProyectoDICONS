package controller;

import controller.messages.CancelarMessageController;
import controller.popups.EditarResponsableController;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import dao.ResponsableDAO;
import model.JefeCentroComputo;
import model.Mensaje;
import model.Responsable;

/**
 * FXML Controller class
 *
 * @author texch
 */
public class FrameResponsablesController implements Initializable {

  @FXML
  private AnchorPane anchorPane;
  @FXML
  private Button btnBack;
  @FXML
  private Button btnAgregar;
  @FXML
  private Button btnEditar;
  @FXML
  private Button btnEliminar;
  @FXML
  private Label lblJefeCc;
  @FXML
  private Button btnRefresh;
  @FXML
  private TableView<Responsable> tblResponsables;
  @FXML
  private TableColumn<Responsable, String> numPersonal;
  @FXML
  private TableColumn<Responsable, String> nombre;
  @FXML
  private TableColumn<Responsable, String> correo;
  @FXML
  private TableColumn<Responsable, String> telefono;
  @FXML
  private TableColumn<Responsable, String> direccion;

  private Responsable responsable;
  @FXML
  private TextField txtBuscar;
  @FXML
  private Button btnSearch;
  @FXML
  private ChoiceBox<String> chbCriterio;

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
  private void agregarResponsable(MouseEvent event) {
    btnEditar.setDisable(true);
    btnEliminar.setDisable(true);
    try {
      Parent sc = FXMLLoader.load(getClass().getResource("/view/popups/agregarResponsable.fxml"));
      Scene nu = new Scene(sc);
      Stage stage = new Stage();
      stage.setTitle("Agregar Responsable");
      stage.initModality(Modality.APPLICATION_MODAL);
      stage.setResizable(false);
      stage.setScene(nu);
      stage.show();
    } catch (IOException e) {
      Logger.getLogger(LoginPrincipalController.class.getName()).log(Level.SEVERE, null, e);
    }
  }

  @FXML
  private void editarResponsable(MouseEvent event) {
    btnEditar.setDisable(true);
    btnEliminar.setDisable(true);
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/view/popups/editarResponsable.fxml"));
    try {
      Parent sc = loader.load();
      EditarResponsableController pantalla = loader.getController();
      pantalla.cargarResponsable(this.responsable);
      Scene nu = new Scene(sc);
      Stage stage = new Stage();
      stage.setTitle("Editar Responsable");
      stage.initModality(Modality.APPLICATION_MODAL);
      stage.setResizable(false);
      stage.setScene(nu);
      stage.show();
    } catch (IOException e) {
      Logger.getLogger(LoginPrincipalController.class.getName()).log(Level.SEVERE, null, e);
    }
  }

  @FXML
  private void eliminarResponsable(MouseEvent event) throws SQLException {
    btnEditar.setDisable(true);
    btnEliminar.setDisable(true);
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/view/messages/EliminarMessage.fxml"));
    try {
      Parent sc = loader.load();
      CancelarMessageController pantalla = loader.getController();
      pantalla.vieneDe("Responsable");
      pantalla.cargarObjeto(responsable);
      Scene nu = new Scene(sc);
      Stage stage = new Stage();
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
  private void actualizarDataTabla(MouseEvent event) throws SQLException {
    btnEditar.setDisable(true);
    btnEliminar.setDisable(true);
    actualizarTabla();
  }

  public void cargarUsuario(JefeCentroComputo jefe) {
    this.jefe = jefe;
    lblJefeCc.setText(jefe.getNombre());
  }

  public void cargarColumnas() {

    numPersonal.setCellValueFactory(
        new PropertyValueFactory<Responsable, String>("numPersonal"));

    nombre.setCellValueFactory(
        new PropertyValueFactory<Responsable, String>("nombre"));

    correo.setCellValueFactory(
        new PropertyValueFactory<Responsable, String>("correo"));

    telefono.setCellValueFactory(
        new PropertyValueFactory<Responsable, String>("telefono"));

    direccion.setCellValueFactory(
        new PropertyValueFactory<Responsable, String>("nombre"));
  }

  public void actualizarTabla() throws SQLException {
    btnEditar.setDisable(true);
    btnEliminar.setDisable(true);
    tblResponsables.getItems().clear();
    ObservableList<Responsable> visibleList
        = (ObservableList<Responsable>) ResponsableDAO.obtenerAllResponsable();
    tblResponsables.getItems().addAll(visibleList);
    txtBuscar.clear();
    chbCriterio.setValue(null);
  }

  @FXML
  private void buscarPorCriterio(MouseEvent event) throws SQLException {
    btnEditar.setDisable(true);
    btnEliminar.setDisable(true);
    if (txtBuscar.getText().isEmpty() || chbCriterio.getValue() == null) {
      Mensaje.mostrarMensaje("Necesitas un criterio y un filtro de busqueda");
    } else {
      tblResponsables.getItems().clear();
      ObservableList<Responsable> visibleList
          = (ObservableList<Responsable>) ResponsableDAO.obtenerResponsableNombre(
              txtBuscar.getText());
      tblResponsables.getItems().addAll(visibleList);
    }
  }

  private void cargarBox() {
    chbCriterio.getItems().addAll("Nombre");
  }

  private void selected() {
    tblResponsables.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
      @Override
      public void changed(ObservableValue observable, Object oldValue, Object newValue) {
        if (tblResponsables.getSelectionModel().getSelectedItem() != null) {
          TableView.TableViewSelectionModel selectionModel = tblResponsables.getSelectionModel();
          responsable = (Responsable) selectionModel.getSelectedItem();
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
