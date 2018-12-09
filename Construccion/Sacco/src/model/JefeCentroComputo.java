/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author texch
 */
public class JefeCentroComputo {

  private String numPersonal;

  private String nombre;

  private String password;

  public JefeCentroComputo(String text, String text0) {
    this.numPersonal = text;
    this.password = text0;
  }

  public JefeCentroComputo(String numPersonal, String nombre, String password) {
    this.numPersonal = numPersonal;
    this.nombre = nombre;
    this.password = password;
  }

  public JefeCentroComputo() {
  }

/**
 * 
 * @return NumPersonal actual
 */
  public String getNumPersonal() {
    return numPersonal;
  }

/**
 * 
 * @return Nombre actual 
 */
  public String getNombre() {
    return nombre;
  }

/**
 * 
 * @return Password actual
 */
  public String getPassword() {
    return password;
  }

}
