package model;


import org.junit.jupiter.api.BeforeAll;
 import org.junit.jupiter.api.Test;

import beans.AdminAccountBean;

import java.sql.SQLException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


class AdmAccountModelTest{
  private static AdmAccountModel classUnderTest;
  private static AdminAccountBean bean;

  @BeforeAll
  static void setUp() throws SQLException {
    try {
      classUnderTest = new AdmAccountModel("", "", "");
    } catch(Exception e) {
      e.printStackTrace();
    }finally {
      classUnderTest = new AdmAccountModel("pizzeria","root","root");
    }
  }

  @Test
  synchronized void testDoSave() throws SQLException{
    System.out.println("doSave");

    //inserimento coi dati
    bean = new AdminAccountBean();
    bean.setUsername("Rafffaelllla");
    bean.setPassword("Romanho");
    bean.toString();

    boolean ok = false;
    try{
      classUnderTest.doSave(bean);
      ok = true;
    }catch(Exception e){
      e.printStackTrace();
    }
    classUnderTest.doDeleteString(bean.getUsername());
    classUnderTest.doDeleteString(bean.getPassword());
    assertTrue(ok);
  }
  
@Test

synchronized void testDoDeleteString() throws SQLException{
    System.out.println("doDeleteString");

    //inserimento coi dati
    bean = new AdminAccountBean();
    bean.setUsername("raffa");
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
synchronized void testDoRetrieveAccountByName() throws SQLException {
  bean = new AdminAccountBean();

  boolean ok = false;

 bean.setUsername("raffaellaa");
 classUnderTest.doSave(bean);
  try {
    classUnderTest.doRetrieveAccountByName(bean.getUsername());
    ok = true;
  } catch (Exception e) {
	  e.printStackTrace();
  }

  assertTrue(ok);
  classUnderTest.doDeleteString(bean.getUsername());
}
@Test
synchronized void testDoRetrieveAllAccount() throws SQLException {
  bean = new AdminAccountBean();

  boolean ok = false;
 bean.setUsername("raffaellarom");
 bean.setPassword("cane");

 classUnderTest.doSave(bean);
  try {
    classUnderTest.doRetrieveAllAccount("username");
    ok = true;
  } catch (Exception e) {
	  e.printStackTrace();
  }

  assertTrue(ok);
  classUnderTest.doDeleteString(bean.getUsername());
}

}