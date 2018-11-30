/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author texch
 */
public class Frame_Dictamen_DictamenController implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Label lblTecnico;
    @FXML
    private Label lblDate;
    @FXML
    private ChoiceBox<String> chbTipo;
    @FXML
    private TextArea txtDescripcion;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnCancelar;
    @FXML
    private TextField txtSolicitante;
    @FXML
    private TextField txtEquipo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    void cargarUsuario(String text) {
      lblTecnico.setText(text);    
    }
    void cargarResumen(String responsable, String equipo){
        txtSolicitante.setText(responsable);
        txtEquipo.setText(equipo);
    }

    @FXML
    private void siguienteDictamen(MouseEvent event) {
    }

    @FXML
    private void cancelarDictamen(MouseEvent event) {
      Stage stage = (Stage) anchorPane.getScene().getWindow();
      stage.close();
    }
    
}
