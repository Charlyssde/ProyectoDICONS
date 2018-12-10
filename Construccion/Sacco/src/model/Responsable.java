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
public class Responsable {

  private String nombre;

  private String numPersonal;

  private String telefono;

  private String extension;

  private String correo;

  public Responsable(String numPersonal, String nombre, String telefono, String extension, String correo) {
    this.nombre = nombre;
    this.numPersonal = numPersonal;
    this.telefono = telefono;
    this.extension = extension;
    this.correo = correo;
  }
  
  public Responsable(){
    
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
 * @return NumPersonal actual
 */
  public String getNumPersonal() {
    return numPersonal;
  }

/**
 * 
 * @return Telefono actual
 */
  public String getTelefono() {
    return telefono;
  }

/**
 * 
 * @return Extension actual
 */
  public String getExtension() {
    return extension;
  }

/**
 * 
 * @return  Correo actual
 */
  public String getCorreo() {
    return correo;
  }

}
