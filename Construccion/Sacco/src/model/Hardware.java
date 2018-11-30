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
  
  private String numInventario;
  
  private String marca;

  private String modelo;
  
  private String numSerie;
  
  private Area ubicacion;
  
  private String estado;
  
  private String tipo;
  
  public Hardware() {
  }

  public Hardware(String numInventario, String marca, String modelo, String numSerie, Area ubicacion, String estado) {
    this.numInventario = numInventario;
    this.marca = marca;
    this.modelo = modelo;
    this.numSerie = numSerie;
    this.ubicacion = ubicacion;
    this.estado = estado;
  }

  public Hardware(String numInventario, String marca, String modelo, String numSerie, String estado, String tipo) {
    this.numInventario = numInventario;
    this.marca = marca;
    this.modelo = modelo;
    this.numSerie = numSerie;
    this.estado = estado;
    this.tipo = tipo;
  }

  public String getNumInventario() {
    return numInventario;
  }

  public String getMarca() {
    return marca;
  }

  public String getModelo() {
    return modelo;
  }

  public String getNumSerie() {
    return numSerie;
  }

  public Area getUbicacion() {
    return ubicacion;
  }
    
  public String getEstado (){
    return estado;
  }
  
  public String getTipo(){
    return tipo;
  }
}
