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
public class Dictamen {

  private String descripcion;

  private String tipo;

  private String observaciones;

  private TecnicoAcademico tecnico;

  private Responsable solicitante;

  private Hardware equipo;

  public Dictamen(String descripcion, String tipo, String observaciones, TecnicoAcademico tecnico,
      Responsable solicitante, Hardware equipo) {
    this.descripcion = descripcion;
    this.tipo = tipo;
    this.observaciones = observaciones;
    this.tecnico = tecnico;
    this.solicitante = solicitante;
    this.equipo = equipo;
  }

/**
 * 
 * @return Descripcion acual 
 */
  public String getDescripcion() {
    return descripcion;
  }

/**
 * 
 * @return Tipo actual
 */
  public String getTipo() {
    return tipo;
  }

/**
 * 
 * @return Obervaciones actuales
 */
  public String getObservaciones() {
    return observaciones;
  }

/**
 * 
 * @return Tecnico actual
 */
  public TecnicoAcademico getTecnico() {
    return tecnico;
  }

/**
 * 
 * @return Solicitante actual
 */
  public Responsable getSolicitante() {
    return solicitante;
  }

/**
 * 
 * @return Equipo actual
 */
  public Hardware getEquipo() {
    return equipo;
  }

}
