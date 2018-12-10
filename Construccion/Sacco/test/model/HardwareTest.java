package model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Pruebas de unidad de la clase Hardware
 * 
 * @author NadiaItzel
 */
public class HardwareTest {
    
    public HardwareTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test de getNumInventario, de la clase Hardware.
     */
    @Test
    public void testGetNumInventario() {
        System.out.println("Probando getNumInventario");
        Hardware instance = new Hardware(123, "Lenovo", "A10000", "1A2B3C", "Nuevo", "No sé");
        Integer expResult = 123;
        Integer result = instance.getNumInventario();
        assertEquals(expResult, result);
    }

    /**
     * Test de getMarca, de la clase Hardware.
     */
    @Test
    public void testGetMarca() {
        System.out.println("Probando getMarca");
        Hardware instance = new Hardware(123, "Lenovo", "A10000", "1A2B3C", "Nuevo", "No sé");
        String expResult = "Lenovo";
        String result = instance.getMarca();
        assertEquals(expResult, result);
    }

    /**
     * Test de getModelo, de la clase Hardware.
     */
    @Test
    public void testGetModelo() {
        System.out.println("Probando getModelo");
        Hardware instance = new Hardware(123, "Lenovo", "A10000", "1A2B3C", "Nuevo", "No sé");
        String expResult = "A10000";
        String result = instance.getModelo();
        assertEquals(expResult, result);
    }

    /**
     * Test de getNumSerie method, de la clase Hardware.
     */
    @Test
    public void testGetNumSerie() {
      System.out.println("Probando getNumSerie");
      Hardware instance = new Hardware(123, "Lenovo", "A10000", "1A2B3C", "Nuevo", "No sé");
      String expResult = "1A2B3C";
      String result = instance.getNumSerie();
      assertEquals(expResult, result);
    } 

    /**
     * Test de getEstado, de la clase Hardware.
     */
    @Test
    public void testGetEstado() {
      System.out.println("Probando getEstado");
      Hardware instance = new Hardware(123, "Lenovo", "A10000", "1A2B3C", "Nuevo", "No sé");
      String expResult = "Nuevo";
      String result =  instance.getEstado();
      assertEquals(expResult, result);
    }

    /**
     * Test de getTipo, de la clase Hardware.
     */
    @Test
    public void testGetTipo() {
      System.out.println("Probando getTipo");
      Hardware instance = new Hardware(123, "Lenovo", "A10000", "1A2B3C", "Nuevo", "No se");
      String expResult = "No se";
      String result =  instance.getTipo();
      assertEquals(expResult, result);
    }

    /**
     * Test de setUbicacion, de la clase Hardware.
     */
    @Test
    public void testSetUbicacion() {
        System.out.println("Probando setUbicacion");
        Area area = null;
        Hardware instance = new Hardware();
        instance.setUbicacion(area);
    }

    /**
     * Test de setEstado method, de la clase Hardware.
     */
    @Test
    public void testSetEstado() {
        System.out.println("Probando setEstado");
        String estado = "";
        Hardware instance = new Hardware();
        instance.setEstado(estado);
    }
    
}
