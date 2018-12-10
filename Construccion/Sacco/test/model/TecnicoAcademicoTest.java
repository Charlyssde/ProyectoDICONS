package model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Pruebas de unidad de la clase TecnicoAcademico
 * 
 * @author NadiaItzel
 */
public class TecnicoAcademicoTest {
    
    public TecnicoAcademicoTest() {
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
     * Test de getNumPersonal, de la clase TecnicoAcademico.
     */
    @Test
    public void testGetNumPersonal() {
      System.out.println("Probando getNumPersonal");
      TecnicoAcademico instance = new TecnicoAcademico("1234", "Ricardo Martinez", "123", "223344554", "+52", "mr@gmail.com");
      String expResult = "1234";
      String result = instance.getNumPersonal();
      assertEquals(expResult, result);
    }

    /**
     * Test de getNombre, de la clase TecnicoAcademico.
     */
    @Test
    public void testGetNombre() {
      System.out.println("Probando getNombre");
      TecnicoAcademico instance = new TecnicoAcademico("1234", "Ricardo Martinez", "123", "223344554", "+52", "mr@gmail.com");
      String expResult = "Ricardo Martinez";
      String result = instance.getNombre();
      assertEquals(expResult, result);
    }

    /**
     * Test de getPassword, de la clase TecnicoAcademico.
     */
    @Test
    public void testGetPassword() {
      System.out.println("Probando getPassword");
      TecnicoAcademico instance = new TecnicoAcademico("1234", "Ricardo Martinez", "123", "223344554", "+52", "mr@gmail.com");
      String expResult = "123";
      String result = instance.getPassword();
      assertEquals(expResult, result);
    }

    /**
     * Test de getTelefono, de la clase TecnicoAcademico.
     */
    @Test
    public void testGetTelefono() {
      System.out.println("Probando getTelefono");
      TecnicoAcademico instance = new TecnicoAcademico("1234", "Ricardo Martinez", "123", "223344554", "+52", "mr@gmail.com");
      String expResult = "223344554";
      String result = instance.getTelefono();
      assertEquals(expResult, result);

    }

    /**
     * Test de getExtension, de la clase TecnicoAcademico.
     */
    @Test
    public void testGetExtension() {
      System.out.println("Probando getExtension");
      TecnicoAcademico instance = new TecnicoAcademico("1234", "Ricardo Martinez", "123", "223344554", "+52", "mr@gmail.com");
      String expResult = "+52";
      String result = instance.getExtension();
      assertEquals(expResult, result);
    }

    /**
     * Test de getCorreoInstitucional, de la clase TecnicoAcademico.
     */
    @Test
    public void testGetCorreoInstitucional() {
      System.out.println("Probando getCorreoInstitucional");
      TecnicoAcademico instance = new TecnicoAcademico("1234", "Ricardo Martinez", "123", "223344554", "+52", "mr@gmail.com");
      String expResult = "mr@gmail.com";
      String result = instance.getCorreoInstitucional();
      assertEquals(expResult, result);
    }
    
}
