package model;

	import org.junit.jupiter.api.BeforeAll;
	 import org.junit.jupiter.api.Test;

import beans.AdminAccountBean;
import beans.ClienteBean;

import java.sql.SQLException;
	import java.util.*;

	import static org.junit.jupiter.api.Assertions.*;
	public class ClientModelTest {
	  private static ClientModel classUnderTest;
	  private static ClienteBean bean;

	  @BeforeAll
	  static void setUp() throws SQLException {
	    try {
	      classUnderTest = new ClientModel("", "", "");
	    } catch(Exception e) {
	      e.printStackTrace();
	    }finally {
	      classUnderTest = new ClientModel("pizzeria","root","root");
	    }
	  }

	  @Test
	  synchronized void testDoSave() throws SQLException{
	    System.out.println("doSave");

	    //inserimento coi dati
	    bean = new ClienteBean();
	    bean.setUsername("Raffaellaa");
	    bean.setPassword("Romano");
	    bean.setCognome("gatto");
	    bean.setNome("cane");
	    bean.toString();
	    boolean ok = false;
	    try{
	      classUnderTest.doSave(bean);
	      ok = true;
	    }catch(Exception e){
	      e.printStackTrace();
	    }
	    classUnderTest.doDeleteString(bean.getUsername());
	    
	    assertTrue(ok);
	  }
	  @Test
	  synchronized void testDoDeleteString() throws SQLException{
		    System.out.println("doDeleteString");

		    //inserimento coi dati
		   bean=new ClienteBean();
		   bean.setUsername("Raffa");
		   classUnderTest.doSave(bean);
		    
		    
		    boolean ok = false;
		    try{
		      classUnderTest.doDeleteString(bean.getUsername());
		      ok = true;
		    }catch(Exception e){
		      e.printStackTrace();
		    }

		    assertTrue(ok);
		  }
	  @Test
	  synchronized void testDoRetrieveClientByName() throws SQLException {
	    bean = new ClienteBean();

	    boolean ok = false;

	   bean.setUsername("raffaella");
	   classUnderTest.doSave(bean);
	    try {
	      classUnderTest.doRetrieveClientByName(bean.getUsername());
	      ok = true;
	    } catch (Exception e) {
	  	  e.printStackTrace();
	    }
	    classUnderTest.doDeleteString(bean.getUsername());
	    assertTrue(ok);
	  }
	  @Test
	  synchronized void testDoRetrieveAllAccount() throws SQLException {
	    bean = new ClienteBean();

	    boolean ok = false;
	   bean.setUsername("raffaellaromano");
	  bean.setNome("cane");
	  bean.setCognome("gatto");

	   classUnderTest.doSave(bean);
	    try {
	      classUnderTest.doRetrieveAllClient();
	      ok = true;
	    } catch (Exception e) {
	  	  e.printStackTrace();
	    }

	    assertTrue(ok);
	    classUnderTest.doDeleteString(bean.getUsername());
	    
	  }
}
