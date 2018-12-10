/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.messages;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import dao.HardwareDAO;
import dao.ResponsableDAO;
import dao.SoftwareDAO;
import dao.TecnicoAcademicoDAO;
import model.Hardware;
import model.Mensaje;
import model.Responsable;
import model.Software;
import model.TecnicoAcademico;

/**
 * FXML Controller class
 *
 * @author texch
 */
public class CancelarMessageController implements Initializable {

  @FXML
  private Button btnEliminar;
  @FXML
  private Button btnCancelar;
  @FXML
  private AnchorPane anchorPane;

  private Object eliminar;

  private String origen;

  /**
   * Initializes the controller class.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    // Nothing
  }

  @FXML
  private void eliminarElemento(MouseEvent event) throws SQLException {
    switch (origen) {
      case "Software":
        SoftwareDAO.eliminarSoftware((Software) eliminar);
        break;
      case "Hardware":
        HardwareDAO.eliminarHardware((Hardware) eliminar);
        break;
      case "Responsable":
        ResponsableDAO.eliminarResponsable((Responsable) eliminar);
        break;
      case "Tecnico":
        TecnicoAcademicoDAO.eliminarTecnicoAcademico((TecnicoAcademico) eliminar);
        break;
      default:
        break;

    }
    Mensaje.mostrarMensaje("Eliminacion realizada exitosamente");
    Stage stage = (Stage) anchorPane.getScene().getWindow();
    stage.close();
  }

  @FXML
  private void cancelarEliminacion(MouseEvent event) {
    Stage stage = (Stage) anchorPane.getScene().getWindow();
    stage.close();
  }

  public void cargarObjeto(Object objeto) {
    switch (objeto.getClass().toString()) {
      case "class model.Hardware":
        eliminar = objeto;
        break;
      case "class model.Software":
        eliminar = objeto;
        break;
      case "class model.TecnicoAcademico":
        eliminar = objeto;
        break;
      case "class model.Responsable":
        eliminar = objeto;
        break;
      default:
        break;

    }
  }

  public void vieneDe(String origen) {
    this.origen = origen;
  }

}
