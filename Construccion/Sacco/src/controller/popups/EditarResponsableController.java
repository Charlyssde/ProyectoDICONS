/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.popups;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.DAO.ResponsableDAO;
import model.Responsable;

/**
 * FXML Controller class
 *
 * @author texch
 */
public class EditarResponsableController implements Initializable {

  @FXML
  private AnchorPane anchorPane;
  @FXML
  private TextField txtNumPersonal;
  @FXML
  private TextField txtNombre;
  @FXML
  private TextField txtTelefono;
  @FXML
  private TextField txtExtension;
  @FXML
  private Button btnGuardar;
  @FXML
  private TextField txtCorreo;
  @FXML
  private Button btnCancelar;

  private Responsable responsable;

  /**
   * Initializes the controller class.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    // TODO
  }

  @FXML
  private void actualizarResponsable(MouseEvent event) throws SQLException {
    if (camposVacios()) {
      mensaje("No pueden quedar campos vacios");
    } else {
      Responsable nuevo = new Responsable(responsable.getNumPersonal(),
          txtNombre.getText(), txtTelefono.getText(), txtExtension.getText(),
          responsable.getCorreo());
      ResponsableDAO.editarResponsable(nuevo);
      mensaje("Responsable actualizado con exito");
      Stage stage = (Stage) anchorPane.getScene().getWindow();
      stage.close();
    }
  }

  @FXML
  private void cancelarRegistro(MouseEvent event) {
    Stage stage = (Stage) anchorPane.getScene().getWindow();
    stage.close();
  }

  public void cargarResponsable(Responsable responsable) {
    this.responsable = responsable;
    txtNumPersonal.setText(responsable.getNumPersonal());
    txtNombre.setText(responsable.getNombre());
    txtTelefono.setText(responsable.getTelefono());
    txtExtension.setText(responsable.getExtension());
    txtCorreo.setText(responsable.getCorreo());

  }

  public boolean camposVacios() {
    if (txtTelefono.getText().isEmpty() || txtExtension.getText().isEmpty()
        || txtNombre.getText().isEmpty()) {
      return true;
    }
    return false;
  }

  private void mensaje(String mensaje) {
    Alert dialogo = new Alert(Alert.AlertType.INFORMATION);
    dialogo.setTitle("Aviso");
    dialogo.setHeaderText(null);
    dialogo.setContentText(mensaje);
    dialogo.initStyle(StageStyle.UTILITY);
    dialogo.showAndWait();
  }
}
