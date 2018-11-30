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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author texch
 */
public class Frame_Dictamen_EquipoController implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TextField txtBuscar;
    @FXML
    private Button btnSearch;
    @FXML
    private ChoiceBox<?> chbEquipo;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnSiguiente;
    @FXML
    private Label lblDate;
    @FXML
    private Label lblTecnico;
    @FXML
    private Button btnAgregarObservaciones;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void buscarEquipo(MouseEvent event) {
    }

    @FXML
    private void cancelarDictamen(MouseEvent event) {
      Stage stage = (Stage) anchorPane.getScene().getWindow();
      stage.close();
    }

    @FXML
    private void siguienteDictamen(MouseEvent event) {
      FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/view/Frame_Dictamen_Dictamen.fxml"));
    try {
      loader.load();
    } catch (IOException ex) {
      Logger.getLogger(LoginPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
    }
    //LoginJefeCCController display = loader.getController();//Utilizar a menos que se haga una accion dentro de la clase que viene
    AnchorPane dict = loader.getRoot();
    Frame_Dictamen_DictamenController dictamen = loader.getController();
    dictamen.cargarUsuario(lblTecnico.getText());
    Scene newScene = new Scene(dict);
    Stage curStage = (Stage) anchorPane.getScene().getWindow();
    curStage.setScene(newScene);
    curStage.setTitle("Dictamen de Mantenimiento");
    curStage.show();
    }

    void cargarUsuario(String text) {
      lblTecnico.setText(text);
    }

    @FXML
    private void agregarObservaciones(MouseEvent event) {
        try {
      Parent sc = FXMLLoader.load(getClass().getResource("/view/popups/Observaciones.fxml"));
      Scene nu = new Scene(sc);
      Stage stage = new Stage();
      stage.setTitle("Agregar Observaciones");
      stage.initModality(Modality.APPLICATION_MODAL);
      stage.setResizable(false);
      stage.setScene(nu);
      stage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
    }
    
}
