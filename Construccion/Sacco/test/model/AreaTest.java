/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author texch
 */
public class AreaTest {
  private Area area;
  public AreaTest() {
  }
  
  @BeforeClass
  public static void setUpClass() {
  }
  
  @AfterClass
  public static void tearDownClass() {
  }
  
  @Before
  public void setUp() {
    area = new Area(1, "Econex", "aula");
  }
  
  @After
  public void tearDown() {
  }

  /**
   * Test of getIdUbicacion method, of class Area.
   */
  @Test
  public void testGetIdUbicacion() {
    System.out.println("getIdUbicacion");
    
    Integer expResult = 1;
    Assert.assertEquals(expResult, area.getIdUbicacion());
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getEdificio method, of class Area.
   */
  @Test
  public void testGetEdificio() {
    System.out.println("getEdificio");
   
    String expResult = "Econex";
    String result = area.getEdificio();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.







    fail("The test case is a prototype.");
  }

  /**
   * Test of getUso method, of class Area.
   */
  @Test
  public void testGetUso() {
    System.out.println("getUso");
   
    String expResult = "aula";
    String result = area.getUso();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of toString method, of class Area.
   */
  @Test
  public void testToString() {
    System.out.println("toString");
    
    String expResult = "Econex - aula";
    String result = area.toString();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }
  
}
