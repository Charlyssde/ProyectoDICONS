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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.DAO.HardwareDAO;
import model.DAO.ResponsableDAO;
import model.DAO.SoftwareDAO;
import model.DAO.TecnicoAcademicoDAO;
import model.Hardware;
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
    // TODO
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
    mensaje("Eliminacion realizada exitosamente");
    Stage stage = (Stage) anchorPane.getScene().getWindow();
    stage.close();
  }

  @FXML
  private void cancelarEliminacion(MouseEvent event) {
    Stage stage = (Stage) anchorPane.getScene().getWindow();
    stage.close();
  }

  public void cargarObjeto(Object objeto) throws SQLException {
    System.out.println(objeto.getClass().toString());
    if (objeto.getClass().toString().equals("class model.Hardware")) {
      eliminar = (Hardware) objeto;
    } else if (objeto.getClass().toString().equals("class model.Software")) {
      eliminar = (Software) objeto;
    }else if(objeto.getClass().toString().equals("class model.TecnicoAcademico")){
      eliminar = (TecnicoAcademico) objeto;
    }else if(objeto.getClass().toString().equals("class model.Software")){
      eliminar = (Responsable) objeto;
    }
  }

  public void vieneDe(String origen) {
    this.origen = origen;
  }

  public void mensaje(String mensaje) {
    Alert dialogo = new Alert(Alert.AlertType.INFORMATION);
    dialogo.setTitle("Aviso");
    dialogo.setHeaderText(null);
    dialogo.setContentText(mensaje);
    dialogo.initStyle(StageStyle.UTILITY);
    dialogo.showAndWait();
  }

}
