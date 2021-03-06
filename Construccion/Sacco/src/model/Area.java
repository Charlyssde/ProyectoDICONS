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
public class Area{

  private Integer idUbicacion;

  private String edificio;

  private String uso;

  private String show;

  public Area() {
  }

  public Area(Integer idUbicacion, String edificio, String uso) {
    this.idUbicacion = idUbicacion;
    this.edificio = edificio;
    this.uso = uso;
    this.show = edificio + " - " + uso;
  }
  
/**
 * 
 * @return Ubicacion actual
 */
  public Integer getIdUbicacion() {
    return idUbicacion;
  }
  
/**
 * 
 * @return Edificio actual
 */
  public String getEdificio() {
    return edificio;
  }

/**
 * 
 * @return Uso actual
 */
  public String getUso() {
    return uso;
  }

  @Override
  public String toString() {
    return show;
  }
}
