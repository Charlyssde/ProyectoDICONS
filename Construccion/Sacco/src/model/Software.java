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

  public Integer getNumInventario() {
    return numInventario;
  }

  public String getNombre() {
    return nombre;
  }

  public Integer getNumLicencias() {
    return numLicencias;
  }

  public String getObservaciones() {
    return observaciones;
  }

  public Date getFechaAdquisicion() {
    return fechaAdquisicion;
  }

  public String getVersion() {
    return version;
  }

}
