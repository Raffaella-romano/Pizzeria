package model;

	import org.junit.jupiter.api.BeforeAll;
	 import org.junit.jupiter.api.Test;

import beans.AdminAccountBean;
import beans.ProdottoBean;

import java.sql.SQLException;
	import java.util.*;

	import static org.junit.jupiter.api.Assertions.*;
	public class ProductModelTest {
	  private static ProductModel classUnderTest;
	  private static ProdottoBean bean;

	  @BeforeAll
	  static void setUp() throws SQLException {
	    try {
	      classUnderTest = new ProductModel("", "", "");
	    } catch(Exception e) {
	      e.printStackTrace();
	    }finally {
	      classUnderTest = new ProductModel("pizzeria","root","root");
	    }
	  }

	  @Test
	  synchronized void testDoAddProduct() throws SQLException{
	    System.out.println("doAdd");

	    //inserimento coi dati
	    bean=new ProdottoBean();
	    bean.setCodice(2);
	    bean.setNome("margheritaa");
	    bean.setTipo("pizzaa");
	    bean.setPrezzo(0);
	    bean.setComponenti("ingredientii");
	    bean.toString();
	    
	    
	    
	    boolean ok = false;
	    try{
	      classUnderTest.doAddProduct(bean);
	      ok = true;
	    }catch(Exception e){
	      e.printStackTrace();
	    }
	    classUnderTest.doDeleteInt(bean.getCodice());
	    assertTrue(ok);
	  }
	  @Test
	  synchronized void testDoSave() throws SQLException{
	    System.out.println("doSave");

	    //inserimento coi dati
	    bean=new ProdottoBean();
	    bean.setCodice(10);
	    bean.setComponenti("ingredientii");
	    bean.setNome("margheritaa");
	    bean.setTipo("pizzaa");
	    bean.setPrezzo(3);
	    
	    boolean ok = false;
	    try{
	      classUnderTest.doSave(bean);
	      ok = true;
	    }catch(Exception e){
	      e.printStackTrace();
	    }
	    classUnderTest.doDeleteInt(bean.getCodice());
	    assertTrue(ok);
	  }
	  @Test
	  synchronized void testDoDeleteInt() throws SQLException{
		    System.out.println("doDeleteInt");

		    //inserimento coi dati
		    bean = new ProdottoBean();
		   bean.setCodice(2);
		    classUnderTest.doSave(bean);
		    
		    boolean ok = false;
		    try{
		      classUnderTest.doDeleteInt(bean.getCodice());
		      ok = true;
		    }catch(Exception e){
		      e.printStackTrace();
		    }

		    assertTrue(ok);
		  }
	  @Test
	  synchronized void testDoRetrieveProductByName() throws SQLException {
	    bean = new ProdottoBean();

	    boolean ok = false;
	    bean=new ProdottoBean();
	    bean.setCodice(10);
	    bean.setComponenti("ingredientii");
	    bean.setNome("margheritaa");
	    bean.setTipo("pizzaa");
	    bean.setPrezzo(3);
	    classUnderTest.doSave(bean);
	    try {
	      classUnderTest.doRetrieveProductByName(bean.getNome());
	      ok = true;
	    } catch (Exception e) {
	  	  e.printStackTrace();
	    }
	    classUnderTest.doDeleteInt(bean.getCodice());
	    assertTrue(ok);
	  }
	@Test
	  synchronized void testDoRetrieveProductByType() throws SQLException {
		    bean = new ProdottoBean();

		    boolean ok = false;
		    bean.setCodice(10);
		    bean.setComponenti("ingredientii");
		    bean.setNome("margheritaa");
		    bean.setTipo("pizzaa");
		    bean.setPrezzo(3);
		    classUnderTest.doSave(bean);
		    try {
		      classUnderTest.doRetrieveProductByType(bean.getTipo());
		      ok = true;
		    } catch (Exception e) {
		  	  e.printStackTrace();
		    }
		    classUnderTest.doDeleteInt(bean.getCodice());
		    assertTrue(ok);
		  }
	@Test
	  synchronized void testDoRetrieveProductByKey() throws SQLException {
	    bean = new ProdottoBean();

	    boolean ok = false;
	    bean.setCodice(10);
	    bean.setComponenti("ingredientii");
	    bean.setNome("margheritaa");
	    bean.setTipo("pizzaa");
	    bean.setPrezzo(3);
	    classUnderTest.doSave(bean);
	    try {
	      classUnderTest.doRetrieveProductByKey(bean.getCodice());
	      ok = true;
	    } catch (Exception e) {
	  	  e.printStackTrace();
	    }
	    classUnderTest.doDeleteInt(bean.getCodice());
	    assertTrue(ok);
	  }
	@Test
	  synchronized void testDoRetrieveProductBySearch() throws SQLException {
	    bean = new ProdottoBean();

	    boolean ok = false;
	    bean.setCodice(10);
	    bean.setComponenti("ingredientii");
	    bean.setNome("margheritaa");
	    bean.setTipo("pizzaa");
	    bean.setPrezzo(3);
	    classUnderTest.doSave(bean);
	    try {
	      classUnderTest.doRetrieveProductBySearch(bean.getNome());
	      ok = true;
	    } catch (Exception e) {
	  	  e.printStackTrace();
	    }
	    classUnderTest.doDeleteInt(bean.getCodice());
	    assertTrue(ok);
	  }
	@Test
	synchronized void testDoRetrieveAllProduct() throws SQLException {
	  bean = new ProdottoBean();

	  boolean ok = false;
	  	bean.setCodice(10);
	    bean.setComponenti("ingredientii");
	    bean.setNome("margheritaa");
	    bean.setTipo("pizzaa");
	    bean.setPrezzo(3);

	 classUnderTest.doSave(bean);
	  try {
	    classUnderTest.doRetrieveAllProduct();
	    ok = true;
	  } catch (Exception e) {
		  e.printStackTrace();
	  }

	  assertTrue(ok);
	  classUnderTest.doDeleteInt(bean.getCodice());
	}
}
