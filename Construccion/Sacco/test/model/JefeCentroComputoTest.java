package model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Pruebas de unidad de la clase del JefeCentroComputo
 * 
 * @author NadiaItzel
 */
public class JefeCentroComputoTest {
    
    public JefeCentroComputoTest() {
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
     * Test de getNumPersonal, de la clase JefeCentroComputo.
     */
    @Test
    public void testGetNumPersonal() {
        System.out.println("Probando getNumPersonal");
        JefeCentroComputo instance = new JefeCentroComputo("1234", "Nadia", "123");
        String expResult = "1234";
        String result = instance.getNumPersonal();
        assertEquals(expResult, result);
    }

    /**
     * Test de getNombre, de la clase JefeCentroComputo.
     */
    @Test
    public void testGetNombre() {
        System.out.println("Probando getNombre");
        JefeCentroComputo instance = new JefeCentroComputo("1234", "Nadia", "123");
        String expResult = "Nadia";
        String result = instance.getNombre();
        assertEquals(expResult, result);
    }

    /**
     * Test de getPassword, de la clase JefeCentroComputo.
     */
    @Test
    public void testGetPassword() {
        System.out.println("Probando getPassword");
        JefeCentroComputo instance = new JefeCentroComputo("1234", "Nadia", "123");
        String expResult = "123";
        String result = instance.getPassword();
        assertEquals(expResult, result);
    }
    
}
