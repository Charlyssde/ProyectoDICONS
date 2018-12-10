package model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Pruebas de unidad de la clase Dictamen
 * 
 * @author NadiaItzel
 */
public class DictamenTest {
    
    public DictamenTest() {
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
     * Test de getDescripcion, de la clase Dictamen.
     */
    @Test
    public void testGetDescripcion() {
      System.out.println("Probando getDescripcion");
      TecnicoAcademico tec = new TecnicoAcademico();
      Responsable resp = new Responsable();
      Hardware had = new Hardware();
      Dictamen instance = new Dictamen("Se rompio", "Impresora", "Se cayo", tec, resp, had);
      String expResult = "Se rompio";
      String result = instance.getDescripcion();
      assertEquals(expResult, result);
    }

    /**
     * Test de getTipo, de la clase Dictamen.
     */
    @Test
    public void testGetTipo() {
      System.out.println("Probando getTipo");
      TecnicoAcademico tec = new TecnicoAcademico();
      Responsable resp = new Responsable();
      Hardware had = new Hardware();
      Dictamen instance = new Dictamen("Se rompio", "Impresora", "Se cayo", tec, resp, had);
      String expResult = "Impresora";
      String result = instance.getTipo();
      assertEquals(expResult, result);
    }

    /**
     * Test de getObservaciones, de la clase Dictamen.
     */
    @Test
    public void testGetObservaciones() {
      System.out.println("Probando getObservaciones");
      TecnicoAcademico tec = new TecnicoAcademico();
      Responsable resp = new Responsable();
      Hardware had = new Hardware();
      Dictamen instance = new Dictamen("Se rompio", "Impresora", "Se cayo", tec, resp, had);
      String expResult = "Se cayo";
      String result = instance.getObservaciones();
      assertEquals(expResult, result);
    }

    /**
     * Test de getTecnico method, de la clase Dictamen.
     */
    @Test
    public void testGetTecnico() {
      System.out.println("Probando getTecnico");
      TecnicoAcademico tec = new TecnicoAcademico("123", "Ricardo Martinez", "123", "2288511209", "+52", "rm@gmail.com");
      Responsable resp = new Responsable();
      Hardware had = new Hardware();
      Dictamen instance = new Dictamen("Se rompio", "Impresora", "Se cayo", tec, resp, had);
      TecnicoAcademico expResult = tec;
      TecnicoAcademico result = instance.getTecnico();
      assertEquals(expResult, result);
    }

    /**
     * Test de getSolicitante, de la clase  Dictamen.
     */
    @Test
    public void testGetSolicitante() {
      System.out.println("Probando getSolicitante");
      TecnicoAcademico tec = new TecnicoAcademico();
      Responsable resp = new Responsable("Ricardo Martinez", "123", "2288511209", "+52", "mt@gmail.com");
      Hardware had = new Hardware();
      Dictamen instance = new Dictamen("Se rompio", "Impresora", "Se cayo", tec, resp, had);
      Responsable expResult = resp;
      Responsable result = instance.getSolicitante();
      assertEquals(expResult, result);
    }
    
}
