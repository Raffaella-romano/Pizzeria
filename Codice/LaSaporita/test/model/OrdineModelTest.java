package model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import beans.ClienteBean;
import beans.OrdineBean;
import beans.ProdottoBean;

import java.sql.SQLException;
import java.util.*;

	import static org.junit.jupiter.api.Assertions.*;
	public class OrdineModelTest {
	  private static OrdineModel classUnderTest;
	  private static OrdineBean bean;
	  private static ProdottoBean bean2;
	  private static ProductModel classUnderTest2;

	  @BeforeAll
	  static void setUp() throws SQLException {
	    try {
	      classUnderTest = new OrdineModel("", "", "");
	      classUnderTest2 = new ProductModel("", "", "");
	    } catch(Exception e) {
	      e.printStackTrace();
	    }finally {
	      classUnderTest = new OrdineModel("pizzeria","root","root");
	      classUnderTest2 = new ProductModel("pizzeria", "root", "root");
	    }
	  }
	  
	  @Test
	  synchronized void testDoSave() throws SQLException{
	    System.out.println("doSave");

	    //inserimento coi dati
	  bean=new OrdineBean();
	  bean2= new ProdottoBean();
	 
	    bean2.setCodice(2);
	    bean2.setNome("margheritaa");
	    bean2.setTipo("pizzaa");
	    bean2.setPrezzo(0);
	    bean2.setComponenti("ingredientii");
	    classUnderTest2.doSave(bean2);
	 bean.setCodice(1);
	 bean.setUsernameCliente("raffaelemarrazzo");
	 bean.addProdotto(bean2);
	
	 
	 
	    boolean ok = false;
	    try{
	      classUnderTest.doSave(bean);
	      ok = true;
	    }catch(Exception e){
	      e.printStackTrace();
	    }
	   
	    classUnderTest.doDeleteInt(bean.getCodice());
	    classUnderTest2.doDeleteInt(bean2.getCodice());
	    assertTrue(ok);
	  }
	  @Test
	  synchronized void testDoDeleteInt() throws SQLException{
		    System.out.println("doDeleteInt");

		    //inserimento coi dati
		    bean = new OrdineBean();
			  bean2= new ProdottoBean();
			 
			    bean2.setCodice(2);
			    bean2.setNome("margheritaa");
			    bean2.setTipo("pizzaa");
			    bean2.setPrezzo(0);
			    bean2.setComponenti("ingredientii");
			    classUnderTest2.doSave(bean2);
			    
			 bean.setCodice(1);
			 bean.setUsernameCliente("raffaelemarrazzo");
			 bean.addProdotto(bean2);	 
			 classUnderTest.doSave(bean);
		    boolean ok = false;
		    try{
		      classUnderTest.doDeleteInt(bean.getCodice());
		      classUnderTest2.doDeleteInt(bean2.getCodice());
		      ok = true;
		    }catch(Exception e){
		      e.printStackTrace();
		    }

		    assertTrue(ok);
		  }
	  @Test
	  synchronized void assegnaCodice() throws SQLException{
		    System.out.println("AssegnaCodice");

		    //inserimento coi dati
		    bean = new OrdineBean();
			  bean2= new ProdottoBean();
			 
			    bean2.setCodice(2);
			    bean2.setNome("margheritaa");
			    bean2.setTipo("pizzaa");
			    bean2.setPrezzo(0);
			    bean2.setComponenti("ingredientii");
			    classUnderTest2.doSave(bean2);
			    
			 bean.setCodice(1);
			 bean.setUsernameCliente("raffaelemarrazzo");
			 bean.addProdotto(bean2);	 
			 classUnderTest.doSave(bean);
		
		    boolean ok = false;
		    try{
		      classUnderTest.assegnaCodice();
		      ok = true;
		    }catch(Exception e){
		      e.printStackTrace();
		    }
		    classUnderTest.doDeleteInt(bean.getCodice());
		      classUnderTest2.doDeleteInt(bean2.getCodice());
		    assertTrue(ok);
		  }	  
	}