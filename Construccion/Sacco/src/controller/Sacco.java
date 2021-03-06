/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Clase encargada de lanzar la aplicacion
 *
 * @author texch
 * @since 2018/05/11
 */
public class Sacco extends Application {

  @Override
  public void start(Stage stage) {
    try {
      Parent sc = FXMLLoader.load(getClass().getResource("/view/LoginPrincipal.fxml"));
      Scene nu = new Scene(sc);
      stage.setTitle("LogIn SACCO");
      stage.setResizable(false);
      stage.setScene(nu);
      stage.show();
    } catch (IOException e) {
      Logger.getLogger(LoginPrincipalController.class.getName()).log(Level.SEVERE, null, e);
    }
  }

  /**
   *  metodo main
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    launch(args);
  }

}
