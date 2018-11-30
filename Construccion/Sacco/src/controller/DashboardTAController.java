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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author texch
 */
public class DashboardTAController implements Initializable {

    @FXML
    private ImageView btnExit;
    @FXML
    private Label lblTa;
    @FXML
    private Button btnMantenimiento;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button btnReportes;
    @FXML
    private Button btnSalir;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    

    private void clickOnMantenimiento(MouseEvent event) {
      FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/view/Frame_Software.fxml"));
    try {
      loader.load();
    } catch (IOException ex) {
      Logger.getLogger(LoginPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
    }
    //LoginJefeCCController display = loader.getController();//Utilizar a menos que se haga una accion dentro de la clase que viene
    AnchorPane mantenimiento = loader.getRoot();
    Scene newScene = new Scene(mantenimiento);
    Stage curStage = (Stage) anchorPane.getScene().getWindow();
    curStage.setScene(newScene);
    curStage.setTitle("LogIn Jefe de Centro de Cómputo");
    curStage.show();
    }

    @FXML
    private void registrarMantenimiento(MouseEvent event) {
      FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/view/Frame_Dictamen_Responsable.fxml"));
    try {
      loader.load();
    } catch (IOException ex) {
      Logger.getLogger(LoginPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
    }
    //LoginJefeCCController display = loader.getController();//Utilizar a menos que se haga una accion dentro de la clase que viene
    AnchorPane dict = loader.getRoot();
    Frame_Dictamen_ResponsableController dictamen = loader.getController();
    dictamen.cargarUsuario(lblTa.getText());
    Scene newScene = new Scene(dict);
    Stage curStage = (Stage) anchorPane.getScene().getWindow();
    curStage.setScene(newScene);
    curStage.setTitle("Dictamen de Mantenimiento");
    curStage.show();
    }

    @FXML
    private void salirDashboard(MouseEvent event) {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/view/LoginPrincipal.fxml"));
    try {
      loader.load();
    } catch (IOException ex) {
      Logger.getLogger(LoginPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
    }
    //LoginJefeCCController display = loader.getController();//Utilizar a menos que se haga una accion dentro de la clase que viene
    AnchorPane logIn = loader.getRoot();
    Scene newScene = new Scene(logIn);
    Stage curStage = (Stage) anchorPane.getScene().getWindow();
    curStage.setScene(newScene);
    curStage.setTitle("LogIn");
    curStage.show();
    }

    void cargarUsuario(String text) {
      lblTa.setText(text);
    }
    
}
