package org.anc.grid.data.masc.client.test;

import org.lappsgrid.api.*;
import org.lappsgrid.discriminator.Types;
import org.anc.grid.data.masc.client.*;

import org.junit.*;

import static org.junit.Assert.*;

import javax.xml.rpc.ServiceException;

import static org.junit.Assert.*;

/**
 * @author Keith Suderman
 */
public class MascTextSourceClientTest
{
   private static final String USER = "operator1";
   private static final String PASS = "operator1";
   private MascTextSourceClient service;

   @Before
   public void before() throws ServiceException
   {
      service = new MascTextSourceClient(USER, PASS);
   }

   @After
   public void after()
   {
      service = null;
   }

   @Test
   public void testList() throws InternalException
   {
      String[] parts = service.list();
      assertTrue(parts != null);
      assertTrue(parts.length > 0);
   }

   @Test
   public void testGet() throws InternalException
   {
      String[] index = service.list();
      for (String key : index)
      {
         Data data = service.get(key);
         assertTrue(Types.TEXT != data.getDiscriminator());
      }
   }
}
