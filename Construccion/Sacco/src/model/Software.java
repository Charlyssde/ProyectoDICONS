/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author texch
 */
public class Software {
  private String numInventario;
  
  private String nombre;
  
  private Integer numLicencias;
  
  private String observaciones;
  
  private Date fechaAdquisicion;
  

  public Software(String numInventario, String nombre, Integer numLicencias, String observaciones,
      Date fechaAdquisicion) {
    this.numInventario = numInventario;
    this.nombre = nombre;
    this.numLicencias = numLicencias;
    this.observaciones = observaciones;
    this.fechaAdquisicion = fechaAdquisicion;
  }

  public Software() {
  }

  public String getNumInventario() {
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
  
  
  
    
}
