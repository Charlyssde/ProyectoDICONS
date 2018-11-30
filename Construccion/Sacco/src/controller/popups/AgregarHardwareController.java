/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.popups;

import controller.Frame_HardwareController;
import java.net.URL;
import java.sql.SQLException;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.DAO.HardwareDAO;
import model.Hardware;

/**
 * FXML Controller class
 *
 * @author texch
 */
public class AgregarHardwareController implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnCancelar;
    @FXML
    private TextField txtMarca;
    @FXML
    private ChoiceBox<String> chbEdificio;
    @FXML
    private ComboBox<String> chbUso;
    @FXML
    private TextField txtNumSerie;
    @FXML
    private TextField txtModelo;
    @FXML
    private ChoiceBox<String> chbTipo;
    
    Frame_HardwareController fhc;
    
    Hardware hardware;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      chbTipo.getItems().addAll("Equipo de Computación", "Equipo de Comunicación","Periférico");
    }    


    @FXML
    private void cancelarRegistro(MouseEvent event) {
      Stage stage = (Stage) anchorPane.getScene().getWindow(); 
      stage.close(); 
    }

    @FXML
    private void guardarHardware(MouseEvent event) throws SQLException {
      hardware = new Hardware(null, txtMarca.getText(), txtModelo.getText(), 
          txtNumSerie.getText(), "Dispo",chbTipo.getValue());
      guardarEnBd(hardware);
      Stage stage = (Stage) anchorPane.getScene().getWindow();
      stage.close();
      mensajeExito("Hardware guardado con exito en la base de datos");
      
    }

  private void guardarEnBd(Hardware hardware) throws SQLException {
    HardwareDAO.agregarHardware(hardware);
  }
  
  public void mensajeExito(String mensaje){
        Alert dialogo = new Alert(Alert.AlertType.INFORMATION);
        dialogo.setTitle("Aviso");
        dialogo.setHeaderText(null);
        dialogo.setContentText(mensaje);
        dialogo.initStyle(StageStyle.UTILITY);
        dialogo.showAndWait();    
  }
    
}
