/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.popups;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.DAO.SoftwareDAO;
import model.Software;

/**
 * FXML Controller class
 *
 * @author texch
 */
public class AgregarSoftwareController implements Initializable {

  @FXML
  private Button btnGuardar;
  @FXML
  private Button btnCancelar;
  @FXML
  private TextField txtSoftware;
  @FXML
  private TextArea txtObservaciones;
  @FXML
  private TextField txtVersion;
  @FXML
  private AnchorPane anchorPane;
  @FXML
  private DatePicker dtpFechaAdq;
  @FXML
  private TextField txtNumLicencias;

  private Software software;

  private final String patron = "[\\d]";

  /**
   * Initializes the controller class.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {

  }

  @FXML
  private void cancelarRegistro(MouseEvent event) {
    Stage stage = (Stage) anchorPane.getScene().getWindow();
    stage.close();

  }

  @FXML
  private void guardarSoftware(MouseEvent event) throws SQLException {
    if (!camposVacios()) {
      Date adq = new Date(dtpFechaAdq.getValue().getYear(),
          dtpFechaAdq.getValue().getMonthValue(), dtpFechaAdq.getValue().getDayOfMonth());

      Date today = new Date(2018, 01, 01);
      if (adq.before(today)) {
        mensaje("No puedes elegir una fecha anterior al 01/01/2018");
      } else if (esNumero()) {
        software = new Software(null, txtSoftware.getText(),
            Integer.parseInt(txtNumLicencias.getText()), adq, txtObservaciones.getText(),
            txtVersion.getText());
        guardarEnBd(software);
        mensaje("Software guardado con exito");
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        stage.close();
      } else {
        mensaje("El numero de licencias debe ser un numero");
      }
    } else {
      mensaje("No pueden quedar campos vacios");
    }
  }

  private boolean camposVacios() {
    if (txtSoftware.getText().isEmpty() || txtVersion.getText().isEmpty()
        || txtObservaciones.getText().isEmpty() || txtNumLicencias.getText().isEmpty()) {
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

  private boolean esNumero() {
    Pattern pattern = Pattern.compile(patron);
    Matcher matcher = pattern.matcher(txtNumLicencias.getText());
    if (matcher.find()) {
      return true;
    }
    return false;
  }

  private void guardarEnBd(Software software) throws SQLException {
    SoftwareDAO.agregarSoftware(software);
  }

}
