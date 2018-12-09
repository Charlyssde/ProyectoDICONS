/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author texch
 */
public class Software {

  private Integer numInventario;

  private String nombre;

  private Integer numLicencias;

  private String observaciones;

  private Date fechaAdquisicion;

  private String version;

  public Software(Integer numInventario, String nombre, int numLicencias,
      Date fechaAdquisicion, String version, String observaciones) {
    this.numInventario = numInventario;
    this.nombre = nombre;
    this.numLicencias = numLicencias;
    this.observaciones = observaciones;
    this.fechaAdquisicion = fechaAdquisicion;
    this.version = version;
  }

  public Software() {
  }

/**
 * 
 * @return NumInventario actual
 */
  public Integer getNumInventario() {
    return numInventario;
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
  * @return NumLicencias actuales
  */
  public Integer getNumLicencias() {
    return numLicencias;
  }

/**
 * 
 * @return Observaciones actuales
 */
  public String getObservaciones() {
    return observaciones;
  }

/**
 * 
 * @return FechaAdquisicion actual
 */
  public Date getFechaAdquisicion() {
    return fechaAdquisicion;
  }

/**
 * 
 * @return Version actual
 */
  public String getVersion() {
    return version;
  }

}
