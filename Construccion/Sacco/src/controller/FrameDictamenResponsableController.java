package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import dao.ResponsableDAO;
import model.Mensaje;
import model.Responsable;
import model.TecnicoAcademico;

/**
 * FXML Controller class
 *
 * @author texch
 */
public class FrameDictamenResponsableController implements Initializable {

  @FXML
  private AnchorPane anchorPane;
  @FXML
  private Button btnSearch;
  @FXML
  private TextField txtBuscar;
  @FXML
  private Button btnSiguiente;
  @FXML
  private Button btnCancelar;
  @FXML
  private TextField txtNombre;
  @FXML
  private Label lblDate;
  @FXML
  private TextField txtCorreo;
  @FXML
  private Label lblTecnico;

  private TecnicoAcademico tecnico;

  private Responsable responsable;

  /**
   * Initializes the controller class.
   * @param url url
   * @param rb rb
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    Date fecha = new Date();
    lblDate.setText(fecha.toString());
  }

  @FXML
  private void buscarPersonal(MouseEvent event) throws SQLException {
    if (txtBuscar.getText().isEmpty()) {
      Mensaje.mostrarMensaje("Escribe un numero de personal");
    } else {
      responsable = ResponsableDAO.obtenerResponsableNumPersonal(txtBuscar.getText());
      if (responsable == null) {
        Mensaje.mostrarMensaje("No se encuentra el numero de personal asociado a un responsable");
      } else {
        llenarCampos(responsable);
        btnSiguiente.setDisable(false);
      }
    }

  }

  @FXML
  private void siguienteDictamen(MouseEvent event) {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/view/Frame_Dictamen_Equipo.fxml"));
    try {
      loader.load();
    } catch (IOException ex) {
      Logger.getLogger(LoginPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
    }
    AnchorPane dict = loader.getRoot();
    FrameDictamenEquipoController dictamen = loader.getController();
    dictamen.cargarDatos(tecnico, responsable);
    Scene newScene = new Scene(dict);
    Stage curStage = (Stage) anchorPane.getScene().getWindow();
    curStage.setScene(newScene);
    curStage.setTitle("Dictamen de Mantenimiento");
    curStage.show();

  }

  @FXML
  private void cancelarDictamen(MouseEvent event) {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/view/DashboardTA.fxml"));
    try {
      loader.load();
    } catch (IOException ex) {
      Logger.getLogger(LoginPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
    }
    AnchorPane dict = loader.getRoot();
    DashboardTaController dashboard = loader.getController();
    dashboard.cargarUsuario(tecnico);
    Scene newScene = new Scene(dict);
    Stage curStage = (Stage) anchorPane.getScene().getWindow();
    curStage.setScene(newScene);
    curStage.setTitle("Dashboard Tecnico Academico");
    curStage.show();
  }

  public void cargarUsuario(TecnicoAcademico user) {
    this.tecnico = user;
    lblTecnico.setText(tecnico.getNombre());
  }

  private void llenarCampos(Responsable responsable) {
    txtNombre.setText(responsable.getNombre());
    txtCorreo.setText(responsable.getCorreo());
  }

}
