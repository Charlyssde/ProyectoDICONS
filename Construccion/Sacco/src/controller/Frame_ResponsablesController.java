package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import model.DAO.ResponsableDAO;
import model.Responsable;

/**
 * FXML Controller class
 *
 * @author texch
 */
public class Frame_ResponsablesController implements Initializable {

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

  /**
   * Initializes the controller class.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    cargarBox();
    cargarColumnas();
    /* try {
      actualizarTabla();
    } catch (SQLException ex) {
      Logger.getLogger(Frame_HardwareController.class.getName()).log(Level.SEVERE, null, ex);
    }*/
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
  private void agregarResponsable(MouseEvent event) {
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
      e.printStackTrace();
    }
  }

  @FXML
  private void editarResponsable(MouseEvent event) {
    try {
      Parent sc = FXMLLoader.load(getClass().getResource("/view/popups/editarResponsable.fxml"));
      Scene nu = new Scene(sc);
      Stage stage = new Stage();
      stage.setTitle("Editar Responsable");
      stage.initModality(Modality.APPLICATION_MODAL);
      stage.setResizable(false);
      stage.setScene(nu);
      stage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @FXML
  private void eliminarResponsable(MouseEvent event) {
    try {
      Parent sc = FXMLLoader.load(getClass().getResource("/view/messages/CancelarMessage.fxml"));
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
  private void actualizarDataTabla(MouseEvent event) {
  }

  public void cargarUsuario(String nombre) {
    lblJefeCc.setText(nombre);
  }

  public void cargarColumnas() {

    numPersonal.setCellValueFactory(
        new PropertyValueFactory<Responsable, String>("numPersonal"));

    nombre.setCellValueFactory(
        new PropertyValueFactory<Responsable, String>("nombre"));

    correo.setCellValueFactory(
        new PropertyValueFactory<Responsable, String>("correoInstitucional"));

    telefono.setCellValueFactory(
        new PropertyValueFactory<Responsable, String>("telefono"));

    direccion.setCellValueFactory(
        new PropertyValueFactory<Responsable, String>("nombre"));
  }

  public void actualizarTabla() {
    ResponsableDAO.obtenerAllResponsable();
  }

  @FXML
  private void buscarPorCriterio(MouseEvent event) {
    ResponsableDAO.obtenerResponsableNombre(txtBuscar.getText());
  }

  private void cargarBox() {
    chbCriterio.getItems().addAll("Nombre");
  }

}
