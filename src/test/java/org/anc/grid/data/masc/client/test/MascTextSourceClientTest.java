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
   private static final String USER = "suderman";
   private static final String PASS = "lapplander";
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
      int limit = 1;
      for (int i = 0; i < limit; ++i)
      {
         String key = index[i];
         System.out.println("Fetching " + key);
         Data data = service.get(key);
         assertTrue(Types.ERROR != data.getDiscriminator());
         System.out.println(data.getPayload());
      }
   }
}
