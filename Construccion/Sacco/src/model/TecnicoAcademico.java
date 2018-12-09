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
public class TecnicoAcademico {

  private String numPersonal;

  private String nombre;

  private String password;

  private String telefono;

  private String extension;

  private String correoInstitucional;

  public TecnicoAcademico(String numPersonal, String nombre, String password, String telefono, String extension, String correoInstitucional) {
    this.numPersonal = numPersonal;
    this.nombre = nombre;
    this.password = password;
    this.telefono = telefono;
    this.extension = extension;
    this.correoInstitucional = correoInstitucional;
  }

  public TecnicoAcademico(String numPersonal, String password) {
    this.numPersonal = numPersonal;
    this.password = password;
  }

  public TecnicoAcademico() {

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
 * @return CorreoInstitucional actual
 */
  public String getCorreoInstitucional() {
    return correoInstitucional;
  }

}
