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
  
  private String password;

  private String telefono;
  
  private String extension;
  
  private String correoInstitucional;

  public Responsable(String nombre, String password, String telefono, String extension, String correoInstitucional) {
    this.nombre = nombre;
    this.password = password;
    this.telefono = telefono;
    this.extension = extension;
    this.correoInstitucional = correoInstitucional;
  }

  public String getNombre() {
    return nombre;
  }

  public String getPassword() {
    return password;
  }

  public String getTelefono() {
    return telefono;
  }

  public String getExtension() {
    return extension;
  }

  public String getCorreoInstitucional() {
    return correoInstitucional;
  }
  
  
  
}
