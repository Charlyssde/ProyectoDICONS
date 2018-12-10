package model;

import java.sql.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Pruebas de unidad de la clase Software
 * 
 * @author NadiaItzel
 */
public class SoftwareTest {
    
    public SoftwareTest() {
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
     * Test de getNumInventario, de la clase Software.
     */
    @Test
    public void testGetNumInventario() {
      System.out.println("Probando getNumInventario");
      Date fecha = new Date(12,11,2018);
      Software instance = new Software(123, "Lenovo", 34, fecha, "Uso academico", "1.0");
      Integer expResult = 123;
      Integer result = instance.getNumInventario();
      assertEquals(expResult, result);
    }

    /**
     * Test de getNombre, de la clase Software.
     */
    @Test
    public void testGetNombre() {
        System.out.println("Probando getNombre");
        Date fecha = new Date(12,11,2018);
        Software instance = new Software(123, "Lenovo", 34, fecha, "Uso academico", "1.0");
        String expResult = "Lenovo";
        String result = instance.getNombre();
        assertEquals(expResult, result);
    }

    /**
     * Test de getNumLicencias, de la clase Software.
     */
    @Test
    public void testGetNumLicencias() {
        System.out.println("Probando getNumLicencias");
        Date fecha = new Date(12,11,2018);
        Software instance = new Software(123, "Lenovo", 34, fecha, "Uso academico", "1.0");
        Integer expResult = 34;
        Integer result = instance.getNumLicencias();
        assertEquals(expResult, result);
    }

    /**
     * Test de getObservaciones, de la clase Software.
     */
    @Test
    public void testGetObservaciones() {
        System.out.println("Probando getObservaciones");
        Date fecha = new Date(12,11,2018);
        Software instance = new Software(123, "Lenovo", 34, fecha, "1.0","Uso academico");
        String expResult = "Uso academico";
        String result = instance.getObservaciones();
        assertEquals(expResult, result);
    }

    /**
     * Test de getFechaAdquisicion, de la clase Software.
     */
    @Test
    public void testGetFechaAdquisicion() {
      System.out.println("Probando getFechaAdquisicion");
      Date fecha = new Date(12,11,2018);
      Software instance = new Software(123, "Lenovo", 34, fecha, "Uso academico", "1.0");
      Date expResult = fecha;
      Date result = instance.getFechaAdquisicion();
      assertEquals(expResult, result);
    }

    /**
     * Test de getVersion, de la clase Software.
     */
    @Test
    public void testGetVersion() {
      System.out.println("Probando getVersion");
      Date fecha = new Date(12,11,2018);
      Software instance = new Software(123, "Lenovo", 34, fecha, "1.0", "Uso academico");
      String expResult = "1.0";
      String result = instance.getVersion();
      assertEquals(expResult, result);
    }
    
}
