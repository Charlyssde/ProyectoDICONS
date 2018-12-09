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
public class Hardware {

  private Integer numInventario;

  private String marca;

  private String modelo;

  private String numSerie;

  private Area ubicacion;

  private String estado;

  private String tipo;

  public Hardware() {
  }

  public Hardware(Integer numInventario, String marca, String modelo, String numSerie,
      String estado, String tipo, Area ubicacion) {
    this.numInventario = numInventario;
    this.marca = marca;
    this.modelo = modelo;
    this.numSerie = numSerie;
    this.ubicacion = ubicacion;
    this.estado = estado;
    this.tipo = tipo;
  }

  public Hardware(Integer numInventario, String marca, String modelo, String numSerie,
      String estado, String tipo) {
    this.numInventario = numInventario;
    this.marca = marca;
    this.modelo = modelo;
    this.numSerie = numSerie;
    this.estado = estado;
    this.tipo = tipo;
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
 * @return Marca actual
 */
  public String getMarca() {
    return marca;
  }

/**
 * 
 * @return Modelo actual
 */
  public String getModelo() {
    return modelo;
  }

/**
 * 
 * @return NumSerie actual
 */
  public String getNumSerie() {
    return numSerie;
  }

/**
 * 
 * @return Ubicacion actual
 */
  public Area getUbicacion() {
    return ubicacion;
  }

/**
 * 
 * @return Estado actual 
 */
  public String getEstado() {
    return estado;
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
   * @param area area to set 
   */
  public void setUbicacion(Area area) {
    this.ubicacion = area;
  }

/**
 * 
 * @param estado estado to set
 */
  public void setEstado(String estado) {
    this.estado = estado;
  }
  
  
}
