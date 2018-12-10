package model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Pruebas de unidad de la clase Responsable
 * 
 * @author NadiaItzel
 */
public class ResponsableTest {
    
    public ResponsableTest() {
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
     * Test de getNombre, de la clase Responsable.
     */
    @Test
    public void testGetNombre() {
      System.out.println("Probando getNombre");
      Responsable instance = new Responsable("1234", "Carlos Carrillo", "22345676", "+52", "cc@gmail.com");
      String expResult =  "Carlos Carrillo";
      String result = instance.getNombre();
      assertEquals(expResult, result);
    }

    /**
     * Test de getNumPersonal, de la clase Responsable.
     */
    @Test
    public void testGetNumPersonal() {
      System.out.println("Probando getNumPersonal");
      Responsable instance = new Responsable("1234", "Carlos Carrillo", "22345676", "+52", "cc@gmail.com");
      String expResult = "1234";
      String result = instance.getNumPersonal();
      assertEquals(expResult, result);
    }

    /**
     * Test de getTelefono, de la clase Responsable.
     */
    @Test
    public void testGetTelefono() {
      System.out.println("Probando getTelefono");
      Responsable instance = new Responsable("Carlos Carrillo", "1234", "22345676", "+52", "cc@gmail.com");
      String expResult = "22345676";
      String result = instance.getTelefono();
      assertEquals(expResult, result);
    }

    /**
     * Test de getExtension, de la clase Responsable.
     */
    @Test
    public void testGetExtension() {
      System.out.println("Probando getExtension");
      Responsable instance = new Responsable("Carlos Carrillo", "1234", "22345676", "+52", "cc@gmail.com");
      String expResult = "+52";
      String result = instance.getExtension();
      assertEquals(expResult, result);
    }

    /**
     * Test de getCorreo, de la clase Responsable.
     */
    @Test
    public void testGetCorreo() {
      System.out.println("Probando getCorreo");
      Responsable instance = new Responsable("Carlos Carrillo", "1234", "22345676", "+52", "cc@gmail.com");
      String expResult = "cc@gmail.com";
      String result = instance.getCorreo();
      assertEquals(expResult, result);
    }
    
}
