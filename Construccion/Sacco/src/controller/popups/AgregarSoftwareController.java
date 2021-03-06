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
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import dao.SoftwareDAO;
import model.Mensaje;
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

  private static final String PATRON = "[\\d]";

  /**
   * Initializes the controller class.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    //Nothing
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
        Mensaje.mostrarMensaje("No puedes elegir una fecha anterior al 01/01/2018");
      } else if (esNumero()) {
        Software software = new Software(null, txtSoftware.getText(),
            Integer.parseInt(txtNumLicencias.getText()), adq, txtObservaciones.getText(),
            txtVersion.getText());
        guardarEnBd(software);
        Mensaje.mostrarMensaje("Software guardado con exito");
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        stage.close();
      } else {
        Mensaje.mostrarMensaje("El numero de licencias debe ser un numero");
      }
    } else {
      Mensaje.mostrarMensaje("No pueden quedar campos vacios");
    }
  }

  private boolean camposVacios() {
    return (txtSoftware.getText().isEmpty() || txtVersion.getText().isEmpty()
        || txtObservaciones.getText().isEmpty() || txtNumLicencias.getText().isEmpty());
  }

  private boolean esNumero() {
    Pattern pattern = Pattern.compile(PATRON);
    Matcher matcher = pattern.matcher(txtNumLicencias.getText());
    return (matcher.find());
  }

  private void guardarEnBd(Software software) {
    SoftwareDAO.agregarSoftware(software);
  }

}
