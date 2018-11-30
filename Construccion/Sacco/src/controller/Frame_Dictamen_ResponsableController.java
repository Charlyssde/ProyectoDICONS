/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
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

/**
 * FXML Controller class
 *
 * @author texch
 */
public class Frame_Dictamen_ResponsableController implements Initializable {

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void buscarPersonal(MouseEvent event) {
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
    //LoginJefeCCController display = loader.getController();//Utilizar a menos que se haga una accion dentro de la clase que viene
    AnchorPane dict = loader.getRoot();
    Frame_Dictamen_EquipoController dictamen = loader.getController();
    dictamen.cargarUsuario(lblTecnico.getText());
    Scene newScene = new Scene(dict);
    Stage curStage = (Stage) anchorPane.getScene().getWindow();
    curStage.setScene(newScene);
    curStage.setTitle("Dictamen de Mantenimiento");
    curStage.show();
      
    }

    @FXML
    private void cancelarDictamen(MouseEvent event) {
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        stage.close();
    }

    void cargarUsuario(String text) {
      lblTecnico.setText(text);
    }
    
}
